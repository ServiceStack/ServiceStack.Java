package net.servicestack.client.tests;

import junit.framework.TestCase;

import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.WebServiceException;

import net.servicestack.client.tests.testdtos.*;

/**
 * Created by mythz on 1/3/2016.
 */
public class TestAuthTests extends TestCase {

    JsonServiceClient client = new JsonServiceClient("http://test.servicestack.net");

    public void test_AuthRequired_returns_401(){
        try {
            client.get(new TestAuth());
            fail("should throw");
        } catch (WebServiceException ex){
            assertEquals(401, ex.getStatusCode());
            assertEquals("Unauthorized", ex.getStatusDescription());
        }
    }

    public void test_does_send_BasicAuthHeaders(){
        client.setCredentials("test", "test");
        client.setAlwaysSendBasicAuthHeaders(true);

        TestAuthResponse response = client.get(new TestAuth());

        assertEquals("1", response.getUserId());
        assertEquals("test", response.getUserName());
        assertEquals("test DisplayName", response.getDisplayName());
        assertNotNull(response.getSessionId());
    }
}
