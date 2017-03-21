package net.servicestack.client.sse;

public interface ServerEventUpdateCallback {
    void execute(ServerEventUpdate e);
}
