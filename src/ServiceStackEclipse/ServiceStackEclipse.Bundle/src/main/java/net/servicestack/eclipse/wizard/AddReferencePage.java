package net.servicestack.eclipse.wizard;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.osgi.framework.Bundle;

import java.net.URL;

public class AddReferencePage extends WizardPage {

    Button _enabled;
    public boolean canFinish = false;

    private Text addressUrlTextField;
    private Text nameTextField;
    private Text packageTextField;

    private String _tempPackageName;

    protected AddReferencePage() {
        super("Add ServiceStack Reference");
        setTitle("Add ServiceStack Reference");
        setDescription("Enter the base URI for the remote ServiceStack server and click Finish.");
        Bundle bundle = Platform.getBundle("net.servicestack.eclipse");
        final URL fullPathString = bundle.getEntry("/icons/logo-100.png");

        ImageDescriptor imageDesc = ImageDescriptor.createFromURL(fullPathString);
        setImageDescriptor(imageDesc);

        // TODO Auto-generated constructor stub
    }

    @Override
    public void createControl(Composite parent) {

        // TODO Auto-generated method stub
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        GridData gd = new GridData(GridData.FILL_BOTH);
        parent.setLayoutData(gd);
        composite.setLayout(layout);
        
        Label addressLabel = new Label(composite, SWT.NONE);
        addressLabel.setText("Address Url");
        addressUrlTextField = new Text(composite, SWT.BORDER | SWT.SINGLE);
        addressUrlTextField.setToolTipText("eg, http://example.com/");
        addressUrlTextField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        addressUrlTextField.addVerifyListener(new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent event) {
                String currentText = ((Text) event.widget).getText();
                //Build current string....
                String text = currentText.substring(0, event.start) + event.text + currentText.substring(event.end);

                boolean result = text.length() > 0;
                if (!result) {
                    setErrorMessage("Address Url is required");
                    canFinish = false;
                } else {
                    canFinish = true;
                }
                // seriously..doit?
                event.doit = true;
                getWizard().getContainer().updateButtons();
            }
        });

        Label packageLabel = new Label(composite, SWT.NONE);
        packageLabel.setText("Package");
        packageTextField = new Text(composite, SWT.BORDER | SWT.SINGLE);
        packageTextField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        packageTextField.setText(_tempPackageName == null ? "" : _tempPackageName);

        Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText("Name");
        nameTextField = new Text(composite, SWT.BORDER | SWT.SINGLE);
        nameTextField.setText("dto.java");
        nameTextField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        nameTextField.addVerifyListener(new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent event) {
                String currentText = ((Text) event.widget).getText();
                //Build current string....
                String text = currentText.substring(0, event.start) + event.text + currentText.substring(event.end);
                boolean result = text.length() > 0;
                if (!result) {
                    setErrorMessage("A file name is required.");
                    canFinish = false;
                } else {
                    canFinish = true;
                }
                event.doit = true;
                getWizard().getContainer().updateButtons();
            }
        });

        setControl(composite);
        dialogChanged();
    }

    public String getAddressUrl() {
        return addressUrlTextField.getText();
    }

    public String getFileName() {
        return nameTextField.getText();
    }

    public void setPackageName(String packageName) {
        _tempPackageName = packageName;
        if (packageTextField != null) {
            packageTextField.setText(_tempPackageName);
        }
    }

    public String getPackageName() {
        return packageTextField.getText();
    }

    private void dialogChanged() {
        updateStatus(null);
    }

    @Override
    public boolean canFlipToNextPage() {
        return canFinish;
    }

    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }
}
