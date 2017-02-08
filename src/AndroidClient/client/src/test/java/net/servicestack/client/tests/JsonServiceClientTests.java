//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client.tests;

import junit.framework.TestCase;
import net.servicestack.client.ConnectionFilter;
import net.servicestack.client.JsonServiceClient;

import net.servicestack.client.WebServiceException;
import test.dtos.*;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Date;

public class JsonServiceClientTests extends TestCase {

    public JsonServiceClientTests() {

    }
    //10.0.2.2 = loopback
    //http://developer.android.com/tools/devices/emulator.html
    JsonServiceClient client = new JsonServiceClient("http://test.servicestack.net");

    public void test_can_GET_HelloAll(){
        Hello request = new Hello()
            .setName("World");

        HelloResponse response = client.get(request);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_can_use_request_filter() {
        final Boolean[] passTest = {false};
        JsonServiceClient localTestClient = new JsonServiceClient("http://test.servicestack.net/");

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

    public void test_does_process_missing_service_correctly() {
        JsonServiceClient localTestClient = new JsonServiceClient("http://techstacks.io/");

        try {
            localTestClient.get(new EchoTypes());
            fail("Should throw");
        } catch (WebServiceException ex) {
            assertEquals(ex.getStatusCode(), 405);
        }
    }

    public void test_can_serialize_dates_correctly_via_get_request() {
        JsonServiceClient client = new JsonServiceClient("http://test.servicestack.net/");

        EchoTypes request = new EchoTypes();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dateRepresentation = cal.getTime();
        Date date = dateRepresentation;

        request.setDateTime(date);
        EchoTypes response = client.get(request);
        assertTrue(response != null);
        assertEquals(request.getDateTime(),response.getDateTime());
    }
}
