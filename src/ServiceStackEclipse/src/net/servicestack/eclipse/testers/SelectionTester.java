package net.servicestack.eclipse.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class SelectionTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if ("hasNonEmptyTextSelection".equals(property)) {
			try {
				IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
				String foo = activePart.toString();
			} catch (Exception e) {
				// Do nothing. Will throw an NPE when the application is closed as there is no longer an active part.
			}
		}
		return true;
	}

}