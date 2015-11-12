//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client.tests;

import junit.framework.TestCase;
import net.servicestack.client.ConnectionFilter;
import net.servicestack.client.JsonServiceClient;

import net.servicestack.client.WebServiceException;
import net.servicestack.client.tests.dto.*;

import java.net.HttpURLConnection;

public class JsonServiceClientTests extends TestCase {

    public JsonServiceClientTests() {

    }
    //10.0.2.2 = loopback
    //http://developer.android.com/tools/devices/emulator.html
    JsonServiceClient client = new JsonServiceClient("http://servicestackunittests.azurewebsites.net");

    public void test_can_GET_HelloAll(){
        Hello request = new Hello()
            .setName("World");

        HelloResponse response = client.get(request);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_can_use_request_filter() {
        final Boolean[] passTest = {false};
        JsonServiceClient localTestClient = new JsonServiceClient("http://servicestackunittests.azurewebsites.net/");

        localTestClient.RequestFilter = new ConnectionFilter() {
            @Override
            public void exec(HttpURLConnection conn) {
                passTest[0] = true;
            }
        };

        Hello request = new Hello()
                .setName("World");

        HelloResponse response = localTestClient.get(request);

        assertEquals("Hello, World!", response.getResult());
        assertTrue(passTest[0]);
    }
//TODO Add service accessible via CI server
//    public void test_can_parse_empty_404_correctly() {
//        Boolean passTest = false;
//        JsonServiceClient localTestClient = new JsonServiceClient("http://localhost:65109/");
//        Hello request = new Hello()
//                .setName("World");
//        try {
//            localTestClient.get(request);
//        } catch (WebServiceException ex) {
//            assertEquals(ex.getStatusCode(),404);
//            passTest = true;
//        }
//
//        assertTrue(passTest);
//    }

}
