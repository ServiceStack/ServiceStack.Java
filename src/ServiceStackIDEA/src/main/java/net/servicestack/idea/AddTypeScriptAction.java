package net.servicestack.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.util.PlatformUtils;

import java.io.File;

/**
 * Created by Layoric on 28/05/2016.
 */
public class AddTypeScriptAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Module module = getModule(anActionEvent);
        AddTypeScriptRef dialog = new AddTypeScriptRef(module);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(dialog.getPreferredSize());
        dialog.setResizable(true);
        dialog.setTitle("Add TypeScript ServiceStack Reference");
        PsiElement element = LangDataKeys.PSI_ELEMENT.getData(anActionEvent.getDataContext());
        INativeTypesHandler defaultTsNativeTypesHandler = new TypeScriptNativeTypesHandler();
        if (element instanceof PsiDirectory) {
            PsiDirectory selectedDir = (PsiDirectory)element;
            dialog.setSelectedDirectory(selectedDir.getVirtualFile().getPath());
            String initialName = getInitialFileName(selectedDir.getVirtualFile().getPath(),defaultTsNativeTypesHandler);
            dialog.setInitialDtoName(initialName);
        }
        showDialog(dialog);
    }

    private void showDialog(AddTypeScriptRef dialog) {
        dialog.setVisible(true);
    }

    private String getInitialFileName(String path, INativeTypesHandler defaultTsNativeTypesHandler) {
        String initName = "ServiceReference";
        Integer count = 1;
        while(true) {
            File existingFile = new File(path + "/" + initName + count.toString() +
                    defaultTsNativeTypesHandler.getFileExtension());
            if(existingFile.exists()) {
                count++;
            } else {
                break;
            }
        }
        return initName + count.toString();
    }

    @Override
    public void update(AnActionEvent e) {
        Module module = getModule(e);
        if (module == null) {
            e.getPresentation().setEnabled(false);
        }

        if (!(PlatformUtils.isWebStorm() || PlatformUtils.isPhpStorm() ||
                PlatformUtils.isRubyMine() || PlatformUtils.isIntelliJ() ||
                PlatformUtils.isPyCharm())) {
            e.getPresentation().setVisible(false);
        }

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
