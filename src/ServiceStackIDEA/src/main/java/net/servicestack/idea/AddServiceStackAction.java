package net.servicestack.idea;

import com.google.common.collect.Lists;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddServiceStackAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        Module module = getModule(e);
        AddRef dialog = new AddRef(module);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(dialog.getPreferredSize());
        dialog.setResizable(true);
        dialog.setTitle("Add ServiceStack Reference");

        //Check if a package was selected in the left hand menu, populate package name
        PsiElement element = LangDataKeys.PSI_ELEMENT.getData(e.getDataContext());
        if (element != null && element instanceof PsiPackage) {
            PsiPackage psiPackage = (PsiPackage) element;
            dialog.setSelectedPackage(psiPackage);
            dialog.setVisible(true);
            return;
        }

        //Check if a directory containing a Java file was selected, populate package name
        if (element != null && element instanceof PsiDirectory) {
            PsiElement firstChild = element.getFirstChild();
            dialog.setSelectedDirectory(((PsiDirectory) element).getVirtualFile().getPath());
            if (firstChild != null && firstChild instanceof PsiJavaFile) {
                PsiJavaFile firstJavaFile = (PsiJavaFile) firstChild;
                PsiPackage mainPackage = JavaPsiFacade.getInstance(module.getProject()).findPackage(firstJavaFile.getPackageName());
                if (mainPackage != null) {
                    dialog.setSelectedPackage(mainPackage);
                }
            } else if(module.getModuleFile() != null) {
                try {
                    PsiDirectory selectedDir = (PsiDirectory) element;
                    String packageName = "";
                    String moduleDirectoryPath = module.getModuleFile().getParent().getPath();
                    List<String> packageArray = new ArrayList<String>();
                    while (selectedDir != null && !(moduleDirectoryPath.equals(selectedDir.getVirtualFile().getPath()))) {
                        packageArray.add(selectedDir.getName());
                        selectedDir = selectedDir.getParent();
                        PsiPackage mainPackage = testPackage(module, packageName, packageArray);
                        if (mainPackage != null) {
                            //"java" is a valid package name in an empty project according to openapi so we have to ignore it and no pre-populate package name....
                            if(mainPackage.getQualifiedName().equals("java")) {
                                break;
                            }
                            dialog.setSelectedPackage(mainPackage);
                            break;
                        }
                    }
                } catch (Exception ex) {
                    //do nothing, can't get package name.
                }
            }
            ShowDialog(module, dialog);
            return;
        }

        //Check if a Java file was selected, display without a package name if no file.
        VirtualFile selectedFile = LangDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (selectedFile == null) {
            Notification notification = new Notification("ServiceStackIDEA", "Error Add ServiceStack Reference", "Context menu failed find folder or file.", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return;
        }
        if (selectedFile.isDirectory()) {
            dialog.setSelectedDirectory(selectedFile.getPath());
        } else if (selectedFile.getParent().isDirectory()) {
            dialog.setSelectedDirectory(selectedFile.getParent().getPath());
        } else {
            Notification notification = new Notification("ServiceStackIDEA", "Error Add ServiceStack Reference", "Context menu failed find folder or file.", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return;
        }

        //Check for document, display without a package name if no document.
        Document document = FileDocumentManager.getInstance().getDocument(selectedFile);
        if (document == null) {
            ShowDialog(module, dialog);
            return;
        }

        //Check if a 'PsiFile', display without a package name if no PsiFile.
        PsiFile psiFile = PsiDocumentManager.getInstance(module.getProject()).getPsiFile(document);
        if (psiFile == null) {
            ShowDialog(module, dialog);
            return;
        }

        //Finally check if a Java file and populate package name with class package name.
        if (psiFile.getFileType().getName().equals("JAVA")) {
            PsiJavaFile javaFile = (PsiJavaFile) psiFile;
            PsiPackage mainPackage = JavaPsiFacade.getInstance(module.getProject()).findPackage(javaFile.getPackageName());
            if (mainPackage != null) {
                dialog.setSelectedPackage(mainPackage);
            }
        }
        ShowDialog(module, dialog);
    }

    private void ShowDialog(Module module, AddRef dialog) {
        if(GradleBuildFileHelper.isGradleModule(module) && GradleBuildFileHelper.isUsingKotlin(module)) {
            dialog.setFileName("dtos.kt");
        }

        if(IDEAPomFileHelper.isMavenProjectWithKotlin(module)) {
            dialog.setFileName("dtos.kt");
        }
        dialog.setVisible(true);
    }

    private PsiPackage testPackage(Module module, String packageName, List<String> packageArray) {
        List<String> packageNameOrderedList = Lists.reverse(packageArray);
        for (int i = 0; i < packageNameOrderedList.size(); i++) {
            if (i < packageNameOrderedList.size() - 1) {
                packageName += packageNameOrderedList.get(i) + ".";
            } else {
                packageName += packageNameOrderedList.get(i);
            }
        }
        return JavaPsiFacade.getInstance(module.getProject()).findPackage(packageName);
    }

    @Override
    public void update(AnActionEvent e) {
        Module module = getModule(e);
        if(module == null) {
            e.getPresentation().setEnabled(false);
            return;
        }

        if(!(PlatformUtils.isIntelliJ() || isAndroidProject(module))) {
            e.getPresentation().setVisible(false);
            return;
        }

        boolean isMavenModule =  IDEAPomFileHelper.isMavenModule(module);

        if (isAndroidProject(module) || isMavenModule) {
            e.getPresentation().setEnabled(true);
        } else {
            e.getPresentation().setEnabled(false);
            return;
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

    private static boolean isAndroidProject(@NotNull Module module) {
        Facet[] facetsByType = FacetManager.getInstance(module).getAllFacets();
        for (Facet facet : facetsByType) {
            if (facet.getTypeId().toString().equals("android")) {
                return true;
            }
        }
        return false;
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
