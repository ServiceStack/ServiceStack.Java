package net.servicestack.eclipse.startup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.servicestack.eclipse.handlers.UpdateCommandState;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.services.ISourceProviderService;

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
    	
    	IWorkbench workbench = PlatformUI.getWorkbench();
    	ISourceProviderService sourceProviderService = 
				(ISourceProviderService) workbench.getService(ISourceProviderService.class);
		    // now get my service
		    UpdateCommandState commandStateService = (UpdateCommandState) sourceProviderService
		        .getSourceProvider(UpdateCommandState.SHOW_UPDATE_CONTEXT_STATE);
		    
        // TODO handle selection changes
        if(!(selection instanceof ITreeSelection)) {
        	commandStateService.setUpdateDisabled();
        	return;
        }
        
        TreeSelection treeSelection = (TreeSelection)selection;
        if(!(treeSelection.getFirstElement() instanceof ICompilationUnit)) {
        	commandStateService.setUpdateDisabled();
        	return;
        }

        ICompilationUnit compilationUnit = (ICompilationUnit)treeSelection.getFirstElement();
    	IResource resource = extractSelection(selection);
    	IProject project = resource.getProject();
        IImportDeclaration importDeclaration = compilationUnit.getImport("net.servicestack.client");
        if(importDeclaration == null) {
        	commandStateService.setUpdateDisabled();
        	return;
        }
        
        IFile file = project.getFile(stripFirstDirectoryFromPath(compilationUnit.getPath()));
        InputStream contents = null;
		try {
			contents = file.getContents();
			InputStreamReader is = new InputStreamReader(contents);
			BufferedReader br = new BufferedReader(is);
			String read;
			try {
				for(int i = 0; i < 10; i++) {
					String line = br.readLine();
					if(line.startsWith("/* Options:")) {
						
						    commandStateService.setUpdateEnabled();
						    return;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contents == null) {
			commandStateService.setUpdateDisabled();
			return;
		}
		commandStateService.setUpdateDisabled();
        System.out.println("File selected");
    }
    
    IResource extractSelection(ISelection sel) {
		if (!(sel instanceof IStructuredSelection))
			return null;
		IStructuredSelection ss = (IStructuredSelection) sel;
		Object element = ss.getFirstElement();
		if (element instanceof IResource)
			return (IResource) element;
		if (!(element instanceof IAdaptable))
			return null;
		IAdaptable adaptable = (IAdaptable) element;
		Object adapter = adaptable.getAdapter(IResource.class);
		return (IResource) adapter;
	}
    
	private IPath stripFirstDirectoryFromPath(IPath path) {
		String constructedPath = "";
		// Skip first segment of project path as we want path relative to
		// project.
		for (int i = 1; i < path.segmentCount(); i++) {
			constructedPath += "/" + path.segment(i);
		}
		if (constructedPath.equals("")) {
			constructedPath = "/";
		}
		Path result = new Path(constructedPath);
		return result;
	}
}
