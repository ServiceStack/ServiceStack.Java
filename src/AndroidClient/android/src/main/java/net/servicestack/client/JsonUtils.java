package net.servicestack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mythz on 2/10/2017.
 */

public class JsonUtils {

    public static JsonObject toJsonObject(String json) {
        JsonElement e = new JsonParser().parse(json);
        if (e.isJsonObject()) {
            return e.getAsJsonObject();
        }
        return null;
    }

    public static String asString(JsonObject obj, String key) {
        if (obj == null)
            return null;
        if (key == null)
            throw new IllegalArgumentException("key is null");

        return asString(obj.get(key));
    }

    public static String asString(JsonElement el)
    {
        return el != null
                ? el.getAsString()
                : null;
    }

    public static long asLong(JsonObject obj, String key) {
        return asLong(obj, key, 0);
    }

    public static long asLong(JsonObject obj, String key, long defaultValue) {
        if (obj == null)
            return defaultValue;
        if (key == null)
            throw new IllegalArgumentException("key is null");

        JsonElement val = obj.get(key);

        return val != null
            ? val.getAsLong()
            : defaultValue;
    }

    private static Gson gson;
    public static Gson getGson(){
        return gson != null
            ? gson
            : (gson = new GsonBuilder().disableHtmlEscaping().create());
    }

    public static void setGson(Gson value){
        gson = value;
    }

    public static <T> T fromJson(String json, Class<?> cls) {
        return (T)getGson().fromJson(json, cls);
    }
}
