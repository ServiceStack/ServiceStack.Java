package net.servicestack.client.sse;

/**
 * Created by mythz on 2/10/2017.
 */

public class ServerEventConnect extends ServerEventCommand  {
    private String id;
    private String unRegisterUrl;
    private String heartbeatUrl;
    private long heartbeatIntervalMs;
    private long idleTimeoutMs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnRegisterUrl() {
        return unRegisterUrl;
    }

    public void setUnRegisterUrl(String unRegisterUrl) {
        this.unRegisterUrl = unRegisterUrl;
    }

    public String getHeartbeatUrl() {
        return heartbeatUrl;
    }

    public void setHeartbeatUrl(String heartbeatUrl) {
        this.heartbeatUrl = heartbeatUrl;
    }

    public long getHeartbeatIntervalMs() {
        return heartbeatIntervalMs;
    }

    public void setHeartbeatIntervalMs(long heartbeatIntervalMs) {
        this.heartbeatIntervalMs = heartbeatIntervalMs;
    }

    public long getIdleTimeoutMs() {
        return idleTimeoutMs;
    }

    public void setIdleTimeoutMs(long idleTimeoutMs) {
        this.idleTimeoutMs = idleTimeoutMs;
    }

    @Override
    public String toString() {
        return "(" + this.getClass().getSimpleName() +
                "\n id: " + id +
                "\n unRegisterUrl: " + unRegisterUrl +
                "\n heartbeatUrl: " + heartbeatUrl +
                "\n heartbeatIntervalMs: " + heartbeatIntervalMs +
                "\n idleTimeoutMs: " + idleTimeoutMs + "\n)";
    }
}
