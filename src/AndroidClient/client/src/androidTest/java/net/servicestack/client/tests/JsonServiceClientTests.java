//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.client.JsonServiceClient;

import net.servicestack.client.tests.dto.*;

public class JsonServiceClientTests extends ApplicationTestCase<Application> {

    public JsonServiceClientTests() {
        super(Application.class);
    }
    //10.0.2.2 = loopback
    //http://developer.android.com/tools/devices/emulator.html
    JsonServiceClient client = new JsonServiceClient("http://10.0.2.2:2020");

    public void ignore_test_can_GET_HelloAll(){
        Hello request = new Hello()
            .setName("World");

        HelloResponse response = client.get(request);

        assertEquals("World", response.getResult());
    }

}
