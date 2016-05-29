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
                         Boolean definitionsOnly,
                         StringBuilder errorMessage) {
        File file = new File(selectedDirectory);
        INativeTypesHandler nativeTypesHandler =
                definitionsOnly ?
                        new TypeScriptDefinitionNativeTypesHandler() :
                        new TypeScriptNativeTypesHandler();

        String dtoPath = file.getAbsolutePath() + File.separator
                + getDtoFileName(fileName,nativeTypesHandler);
        List<String> codeLines = getDtoLines(addressUrl,nativeTypesHandler,errorMessage);

        if (!IDEAUtils.writeDtoFile(codeLines, dtoPath, errorMessage)) {
            return;
        }

        Analytics.SubmitAnonymousAddReferenceUsage(nativeTypesHandler);
        refreshFile(module,dtoPath, true);
        VirtualFileManager.getInstance().syncRefresh();
    }

    private static List<String> getDtoLines(String addressUrl, INativeTypesHandler nativeTypesHandler,
                                            StringBuilder errorMessage) {
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

    public static String getDtoFileName(String name, INativeTypesHandler nativeTypesHandler) {
        if (!name.endsWith(nativeTypesHandler.getFileExtension())) {
            /* file has no extension */
            return name + nativeTypesHandler.getFileExtension();
        } else {
            /* file has extension */
            return name;
        }
    }
}
