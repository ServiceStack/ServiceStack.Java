package net.servicestack.android;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ApplicationTestCase;

import com.google.gson.annotations.SerializedName;

import net.servicestack.client.AsyncResult;
import net.servicestack.client.Flags;
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

    public void test_Can_deserialize_enum_flags(){
        EnumTest o = client.getGson().fromJson("{\"Flags\":2}", EnumTest.class);

        assertEquals(o.Flags, EnumFlags.Value2);

        o = client.getGson().fromJson("{\"Flags\":4}", EnumTest.class);
        assertEquals(o.Flags, EnumFlags.Value3);
    }

    public static class EnumTest
    {
        public EnumFlags Flags;
    }

    @Flags()
    public static enum EnumFlags
    {
        @SerializedName("1") Value1(1),
        @SerializedName("2") Value2(2),
        @SerializedName("4") Value3(4);

        private final int value;
        EnumFlags(final int intValue) { value = intValue; }
        public int getValue() { return value; }
    }

}