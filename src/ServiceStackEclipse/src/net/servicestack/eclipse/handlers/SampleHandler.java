package net.servicestack.eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.services.ISourceProviderService;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the source provider service
	    ISourceProviderService sourceProviderService = (ISourceProviderService) HandlerUtil
	        .getActiveWorkbenchWindow(event).getService(ISourceProviderService.class);
	    // now get my service
	    UpdateCommandState commandStateService = (UpdateCommandState) sourceProviderService
	        .getSourceProvider(UpdateCommandState.SHOW_UPDATE_CONTEXT_STATE);
	    commandStateService.setUpdateEnabled();
	    return null;
	}
	
	
}
