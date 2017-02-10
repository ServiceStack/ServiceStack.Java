package net.servicestack.client.sse;

import java.net.HttpURLConnection;

/**
 * Created by mythz on 2/10/2017.
 */

public interface HttpRequestFilter {
    void execute(HttpURLConnection req);
}
