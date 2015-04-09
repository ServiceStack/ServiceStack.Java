import com.google.gson.Gson;
import com.intellij.codeInsight.intention.impl.QuickEditAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.util.IncorrectOperationException;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UpdateServiceStackReference extends QuickEditAction implements Iconable {

    @Override
    public String getText() {
        return "Update ServiceStack reference";
    }

    @Override
    public String getFamilyName() {
        return "UpdateServiceStackReference";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        try {
            if(psiFile == null || !(psiFile instanceof PsiJavaFile)) {
                return false;
            }
            PsiJavaFile classFile = (PsiJavaFile)psiFile;
            if(containsOptionsHeader(classFile)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean containsOptionsHeader(PsiJavaFile psiJavaFile) {
        Document dtoDocument = FileDocumentManager.getInstance().getDocument(psiJavaFile.getVirtualFile());
        if(dtoDocument == null) {
            return false;
        }
        //Only pull in the first 1000 chars max to look for header.
        int range = dtoDocument.getTextLength() > 1000 ? 1000 : dtoDocument.getTextLength();
        String code = dtoDocument.getText(new TextRange(0,range));

        String[] codeLines = code.split("\n");
        for(String line : codeLines) {
            if(line.startsWith("BaseUrl:")) {
                return true;
            }
        }
        return false;
    }

    private boolean validateEndPoint(String url) {

        URL metadataUrl = null;
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(url.replace("types/java","types/metadata"));
            builder.addParameter("format","json");
            metadataUrl = new URL(builder.build().toString());
        } catch (URISyntaxException e) {
            //Log error to IDEA warning bubble/window.
            e.printStackTrace();
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Invalid BaseUrl provided", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Invalid BaseUrl provided", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return false;
        }

        URLConnection metadataConnection = null;
        String errorMessage = null;
        try {
            metadataConnection = metadataUrl.openConnection();
        } catch (IOException e) {
            errorMessage = "Problem connecting to BaseUrl - " + e.getMessage();
        }
        metadataConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
        BufferedReader metadataBufferReader = null;
        try {
            metadataBufferReader = new BufferedReader(
                    new InputStreamReader(
                            metadataConnection.getInputStream()));
        } catch (IOException e) {
            errorMessage = "Problem connecting to BaseUrl - " + e.getMessage();
        }
        String metadataInputLine;
        StringBuilder metadataResponse = new StringBuilder();
        try {
            while ((metadataInputLine = metadataBufferReader.readLine()) != null)
                metadataResponse.append(metadataInputLine);

            metadataBufferReader.close();
        } catch (IOException e) {
            errorMessage = "Invalid response, check the BaseUrl is a valid ServiceStack endpoint - " + e.getMessage();
        }


        String metadataJson = metadataResponse.toString();
        Gson gson = new Gson();
        try {
            ServiceStackMetadata metadata = gson.fromJson(metadataJson, ServiceStackMetadata.class);
            if (metadata == null || metadata.getConfig() == null || metadata.getConfig().getBaseUrl() == null) {
                errorMessage = "The address url is not a valid ServiceStack endpoint.";
            }
        } catch (Exception e) {
            errorMessage = "The address url is not a valid ServiceStack endpoint.";
        }

        if(errorMessage != null) {
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", errorMessage, NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return false;
        }

        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        String code = psiFile.getText();
        Scanner scanner = new Scanner(code);
        List<String> linesOfCode = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            linesOfCode.add(line);
            if(line.startsWith("*/")) break;
        }
        scanner.close();

        int startParamsIndex = 0;
        String baseUrl = null;
        for(String item : linesOfCode) {
            startParamsIndex++;
            if(item.startsWith("BaseUrl:")) {
                baseUrl = item.split(":",2)[1].trim();
                break;
            }
        }
        if(baseUrl == null) {
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "BaseUrl property not found.", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return;
        }
        if(!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(baseUrl);
        } catch (URISyntaxException e) {
            //Log error to IDEA warning bubble/window.
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Invalid BaseUrl provided", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return;
        }

        String existingPath = builder.getPath();
        if(existingPath == null || existingPath.equals("/")) {
            builder.setPath("/types/java");
        } else {
            builder.setPath(existingPath + "/types/java");
        }


        try {
            if(!validateEndPoint(builder.build().toString())) {
                return;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Unable to parse BaseUrl", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return;
        }

        for(int i = startParamsIndex; i < linesOfCode.size(); i++) {
            String configLine = linesOfCode.get(i);
            if(!configLine.startsWith("//") && configLine.contains(":")) {
                String[] keyVal = configLine.split(":");
                builder.addParameter(keyVal[0],keyVal[1].trim());
            }
        }

        try {
            String serverUrl = builder.build().toString();
            URL javaCodeUrl = new URL(serverUrl);

            URLConnection javaCodeConnection = javaCodeUrl.openConnection();
            javaCodeConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
            BufferedReader javaCodeBufferReader = new BufferedReader(
                    new InputStreamReader(
                            javaCodeConnection.getInputStream()));
            String javaCodeInput;
            StringBuilder javaCodeResponse = new StringBuilder();
            while ((javaCodeInput = javaCodeBufferReader.readLine()) != null) {
                javaCodeResponse.append(javaCodeInput);
                //All documents inside IntelliJ IDEA always use \n line separators.
                //http://confluence.jetbrains.net/display/IDEADEV/IntelliJ+IDEA+Architectural+Overview
                javaCodeResponse.append("\n");
            }

            Document document = FileDocumentManager.getInstance().getDocument(psiFile.getVirtualFile());
            if (document != null) {
                document.setText(javaCodeResponse);
            } else {
                //Show error
                Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "DTO file not found.", NotificationType.ERROR);
                Notifications.Bus.notify(notification);
            }
        } catch (Exception e) {
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Invalid BaseUrl provided", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            e.printStackTrace();
        }
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }

    @Override
    public Icon getIcon(@IconFlags int i) {
        return new ImageIcon(this.getClass().getResource("/icons/logo-16.png"));
    }
}
