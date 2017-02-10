package net.servicestack.client.sse;

/**
 * Created by mythz on 2/10/2017.
 */

public interface ServerEventCallback {
    void execute(ServerEventsClient client, ServerEventMessage e);
}
