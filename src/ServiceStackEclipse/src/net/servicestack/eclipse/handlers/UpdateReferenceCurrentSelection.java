package net.servicestack.eclipse.handlers;

import org.eclipse.core.resources.IFile;

public class UpdateReferenceCurrentSelection {

	private static UpdateReferenceCurrentSelection instance = null;
	
	private UpdateReferenceCurrentSelection() {}

    public static UpdateReferenceCurrentSelection getInstance() {
        if (instance == null) {
            instance = new UpdateReferenceCurrentSelection();
        }
        return instance;
    }
    
    public IFile UpdateReferenceFile;
}
