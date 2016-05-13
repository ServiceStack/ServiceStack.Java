package net.servicestack.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

/**
 * Created by Layoric on 9/04/2015.
 * Update ServiceStack Reference Project menu context action
 */
public class UpdateServiceStackReference extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final PsiFile psiFile = getPsiFile(anActionEvent);
        if(UpdateServiceStackUtils.containsOptionsHeader(psiFile)) {
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    UpdateServiceStackUtils.updateServiceStackReference(psiFile);
                }
            });
        }
    }

    @Override
    public void update(AnActionEvent e) {
        PsiFile psiFile = getPsiFile(e);
        if (psiFile == null) {
            e.getPresentation().setVisible(false);
            return;
        }

        INativeTypesHandler nativeTypesHandler = IDEAUtils.getNativeTypesHandler(psiFile.getName());

        if(nativeTypesHandler == null) {
            e.getPresentation().setVisible(false);
            return;
        }

        if(!UpdateServiceStackUtils.containsOptionsHeader(psiFile)) {
            e.getPresentation().setVisible(false);
            return;
        }

        e.getPresentation().setVisible(true);
        super.update(e);
    }

    static Module getModule(Project project) {
        if (project == null)
            return null;
        Module[] modules = ModuleManager.getInstance(project).getModules();
        if (modules.length > 0) {
            return modules[0];
        }
        return null;
    }

    private static PsiFile getPsiFile(AnActionEvent e) {

        Module module = getModule(e);
        if(module == null) {
            return null;
        }
        VirtualFile selectedFile = LangDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if(selectedFile == null) {
            return null;
        }
        Document document = FileDocumentManager.getInstance().getDocument(selectedFile);
        if(document == null) {
            return null;
        }

        //Check if a 'PsiFile', display without a package name if no PsiFile.
        PsiFile psiFile = PsiDocumentManager.getInstance(module.getProject()).getPsiFile(document);
        if(psiFile == null) {
            return null;
        }

        return psiFile;
    }

    static Module getModule(AnActionEvent e) {
        Module module = e.getData(LangDataKeys.MODULE);
        if (module == null) {
            Project project = e.getData(LangDataKeys.PROJECT);
            return getModule(project);
        } else {
            return module;
        }
    }
}
