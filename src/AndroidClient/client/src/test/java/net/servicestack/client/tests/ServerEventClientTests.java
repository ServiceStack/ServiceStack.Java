package net.servicestack.client.tests;

import junit.framework.TestCase;

import net.servicestack.client.Log;
import net.servicestack.client.sse.ServerEventConnect;
import net.servicestack.client.sse.ServerEventConnectCallback;
import net.servicestack.client.sse.ServerEventsClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by mythz on 2/10/2017.
 */

public class ServerEventClientTests extends TestCase {

    public void test_can_connect_to_EventStream() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        ServerEventsClient client = new ServerEventsClient("http://chat.servicestack.net", "home")
            .setOnConnect(e -> {
                System.out.print("onConnect: " + e);
                signal.countDown();
            });

        client.start();

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }
}
