package net.servicestack.idea;

import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Created by Layoric on 4/12/2015.
 */
public interface INativeTypesHandler {
    String getFileExtension();
    URIBuilder getUrl(String baseUrl) throws MalformedURLException, URISyntaxException;
    List<String> getUpdatedCode(String baseUrl, Map<String, String> options) throws IOException, URISyntaxException;
    String getRelativeTypesUrl();
    NativeTypesLanguage getTypesLanguage();
    String getLanguageUrlName();
}
