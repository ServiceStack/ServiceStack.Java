//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Map;

public interface ServiceClient {
    boolean getAlwaysSendBasicAuthHeaders();
    void setAlwaysSendBasicAuthHeaders(boolean value);
    void setCredentials(String userName, String password);

    <TResponse> TResponse send(IReturn<TResponse> request);
    void send(IReturnVoid request);

    <TResponse> TResponse get(IReturn<TResponse> request);
    void get(IReturnVoid request);
    <TResponse> TResponse get(IReturn<TResponse> request, Map<String,String> queryParams);
    <TResponse> TResponse get(String path, Class responseType);
    <TResponse> TResponse get(String path, Type responseType);
    HttpURLConnection get(String path);

    <TResponse> TResponse post(IReturn<TResponse> request);
    void post(IReturnVoid request);
    <TResponse> TResponse post(String path, Object request, Class responseType);
    <TResponse> TResponse post(String path, Object request, Type responseType);
    <TResponse> TResponse post(String path, byte[] requestBody, String contentType, Class responseType);
    <TResponse> TResponse post(String path, byte[] requestBody, String contentType, Type responseType);
    HttpURLConnection post(String path, byte[] requestBody, String contentType);

    <TResponse> TResponse put(IReturn<TResponse> request);
    void put(IReturnVoid request);
    <TResponse> TResponse put(String path, Object request, Class responseType);
    <TResponse> TResponse put(String path, Object request, Type responseType);
    <TResponse> TResponse put(String path, byte[] requestBody, String contentType, Class responseType);
    <TResponse> TResponse put(String path, byte[] requestBody, String contentType, Type responseType);
    HttpURLConnection put(String path, byte[] requestBody, String contentType);

    <TResponse> TResponse delete(IReturn<TResponse> request);
    void delete(IReturnVoid request);
    <TResponse> TResponse delete(IReturn<TResponse> request, Map<String,String> queryParams);
    <TResponse> TResponse delete(String path, Class responseType);
    <TResponse> TResponse delete(String path, Type responseType);
    HttpURLConnection delete(String path);

    void setCookie(String name, String value);
    void setCookie(String name, String value, Long expiresInSecs);
}
