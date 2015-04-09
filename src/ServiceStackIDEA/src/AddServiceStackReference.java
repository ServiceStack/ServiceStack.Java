import com.intellij.facet.Facet;
import com.intellij.facet.FacetManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AddServiceStackReference extends AnAction {

    public void actionPerformed(AnActionEvent e) {

        // TODO: insert action logic here
        Module module = getModule(e);
        AddRef dialog = new AddRef(module);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(dialog.getPreferredSize());
        dialog.setResizable(true);

        //Check if a package was selected in the left hand menu, populate package name
        PsiElement element = DataKeys.PSI_ELEMENT.getData(e.getDataContext());
        if (element != null && element instanceof PsiPackage){
            PsiPackage psiPackage = (PsiPackage)element;
            dialog.setPackageBrowseText(psiPackage.getName());
            return;
        }

        //Check if a directory containing a Java file was selected, populate package name
        if (element != null && element instanceof PsiDirectory){
            PsiElement firstChild = element.getFirstChild();
            if(firstChild != null && firstChild instanceof PsiJavaFile) {
                String packageName = ((PsiJavaFile)firstChild).getPackageName();
                dialog.setPackageBrowseText(packageName);
                dialog.setVisible(true);
                return;
            }
        }

        //Check if a Java file was selected, display without a package name if no file.
        VirtualFile selectedFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if(selectedFile == null) {
            dialog.setVisible(true);
            return;
        }

        //Check for document, display without a package name if no document.
        Document document = FileDocumentManager.getInstance().getDocument(selectedFile);
        if(document == null) {
            dialog.setVisible(true);
            return;
        }

        //Check if a 'PsiFile', display without a package name if no PsiFile.
        PsiFile psiFile = PsiDocumentManager.getInstance(module.getProject()).getPsiFile(document);
        if(psiFile == null) {
            dialog.setVisible(true);
            return;
        }

        //Finally check if a Java file and populate package name with class package name.
        if(Objects.equals(psiFile.getFileType().getName(), "JAVA")) {
            PsiJavaFile javaFile = (PsiJavaFile)psiFile;
            dialog.setPackageBrowseText(javaFile.getPackageName());
        }

        dialog.setVisible(true);
    }

    @Override
    public void update(AnActionEvent e) {
        Module m = getModule(e);
        if (m == null || !isAndroidProject(m)) {
            e.getPresentation().setEnabled(false);
            return;
        }
        e.getPresentation().setEnabled(true);
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

    private static boolean isAndroidProject(@NotNull Module module) {
        Facet[] facetsByType = FacetManager.getInstance(module).getAllFacets();
        for (Facet facet :facetsByType) {
            if(Objects.equals(facet.getTypeId().toString(), "android")) {
                return true;
            }
        }
        return false;
    }

    static Module getModule(AnActionEvent e) {
        Module module = e.getData(DataKeys.MODULE);
        if (module == null) {
            Project project = e.getData(DataKeys.PROJECT);
            return getModule(project);
        } else {
            return module;
        }
    }
}
