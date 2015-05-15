package net.servicestack.eclipse.popup.actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;

public class AddRefDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane errorTextPane;
    private JTextField addressUrlTextField;
    private JTextField nameTextField;
    private JTextPane infoTextPane;
    
    private String errorMessage;
    private String selectedDirectory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddRefDialog dialog = new AddRefDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddRefDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		infoTextPane = new JTextPane();
		panel.add(infoTextPane);
		infoTextPane.setEditable(false);
		infoTextPane.setFocusCycleRoot(false);
		infoTextPane.setFocusable(false);
		infoTextPane.setFont(new Font("Arial", infoTextPane.getFont().getStyle(), infoTextPane.getFont().getSize()));
		infoTextPane.setOpaque(false);
		infoTextPane.setRequestFocusEnabled(false);
		infoTextPane.setText("To generate the DTO Service Model types for a specific ServiceStack instance, enter the base URI for the remote ServiceStack server and click OK.");
		errorTextPane = new JTextPane();
		errorTextPane.setForeground(Color.RED);
		errorTextPane.setVisible(false);
		panel.add(errorTextPane);
		errorTextPane.setEditable(false);
		errorTextPane.setFocusCycleRoot(false);
		errorTextPane.setFocusable(false);
		errorTextPane.setFont(new Font("Arial", errorTextPane.getFont().getStyle(), errorTextPane.getFont().getSize()));
		errorTextPane.setOpaque(false);
		final JPanel panel3 = new JPanel();
		contentPanel.add(panel3);
		panel3.setFocusable(false);
		panel3.setLayout(new GridLayout(0, 1, 0, 0));
		final JLabel label3 = new JLabel();
		label3.setText("Name");
		panel3.add(label3);
		nameTextField = new JTextField();
		nameTextField.setText("dto.java");
		panel3.add(nameTextField);
		label3.setLabelFor(nameTextField);
		final JLabel label1 = new JLabel();
		panel3.add(label1);
		label1.setText("Address Url");
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonOK = new JButton("OK");
				buttonOK.setActionCommand("OK");
				buttonPane.add(buttonOK);
				getRootPane().setDefaultButton(buttonOK);
			}
			{
				buttonCancel = new JButton("Cancel");
				buttonCancel.setActionCommand("Cancel");
				buttonPane.add(buttonCancel);
			}
		}
		addressUrlTextField = new JTextField();
		panel3.add(addressUrlTextField);
		addressUrlTextField.setToolTipText("eg, http://example.com/");
		label1.setLabelFor(addressUrlTextField);
		
		setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        ImageIcon imageIcon = createImageIcon("/icons/logo-16.png", "ServiceStack");
        if (imageIcon != null) {
            this.setIconImage(imageIcon.getImage());
        }
        errorTextPane.setForeground(Color.RED);

        buttonOK.setEnabled(false);

        addressUrlTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = null;
                if (input instanceof JTextField) {
                    text = ((JTextField) input).getText();
                }

                return text != null && text.length() > 0;
            }

            @Override
            public boolean shouldYieldFocus(JComponent input) {
                boolean valid = verify(input);
                if (!valid) {
                    errorMessage = "URL Address is required";
                    errorTextPane.setVisible(true);
                    errorTextPane.setText(errorMessage);
                }

                return true;
            }
        });
        nameTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = null;
                if (input instanceof JTextField) {
                    text = ((JTextField) input).getText();
                }

                return text != null && text.length() > 0;
            }

            @Override
            public boolean shouldYieldFocus(JComponent input) {
                boolean valid = verify(input);
                if (!valid) {
                    errorMessage = "A file name is required.";
                    errorTextPane.setVisible(true);
                    errorTextPane.setText(errorMessage);
                }

                return true;
            }
        });

        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (nameTextField.getInputVerifier().verify(nameTextField) && addressUrlTextField.getInputVerifier().verify(addressUrlTextField)) {
                    buttonOK.setEnabled(true);
                } else {
                    buttonOK.setEnabled(false);
                }
            }
        });

        addressUrlTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (nameTextField.getInputVerifier().verify(nameTextField) && addressUrlTextField.getInputVerifier().verify(addressUrlTextField)) {
                    buttonOK.setEnabled(true);
                } else {
                    buttonOK.setEnabled(false);
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}
	
	public void setSelectedDirectory(String selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    private void processOK() {
        buttonOK.setEnabled(false);
        buttonCancel.setEnabled(false);
        errorMessage = null;
        errorTextPane.setVisible(false);

        Runnable r = new Runnable() {
            public void run() {
                try {
                    onOK();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    errorMessage = errorMessage != null ? errorMessage : "An error occurred adding reference - " + e1.getMessage();
                }
                if (errorMessage != null) {
                    errorTextPane.setVisible(true);
                    errorTextPane.setText(errorMessage);
                }
                buttonOK.setEnabled(true);
                buttonCancel.setEnabled(true);

            }
        };
        SwingUtilities.invokeLater(r);
    }

    private void setPackageBrowseText(String packageName) {
        
    }

    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void onOK() {
        StringBuilder errorMessage = new StringBuilder();
//        AddServiceStackRefUtils.handleOk(
//                addressUrlTextField.getText(),
//                packageBrowse.getText(),
//                nameTextField.getText(),
//                selectedDirectory,
//                module,
//                errorMessage);

        if(errorMessage.toString().length() > 0) {
            errorTextPane.setText(errorMessage.toString());
            errorTextPane.setVisible(true);
        } else {
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }

}
