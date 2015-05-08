package net.servicestack.android;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.android.AndroidLogProvider;
import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.Log;

public class AndroidServiceClientTests extends ApplicationTestCase<Application> {
    public AndroidServiceClientTests() {
        super(Application.class);
        Log.Instance = new AndroidLogProvider("ZZZ");
    }

    AndroidServiceClient client = new AndroidServiceClient("http://techstacks.io");

    public void test(){

    }
}
