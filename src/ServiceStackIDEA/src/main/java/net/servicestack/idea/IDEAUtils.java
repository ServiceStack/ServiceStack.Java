package net.servicestack.idea;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiDocumentManager;

import java.io.File;

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

    public static void closeFile(Module module, String filePath) {
        File file = new File(filePath);
        VirtualFile fileByUrl = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
        if (fileByUrl == null) {
            return;
        }
        FileEditorManager.getInstance(module.getProject()).closeFile(fileByUrl);
    }

    public static INativeTypesHandler getDefaultNativeTypesHandler(Module module) {
        if(GradleBuildFileHelper.isGradleModule(module) && GradleBuildFileHelper.isUsingKotlin(module)) {
            return new KotlinNativeTypesHandler();
        }

        if(IDEAPomFileHelper.isMavenProjectWithKotlin(module)) {
            return new KotlinNativeTypesHandler();
        }
        return new JavaNativeTypesHandler();
    }

    public static INativeTypesHandler getNativeTypesHandler(Module module,String fileName) {
        if(fileName.endsWith(".kt")) return new KotlinNativeTypesHandler();
        if(fileName.endsWith(".java")) return new JavaNativeTypesHandler();
        return getDefaultNativeTypesHandler(module);
    }
}
