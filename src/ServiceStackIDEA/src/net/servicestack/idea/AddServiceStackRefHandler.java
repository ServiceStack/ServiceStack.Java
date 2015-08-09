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
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.idea.maven.project.MavenProjectsManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Layoric on 14/05/2015.
 */
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
        String url;
        List<String> javaCodeLines = new ArrayList<String>();
        try {
            URIBuilder urlBuilder = createUrl(addressUrl);
            urlBuilder.addParameter("Package", qualifiedPackageName);
            String name = getDtoNameWithoutExtension(fileName).replaceAll("\\.", "_");
            urlBuilder.addParameter("GlobalNamespace", name);
            url = urlBuilder.build().toString();

            URL serviceUrl = new URL(url);
            URLConnection javaResponseConnection = serviceUrl.openConnection();
            BufferedReader javaResponseReader = new BufferedReader(
                    new InputStreamReader(
                            javaResponseConnection.getInputStream()));
            String metadataInputLine;

            while ((metadataInputLine = javaResponseReader.readLine()) != null)
                javaCodeLines.add(metadataInputLine);

            javaResponseReader.close();

            if(!javaCodeLines.get(0).startsWith("/* Options:")) {
                //Invalid endpoint
                errorMessage.append("The address url is not a valid ServiceStack endpoint.");
                return;
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
            errorMessage.append(e.getClass().getName()).append(" - Invalid ServiceStack endpoint provided - ").append(addressUrl);
            return;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errorMessage.append(e.getClass().getName()).append(" - Invalid ServiceStack endpoint provided - ").append(addressUrl);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.append(e.getClass().getName()).append(" - Failed to read response - ").append(addressUrl);
            return;
        }


        GradleBuildFileHelper gradleBuildFileHelper = new GradleBuildFileHelper(module);
        boolean showDto = true;
        final MavenProjectsManager mavenProjectsManager = MavenProjectsManager.getInstance(module.getProject());

        boolean isMavenModule = mavenProjectsManager != null && mavenProjectsManager.isMavenizedModule(module);
        if(isMavenModule) {
            showDto = tryAddMavenDependency(module);
        } else {
            //Gradle
            try {
                showDto = addGradleDependencyIfRequired(module,gradleBuildFileHelper);
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

    private static boolean addGradleDependencyIfRequired(Module module, GradleBuildFileHelper gradleBuildFileHelper) throws FileNotFoundException {
        boolean result = true;
        if(gradleBuildFileHelper.addDependency(dependencyGroupId, dependencyPackageId, dependencyVersion)) {
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
            File foo = new File(selectedDirectory);
            VirtualFile selectedFolder = LocalFileSystem.getInstance().findFileByIoFile(foo);
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

    private static URIBuilder createUrl(String text) throws MalformedURLException, URISyntaxException {
        String serverUrl = text.endsWith("/") ? text : (text + "/");
        serverUrl = (serverUrl.startsWith("http://") || serverUrl.startsWith("https://")) ? serverUrl : ("http://" + serverUrl);
        URL url = new URL(serverUrl);
        String path = url.getPath().contains("?") ? url.getPath().split("\\?", 2)[0] : url.getPath();
        if (!path.endsWith("types/java/")) {
            serverUrl += "types/java/";
        }
        URIBuilder builder;

        try {
            builder = new URIBuilder(serverUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw e;
        }


        return builder;
    }

    private static String getDtoFileName(String name) {
        int p = name.lastIndexOf(".");
        String e = name.substring(p + 1);
        if (p == -1 || !e.equals("java")) {
            /* file has no extension */
            return name + ".java";
        } else {
            /* file has extension e */
            return name;
        }
    }

    private static String getDtoNameWithoutExtension(String name) {
        int p = name.lastIndexOf(".");
        String e = name.substring(p + 1);
        if (p == -1 || !e.equals("java")) {
            /* file has no extension */
            return name;
        } else {
            /* file has extension e */
            return name.substring(0, p);
        }
    }

}
