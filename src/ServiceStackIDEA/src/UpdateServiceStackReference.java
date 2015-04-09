import com.intellij.codeInsight.intention.impl.QuickEditAction;
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
            //throw error
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
            return;
        }
        builder.setPath("/types/java");
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
            }
        } catch (Exception e) {
            //Log with IDEA bubble
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
