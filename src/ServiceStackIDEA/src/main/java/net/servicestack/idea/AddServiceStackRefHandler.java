package net.servicestack.idea;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiPackage;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.maven.project.MavenProjectsManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddServiceStackRefHandler {

    private static final String dependencyGroupId = "net.servicestack";
    private static final String dependencyPackageId = "android";
    //Fallback version of dependencies if GitHub tags can't be checked.
    private static String dependencyVersion = "1.0.15";
    private static final String clientPackageId = "client";

    public static void setDependencyVersion(String version) {
        dependencyVersion = version;
    }

    public static void handleOk(String addressUrl, String qualifiedPackageName, String fileName, String selectedDirectory, Module module, StringBuilder errorMessage) {
        List<String> javaCodeLines = getDtoLines(addressUrl, qualifiedPackageName, fileName, errorMessage);

        if (javaCodeLines == null) return;

        boolean showDto = true;
        final MavenProjectsManager mavenProjectsManager = MavenProjectsManager.getInstance(module.getProject());

        boolean isMavenModule = mavenProjectsManager != null && mavenProjectsManager.isMavenizedModule(module);
        if(isMavenModule) {
            showDto = tryAddMavenDependency(module);
        } else {
            //Gradle
            try {
                showDto = addGradleDependencyIfRequired(module);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                String message = "Failed to update build.gradle with '" +
                        dependencyGroupId + ":" + clientPackageId + ":" + dependencyVersion +
                        "'. " + e.getLocalizedMessage();
                Notification notification = new Notification(
                        "ServiceStackIDEA",
                        "Warning Add ServiceStack Reference",
                        message,
                        NotificationType.WARNING);
                Notifications.Bus.notify(notification);
            }
        }

        String dtoPath;
        try {
            dtoPath = getDtoPath(module,qualifiedPackageName,selectedDirectory, fileName,errorMessage);
        } catch (Exception e) {
            return;
        }

        if (!writeDtoFile(javaCodeLines, dtoPath, errorMessage)) {
            return;
        }
        refreshFile(module, dtoPath, showDto);
        VirtualFileManager.getInstance().syncRefresh();
    }

    @Nullable
    private static List<String> getDtoLines(String addressUrl, String qualifiedPackageName, String fileName, StringBuilder errorMessage) {
        Map<String,String> options = new HashMap<String,String>();
        List<String> javaCodeLines;
        try {
            options.put("Package", qualifiedPackageName);
            String name = getDtoNameWithoutExtension(fileName).replaceAll("\\.", "_");
            options.put("GlobalNamespace", name);
            javaCodeLines = getNativeTypesHandler(fileName).getUpdatedCode(addressUrl,options);

            if(!javaCodeLines.get(0).startsWith("/* Options:")) {
                //Invalid endpoint
                errorMessage.append("The address url is not a valid ServiceStack endpoint.");
                return null;
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
            errorMessage.append(e.getClass().getName()).append(" - Invalid ServiceStack endpoint provided - ").append(addressUrl);
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errorMessage.append(e.getClass().getName()).append(" - Invalid ServiceStack endpoint provided - ").append(addressUrl);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.append(e.getClass().getName()).append(" - Failed to read response - ").append(addressUrl);
            return null;
        }
        return javaCodeLines;
    }

    private static INativeTypesHandler getNativeTypesHandler(String fileName) {
        //Default Java
        INativeTypesHandler result = new JavaNativeTypesHandler();
        if(fileName.endsWith(".kt")) return new KotlinNativeTypesHandler();
        return result;
    }

    private static boolean tryAddMavenDependency(Module module) {
        boolean showDto;
        String message = "Unable to locate module pom.xml file. Can't add required dependency '" +
                dependencyGroupId + ":" + clientPackageId + ":" + dependencyVersion +
                "'.";
        Notification notification = new Notification(
                "ServiceStackIDEA",
                "Warning Add ServiceStack Reference",
                message,
                NotificationType.WARNING);
        try {
            IPomFileHelper pomFileHelper = new IDEAPomFileHelper();
            String pomFilePath = pomFileHelper.findNearestModulePomFile(module);
            if(pomFilePath == null) {
                Notifications.Bus.notify(notification);
                return false;
            }
            File pomLibFile = new File(pomFilePath);
            showDto = pomFileHelper.addMavenDependencyIfRequired(pomLibFile, dependencyGroupId, clientPackageId, dependencyVersion);
        } catch(Exception e) {
            showDto = false;
            e.printStackTrace();
            notification = new Notification(
                    "ServiceStackIDEA",
                    "Warning Add ServiceStack Reference",
                    "Unable to add maven dependency. " + e.getLocalizedMessage(),
                    NotificationType.WARNING);
            Notifications.Bus.notify(notification);
        }
        return showDto;
    }

    private static boolean addGradleDependencyIfRequired(Module module) throws FileNotFoundException {
        boolean result = true;
        if(GradleBuildFileHelper.addDependency(module,dependencyGroupId, dependencyPackageId, dependencyVersion)) {
            result = false;
            refreshBuildFile(module);
        }
        return result;
    }

    private static boolean writeDtoFile(List<String> javaCode, String path, StringBuilder errorMessage) {
        BufferedWriter writer = null;
        boolean result = true;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path), "utf-8"));
            for (String item : javaCode) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException ex) {
            result = false;
            errorMessage.append("Error writing DTOs to file - ").append(ex.getMessage());
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    private static String getDtoPath(Module module, String qualifiedPackageName, String selectedDirectory, String fileName, StringBuilder errorMessage) throws FileNotFoundException {
        VirtualFile moduleFile = module.getModuleFile();
        if(moduleFile == null) {
            throw new FileNotFoundException("Module file not found. Unable to add DTO to project.");
        }
        String fullDtoPath;

        PsiPackage mainPackage = JavaPsiFacade.getInstance(module.getProject()).findPackage(qualifiedPackageName);
        if(mainPackage != null && mainPackage.isValid() && mainPackage.getDirectories().length > 0) {
            File file = new File(selectedDirectory);
            VirtualFile selectedFolder = LocalFileSystem.getInstance().findFileByIoFile(file);
            if(selectedFolder == null) {
                errorMessage.append("Unable to determine path for DTO file.");
                throw new FileNotFoundException();
            }
            PsiDirectory rootPackageDir = PsiManager.getInstance(module.getProject()).findDirectory(selectedFolder);
            if(rootPackageDir == null) {
                errorMessage.append("Unable to determine path for DTO file.");
                throw new FileNotFoundException();
            }
            fullDtoPath = rootPackageDir.getVirtualFile().getPath() + "/" + getDtoFileName(fileName);
        } else {
            String moduleSourcePath;
            if(moduleFile.getParent() == null) {
                moduleSourcePath = moduleFile.getPath() + "/main/java";
            } else {
                moduleSourcePath = moduleFile.getParent().getPath() + "/src/main/java";
            }
            fullDtoPath = moduleSourcePath + "/" + getDtoFileName(fileName);
        }
        return fullDtoPath;
    }

    private static void refreshBuildFile(Module module) {
        VirtualFileManager.getInstance().syncRefresh();
        if(module.getModuleFile() == null) { return; }

        VirtualFile fileByUrl = VirtualFileManager.getInstance().findFileByUrl(module.getModuleFile().getParent().getUrl() + "/build.gradle");

        if(fileByUrl == null) { return; }

        FileEditorManager.getInstance(module.getProject()).openFile(fileByUrl, false);
        Editor currentEditor = FileEditorManager.getInstance(module.getProject()).getSelectedTextEditor();
        if(currentEditor == null) { return; }
        Document document = currentEditor.getDocument();

        FileDocumentManager.getInstance().reloadFromDisk(document);
        VirtualFileManager.getInstance().syncRefresh();
    }

    private static void refreshFile(Module module, String filePath, boolean openFile) {
        VirtualFileManager.getInstance().syncRefresh();
        File file = new File(filePath);
        VirtualFile fileByUrl = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);

        if (fileByUrl == null) {
            return;
        }

        FileEditorManager.getInstance(module.getProject()).openFile(fileByUrl, false);
        Editor currentEditor = FileEditorManager.getInstance(module.getProject()).getSelectedTextEditor();
        if(currentEditor == null) { return; }
        Document document = currentEditor.getDocument();

        if (!openFile) FileEditorManager.getInstance(module.getProject()).closeFile(fileByUrl);

        FileDocumentManager.getInstance().reloadFromDisk(document);
        VirtualFileManager.getInstance().syncRefresh();
    }

    public static String getDtoFileName(String name) {
        INativeTypesHandler nativeTypesHandler = getNativeTypesHandler(name);
        int p = name.lastIndexOf(".");
        String e = name.substring(p);
        if (p == -1 || !e.equals(nativeTypesHandler.getFileExtension())) {
            /* file has no extension */
            return name + nativeTypesHandler.getFileExtension();
        } else {
            /* file has extension e */
            return name;
        }
    }

    public static String getDtoNameWithoutExtension(String name) {
        INativeTypesHandler nativeTypesHandler = getNativeTypesHandler(name);
        int p = name.lastIndexOf(".");
        String e = name.substring(p);
        if (p == -1 || !e.equals(nativeTypesHandler.getFileExtension())) {
            /* file has no extension */
            return name;
        } else {
            /* file has extension e */
            return name.substring(0, p);
        }
    }
}
