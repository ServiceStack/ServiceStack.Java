package net.servicestack.idea;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by Layoric on 9/04/2015.
 * Helper methods to handle Update Reference intention.
 */
public class UpdateServiceStackUtils {

    public static void updateServiceStackReference(PsiFile psiFile) {
        String code = psiFile.getText();
        Scanner scanner = new Scanner(code);
        List<String> linesOfCode = new ArrayList<String>();
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

        URIBuilder builder;
        try {
            builder = new URIBuilder(baseUrl);
        } catch (URISyntaxException e) {
            //Log error to IDEA warning bubble/window.
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Invalid BaseUrl provided", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return;
        }

        INativeTypesHandler nativeTypesHandler = IDEAUtils.getNativeTypesHandler(psiFile.getName());

        String existingPath = builder.getPath();
        if(existingPath == null || existingPath.equals("/")) {
            builder.setPath("/" + nativeTypesHandler.getRelativeTypesUrl());
        } else {
            builder.setPath(existingPath + "/" + nativeTypesHandler.getRelativeTypesUrl());
        }

        Map<String,String> options = new HashMap<String,String>();
        for(int i = startParamsIndex; i < linesOfCode.size(); i++) {
            String configLine = linesOfCode.get(i);
            if(!configLine.startsWith("//") && configLine.contains(":")) {
                String[] keyVal = configLine.split(":");
                options.put(keyVal[0], keyVal[1].trim());
            }
        }

        try {
            String serverUrl = builder.build().toString();
            int count = 0;
            // Using URIBuilder with 'addParameter' URL encodes query values..
            // Append manually below to avoid issues https://github.com/ServiceStack/ServiceStack.Java/issues/6
            for(Map.Entry<String,String> option : options.entrySet()) {
                if(count == 0) {
                    serverUrl += "?";
                } else {
                    serverUrl += "&";
                }
                //remove spaces
                serverUrl += option.getKey() + "=" + option.getValue().trim().replaceAll("\\u0020","");
                count++;
            }
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

            String javaCode = javaCodeResponse.toString();
            if(!javaCode.startsWith("/* Options:")) {
                Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", "Invalid response from provided BaseUrl - " + baseUrl, NotificationType.ERROR);
                Notifications.Bus.notify(notification);
                return;
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
            Notification notification = new Notification("ServiceStackIDEA", "Error Updating Reference", e.getMessage(), NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            e.printStackTrace();
        }
    }

    public static boolean containsOptionsHeader(PsiFile psiJavaFile) {
        Document dtoDocument = FileDocumentManager.getInstance().getDocument(psiJavaFile.getVirtualFile());
        if(dtoDocument == null) {
            return false;
        }
        //Only pull in the first 1000 chars max to look for header.
        int range = dtoDocument.getTextLength() > 1000 ? 1000 : dtoDocument.getTextLength();
        String code = dtoDocument.getText(new TextRange(0, range));

        String[] codeLines = code.split("\n");
        for(String line : codeLines) {
            if(line.startsWith("BaseUrl:")) {
                return true;
            }
        }
        return false;
    }
}
