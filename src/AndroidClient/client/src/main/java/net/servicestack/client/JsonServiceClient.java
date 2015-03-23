//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JsonServiceClient implements ServiceClient {
    static Charset UTF8 = Charset.forName("UTF-8");
    String baseUrl;
    String replyUrl;

    Integer timeoutMs;
    public ConnectionFilter RequestFilter;
    public ConnectionFilter ResponseFilter;

    public static ConnectionFilter GlobalRequestFilter;
    public static ConnectionFilter GlobalResponseFilter;
    Gson gson;
    static boolean DEBUG = true;

    public JsonServiceClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.replyUrl = this.baseUrl + "json/reply/";
    }

    public static class MimeTypes
    {
        public static final String Json = "application/json";
    }

    public static class Headers
    {
        public static final String Accept = "Accept";
        public static final String ContentType = "Content-Type";
        public static final String ContentLength = "Content-Length";
    }

    public static class HttpMethods
    {
        public static final String Options = "OPTIONS";
        public static final String Head = "HEAD";
        public static final String Get = "GET";
        public static final String Post = "POST";
        public static final String Put = "PUT";
        public static final String Delete = "DELETE";
        public static final String Trace = "TRACE";
        public static final String Patch = "PATCH";
    }

    public void setTimeout(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public void setGson(Gson gson) { this.gson = gson; }

    public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, JsonSerializers.getDateSerializer())
                .registerTypeAdapter(Date.class, JsonSerializers.getDateDeserializer())
                .registerTypeAdapter(TimeSpan.class, JsonSerializers.getTimeSpanSerializer())
                .registerTypeAdapter(TimeSpan.class, JsonSerializers.getTimeSpanDeserializer())
                .registerTypeAdapter(UUID.class, JsonSerializers.getGuidSerializer())
                .registerTypeAdapter(UUID.class, JsonSerializers.getGuidDeserializer())
                .create();
        }
        return gson;
    }

    public String createUrl(Object requestDto){
        return createUrl(requestDto, null);
    }

    public String createUrl(Object requestDto, Map<String,String> query){
        String requestUrl = this.replyUrl + requestDto.getClass().getSimpleName();

        StringBuilder sb = new StringBuilder();
        Field lastField = null;
        try {
            for (Field f : requestDto.getClass().getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers()))
                    continue;

                Object val = f.get(requestDto);

                if (val == null)
                    continue;

                sb.append(sb.length() == 0 ? "?" : "&");
                sb.append(URLEncoder.encode(f.getName(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(val.toString(), "UTF-8"));
            }

            if (query != null) {
                for (Map.Entry<String, String> entry : query.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();

                    sb.append(sb.length() == 0 ? "?" : "&");
                    sb.append(URLEncoder.encode(key, "UTF-8"));
                    sb.append("=");
                    if (val != null) {
                        sb.append(URLEncoder.encode(val, "UTF-8"));
                    }
                }
            }
        } catch (IllegalAccessException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return requestUrl + sb.toString();
    }

    public HttpURLConnection createRequest(String url, String httpMethod) {
        return createRequest(url, httpMethod, null, null);
    }
    public HttpURLConnection createRequest(String url, String httpMethod, Object request) {
        String contentType = null;
        byte[] requestBody = null;

        if (request != null) {
            contentType = MimeTypes.Json;
            String json = getGson().toJson(request);
            if (DEBUG){
                Log.i("ZZZ-json>", json);
            }
            requestBody = json.getBytes(UTF8);
        }

        return createRequest(url, httpMethod, contentType, requestBody);
    }

    public HttpURLConnection createRequest(String requestUrl, String httpMethod, String requestType, byte[] requestBody) {
        try {
            URL url = new URL(requestUrl);

            HttpURLConnection req = (HttpURLConnection) url.openConnection();

            if (timeoutMs != null) {
                req.setConnectTimeout(timeoutMs);
                req.setReadTimeout(timeoutMs);
            }

            req.setRequestMethod(httpMethod);
            req.setRequestProperty(Headers.Accept, MimeTypes.Json);

            if (requestType != null) {
                req.setRequestProperty(Headers.ContentType, requestType);
            }

            if (requestBody != null) {
                req.setRequestProperty(Headers.ContentLength, Integer.toString(requestBody.length));
                DataOutputStream wr = new DataOutputStream(req.getOutputStream());
                wr.write(requestBody);
                wr.flush();
                wr.close();
            }

            if (RequestFilter != null) {
                RequestFilter.exec(req);
            }

            if (GlobalRequestFilter != null) {
                GlobalRequestFilter.exec(req);
            }

            return req;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <TResponse> TResponse send(HttpURLConnection req, String httpMethod, Class responseClass) {

        try {
            InputStream is = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            if (DEBUG) {
                String line = null;
                StringBuilder sb = new StringBuilder();
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    sb.append("\n");
                }

                String json = sb.toString();

                Log.i("ZZZ-json<", json);

                reader.close();

                TResponse response = (TResponse) getGson().fromJson(json, responseClass);
                return response;
            }
            else {
                TResponse response = (TResponse) getGson().fromJson(reader, responseClass);
                return response;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String combinePath(String basePath, String withPath){
        if (basePath == null)
            basePath = "";
        if (withPath == null)
            withPath = "";

        String prefix = basePath.endsWith("/")
                ? basePath
                : basePath + "/";

        String suffix = withPath.startsWith("/")
                ? withPath.substring(1)
                : withPath;

        return prefix + suffix;
    }

    static String typeName(Object o){
        return o.getClass().getSimpleName();
    }

    @Override
    public <TResponse> TResponse get(IReturn<TResponse> request) {
        return send(createRequest(createUrl(request), HttpMethods.Get), Headers.ContentType, request.getResponseType());
    }

    @Override
    public <TResponse> TResponse post(IReturn<TResponse> request) {
        return send(createRequest(combinePath(replyUrl, typeName(request)), HttpMethods.Post, request), Headers.ContentType, request.getResponseType());
    }

    @Override
    public <TResponse> TResponse put(IReturn<TResponse> request) {
        return send(createRequest(combinePath(replyUrl,typeName(request)), HttpMethods.Put, request), Headers.ContentType, request.getResponseType());
    }

    @Override
    public <TResponse> TResponse delete(IReturn<TResponse> request) {
        return send(createRequest(createUrl(request), HttpMethods.Delete), Headers.ContentType, request.getResponseType());
    }

}
