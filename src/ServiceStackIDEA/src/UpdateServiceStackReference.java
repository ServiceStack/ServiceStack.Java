import com.intellij.codeInsight.intention.impl.QuickEditAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.util.IncorrectOperationException;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.BufferedReader;
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
            PsiJavaFile classFile = (PsiJavaFile)psiFile;
            String className= classFile.getClasses()[0].getName();
            if(className.equals("dto")){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
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

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(baseUrl);
        } catch (URISyntaxException e) {
            //Log error to IDEA warning bubble/window.
            return;
        }

        for(int i = startParamsIndex; i < linesOfCode.size(); i++) {
            String configLine = linesOfCode.get(i);
            if(!configLine.startsWith("//") && configLine.contains(":")) {
                String[] keyVal = configLine.split(":");
                builder.addParameter(keyVal[0],keyVal[1].trim());
            }
        }

        String serverUrl = builder.toString();

//        URL metadataUrl = new URL(serverUrl);
//        URLConnection metadataConnection = metadataUrl.openConnection();
//        metadataConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
//        BufferedReader metadataBufferReader = new BufferedReader(
//                new InputStreamReader(
//                        metadataConnection.getInputStream()));
//        String metadataInputLine;
//        StringBuilder metadataResponse = new StringBuilder();
//        while ((metadataInputLine = metadataBufferReader.readLine()) != null)
//            metadataResponse.append(metadataInputLine);

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
