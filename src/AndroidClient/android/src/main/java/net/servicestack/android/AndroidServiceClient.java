//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.android;

import android.os.AsyncTask;

import net.servicestack.client.AsyncResult;
import net.servicestack.client.AsyncServiceClient;
import net.servicestack.client.IReturn;
import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.Utils;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Map;

public class AndroidServiceClient extends JsonServiceClient implements AsyncServiceClient {

    public AndroidServiceClient(String baseUrl) {
        super(baseUrl);
    }

    // Override to customize execution of AsyncTask
    public <T,TResponse> void execTask(AsyncTask<T,Void,TResponse> asyncTask, T... request){
        asyncTask.execute(request);
    }

    /* GET */

    public <T> void getAsync(IReturn<T> request, final AsyncResult<T> asyncResult){
        final AndroidServiceClient client = this;
        execTask(new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.get(params[0]);
                } catch (Exception e){
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

    /* DELETE */

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
}
