package net.servicestack.client.sse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.servicestack.client.JsonUtils;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mythz on 2/10/2017.
 */

public class ServerEventMessage {
    long eventId;
    String channel;
    String data;
    String selector;
    String json;
    String op;
    String target;
    String cssSelector;
    Map<String,String> meta;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    public ServerEventMessage populate(ServerEventMessage src, JsonObject obj){
        this.eventId = src.eventId;
        this.data = src.data;
        this.selector = src.selector;
        this.channel = src.channel;
        this.json = src.json;
        this.op = src.op;

        populate(obj);

        return this;
    }

    protected void populate(JsonObject obj) {
        if (this.meta == null)
            this.meta = new HashMap<>();

        for (Map.Entry<String,JsonElement> entry : obj.entrySet()) {
            this.meta.put(entry.getKey(), JsonUtils.asString(entry.getValue()));
        }
    }
}
