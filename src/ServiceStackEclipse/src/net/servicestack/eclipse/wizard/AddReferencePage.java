package net.servicestack.eclipse.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddReferencePage extends WizardPage {

	Button _enabled;
	private boolean _enabledValue = true;
	private String _applicationFolder;
	
    private Text addressUrlTextField;
    private Text nameTextField;
    
    private String errorMessage;
    private String selectedDirectory;
	
	protected AddReferencePage() {
		super("Add ServiceStack Reference");
		setTitle("Add ServiceStack Reference");
		setDescription("To generate the DTO Service Model types for a specific ServiceStack instance, enter the base URI for the remote ServiceStack server and click Finish.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.NULL); 
		composite.setBounds(100, 100, 450, 300);
        GridLayout layout = new GridLayout();
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        composite.setLayout(layout);
        layout.numColumns = 1;
		Label label1 = new Label(composite,SWT.NONE);
		label1.setText("Address Url");
		addressUrlTextField = new Text(composite,SWT.BORDER | SWT.SINGLE);
		addressUrlTextField.setToolTipText("eg, http://example.com/");
		addressUrlTextField.setLayoutData(gd);
		addressUrlTextField.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent event) {
              String text = event.text;
              //seriously..doit?
              boolean result = text != null && text.length() > 0;
              if(!result) {
            	  setErrorMessage("URL Address is required");
              }
              event.doit = result;
			}
		});
		Label label3 = new Label(composite, SWT.NONE);
		label3.setText("Name");
		nameTextField = new Text(composite,SWT.BORDER | SWT.SINGLE);
		nameTextField.setText("dto.java");
		nameTextField.setLayoutData(gd);
		nameTextField.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent event) {
              String text = event.text;
              //seriously..doit?
              boolean result = text != null && text.length() > 0;
              if(!result) {
            	  setErrorMessage("A file name is required.");
              }
              event.doit = result;
			}
		});

		setControl(composite);
		dialogChanged();
	}
	
	private void dialogChanged() {
		updateStatus(null);	
	}
	
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

}
