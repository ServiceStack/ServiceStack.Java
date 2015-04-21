//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;
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
}
