package net.servicestack.idea;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.PlatformUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Layoric on 10/12/2015.
 */
public class IDEAUtils {

    public static void refreshBuildFile(Module module) {
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

    public static void refreshFile(Module module, String filePath, boolean openFile) {
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
        if (!openFile) closeFile(module,filePath);

        FileDocumentManager.getInstance().reloadFromDisk(document);
        VirtualFileManager.getInstance().syncRefresh();
    }

    public static boolean writeDtoFile(List<String> codeLines, String path, StringBuilder errorMessage) {
        BufferedWriter writer = null;
        boolean result = true;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
            for (String item : codeLines) {
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

    public static void closeFile(Module module, String filePath) {
        File file = new File(filePath);
        VirtualFile fileByUrl = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
        if (fileByUrl == null) {
            return;
        }
        FileEditorManager.getInstance(module.getProject()).closeFile(fileByUrl);
    }

    public static INativeTypesHandler getDefaultNativeTypesHandler(Module module) {
        if (GradleBuildFileHelper.isGradleModule(module) && GradleBuildFileHelper.isUsingKotlin(module)) {
            return new KotlinNativeTypesHandler();
        }

        if (IDEAPomFileHelper.isMavenProjectWithKotlin(module)) {
            return new KotlinNativeTypesHandler();
        }

        if (GradleBuildFileHelper.isDartProject(module)) {
            return new DartNativeTypesHandler();
        }

        if (PlatformUtils.isWebStorm()) {
            return new TypeScriptConcreteNativeTypesHandler();
        }

        return new JavaNativeTypesHandler();
    }

    public static INativeTypesHandler getNativeTypesHandler(String fileName) {
        INativeTypesHandler result = null;
        if (fileName.endsWith(".kt")) result = new KotlinNativeTypesHandler();
        if (fileName.endsWith(".java")) result =  new JavaNativeTypesHandler();
        if (fileName.endsWith("dtos.dart")) result = new DartNativeTypesHandler();
        if (fileName.endsWith("dtos.ts")) result = new TypeScriptConcreteNativeTypesHandler();
        if (fileName.endsWith("dtos.d.ts")) result = new TypeScriptNativeTypesHandler();
        return result;
    }
}
