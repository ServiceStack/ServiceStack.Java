//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.android;

import android.content.Context;
import android.os.AsyncTask;

import net.servicestack.client.AsyncError;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.AsyncResultVoid;
import net.servicestack.client.AsyncServiceClient;
import net.servicestack.client.AsyncSuccess;
import net.servicestack.client.AsyncSuccessVoid;
import net.servicestack.client.IReturn;
import net.servicestack.client.IReturnVoid;
import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.Utils;
import net.servicestack.cookies.SerializableCookieStore;

import java.lang.reflect.Type;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.util.Map;

public class AndroidServiceClient extends JsonServiceClient implements AsyncServiceClient {

    Context context;

    public AndroidServiceClient(String baseUrl) {
        super(baseUrl);
    }

    public AndroidServiceClient(String baseUrl, Context context) {
        super(baseUrl, false);
        this.context = context;
        this.initCookieHandler();
    }

    @Override
    public void initCookieHandler() {
        //Automatically populate response cookies
        if (CookieHandler.getDefault() == null) {
            if (this.context != null) {
                SerializableCookieStore cookieStore = new SerializableCookieStore(context);
                CookieManager cookieManager = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);
                CookieHandler.setDefault(cookieManager);
            } else {
                //Doesn't support getCookieStore() throws UnsupportedOperationException
                CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
                CookieHandler.setDefault(cookieManager);
            }
        }
    }

    // Override to customize execution of AsyncTask
    public <T,TResponse> void execTask(AsyncTask<T,Void,TResponse> asyncTask, T... request){
        asyncTask.execute(request);
    }

    <T> AsyncResult<T> createAsyncResult(final AsyncSuccess<T> success, final AsyncError error){
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

    AsyncResultVoid createAsyncResultVoid(final AsyncSuccessVoid success, final AsyncError error){
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

    /* SEND */

    @Override
    public <T> void sendAsync(IReturn<T> request, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.send(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void sendAsync(IReturn<T> request, AsyncSuccess<T> success) {
        sendAsync(request, success, null);
    }

    @Override
    public <T> void sendAsync(IReturn<T> request, AsyncSuccess<T> success, AsyncError error) {
        sendAsync(request, createAsyncResult(success, error));
    }

    @Override
    public void sendAsync(IReturnVoid request, final AsyncResultVoid asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturnVoid, Void, Void>() {
            @Override
            protected Void doInBackground(IReturnVoid... params) {
                try {
                    client.send(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void noResponse) {
                asyncResult.completeResult();
            }

        }, request);
    }

    @Override
    public void sendAsync(IReturnVoid request, AsyncSuccessVoid success) {
        sendAsync(request, success, null);
    }

    @Override
    public void sendAsync(IReturnVoid request, AsyncSuccessVoid success, AsyncError error) {
        sendAsync(request, createAsyncResultVoid(success, error));
    }

    /* GET */
    @Override
    public <T> void getAsync(IReturn<T> request, final AsyncResult<T> asyncResult){
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.get(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void getAsync(IReturn<T> request, final AsyncSuccess<T> successCallback) {
        getAsync(request, successCallback, null);
    }

    @Override
    public <T> void getAsync(IReturn<T> request, final AsyncSuccess<T> success, final AsyncError error){
        getAsync(request, createAsyncResult(success, error));
    }

    @Override
    public void getAsync(IReturnVoid request, final AsyncResultVoid asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturnVoid, Void, Void>() {
            @Override
            protected Void doInBackground(IReturnVoid... params) {
                try {
                    client.get(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void noResponse) {
                asyncResult.completeResult();
            }

        }, request);
    }

    @Override
    public void getAsync(IReturnVoid request, final AsyncSuccessVoid success) {
        getAsync(request, success, null);
    }

    @Override
    public void getAsync(IReturnVoid request, final AsyncSuccessVoid success, final AsyncError error) {
        getAsync(request, createAsyncResultVoid(success, error));
    }

    @Override
    public <T> void getAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResult<T> asyncResult){
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.get(params[0], queryParams);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void getAsync(IReturn<T> request, Map<String, String> queryParams, AsyncSuccess<T> success) {
        getAsync(request, queryParams, createAsyncResult(success, null));
    }

    @Override
    public <T> void getAsync(String path, final Class responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.get(params[0], responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void getAsync(String path, Class responseType, AsyncSuccess<T> success) {
        getAsync(path, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void getAsync(String path, final Type responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.get(params[0], responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void getAsync(String path, Type responseType, AsyncSuccess<T> success) {
        getAsync(path, responseType, createAsyncResult(success, null));
    }

    @Override
    public void getAsync(String path, final AsyncResult<byte[]> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(String... params) {
                try {
                    HttpURLConnection httpRes = client.get(params[0]);
                    return Utils.readBytesToEnd(httpRes);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(byte[] bytes) {
                asyncResult.completeResult(bytes);
            }
        }, path);
    }

    @Override
    public void getAsync(String path, AsyncSuccess<byte[]> success) {
        getAsync(path, createAsyncResult(success, null));
    }

    /* POST */

    @Override
    public <T> void postAsync(IReturn<T> request, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.post(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void postAsync(IReturn<T> request, AsyncSuccess<T> success) {
        postAsync(request, success, null);
    }

    @Override
    public <T> void postAsync(IReturn<T> request, AsyncSuccess<T> success, AsyncError error) {
        postAsync(request, createAsyncResult(success, error));
    }

    @Override
    public void postAsync(IReturnVoid request, final AsyncResultVoid asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturnVoid, Void, Void>() {
            @Override
            protected Void doInBackground(IReturnVoid... params) {
                try {
                    client.post(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void noResponse) {
                asyncResult.completeResult();
            }

        }, request);
    }

    @Override
    public void postAsync(IReturnVoid request, AsyncSuccessVoid success) {
        postAsync(request, success, null);
    }

    @Override
    public void postAsync(IReturnVoid request, AsyncSuccessVoid success, AsyncError error) {
        postAsync(request, createAsyncResultVoid(success, error));
    }

    @Override
    public <T> void postAsync(String path, final Object request, final Class responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.post(params[0], request, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void postAsync(String path, Object request, Class responseType, AsyncSuccess<T> success) {
        postAsync(path, request, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void postAsync(String path, final Object request, final Type responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.post(params[0], request, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void postAsync(String path, Object request, Type responseType, AsyncSuccess<T> success) {
        postAsync(path, request, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void postAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.post(params[0], requestBody, contentType, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void postAsync(String path, byte[] requestBody, String contentType, Class responseType, AsyncSuccess<T> success) {
        postAsync(path, requestBody, contentType, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void postAsync(String path, final byte[] requestBody, final String contentType, final Type responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.post(params[0], requestBody, contentType, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void postAsync(String path, byte[] requestBody, String contentType, Type responseType, AsyncSuccess<T> success) {
        postAsync(path, requestBody, contentType, responseType, createAsyncResult(success, null));
    }

    @Override
    public void postAsync(String path, final byte[] requestBody, final String contentType, final AsyncResult<byte[]> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(String... params) {
                try {
                    HttpURLConnection httpRes = client.post(params[0], requestBody, contentType);
                    return Utils.readBytesToEnd(httpRes);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(byte[] bytes) {
                asyncResult.completeResult(bytes);
            }

        }, path);
    }

    @Override
    public void postAsync(String path, byte[] requestBody, String contentType, AsyncSuccess<byte[]> success) {
        postAsync(path, requestBody, contentType, createAsyncResult(success, null));
    }

    /* PUT */

    @Override
    public <T> void putAsync(IReturn<T> request, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.put(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void putAsync(IReturn<T> request, AsyncSuccess<T> success) {
        putAsync(request, success, null);
    }

    @Override
    public <T> void putAsync(IReturn<T> request, AsyncSuccess<T> success, AsyncError error) {
        putAsync(request, createAsyncResult(success, error));

    }

    @Override
    public void putAsync(IReturnVoid request, final AsyncResultVoid asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturnVoid, Void, Void>() {
            @Override
            protected Void doInBackground(IReturnVoid... params) {
                try {
                    client.put(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void noResponse) {
                asyncResult.completeResult();
            }

        }, request);
    }

    @Override
    public void putAsync(IReturnVoid request, AsyncSuccessVoid success) {
        putAsync(request, success, null);
    }

    @Override
    public void putAsync(IReturnVoid request, AsyncSuccessVoid success, AsyncError error) {
        putAsync(request, createAsyncResultVoid(success, error));
    }

    @Override
    public <T> void putAsync(String path, final Object request, final Class responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.put(params[0], request, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void putAsync(String path, Object request, Class responseType, AsyncSuccess<T> success) {
        putAsync(path, request, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void putAsync(String path, final Object request, final Type responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.put(params[0], request, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void putAsync(String path, Object request, Type responseType, AsyncSuccess<T> success) {
        putAsync(path, request, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void putAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.put(params[0], requestBody, contentType, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void putAsync(String path, byte[] requestBody, String contentType, Class responseType, AsyncSuccess<T> success) {
        putAsync(path, requestBody, contentType, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void putAsync(String path, final byte[] requestBody, final String contentType, final Type responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.put(params[0], requestBody, contentType, responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void putAsync(String path, byte[] requestBody, String contentType, Type responseType, AsyncSuccess<T> success) {
        putAsync(path, requestBody, contentType, responseType, createAsyncResult(success, null));
    }

    @Override
    public void putAsync(String path, final byte[] requestBody, final String contentType, final AsyncResult<byte[]> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(String... params) {
                try {
                    HttpURLConnection httpRes = client.put(params[0], requestBody, contentType);
                    return Utils.readBytesToEnd(httpRes);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(byte[] bytes) {
                asyncResult.completeResult(bytes);
            }

        }, path);
    }

    @Override
    public void putAsync(String path, byte[] requestBody, String contentType, AsyncSuccess<byte[]> success) {
        putAsync(path, requestBody, contentType, createAsyncResult(success, null));
    }

    /* DELETE */

    @Override
    public <T> void deleteAsync(IReturn<T> request, final AsyncResult<T> asyncResult){
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.delete(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void deleteAsync(IReturn<T> request, AsyncSuccess<T> success) {
        deleteAsync(request, success, null);
    }

    @Override
    public <T> void deleteAsync(IReturn<T> request, AsyncSuccess<T> success, AsyncError error) {
        deleteAsync(request, createAsyncResult(success, error));
    }

    @Override
    public void deleteAsync(IReturnVoid request, final AsyncResultVoid asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturnVoid, Void, Void>() {
            @Override
            protected Void doInBackground(IReturnVoid... params) {
                try {
                    client.delete(params[0]);
                } catch (Exception e) {
                    asyncResult.setError(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void noResponse) {
                asyncResult.completeResult();
            }

        }, request);
    }

    @Override
    public void deleteAsync(IReturnVoid request, AsyncSuccessVoid success) {
        deleteAsync(request, success, null);
    }

    @Override
    public void deleteAsync(IReturnVoid request, AsyncSuccessVoid success, AsyncError error) {
        deleteAsync(request, createAsyncResultVoid(success, error));
    }

    @Override
    public <T> void deleteAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResult<T> asyncResult){
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>() {
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.delete(params[0], queryParams);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, request);
    }

    @Override
    public <T> void deleteAsync(IReturn<T> request, Map<String, String> queryParams, AsyncSuccess<T> success) {
        deleteAsync(request, queryParams, createAsyncResult(success, null));
    }

    @Override
    public <T> void deleteAsync(String path, final Class responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.delete(params[0], responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void deleteAsync(String path, Class responseType, AsyncSuccess<T> success) {
        deleteAsync(path, responseType, createAsyncResult(success, null));
    }

    @Override
    public <T> void deleteAsync(String path, final Type responseType, final AsyncResult<T> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, T>() {
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.delete(params[0], responseType);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                asyncResult.completeResult(response);
            }

        }, path);
    }

    @Override
    public <T> void deleteAsync(String path, Type responseType, AsyncSuccess<T> success) {
        deleteAsync(path, responseType, createAsyncResult(success, null));
    }

    @Override
    public void deleteAsync(String path, final AsyncResult<byte[]> asyncResult) {
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<String, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(String... params) {
                try {
                    HttpURLConnection httpRes = client.delete(params[0]);
                    return Utils.readBytesToEnd(httpRes);
                } catch (Exception e) {
                    asyncResult.setError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(byte[] bytes) {
                asyncResult.completeResult(bytes);
            }

        }, path);
    }

    @Override
    public void deleteAsync(String path, AsyncSuccess<byte[]> success) {
        deleteAsync(path, createAsyncResult(success, null));
    }
}
