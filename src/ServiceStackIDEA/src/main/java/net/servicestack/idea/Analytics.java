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
            final String url = addRefUrl + typesHandler.getTypesLanguage().name();
            final URL[] serviceUrl = {null};
            final URLConnection[] javaResponseConnection = {null};
            final BufferedReader[] javaResponseReader = {null};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serviceUrl[0] = new URL(url);
                        javaResponseConnection[0] = serviceUrl[0].openConnection();
                        javaResponseReader[0] = new BufferedReader(
                                new InputStreamReader(
                                        javaResponseConnection[0].getInputStream()));

                        javaResponseReader[0].close();
                    } catch (IOException e) {
                        // Ignore failure (eg no internet connection).
                    }
                }
            });
            thread.start();

        }
    }
}
