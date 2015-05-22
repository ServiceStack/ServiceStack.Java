package net.servicestack.eclipse.nativetypes;

import java.io.IOException;
import java.util.Map;

public interface INativeTypesHandler {
    Map<String, String> parseComments(String codeOutput);
    String generateUrl(String baseUrl, Map<String, String> options);
    String getUpdatedCode(String baseUrl, Map<String, String> options) throws IOException;
    NativeTypesLanguage getTypesLanguage();
    boolean isFileAServiceStackReference(String fileContents);
    String getRelativeTypesUrl();
    boolean validateServiceStackEndpoint(String baseUrl) throws IOException;
} 