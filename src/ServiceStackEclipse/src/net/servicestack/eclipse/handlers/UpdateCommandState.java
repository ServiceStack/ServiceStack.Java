package net.servicestack.eclipse.handlers;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;

public class UpdateCommandState extends AbstractSourceProvider {
	public final static String MY_STATE = "net.servicestack.eclipse.handlers.active";
	public final static String ENABLED = "ENABLED";
	public final static String DISENABLED = "DISENABLED";
	public boolean enabled = true;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map getCurrentState() {
		Map map = new HashMap(1);
	    String value = enabled ? ENABLED : DISENABLED;
	    map.put(MY_STATE, value);
	    return map;
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] { MY_STATE };
	}

}
