package net.servicestack.android.test;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.HttpMethods;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static net.servicestack.android.test.dtos.*;

/**
 * Created by mythz on 9/11/2015.
 */
public class TestInterfaceMarkerTestsAsync  extends ApplicationTestCase<Application> {
    public TestInterfaceMarkerTestsAsync() {
        super(Application.class);
    }

    AndroidServiceClient client = new AndroidServiceClient("http://test.servicestack.net");

    public void test_Does_SendDefault_as_POST() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.sendAsync(new SendDefault().setId(1),
            new AsyncResult<SendVerbResponse>() {
                @Override
                public void success(SendVerbResponse response) {
                    assertEquals(1, (int) response.getId());
                    assertEquals(HttpMethods.Post, response.getRequestMethod());
                    assertEquals("/json/reply/SendDefault", response.getPathInfo());
                }

                @Override
                public void complete() {
                    signal.countDown();
                }
            });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Does_SendRestGet_as_GET_using_Predefined_Route() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.sendAsync(new SendRestGet().setId(1),
                new AsyncResult<SendVerbResponse>() {
                    @Override
                    public void success(SendVerbResponse response) {
                        assertEquals(1, (int) response.getId());
                        assertEquals(HttpMethods.Get, response.getRequestMethod());
                        assertEquals("/json/reply/SendRestGet", response.getPathInfo());
                    }

                    @Override
                    public void complete() {
                        signal.countDown();
                    }
                });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Does_SendGet_as_GET() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.sendAsync(new SendGet().setId(1),
                new AsyncResult<SendVerbResponse>() {
                    @Override
                    public void success(SendVerbResponse response) {
                        assertEquals(1, (int) response.getId());
                        assertEquals(HttpMethods.Get, response.getRequestMethod());
                        assertEquals("/json/reply/SendGet", response.getPathInfo());
                    }

                    @Override
                    public void complete() {
                        signal.countDown();
                    }
                });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Does_SendPost_as_POST() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.sendAsync(new SendPost().setId(1),
                new AsyncResult<SendVerbResponse>() {
                    @Override
                    public void success(SendVerbResponse response) {
                        assertEquals(1, (int) response.getId());
                        assertEquals(HttpMethods.Post, response.getRequestMethod());
                        assertEquals("/json/reply/SendPost", response.getPathInfo());
                    }

                    @Override
                    public void complete() {
                        signal.countDown();
                    }
                });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Does_SendPut_as_PUT() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.sendAsync(new SendPut().setId(1),
                new AsyncResult<SendVerbResponse>() {
                    @Override
                    public void success(SendVerbResponse response) {
                        assertEquals(1, (int) response.getId());
                        assertEquals(HttpMethods.Put, response.getRequestMethod());
                        assertEquals("/json/reply/SendPut", response.getPathInfo());
                    }

                    @Override
                    public void complete() {
                        signal.countDown();
                    }
                });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }
}
