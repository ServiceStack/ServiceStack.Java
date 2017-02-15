package net.servicestack.android;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import net.servicestack.client.AsyncResult;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mythz on 2/15/2017.
 */

public class AsyncUtils {

    public static void readBitmap(final String url, final AsyncResult<Bitmap> asyncResult){
        new AsyncTask<String, Void, Bitmap>(){

            @Override
            protected Bitmap doInBackground(String... url) {
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection)new URL(url[0]).openConnection();
                    return AndroidUtils.readBitmap(conn.getInputStream());
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                asyncResult.setResult(bitmap);
            }
        };
    }
}
