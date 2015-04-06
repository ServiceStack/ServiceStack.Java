import com.google.gson.Gson;
import com.intellij.ide.util.PackageChooserDialog;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AddRef extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane errorTextPane;
    private JTextField addressUrlTextField;
    private TextFieldWithBrowseButton packageBrowse;
    private JTextField nameTextField;
    private JTextPane infoTextPane;
    private Module module;

    public AddRef(@Nullable Module module) {
        this.module = module;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        ImageIcon imageIcon = createImageIcon("/icons/logo-16.png","ServiceStack");
        if(imageIcon != null) {
            this.setIconImage(imageIcon.getImage());
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        packageBrowse.addActionListener(new BrowsePackageListener(packageBrowse, module.getProject(), "Browse packages"));

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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

    private void onOK() throws IOException {
        GradleBuildFileHelper gradleBuildFileHelper = new GradleBuildFileHelper(this.module);
        gradleBuildFileHelper.addDependency("com.google.code.gson", "gson", "2.3.1");
        refreshBuildFile();

        try
        {
            if (!ValidateEndpoint()) return;
        } catch (IOException exception) {
            this.errorTextPane.setText("Error has occurred updating build.gradle file - " + exception.getLocalizedMessage());
            return;
        }


        String url = createUrl(addressUrlTextField.getText());
        URL serviceUrl = new URL(url);
        URLConnection javaResponseConnection = serviceUrl.openConnection();
        BufferedReader javaResponseReader = new BufferedReader(
                new InputStreamReader(
                        javaResponseConnection.getInputStream()));
        String metadataInputLine;
        List<String> javaCodeLines = new ArrayList<>();
        while ((metadataInputLine = javaResponseReader.readLine()) != null)
            javaCodeLines.add(metadataInputLine);

        javaResponseReader.close();

        String dtoPath = getDtoPath();
        if(!writeDtoFile(javaCodeLines,dtoPath)) {
            return;
        }
        dispose();
    }

    private boolean writeDtoFile(List<String> javaCode, String path) {
        BufferedWriter writer = null;
        boolean result = true;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path), "utf-8"));
            for(String item : javaCode) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException ex) {
            result = false;
            errorTextPane.setText("Error writing DTOs to file - " + ex.getLocalizedMessage());
        } finally {
            try {
                assert writer != null;
                writer.close();} catch (Exception ignored) {}
        }
        return result;
    }

    private String getDtoPath() {
        String moduleSourcePath = module.getModuleFile().getParent().getPath() + "/src/main/java";
        String packagePath = packageBrowse.getText().replace(".", "/");
        File assumedPackageDirectory = new File(moduleSourcePath + "/" + packagePath);
        String fullDtoPath;
        if(assumedPackageDirectory.exists()) {
            File dtoFile = new File(assumedPackageDirectory.getPath() + "/" + nameTextField.getText());
            fullDtoPath = dtoFile.getPath();
        } else {
            File dtoFile = new File(moduleSourcePath + "/" + nameTextField.getText());
            fullDtoPath = dtoFile.getPath();
        }
        return fullDtoPath;
    }

    private boolean ValidateEndpoint() throws IOException {
        String typeMetadataUrl = createUrl(addressUrlTextField.getText()).replace("types/java/", "types/metadata/?format=json");
        URL metadataUrl = new URL(typeMetadataUrl);
        URLConnection metadataConnection = metadataUrl.openConnection();
        metadataConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
        BufferedReader metadataBufferReader = new BufferedReader(
                new InputStreamReader(
                        metadataConnection.getInputStream()));
        String metadataInputLine;
        StringBuilder metadataResponse = new StringBuilder();
        while ((metadataInputLine = metadataBufferReader.readLine()) != null)
            metadataResponse.append(metadataInputLine);

        metadataBufferReader.close();
        String metadataJson = metadataResponse.toString();
        Gson gson = new Gson();
        ServiceStackMetadata metadata = gson.fromJson(metadataJson, ServiceStackMetadata.class);
        if(metadata == null || metadata.getConfig() == null || metadata.getConfig().getBaseUrl() == null) {
            errorTextPane.setText("The address url is not a valid ServiceStack endpoint.");
            return false;
        }
        return true;
    }

    private void refreshBuildFile() {
        VirtualFileManager.getInstance().syncRefresh();
        VirtualFile fileByUrl = VirtualFileManager.getInstance().findFileByUrl(module.getModuleFile().getParent().getUrl() + "/build.gradle");

        FileEditorManager.getInstance(module.getProject()).openFile(fileByUrl,false);
        Document document = FileEditorManager.getInstance(module.getProject()).getSelectedTextEditor().getDocument();

        FileDocumentManager.getInstance().reloadFromDisk(document);
        VirtualFileManager.getInstance().syncRefresh();
    }

    private void refreshFile(String filePath, boolean openFile) {
        VirtualFileManager.getInstance().syncRefresh();
        VirtualFile fileByUrl = VirtualFileManager.getInstance().findFileByUrl(filePath);

        FileEditorManager.getInstance(module.getProject()).openFile(fileByUrl,false);
        Document document = FileEditorManager.getInstance(module.getProject()).getSelectedTextEditor().getDocument();

        if(!openFile) FileEditorManager.getInstance(module.getProject()).closeFile(fileByUrl);

        FileDocumentManager.getInstance().reloadFromDisk(document);
        VirtualFileManager.getInstance().syncRefresh();
    }

    private static class BrowsePackageListener implements ActionListener {
        private TextFieldWithBrowseButton _textField;
        private Project _project;
        private String _title;

        public BrowsePackageListener(TextFieldWithBrowseButton textField, Project project, String title) {
            _textField = textField;
            _project = project;
            _title = title;
        }

        public void actionPerformed(ActionEvent e) {
            PackageChooserDialog dialog = new PackageChooserDialog(_title,_project);
            dialog.selectPackage(_textField.getText());
            dialog.show();

            if (dialog.getExitCode() == PackageChooserDialog.CANCEL_EXIT_CODE) {
                return;
            }

            _textField.setText(dialog.getSelectedPackage().getQualifiedName());
        }
    }

    private String createUrl(String text) throws MalformedURLException {
        String serverUrl = text.endsWith("/") ? text : (text + "/");
        serverUrl = (serverUrl.startsWith("http://") || serverUrl.startsWith("https://")) ? serverUrl : ("http://" + serverUrl);
        URL url = new URL(serverUrl);
        String path = url.getPath().contains("?") ? url.getPath().split("\\?", 2)[0] : url.getPath();
        if (!path.endsWith("types/java/"))
        {
            serverUrl += "types/java/";
        }

        if(packageBrowse.getText() != null && !packageBrowse.getText().isEmpty()) {
            try {
                URIBuilder builder = new URIBuilder(serverUrl);
                builder.addParameter("Package",packageBrowse.getText());
                serverUrl = builder.build().toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return serverUrl;
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddRef dialog = new AddRef(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
