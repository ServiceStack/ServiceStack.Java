package net.servicestack.android;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import net.servicestack.client.AsyncError;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.AsyncResultVoid;
import net.servicestack.client.AsyncSuccess;
import net.servicestack.client.AsyncSuccessVoid;
import net.servicestack.client.Utils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mythz on 2/15/2017.
 */

public class AsyncUtils {

    public static <T> AsyncResult<T> createAsyncResult(final AsyncSuccess<T> success, final AsyncError error){
        return new AsyncResult<T>() {
            @Override
            public void success(T response) {
                success.success(response);
            }
            @Override
            public void error(Exception ex) {
                if (error != null)
                    error.error(ex);
            }
        };
    }

    public static AsyncResultVoid createAsyncResult(final AsyncSuccessVoid success, final AsyncError error){
        return new AsyncResultVoid() {
            @Override
            public void success() {
                success.success();
            }
            @Override
            public void error(Exception ex) {
                if (error != null)
                    error.error(ex);
            }
        };
    }

    public static void readBitmap(final String url, final AsyncSuccess<Bitmap> success){
        readBitmap(url, createAsyncResult(success, null));
    }

    public static void readBitmap(final String url, final AsyncSuccess<Bitmap> success, AsyncError error){
        readBitmap(url, createAsyncResult(success, error));
    }

    private static void readBitmap(final String imgUrl, final AsyncResult<Bitmap> asyncResult){
        new AsyncTask<String, Void, Bitmap>(){

            @Override
            protected Bitmap doInBackground(String... urls) {
                HttpURLConnection conn = null;
                String url = urls[0];
                try {
                    while (!Utils.isNullOrEmpty(url)){
                        conn = (HttpURLConnection)new URL(url).openConnection();
                        conn.setInstanceFollowRedirects(true);

                        switch (conn.getResponseCode()){
                            case HttpURLConnection.HTTP_MOVED_PERM:
                            case HttpURLConnection.HTTP_MOVED_TEMP:
                                url = conn.getHeaderField("Location");
                                if (!Utils.isNullOrEmpty(url))
                                    continue;
                        }

                        Bitmap ret = AndroidUtils.readBitmap(conn.getInputStream());
                        return ret;
                    }

                    asyncResult.setError(new RuntimeException("Could not download Bitmap from: " + urls[0]));
                    return null;
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                asyncResult.completeResult(bitmap);
            }
        }.execute(imgUrl);
    }
}
