package net.servicestack.android;

import net.servicestack.client.JsonUtils;
import net.servicestack.client.sse.ServerEventMessage;
import net.servicestack.client.sse.ServerEventReceiver;

import static chat.chatdtos.*;

/**
 * Created by mythz on 2/11/2017.
 */

public class TestNamedReceiver extends ServerEventReceiver {

    public static CustomType FooMethodReceived;
    public static CustomType BarMethodReceived;
    public static CustomType NoSuchMethodReceived;
    public static String NoSuchMethodSelector;
    public static CustomType QuxSetterReceived;

    public void setQuxSetter(CustomType value){
        QuxSetterReceived = value;
    }

    public void fooMethod(CustomType request) {
        FooMethodReceived = request;
    }

    public CustomType barMethod(CustomType request) {
        BarMethodReceived = request;
        return request;
    }

    @Override
    public void noSuchMethod(String selector, Object message) {
        ServerEventMessage msg = (ServerEventMessage)message;
        NoSuchMethodReceived = JsonUtils.fromJson(msg.getJson(), CustomType.class);
        NoSuchMethodSelector = selector;
    }
}
