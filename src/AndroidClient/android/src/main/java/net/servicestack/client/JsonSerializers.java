//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.*;

public class JsonSerializers {
    public static JsonSerializer<Date> getDateSerializer(){
        return (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(Utils.toJsonDate(src));
    }

    public static JsonDeserializer<Date> getDateDeserializer(){
        return (json, typeOfT, context) -> json == null ? null : Utils.parseDate(json.getAsString());
    }

    public static JsonSerializer<TimeSpan> getTimeSpanSerializer(){
        return (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(src.toXsdDuration());
    }

    public static JsonDeserializer<TimeSpan> getTimeSpanDeserializer(){
        return (json, typeOfT, context) -> json == null ? null : TimeSpan.parse(json.getAsString());
    }

    public static JsonSerializer<UUID> getGuidSerializer(){
        return (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(Utils.toGuidString(src));
    }

    public static JsonDeserializer<UUID> getGuidDeserializer(){
        return (json, typeOfT, context) -> json == null ? null : Utils.fromGuidString(json.getAsString());
    }

    public static JsonSerializer<byte[]> getByteArraySerializer(){
        return (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(Base64.getEncoder().encodeToString(src));
    }

    public static JsonDeserializer<byte[]> getByteArrayDeserializer(){
        return (json, typeOfT, context) -> json == null ? null : Base64.getDecoder().decode(json.getAsString());
    }

    public static class CaseInsensitiveEnumTypeAdapterFactory implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (!rawType.isEnum()) {
                return null;
            }

            final Map<String, T> lowercaseToConstant = new HashMap<String, T>();
            for (T constant : rawType.getEnumConstants()) {
                lowercaseToConstant.put(toLowercase(constant), constant);
            }

            return new TypeAdapter<T>() {
                public void write(JsonWriter out, T value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                    } else {
                        out.value(value.toString());
                    }
                }

                public T read(JsonReader reader) throws IOException {
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        return null;
                    } else {
                        return lowercaseToConstant.get(toLowercase(reader.nextString()));
                    }
                }
            };
        }

        private String toLowercase(Object o) {
            return o.toString().toLowerCase(Locale.US);
        }
    }

    public static TypeAdapterFactory getCaseInsensitiveEnumTypeAdapterFactory() {
        return new CaseInsensitiveEnumTypeAdapterFactory();
    }
}
