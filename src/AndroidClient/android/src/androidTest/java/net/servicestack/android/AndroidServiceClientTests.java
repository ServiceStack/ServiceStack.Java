package net.servicestack.android;

import android.support.test.runner.AndroidJUnit4;

import net.servicestack.client.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AndroidServiceClientTests {
    public AndroidServiceClientTests() {
        Log.Instance = new AndroidLogProvider("ZZZ");
    }

    AndroidServiceClient client = new AndroidServiceClient("https://techstacks.io");

    @Test
    public void test(){

    }
}
