package net.servicestack.idea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Layoric on 27/05/2016.
 */
public final class Analytics {

    private static String addRefUrl = "https://servicestack.net/stats/addref/record?Name=";

    public static void SubmitAnonymousAddReferenceUsage(INativeTypesHandler typesHandler) {
        PluginSettingsService settings = PluginSettingsService.getInstance();
        if(!settings.optOutOfStats) {
            String url = addRefUrl + typesHandler.getTypesLanguage().name();
            URL serviceUrl = null;
            URLConnection javaResponseConnection = null;
            BufferedReader javaResponseReader = null;
            try {
                serviceUrl = new URL(url);
                javaResponseConnection = serviceUrl.openConnection();
                javaResponseReader = new BufferedReader(
                        new InputStreamReader(
                                javaResponseConnection.getInputStream()));

                javaResponseReader.close();
            } catch (IOException e) {
                // Ignore failure (eg no internet connection).
            }
        }
    }
}
