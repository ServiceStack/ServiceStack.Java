//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Map;

public interface AsyncServiceClient {
    <T> void sendAsync(IReturn<T> request, final AsyncResult<T> asyncResult);
    void sendAsync(IReturnVoid request, final AsyncResultVoid asyncResult);

    <T> void getAsync(IReturn<T> request, final AsyncResult<T> asyncResult);
    <T> void getAsync(IReturn<T> request, final AsyncSuccess<T> success);
    <T> void getAsync(IReturn<T> request, final AsyncSuccess<T> success, final AsyncError error);
    void getAsync(IReturnVoid request, final AsyncResultVoid asyncResult);
    <T> void getAsync(IReturnVoid request, final AsyncSuccessVoid success);
    <T> void getAsync(IReturnVoid request, final AsyncSuccessVoid success, final AsyncError error);
    <T> void getAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResult<T> asyncResult);
    <T> void getAsync(IReturn<T> request, final Map<String, String> queryParams, AsyncSuccess<T> success);
    <T> void getAsync(String path, final Class responseType, final AsyncResult<T> asyncResult);
    <T> void getAsync(String path, final Class responseType, final AsyncSuccess<T> success);
    <T> void getAsync(String path, final Type responseType, final AsyncResult<T> asyncResult);
    <T> void getAsync(String path, final Type responseType, final AsyncSuccess<T> success);
    void getAsync(String path, final AsyncResult<byte[]> asyncResult);
    void getAsync(String path, final AsyncSuccess<byte[]> success);

    <T> void postAsync(IReturn<T> request, final AsyncResult<T> asyncResult);
    void postAsync(IReturnVoid request, final AsyncResultVoid asyncResult);
    <T> void postAsync(String path, final Object request, final Class responseType, final AsyncResult<T> asyncResult);
    <T> void postAsync(String path, final Object request, final Type responseType, final AsyncResult<T> asyncResult);
    <T> void postAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResult<T> asyncResult);
    <T> void postAsync(String path, final byte[] requestBody, final String contentType, final Type responseType, final AsyncResult<T> asyncResult);
    void postAsync(String path, final byte[] requestBody, final String contentType, final AsyncResult<byte[]> asyncResult);

    <T> void putAsync(IReturn<T> request, final AsyncResult<T> asyncResult);
    void putAsync(IReturnVoid request, final AsyncResultVoid asyncResult);
    <T> void putAsync(String path, final Object request, final Class responseType, final AsyncResult<T> asyncResult);
    <T> void putAsync(String path, final Object request, final Type responseType, final AsyncResult<T> asyncResult);
    <T> void putAsync(String path, final byte[] requestBody, final String contentType, final Class responseType, final AsyncResult<T> asyncResult);
    <T> void putAsync(String path, final byte[] requestBody, final String contentType, final Type responseType, final AsyncResult<T> asyncResult);
    void putAsync(String path, final byte[] requestBody, final String contentType, final AsyncResult<byte[]> asyncResult);

    <T> void deleteAsync(IReturn<T> request, final AsyncResult<T> asyncResult);
    void deleteAsync(IReturnVoid request, final AsyncResultVoid asyncResult);
    <T> void deleteAsync(IReturn<T> request, final Map<String, String> queryParams, final AsyncResult<T> asyncResult);
    <T> void deleteAsync(String path, final Class responseType, final AsyncResult<T> asyncResult);
    <T> void deleteAsync(String path, final Type responseType, final AsyncResult<T> asyncResult);
    void deleteAsync(String path, final AsyncResult<byte[]> asyncResult);
}
