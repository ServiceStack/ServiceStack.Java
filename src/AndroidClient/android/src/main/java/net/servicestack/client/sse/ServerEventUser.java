package net.servicestack.client.sse;

import java.util.Map;

/**
 * Created by mythz on 2/12/2017.
 */

public class ServerEventUser {

    private String userId;
    private String displayName;
    private String profileUrl;
    private String[] channels;
    private Map<String, String> meta;

    public String getUserId() {
        return userId;
    }

    public ServerEventUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ServerEventUser setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public ServerEventUser setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }

    public String[] getChannels() {
        return channels;
    }

    public ServerEventUser setChannels(String[] channels) {
        this.channels = channels;
        return this;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public ServerEventUser setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }
}
