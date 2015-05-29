package net.servicestack.eclipse.startup;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class PluginStartup implements IStartup, ISelectionListener {

	@Override
    public void earlyStartup() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        workbench.addWindowListener(new IWindowListener() {

            @Override
            public void windowOpened(IWorkbenchWindow window) {
                addSelectionListener(window);
            }

            @Override
            public void windowClosed(IWorkbenchWindow window) {
                removeSelectionListener(window);
            }

			@Override
			public void windowActivated(IWorkbenchWindow window) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(IWorkbenchWindow window) {
				// TODO Auto-generated method stub
				
			}
        });

        workbench.getDisplay().asyncExec(new Runnable() {
            public void run() {
                for (IWorkbenchWindow window : workbench.getWorkbenchWindows()) {
                    addSelectionListener(window);
                }
            }
        });
    }

    private void addSelectionListener(IWorkbenchWindow window) {
        if (window != null) {
            window.getSelectionService().addSelectionListener("org.eclipse.jdt.ui.PackageExplorer", this);
        }
    }

    private void removeSelectionListener(IWorkbenchWindow window) {
        if (window != null) {
            window.getSelectionService().removeSelectionListener("org.eclipse.jdt.ui.PackageExplorer", this);
        }
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        // TODO handle selection changes
        System.out.println("selection changed");
    }
}
