package net.servicestack.idea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Helper to submit anonymous add reference usage.
 */
public final class Analytics {

    private final static String addRefUrl = "https://servicestack.net/stats/addref/record?Name=";

    public static void SubmitAnonymousAddReferenceUsage(INativeTypesHandler typesHandler) {
        PluginSettingsService settings = PluginSettingsService.getInstance();
        if(!settings.optOutOfStats) {
            final String url = addRefUrl + typesHandler.getTypesLanguage().name();
            final URL[] serviceUrl = {null};
            final URLConnection[] responseConnection = {null};
            final BufferedReader[] responseReader = {null};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serviceUrl[0] = new URL(url);
                        responseConnection[0] = serviceUrl[0].openConnection();
                        responseReader[0] = new BufferedReader(
                                new InputStreamReader(
                                        responseConnection[0].getInputStream()));

                        responseReader[0].close();
                    } catch (IOException e) {
                        // Ignore failure (eg no internet connection).
                    }
                }
            });
            thread.start();

        }
    }
}
