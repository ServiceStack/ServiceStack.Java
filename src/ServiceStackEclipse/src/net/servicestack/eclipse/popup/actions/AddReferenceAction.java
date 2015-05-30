package net.servicestack.eclipse.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import net.servicestack.eclipse.wizard.AddReferenceWizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IPackageFragment;

public class AddReferenceAction implements IObjectActionDelegate {

	private Shell shell;
	private IFolder _selection;
	private IPackageFragment _packageFragment;
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
		targetPart.getSite().getWorkbenchWindow().getSelectionService();
		selectionService = targetPart.getSite().getWorkbenchWindow().getSelectionService();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (_selection != null) {
			IStructuredSelection packageSelection = (IStructuredSelection) selectionService
					.getSelection("org.eclipse.jdt.ui.PackageExplorer");
			
			AddReferenceWizard generationWizard = new AddReferenceWizard(_selection, packageSelection);
			WizardDialog dialog = new WizardDialog(shell, generationWizard);
			dialog.setBlockOnOpen(false);
			Display display = shell.getDisplay();
			Monitor primaryMonitor = display.getPrimaryMonitor ();
			Rectangle bounds = primaryMonitor.getBounds ();
			dialog.open();
			Rectangle rect = dialog.getShell().getBounds ();
			int x = bounds.x + (bounds.width - rect.width) / 2 ;
			int y = bounds.y + (bounds.height - rect.height) / 2 ;
			dialog.getShell().setLocation (x, y);
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection)selection;
			Object firstElement = structured.getFirstElement();
			if(firstElement instanceof IFolder) {
				_selection = (IFolder) firstElement;	
			}
		}
	}
	
}
