package test;

import net.servicestack.client.JsonUtils;
import net.servicestack.client.sse.ServerEventMessage;

/**
 * Created by mythz on 2/11/2017.
 */

public class TestNamedReceiver {

    public class CustomType {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static CustomType FooMethodReceived;
    public static CustomType BarMethodReceived;
    public static CustomType NoSuchMethodReceived;
    public static String NoSuchMethodSelector;

    public void FooMethod(CustomType request) {
        FooMethodReceived = request;
    }

    public CustomType BarMethod(CustomType request) {
        BarMethodReceived = request;
        return request;
    }

    public void NoSuchMethod(String selector, Object message) {
        ServerEventMessage msg = (ServerEventMessage)message;
        NoSuchMethodReceived = JsonUtils.fromJson(msg.getJson(), CustomType.class);
        NoSuchMethodSelector = selector;
    }
}
