package net.servicestack.android;

import net.servicestack.client.JsonUtils;
import net.servicestack.client.sse.ServerEventMessage;
import net.servicestack.client.sse.ServerEventReceiver;

import static chat.chatdtos.*;

/**
 * Created by mythz on 2/12/2017.
 */

public class TestGlobalReceiver extends ServerEventReceiver {
    public static CustomType FooMethodReceived;
    public static CustomType NoSuchMethodReceived;
    public static String NoSuchMethodSelector;
    public static SetterType AnyNamedSetterReceived;

    public void setAnyNamedSetter(SetterType value) {
        AnyNamedSetterReceived = value;
    }

    public void anyNamedMethod(CustomType request)
    {
        FooMethodReceived = request;
    }

    @Override
    public void noSuchMethod(String selector, Object message)
    {
        ServerEventMessage msg = (ServerEventMessage)message;
        NoSuchMethodReceived = JsonUtils.fromJson(msg.getJson(), CustomType.class);
        NoSuchMethodSelector = selector;
    }
}
