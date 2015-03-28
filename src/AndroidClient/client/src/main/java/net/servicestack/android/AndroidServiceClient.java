package net.servicestack.android;

import android.os.AsyncTask;

import net.servicestack.client.AsyncResponse;
import net.servicestack.client.AsyncServiceClient;
import net.servicestack.client.IReturn;
import net.servicestack.client.JsonServiceClient;

import java.net.HttpURLConnection;
import java.util.Map;

public class AndroidServiceClient extends JsonServiceClient implements AsyncServiceClient {

    public AndroidServiceClient(String baseUrl) {
        super(baseUrl);
    }

    /* GET */

    public <T> void getAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse){
        final AndroidServiceClient client = this;
        new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.get(params[0]);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(request);
    }

    public <T> void getAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResponse<T> asyncResponse){
        final AndroidServiceClient client = this;
        new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.get(params[0], queryParams);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(request);
    }

    public <T> void getAsync(String path, final Class responseType, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, T>(){
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.get(params[0], responseType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    public void getAsync(String path, final AsyncResponse<HttpURLConnection> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, HttpURLConnection>(){
            @Override
            protected HttpURLConnection doInBackground(String... params) {
                try {
                    return client.get(params[0]);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(HttpURLConnection response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    /* POST */

    @Override
    public <T> void postAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.post(params[0]);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(request);
    }

    @Override
    public <T> void postAsync(String path, final Object request, final Class responseType, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, T>(){
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.post(params[0], request, responseType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    @Override
    public <T> void postAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, T>(){
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.post(params[0], requestBody, contentType, responseType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    @Override
    public void postAsync(String path, final byte[] requestBody, final String contentType, final AsyncResponse<HttpURLConnection> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, HttpURLConnection>(){
            @Override
            protected HttpURLConnection doInBackground(String... params) {
                try {
                    return client.post(params[0], requestBody, contentType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(HttpURLConnection response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    /* PUT */

    @Override
    public <T> void putAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.put(params[0]);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(request);
    }

    @Override
    public <T> void putAsync(String path, final Object request, final Class responseType, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, T>(){
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.put(params[0], request, responseType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    @Override
    public <T> void putAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, T>(){
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.put(params[0], requestBody, contentType, responseType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    @Override
    public void putAsync(String path, final byte[] requestBody, final String contentType, final AsyncResponse<HttpURLConnection> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, HttpURLConnection>(){
            @Override
            protected HttpURLConnection doInBackground(String... params) {
                try {
                    return client.put(params[0], requestBody, contentType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(HttpURLConnection response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    /* DELETE */

    public <T> void deleteAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse){
        final AndroidServiceClient client = this;
        new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.delete(params[0]);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(request);
    }

    public <T> void deleteAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResponse<T> asyncResponse){
        final AndroidServiceClient client = this;
        new AsyncTask<IReturn<T>, Void, T>(){
            @Override
            protected T doInBackground(IReturn<T>... params) {
                try {
                    return client.delete(params[0], queryParams);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(request);
    }

    public <T> void deleteAsync(String path, final Class responseType, final AsyncResponse<T> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, T>(){
            @Override
            protected T doInBackground(String... params) {
                try {
                    return client.delete(params[0], responseType);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(T response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }

    public void deleteAsync(String path, final AsyncResponse<HttpURLConnection> asyncResponse) {
        final AndroidServiceClient client = this;
        new AsyncTask<String, Void, HttpURLConnection>(){
            @Override
            protected HttpURLConnection doInBackground(String... params) {
                try {
                    return client.delete(params[0]);
                } catch (Exception e){
                    asyncResponse.error(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(HttpURLConnection response) {
                if (response != null) {
                    asyncResponse.success(response);
                }
                asyncResponse.complete();
            }

        }.execute(path);
    }
}
