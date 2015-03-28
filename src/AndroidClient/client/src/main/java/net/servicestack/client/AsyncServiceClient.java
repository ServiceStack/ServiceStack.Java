package net.servicestack.client;

import java.net.HttpURLConnection;
import java.util.Map;

public interface AsyncServiceClient {
    public <T> void getAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse);
    public <T> void getAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResponse<T> asyncResponse);
    public <T> void getAsync(String path, final Class responseType, final AsyncResponse<T> asyncResponse);
    public void getAsync(String path, final AsyncResponse<HttpURLConnection> asyncResponse);

    public <T> void postAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse);
    public <T> void postAsync(String path, final Object request, final Class responseType, final AsyncResponse<T> asyncResponse);
    public <T> void postAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResponse<T> asyncResponse);
    public void postAsync(String path, final byte[] requestBody, final String contentType, final AsyncResponse<HttpURLConnection> asyncResponse);

    public <T> void putAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse);
    public <T> void putAsync(String path, final Object request, final Class responseType, final AsyncResponse<T> asyncResponse);
    public <T> void putAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResponse<T> asyncResponse);
    public void putAsync(String path, final byte[] requestBody, final String contentType, final AsyncResponse<HttpURLConnection> asyncResponse);

    public <T> void deleteAsync(IReturn<T> request, final AsyncResponse<T> asyncResponse);
    public <T> void deleteAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResponse<T> asyncResponse);
    public <T> void deleteAsync(String path, final Class responseType, final AsyncResponse<T> asyncResponse);
    public void deleteAsync(String path, final AsyncResponse<HttpURLConnection> asyncResponse);
}
