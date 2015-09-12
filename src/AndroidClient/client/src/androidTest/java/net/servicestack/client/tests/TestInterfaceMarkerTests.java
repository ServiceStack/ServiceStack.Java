package net.servicestack.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.client.HttpMethods;
import net.servicestack.client.JsonServiceClient;

import static test.dto.*;

/**
 * Created by mythz on 9/11/2015.
 */
public class TestInterfaceMarkerTests  extends ApplicationTestCase<Application> {
    public TestInterfaceMarkerTests() {
        super(Application.class);
    }

    JsonServiceClient client = new JsonServiceClient("http://test.servicestack.net");

    public void test_Does_SendDefault_as_POST(){
        SendVerbResponse response = client.send(new SendDefault().setId(1));
        assertEquals(1, (int)response.getId());
        assertEquals(HttpMethods.Post, response.getRequestMethod());
        assertEquals("/json/reply/SendDefault", response.getPathInfo());
    }

    public void test_Does_SendRestGet_as_GET_using_Predefined_Route(){
        SendVerbResponse response = client.send(new SendRestGet().setId(1));
        assertEquals(1, (int)response.getId());
        assertEquals(HttpMethods.Get, response.getRequestMethod());
        assertEquals("/json/reply/SendRestGet", response.getPathInfo());
    }

    public void test_Does_SendGet_as_GET(){
        SendVerbResponse response = client.send(new SendGet().setId(1));
        assertEquals(1, (int)response.getId());
        assertEquals(HttpMethods.Get, response.getRequestMethod());
        assertEquals("/json/reply/SendGet", response.getPathInfo());
    }

    public void test_Does_SendPost_as_POST(){
        SendVerbResponse response = client.send(new SendPost().setId(1));
        assertEquals(1, (int)response.getId());
        assertEquals(HttpMethods.Post, response.getRequestMethod());
        assertEquals("/json/reply/SendPost", response.getPathInfo());
    }

    public void test_Does_SendPut_as_PUT(){
        SendVerbResponse response = client.send(new SendPut().setId(1));
        assertEquals(1, (int)response.getId());
        assertEquals(HttpMethods.Put, response.getRequestMethod());
        assertEquals("/json/reply/SendPut", response.getPathInfo());
    }
}
