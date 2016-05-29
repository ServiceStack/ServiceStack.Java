package net.servicestack.idea;

import com.intellij.codeInsight.intention.impl.QuickEditAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.*;
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
            if(psiFile == null) {
                return false;
            }
            INativeTypesHandler nativeTypesHandler = IDEAUtils.getNativeTypesHandler(psiFile.getName());
            if(nativeTypesHandler == null) {
                return false;
            }

            if(UpdateServiceStackUtils.containsOptionsHeader(psiFile)) {
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
        return new ImageIcon(this.getClass().getResource("/logo-16.png"));
    }
}
