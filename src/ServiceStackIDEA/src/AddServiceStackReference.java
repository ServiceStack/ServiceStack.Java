import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetTypeId;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class AddServiceStackReference extends AnAction {
    public static final FacetTypeId ID = new FacetTypeId("android");

    public void actionPerformed(AnActionEvent e) {

        // TODO: insert action logic here
        Module module = getModule(e);

        AddRef dialog = new AddRef(module);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(dialog.getPreferredSize());
        dialog.setVisible(true);
    }

    @Override
    public void update(AnActionEvent e) {
        Module m = getModule(e);
//        if (m == null || !isAndroidProject(m)) {
//            e.getPresentation().setEnabled(false);
//            return;
//        }
//        e.getPresentation().setEnabled(false);
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
        Collection facetsByType = FacetManager.getInstance(module).getFacetsByType(ID);
        return facetsByType.size() > 0;
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
