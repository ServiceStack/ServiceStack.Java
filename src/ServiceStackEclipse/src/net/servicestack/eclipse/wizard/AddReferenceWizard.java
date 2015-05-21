package net.servicestack.eclipse.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

public class AddReferenceWizard extends Wizard {
	private AddReferencePage _page;
	private IFolder _selection;
	IPackageFragment _packageFragment;
	
	public AddReferenceWizard(IFolder selection, IPackageFragment packageFragment) {
		_selection = selection;
		_packageFragment = packageFragment;
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
			      public void run(IProgressMonitor monitor) throws InterruptedException {
			         int sum = 20;
			         monitor.beginTask("Computing sum: ", sum);
			         for (int i = 0; i < sum; i++) {
			            monitor.subTask(Integer.toString(i));
			            //sleep to simulate long running operation
			            Thread.sleep(100);
			            monitor.worked(1);
			         }
			         monitor.done();
			      }
			   });
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		_page = new AddReferencePage();
		String sessionName = _selection.getParent().getParent().getName().toLowerCase();
//		_page.setPathToOutput(_outputFolder.getFolder(sessionName).getLocation().toOSString());
//		_page.setPathToInputFolder(_selection.getFolder(sessionName).getLocation().toOSString());
		addPage(_page);
	}
	
	@Override
	public boolean needsProgressMonitor() {
		return true;
	}

}
