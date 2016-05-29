package net.servicestack.idea;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.EnvironmentUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import static net.servicestack.idea.IDEAUtils.getNativeTypesHandler;
import static net.servicestack.idea.IDEAUtils.refreshFile;

public class AddTypeScriptRefHandler {

    static void handleOk(Module module,
                         String addressUrl,
                         String fileName,
                         String selectedDirectory,
                         StringBuilder errorMessage) {

        List<String> codeLines = getDtoLines(addressUrl,fileName,errorMessage);
        if (!IDEAUtils.writeDtoFile(codeLines, selectedDirectory, errorMessage)) {
            return;
        }
        File file = new File(selectedDirectory);
        String dtoPath = file.getAbsolutePath() + "/" + getDtoFileName(fileName);
        Analytics.SubmitAnonymousAddReferenceUsage(getNativeTypesHandler(fileName));
        refreshFile(module,dtoPath, true);
        VirtualFileManager.getInstance().syncRefresh();
    }

    private static List<String> getDtoLines(String addressUrl, String fileName,
                                            StringBuilder errorMessage) {
        INativeTypesHandler nativeTypesHandler = getNativeTypesHandler(fileName);
        try {
            return nativeTypesHandler.getUpdatedCode(addressUrl, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            DialogErrorMessages.appendInvalidEnpoint(errorMessage, addressUrl, e);
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            DialogErrorMessages.appendInvalidEnpoint(errorMessage, addressUrl, e);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            DialogErrorMessages.appendReadResponseError(errorMessage, addressUrl, e);
            return null;
        }
    }

    public static String getDtoFileName(String name) {
        INativeTypesHandler nativeTypesHandler = getNativeTypesHandler(name);
        int p = name.lastIndexOf(".");
        String e = name.substring(p);
        if (p == -1 || !e.equals(nativeTypesHandler.getFileExtension())) {
            /* file has no extension */
            return name + nativeTypesHandler.getFileExtension();
        } else {
            /* file has extension e */
            return name;
        }
    }
}
