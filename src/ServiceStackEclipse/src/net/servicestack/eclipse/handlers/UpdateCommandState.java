package net.servicestack.eclipse.handlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class UpdateCommandState extends AbstractSourceProvider {
	public final static String ID = "net.servicestack.eclipse.handlers.updatecommandstate";
	public final static String SHOW_UPDATE_CONTEXT_STATE = "net.servicestack.eclipse.handlers.updatecommandstate.update";
	private boolean enabled = true;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getCurrentState() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(ID, ID);
		String value = enabled ? "true" : "false";
	    hashMap.put(SHOW_UPDATE_CONTEXT_STATE, value);
	    return hashMap;
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] { SHOW_UPDATE_CONTEXT_STATE };
	}
	
	public void setUpdateEnabled() {
	    enabled = true;
	    fireSourceChanged(ISources.WORKBENCH, SHOW_UPDATE_CONTEXT_STATE, "true");
	  }
	
	public void setUpdateDisabled() {
	    enabled = false;
	    fireSourceChanged(ISources.WORKBENCH, SHOW_UPDATE_CONTEXT_STATE, "false");
	  }

}
