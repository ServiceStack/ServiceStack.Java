package net.servicestack.client.sse;

import net.servicestack.client.IReceiver;
import net.servicestack.client.Log;

/**
 * Created by mythz on 2/11/2017.
 */

public class ServerEventReceiver implements IReceiver {

    protected ServerEventsClient client;
    protected ServerEventMessage request;

    public ServerEventsClient getClient() {
        return client;
    }

    public void setClient(ServerEventsClient client) {
        this.client = client;
    }

    public ServerEventMessage getRequest() {
        return request;
    }

    public void setRequest(ServerEventMessage request) {
        this.request = request;
    }

    @Override
    public void noSuchMethod(String selector, Object message) {
        if (Log.isDebugEnabled())
            Log.d("noSuchMethod defined for " + selector);
    }
}
