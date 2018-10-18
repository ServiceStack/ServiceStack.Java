//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class JsonSerializers {
    public static JsonSerializer<Date> getDateSerializer(){
        return new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(Utils.toJsonDate(src));
            }
        };
    }

    public static JsonDeserializer<Date> getDateDeserializer(){
        return new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json == null ? null : Utils.parseDate(json.getAsString());
            }
        };
    }

    public static JsonSerializer<TimeSpan> getTimeSpanSerializer(){
        return new JsonSerializer<TimeSpan>() {
            @Override
            public JsonElement serialize(TimeSpan src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.toXsdDuration());
            }
        };
    }

    public static JsonDeserializer<TimeSpan> getTimeSpanDeserializer(){
        return new JsonDeserializer<TimeSpan>() {
            @Override
            public TimeSpan deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json == null ? null : TimeSpan.parse(json.getAsString());
            }
        };
    }

    public static JsonSerializer<UUID> getGuidSerializer(){
        return new JsonSerializer<UUID>() {
            @Override
            public JsonElement serialize(UUID src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(Utils.toGuidString(src));
            }
        };
    }

    public static JsonDeserializer<UUID> getGuidDeserializer(){
        return new JsonDeserializer<UUID>() {
            @Override
            public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json == null ? null : Utils.fromGuidString(json.getAsString());
            }
        };
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
