package net.servicestack.android;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ApplicationTestCase;

import net.servicestack.client.AsyncResult;
import net.servicestack.client.Utils;

import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    AndroidServiceClient client = new AndroidServiceClient("http://techstacks.io");

    public void test_Can_download_image_bytes(){
        HttpURLConnection httpRes = client.get("https://servicestack.net/img/logo.png");
        byte[] imgBytes = Utils.readBytesToEnd(httpRes);
        Bitmap img = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

        assertEquals(338, img.getWidth());
        assertEquals(55, img.getHeight());
    }

    public void test_Can_download_image_bytes_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("https://servicestack.net/img/logo.png", new AsyncResult<byte[]>() {
            @Override
            public void success(byte[] imgBytes) {
                Bitmap img = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                assertEquals(338, img.getWidth());
                assertEquals(55, img.getHeight());
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }
}