package net.servicestack.client.sse;

import com.google.gson.JsonObject;

import net.servicestack.client.JsonUtils;
import net.servicestack.client.Utils;

/**
 * Created by mythz on 2/10/2017.
 */

public class ServerEventCommand extends ServerEventMessage {
    private String userId;
    private String displayName;
    private String profileUrl;
    private boolean isAuthenticated;
    private String[] channels;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

    @Override
    protected void populate(JsonObject obj) {
        super.populate(obj);

        this.userId = JsonUtils.asString(obj, "userId");
        this.displayName = JsonUtils.asString(obj, "displayName");
        this.isAuthenticated = "true".equals(JsonUtils.asString(obj, "isAuthenticated"));
        this.profileUrl = JsonUtils.asString(obj, "profileUrl");

        String channels = JsonUtils.asString(obj, "channels");
        if (!Utils.isNullOrEmpty(channels))
            this.channels = channels.split(",");
    }
}
