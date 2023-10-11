package net.servicestack.android.test;

import junit.framework.TestCase;

import net.servicestack.client.HttpMethods;
import net.servicestack.client.JsonServiceClient;

import static net.servicestack.android.test.dtos.*;

/**
 * Created by mythz on 9/11/2015.
 */
public class TestInterfaceMarkerTests  extends TestCase {
    public TestInterfaceMarkerTests() {

    }

    JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net");

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
