//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Map;

public interface ServiceClient {
    public <TResponse> TResponse get(IReturn<TResponse> request);
    public <TResponse> TResponse get(IReturn<TResponse> request, Map<String,String> queryParams);
    public <TResponse> TResponse get(String path, Class responseType);
    public <TResponse> TResponse get(String path, Type responseType);
    public HttpURLConnection get(String path);

    public <TResponse> TResponse post(IReturn<TResponse> request);
    public <TResponse> TResponse post(String path, Object request, Class responseType);
    public <TResponse> TResponse post(String path, Object request, Type responseType);
    public <TResponse> TResponse post(String path, byte[] requestBody, String contentType, Class responseType);
    public <TResponse> TResponse post(String path, byte[] requestBody, String contentType, Type responseType);
    public HttpURLConnection post(String path, byte[] requestBody, String contentType);

    public <TResponse> TResponse put(IReturn<TResponse> request);
    public <TResponse> TResponse put(String path, Object request, Class responseType);
    public <TResponse> TResponse put(String path, Object request, Type responseType);
    public <TResponse> TResponse put(String path, byte[] requestBody, String contentType, Class responseType);
    public <TResponse> TResponse put(String path, byte[] requestBody, String contentType, Type responseType);
    public HttpURLConnection put(String path, byte[] requestBody, String contentType);

    public <TResponse> TResponse delete(IReturn<TResponse> request);
    public <TResponse> TResponse delete(IReturn<TResponse> request, Map<String,String> queryParams);
    public <TResponse> TResponse delete(String path, Class responseType);
    public <TResponse> TResponse delete(String path, Type responseType);
    public HttpURLConnection delete(String path);
}
