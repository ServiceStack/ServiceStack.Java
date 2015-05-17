package net.servicestack.eclipse.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.jdt.internal.core.PackageFragment;

public class AddReferenceAction implements IObjectActionDelegate {

	private Shell shell;
	private ISelectionService selectionService;
	
	/**
	 * Constructor for Action1.
	 */
	public AddReferenceAction() {
		super();
	}
	
	/**
	 * @see IObjectActionDelegate
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		selectionService = targetPart.getSite().getWorkbenchWindow().getSelectionService();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection structured = (IStructuredSelection) selectionService
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		Object firstElement = structured.getFirstElement();
		if(firstElement instanceof PackageFragment) { //NoClassDefFoundError... 
			PackageFragment packFrag = (PackageFragment)firstElement;
		}
//		IFolder folder = (IFolder) structured.getFirstElement();
//		if(folder == null) {
//			return;
//		}
//		IPath path = folder.getLocation();
//		
//		ObjectPluginAction pluginAction = (ObjectPluginAction)action;
		AddRefDialog dialog = new AddRefDialog();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(dialog.getPreferredSize());
        dialog.setResizable(true);
        dialog.setTitle("Add ServiceStack Reference");
        dialog.setVisible(true);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
