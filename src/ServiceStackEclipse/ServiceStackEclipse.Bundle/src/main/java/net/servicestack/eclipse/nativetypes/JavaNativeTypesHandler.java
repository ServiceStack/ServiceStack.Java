package net.servicestack.eclipse.nativetypes;

import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class JavaNativeTypesHandler implements INativeTypesHandler {

    public Map<String, String> parseComments(String codeOutput) {
        Map<String, String> result = new HashMap<>();
        Scanner scanner = new Scanner(codeOutput);
        List<String> linesOfCode = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            linesOfCode.add(line);
            if(line.trim().startsWith("*/")) break;
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
        	throw new IllegalArgumentException("Missing BaseURL value");
        }
        if(!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        result.put("BaseUrl", baseUrl);
        for(int i = startParamsIndex; i < linesOfCode.size(); i++) {
            String configLine = linesOfCode.get(i);
            if(!configLine.startsWith("//") && configLine.contains(":")) {
                String[] keyVal = configLine.split(":");
                result.put(keyVal[0],keyVal[1].trim());
            }
        }

        
        return result;
    }


    public String generateUrl(String baseUrl, Map<String, String> options) {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        
        baseUrl = (baseUrl.startsWith("http://") || baseUrl.startsWith("https://")) ? baseUrl : ("http://" + baseUrl);
        URL url;
		try {
			url = new URL(baseUrl);
		} catch (MalformedURLException e1) {

			e1.printStackTrace();
			return null;
		}
        
        URIBuilder builder;
        try {
            builder = new URIBuilder(baseUrl);
        } catch (URISyntaxException e) {
            return null;
        }

        String existingPath = builder.getPath();
        if (existingPath == null || existingPath.equals("/")) {
            builder.setPath("/types/java");
        } else {
            builder.setPath(existingPath + "/types/java");
        }
        if(options != null) {
            for (Map.Entry<String, String> item : options.entrySet()) {
                builder.addParameter(item.getKey(), item.getValue().trim());
            }
        }
        try {
            return builder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getUpdatedCode(String baseUrl, Map<String, String> options) throws IOException {
        String result;
        String url = generateUrl(baseUrl, options);
        URL javaCodeUrl = new URL(url);

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
        if (!javaCode.trim().startsWith("/* Options:")) {
            return null;
        }
        result = javaCode;
        return result;
    }


    public NativeTypesLanguage getTypesLanguage() {
        return NativeTypesLanguage.Java;
    }

    @Override
    public boolean validateServiceStackEndpoint(String baseUrl) throws IOException {
        String javaCode = getUpdatedCode(baseUrl, null);
        return javaCode != null && isFileAServiceStackReference(javaCode);
    }

    public boolean isFileAServiceStackReference(String fileContents) {
        return fileContents.trim().startsWith("/* Options:");
    }


    public String getRelativeTypesUrl() {
        return "types/java";
    }
}