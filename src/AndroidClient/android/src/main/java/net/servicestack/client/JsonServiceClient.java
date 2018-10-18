//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
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

    boolean alwaysSendBasicAuthHeaders;
    String userName;
    String password;

    Integer timeoutMs;
    public ConnectionFilter RequestFilter;
    public ConnectionFilter ResponseFilter;
    public ExceptionFilter ExceptionFilter;

    public static ConnectionFilter GlobalRequestFilter;
    public static ConnectionFilter GlobalResponseFilter;
    public static ExceptionFilter GlobalExceptionFilter;
    Gson gson;

    public JsonServiceClient(String baseUrl) {
        setBaseUrl(baseUrl);

        //Automatically populate response cookies
        if (CookieHandler.getDefault() == null){
            CookieHandler.setDefault(new CookieManager());
        }
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.replyUrl = this.baseUrl + "json/reply/";
    }

    public void setTimeout(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
            .registerTypeAdapterFactory(JsonSerializers.getCaseInsensitiveEnumTypeAdapterFactory())
            .registerTypeAdapter(Date.class, JsonSerializers.getDateSerializer())
            .registerTypeAdapter(Date.class, JsonSerializers.getDateDeserializer())
            .registerTypeAdapter(TimeSpan.class, JsonSerializers.getTimeSpanSerializer())
            .registerTypeAdapter(TimeSpan.class, JsonSerializers.getTimeSpanDeserializer())
            .registerTypeAdapter(UUID.class, JsonSerializers.getGuidSerializer())
            .registerTypeAdapter(UUID.class, JsonSerializers.getGuidDeserializer());
    }

    public Gson getGson() {
        if (gson == null) {
            gson = getGsonBuilder().disableHtmlEscaping().create();
        }
        return gson;
    }

    public String toJson(Object o){
        String json = getGson().toJson(o);
        return json;
    }

    public Object fromJson(String json, Class c){
        Object o = getGson().fromJson(json, c);
        return o;
    }

    public void setGson(Gson gson) { this.gson = gson; }

    public String createUrl(Object requestDto){
        return createUrl(requestDto, null);
    }

    public String createUrl(Object requestDto, Map<String,String> query){
        String requestUrl = this.replyUrl + requestDto.getClass().getSimpleName();

        StringBuilder sb = new StringBuilder();
        Field lastField = null;
        try {
            for (Field f : Utils.getSerializableFields(requestDto.getClass())) {
                Object val = f.get(requestDto);

                if (val == null)
                    continue;

                sb.append(sb.length() == 0 ? "?" : "&");
                sb.append(URLEncoder.encode(f.getName(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(Utils.stripQuotes(getGson().toJson(val)), "UTF-8"));
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

    public HttpURLConnection createRequest(String requestUrl, String httpMethod, byte[] requestBody, String requestType) {
    	return createRequest(requestUrl, httpMethod, requestBody, requestType, false);
    
    }
    
    public HttpURLConnection createRequest(String requestUrl, String httpMethod, byte[] requestBody, String requestType, Boolean forceAuthentication) {
        try {
            URL url = new URL(requestUrl);

            HttpURLConnection req = (HttpURLConnection) url.openConnection();

            if (timeoutMs != null) {
                req.setConnectTimeout(timeoutMs);
                req.setReadTimeout(timeoutMs);
            }

            req.setRequestMethod(httpMethod);
            req.setRequestProperty(HttpHeaders.Accept, MimeTypes.Json);

            if (requestType != null) {
                req.setRequestProperty(HttpHeaders.ContentType, requestType);
            }

            if (forceAuthentication || alwaysSendBasicAuthHeaders) {
                addBasicAuth(req, userName, password);
            }

            if (RequestFilter != null) {
                RequestFilter.exec(req);
            }

            if (GlobalRequestFilter != null) {
                GlobalRequestFilter.exec(req);
            }

            if (requestBody != null) {
                req.setDoOutput(true);
                req.setRequestProperty(HttpHeaders.ContentLength, Integer.toString(requestBody.length));
                DataOutputStream wr = new DataOutputStream(req.getOutputStream());
                wr.write(requestBody);
                wr.flush();
                Utils.closeQuietly(wr);
            }

            return req;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addBasicAuth(HttpURLConnection req, String userName, String password) {
        req.setRequestProperty(HttpHeaders.Authorization,
            "Basic " + Utils.toBase64String(userName + ":" + password));
        req.setRequestProperty("X-Auth", "Basic"); // HttpURLConnection doesn't allow re-reading Authorization Header
    }

    private static boolean shouldAuthenticate(HttpURLConnection req, String userName, String password){
        try {
            return req.getResponseCode() == 401
                && req.getRequestProperty("X-Auth") == null //only auth if auth never attempted
                && userName != null
                && password != null;
        } catch (IOException e) {
            return false;
        }
    }

    public static RuntimeException createException(HttpURLConnection res, int responseCode){

        WebServiceException webEx = null;
        try {
            InputStream errorStream = res.getErrorStream();

            String responseBody = errorStream != null
                ? Utils.readToEnd(errorStream, UTF8.name())
                : null;

            webEx = new WebServiceException(responseCode, res.getResponseMessage(), responseBody);

            if (res.getHeaderFields().containsKey(HttpHeaders.ContentType) && Utils.matchesContentType(res.getHeaderField(HttpHeaders.ContentType), MimeTypes.Json)){
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(responseBody, JsonElement.class);
                if(element != null) {
                    JsonObject jsonObj = element.getAsJsonObject();

                    for (Map.Entry<String,JsonElement> jsonElementEntry : jsonObj.entrySet()) {
                        if(jsonElementEntry.getKey().toLowerCase().equals("responsestatus")) {
                            webEx.setResponseStatus(Utils.createResponseStatus(jsonObj.get(jsonElementEntry.getKey())));
                            break;
                        }
                    }
                }
            }

            return webEx;

        } catch (IOException e) {
            if (webEx != null)
                return webEx;
            return new RuntimeException(e);
        }
    }

    public static String GetSendMethod(Object request)
    {
        return request instanceof IGet ?
              HttpMethods.Get
            : request instanceof IPost ?
              HttpMethods.Post
            : request instanceof IPut ?
              HttpMethods.Put
            : request instanceof IDelete ?
              HttpMethods.Delete
            : request instanceof IPatch ?
              HttpMethods.Patch :
              HttpMethods.Post;
    }

    public static boolean hasRequestBody(String httpMethod)
    {
        switch (httpMethod)
        {
            case HttpMethods.Get:
            case HttpMethods.Delete:
            case HttpMethods.Head:
            case HttpMethods.Options:
                return false;
        }
        return true;
    }

    @Override
    public boolean getAlwaysSendBasicAuthHeaders() {
        return this.alwaysSendBasicAuthHeaders;
    }

    @Override
    public void setAlwaysSendBasicAuthHeaders(boolean value) {
        this.alwaysSendBasicAuthHeaders = value;
    }

    @Override
    public void setCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public <TResponse> TResponse sendRequest(Object request, Object responseClass) {
        String httpMethod = GetSendMethod(request);
        if (hasRequestBody(httpMethod)){
            return send(Utils.combinePath(replyUrl, typeName(request)), httpMethod, request, responseClass);
        } else {
            String url = createUrl(request);
            return send(url, httpMethod, null, null, responseClass);
        }
    }

    @Override
    public <TResponse> TResponse send(IReturn<TResponse> request) {
        return sendRequest(request, request.getResponseType());
    }

    @Override
    public void send(IReturnVoid request) {
        String httpMethod = GetSendMethod(request);
        send(Utils.combinePath(replyUrl, typeName(request)), httpMethod, request,
            IReturnVoid.class);
    }

    public <TResponse> TResponse send(String url, String httpMethod, Object responseClass) {
        return send(url, httpMethod, null, null, responseClass);
    }

    public <TResponse> TResponse send(String url, String httpMethod, Object request, Object responseClass) {
        String contentType = null;
        byte[] requestBody = null;

        if (request != null) {
            contentType = MimeTypes.Json;
            String json = getGson().toJson(request);
            if (Log.isDebugEnabled()){
                Log.d(json);
            }
            requestBody = json.getBytes(UTF8);
        }

        return send(url, httpMethod, requestBody, contentType, responseClass);
    }

    public <TResponse> TResponse send(String requestUrl, String httpMethod, byte[] requestBody, String requestType, Object responseClass) {
        HttpURLConnection req = null;
        Class resClass = null;
        try {
            req = createRequest(requestUrl, httpMethod, requestBody, requestType);
            resClass = responseClass instanceof Class ? (Class)responseClass : null;
            Type resType = responseClass instanceof Type ? (Type)responseClass : null;
            if (resClass == null && resType == null)
                throw new RuntimeException("responseClass '" + responseClass.getClass().getSimpleName() + "' must be a Class or Type");

            int responseCode = req.getResponseCode();
            if (responseCode >= 400){
                boolean success = false;

                if (shouldAuthenticate(req, userName, password)){
                    req.disconnect();
                    req = createRequest(requestUrl, httpMethod, requestBody, requestType, true);
                  
                    success = req.getResponseCode() < 400;
                }

                if (!success){
                    RuntimeException ex = createException(req, responseCode);

                    if (ExceptionFilter != null)
                        ExceptionFilter.exec(req, ex);

                    if (GlobalExceptionFilter != null)
                        GlobalExceptionFilter.exec(req, ex);

                    throw ex;
                }
            }

            InputStream is = req.getInputStream();

            if (resClass == IReturnVoid.class){
                Utils.readBytesToEnd(is);
                return null;
            }

            if (ResponseFilter != null)
                ResponseFilter.exec(req);
            if (GlobalResponseFilter != null)
                GlobalResponseFilter.exec(req);

            if (resClass == byte[].class)
                return (TResponse)Utils.readBytesToEnd(req);
            if (resClass == String.class)
                return (TResponse)Utils.readToEnd(is, UTF8.name());
            if (resClass == InputStream.class)
                return (TResponse)is;

            if (Log.isDebugEnabled()) {
                String json = Utils.readToEnd(is, UTF8.name());
                Log.d(json);

                TResponse response = resClass != null
                    ? (TResponse) getGson().fromJson(json, resClass)
                    : (TResponse) getGson().fromJson(json, resType);

                return response;
            }
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                TResponse response = resClass != null
                    ? (TResponse) getGson().fromJson(reader, resClass)
                    : (TResponse) getGson().fromJson(reader, resType);

                Utils.closeQuietly(reader);
                return response;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (req != null && resClass != InputStream.class)
                req.disconnect();
        }
    }

    static String typeName(Object o){
        return o.getClass().getSimpleName();
    }

    private String resolveUrl(String relativeOrAbsoluteUrl) {
        return relativeOrAbsoluteUrl.startsWith("http:")
            || relativeOrAbsoluteUrl.startsWith("https:")
            ? relativeOrAbsoluteUrl
            : Utils.combinePath(baseUrl, relativeOrAbsoluteUrl);
    }

    @Override
    public <TResponse> TResponse get(IReturn<TResponse> request) {
        return send(createUrl(request), HttpMethods.Get, request.getResponseType());
    }

    @Override
    public void get(IReturnVoid request) {
        send(createUrl(request), HttpMethods.Get, IReturnVoid.class);
    }

    @Override
    public <TResponse> TResponse get(IReturn<TResponse> request, Map<String, String> queryParams) {
        return send(createUrl(request, queryParams), HttpMethods.Get, request.getResponseType());
    }

    @Override
    public <TResponse> TResponse get(String path, Class responseType) {
        return send(resolveUrl(path), HttpMethods.Get, responseType);
    }

    @Override
    public <TResponse> TResponse get(String path, Type responseType) {
        return send(resolveUrl(path), HttpMethods.Get, responseType);
    }

    @Override
    public HttpURLConnection get(String path) {
        return createRequest(resolveUrl(path), HttpMethods.Get, null, null);
    }

    @Override
    public <TResponse> TResponse post(IReturn<TResponse> request) {
        return send(
            Utils.combinePath(replyUrl, typeName(request)), HttpMethods.Post, request,
            request.getResponseType());
    }

    @Override
    public void post(IReturnVoid request) {
        send(Utils.combinePath(replyUrl, typeName(request)), HttpMethods.Post, request, IReturnVoid.class);
    }

    @Override
    public <TResponse> TResponse post(String path, Object request, Class responseType) {
        return send(resolveUrl(path), HttpMethods.Post, request, responseType);
    }

    @Override
    public <TResponse> TResponse post(String path, Object request, Type responseType) {
        return send(resolveUrl(path), HttpMethods.Post, request, responseType);
    }

    @Override
    public <TResponse> TResponse post(String path, byte[] requestBody, String contentType, Class responseType) {
        return send(resolveUrl(path), HttpMethods.Post, requestBody, contentType, responseType);
    }

    @Override
    public <TResponse> TResponse post(String path, byte[] requestBody, String contentType, Type responseType) {
        return send(resolveUrl(path), HttpMethods.Post, requestBody, contentType, responseType);
    }

    @Override
    public HttpURLConnection post(String path, byte[] requestBody, String contentType) {
        return createRequest(resolveUrl(path), HttpMethods.Post, requestBody, contentType);
    }

    @Override
    public <TResponse> TResponse put(IReturn<TResponse> request) {
        return send(
            Utils.combinePath(replyUrl, typeName(request)), HttpMethods.Put, request,
            request.getResponseType());
    }

    @Override
    public void put(IReturnVoid request) {
        send(Utils.combinePath(replyUrl, typeName(request)), HttpMethods.Put, request,
            IReturnVoid.class);
    }

    @Override
    public <TResponse> TResponse put(String path, Object request, Class responseType) {
        return send(resolveUrl(path), HttpMethods.Put, request, responseType);
    }

    @Override
    public <TResponse> TResponse put(String path, Object request, Type responseType) {
        return send(resolveUrl(path), HttpMethods.Put, request, responseType);
    }

    @Override
    public <TResponse> TResponse put(String path, byte[] requestBody, String contentType, Class responseType) {
        return send(resolveUrl(path), HttpMethods.Put, requestBody, contentType, responseType);
    }

    @Override
    public <TResponse> TResponse put(String path, byte[] requestBody, String contentType, Type responseType) {
        return send(resolveUrl(path), HttpMethods.Put, requestBody, contentType, responseType);
    }

    @Override
    public HttpURLConnection put(String path, byte[] requestBody, String contentType) {
        return createRequest(resolveUrl(path), HttpMethods.Put, requestBody, contentType);
    }

    @Override
    public <TResponse> TResponse delete(IReturn<TResponse> request) {
        return send(createUrl(request), HttpMethods.Delete, request.getResponseType());
    }

    @Override
    public void delete(IReturnVoid request) {
        send(createUrl(request), HttpMethods.Delete, IReturnVoid.class);
    }

    @Override
    public <TResponse> TResponse delete(IReturn<TResponse> request, Map<String, String> queryParams) {
        return send(createUrl(request, queryParams), HttpMethods.Delete, request.getResponseType());
    }

    @Override
    public <TResponse> TResponse delete(String path, Class responseType) {
        return send(resolveUrl(path), HttpMethods.Delete, responseType);
    }

    @Override
    public <TResponse> TResponse delete(String path, Type responseType) {
        return send(resolveUrl(path), HttpMethods.Delete, responseType);
    }

    @Override
    public HttpURLConnection delete(String path) {
        return createRequest(resolveUrl(path), HttpMethods.Delete, null, null);
    }

    @Override
    public void setCookie(String name, String value) {
        setCookie(name, value, null);
    }

    @Override
    public void setCookie(String name, String value, Long expiresInSecs) {
        CookieManager cookieManager = (CookieManager) CookieHandler.getDefault();
        HttpCookie cookie = new HttpCookie(name, value);
        if (expiresInSecs != null){
            cookie.setMaxAge(expiresInSecs);
        }
        cookieManager.getCookieStore().getCookies().add(cookie);
    }

    @Override
    public void clearCookies() {
        CookieManager cookieManager = (CookieManager) CookieHandler.getDefault();
        cookieManager.getCookieStore().removeAll();
    }
}
