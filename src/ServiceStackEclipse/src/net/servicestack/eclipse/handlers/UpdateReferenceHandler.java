package net.servicestack.eclipse.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.servicestack.eclipse.nativetypes.INativeTypesHandler;
import net.servicestack.eclipse.nativetypes.JavaNativeTypesHandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.services.ISourceProviderService;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class UpdateReferenceHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public UpdateReferenceHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Job job = new Job("Updating ServiceStack Reference") {
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			@Override
			protected IStatus run(IProgressMonitor monitor) {

				monitor.beginTask("Updating ServiceStack Reference", 5);
				// Get the source provider service
				IFile fileToUpdate = UpdateReferenceCurrentSelection.getInstance().UpdateReferenceFile;
				if(fileToUpdate == null) {
					showErrorDialogOnUIThread(shell,"Unable to read ServiceStack reference from project.");
					return Status.CANCEL_STATUS;
				}
				String fileContents = "";
				try {
					fileContents = getStringFromInputStream(fileToUpdate.getContents());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(fileContents == null || fileContents.length() == 0) {
					showErrorDialogOnUIThread(shell,"Unable to read ServiceStack reference from project.");
					return Status.CANCEL_STATUS;
				}
				
				INativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
				Map<String,String> options = nativeTypesHandler.parseComments(fileContents);
				String baseUrl = options.get("BaseUrl");
				options.remove("BaseUrl");
				String updatedCode = "";
				monitor.worked(1);
				try {
					updatedCode = nativeTypesHandler.getUpdatedCode(baseUrl, options);
				}
				catch (FileNotFoundException e) {
					showErrorDialogOnUIThread(shell,"Failed to updated ServiceStack reference. Server responded with 404." + e.getLocalizedMessage());
					
					return Status.CANCEL_STATUS;
				}
				catch (IOException e) {
					showErrorDialogOnUIThread(shell,"Failed to updated ServiceStack reference. " + e.getClass().getName() + " - " + e.getLocalizedMessage());
										
					return Status.CANCEL_STATUS;
				}
				
				monitor.worked(3);
				if(updatedCode == null || updatedCode.length() == 0) {
					showErrorDialogOnUIThread(shell, "Failed to updated ServiceStack reference. Response was empty. Check BaseUrl.");
					return Status.CANCEL_STATUS;
				}
				if(!nativeTypesHandler.isFileAServiceStackReference(updatedCode)) {
					showErrorDialogOnUIThread(shell, "Response from server was not a valid ServiceStack reference. Check BaseUrl.");
					return Status.CANCEL_STATUS;
				}
				InputStream stream = new ByteArrayInputStream(updatedCode.getBytes(StandardCharsets.UTF_8));
				try {
					fileToUpdate.setContents(stream, 0, null);
					fileToUpdate.refreshLocal(IResource.DEPTH_ZERO, null);
				} catch (CoreException e) {
					showErrorDialogOnUIThread(shell,"Failed to write ServiceStack reference. " + e.getLocalizedMessage());					
					return Status.CANCEL_STATUS;
				}
				monitor.worked(1);
				monitor.done();
				return Status.OK_STATUS;
			}
			
		};
		
		job.schedule();
		
	    return null;
	}
	
	private void showErrorDialogOnUIThread(final Shell shell, final String message) {
		UIJob uiJob = new UIJob("update servicestack reference error") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				MessageDialog.openError(
						shell,
						"Error", 
						message);
				return Status.OK_STATUS;
			}
		
		};
		uiJob.schedule();
	}

	private String getStringFromInputStream(InputStream inputStream) throws IOException {
		InputStreamReader is = new InputStreamReader(inputStream);
		StringBuilder sb=new StringBuilder();
		BufferedReader br = new BufferedReader(is);
		String read = br.readLine();

		while(read != null) {
		    //System.out.println(read);
		    sb.append(read);
		    sb.append(System.lineSeparator());
		    read =br.readLine();

		}

		return sb.toString();
	}
	
}
