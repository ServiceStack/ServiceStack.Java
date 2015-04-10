import com.intellij.codeInsight.intention.impl.QuickEditAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class UpdateServiceStackReferenceIntention extends QuickEditAction implements Iconable {

    @NotNull
    @Override
    public String getText() {
        return "Update ServiceStack Reference";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "UpdateServiceStackReferenceIntention";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        try {
            if(psiFile == null || !(psiFile instanceof PsiJavaFile)) {
                return false;
            }
            PsiJavaFile classFile = (PsiJavaFile)psiFile;
            if(UpdateServiceStackUtils.containsOptionsHeader(classFile)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        UpdateServiceStackUtils.updateServiceStackReference(psiFile);
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }

    @Override
    public Icon getIcon(@IconFlags int i) {
        return new ImageIcon(this.getClass().getResource("/icons/logo-16.png"));
    }
}
