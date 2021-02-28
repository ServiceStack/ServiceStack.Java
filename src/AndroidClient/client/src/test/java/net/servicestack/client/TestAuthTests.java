package net.servicestack.client;

import junit.framework.TestCase;

/**
 * Created by mythz on 1/3/2016.
 */
public class TestAuthTests extends TestCase {

    public JsonServiceClient CreateClient(){
        return new JsonServiceClient("http://test.servicestack.net");
    }

    public void test_AuthRequired_returns_401(){
        try {
            ServiceClient client = CreateClient();
            client.get(new testdtos.TestAuth());
            fail("should throw");
        } catch (WebServiceException ex){
            assertEquals(401, ex.getStatusCode());
            assertEquals("Unauthorized", ex.getStatusDescription());
        }
    }

    public void test_does_send_BasicAuthHeaders(){
        ServiceClient client = CreateClient();
        client.setCredentials("test", "test");
        client.setAlwaysSendBasicAuthHeaders(true);

        testdtos.TestAuthResponse response = client.get(new testdtos.TestAuth());

        assertEquals("1", response.getUserId());
        assertEquals("test", response.getUserName());
        assertEquals("test DisplayName", response.getDisplayName());
        assertNotNull(response.getSessionId());
    }

    private void does_transparently_send_BasicAuthHeader_on_401(Boolean isPost){
        ServiceClient client = CreateClient();
        client.setCredentials("test", "test");
        
        testdtos.TestAuthResponse response = null;
        if (isPost) {
        	response = client.post(new testdtos.TestAuth());
        } else {
        	response = client.get(new testdtos.TestAuth());
        }
        assertEquals("1", response.getUserId());
        assertEquals("test", response.getUserName());
        assertEquals("test DisplayName", response.getDisplayName());
        assertNotNull(response.getSessionId());
    }
    
    public void test_does_transparently_send_BasicAuthHeader_on_401_response_From_get(){
        
    	does_transparently_send_BasicAuthHeader_on_401( false);

    }
    
    public void test_does_transparently_send_BasicAuthHeader_on_401_response_from_post(){
    	
    	does_transparently_send_BasicAuthHeader_on_401( true);

    }

    public void test_can_authenticate_with_CredentialsAuth(){
        ServiceClient client = CreateClient();

        testdtos.AuthenticateResponse authResponse = client.post(new testdtos.Authenticate()
            .setProvider("credentials")
            .setUserName("test")
            .setPassword("test"));

        assertEquals("1", authResponse.getUserId());
        assertEquals("test", authResponse.getUserName());
        assertNotNull(authResponse.getSessionId());

        testdtos.TestAuthResponse response = client.get(new testdtos.TestAuth());

        assertEquals("1", response.getUserId());
        assertEquals("test", response.getUserName());
        assertEquals("test DisplayName", response.getDisplayName());
        assertNotNull(response.getSessionId());
    }

    public void test_Does_fetch_AccessToken_using_RefreshTokenCookies(){
        ServiceClient client = CreateClient();

        testdtos.AuthenticateResponse authResponse = client.post(new testdtos.Authenticate()
            .setProvider("credentials")
            .setUserName("test")
            .setPassword("test"));

        String initialAccessToken = client.getTokenCookie();
        String initialRefreshToken = client.getRefreshTokenCookie();
        assertNotNull(initialAccessToken);
        assertNotNull(initialRefreshToken);

        testdtos.Secured request = new testdtos.Secured()
            .setName("test");
        testdtos.SecuredResponse response = client.send(request);
        assertEquals(response.getResult(), request.getName());

        client.post(new testdtos.InvalidateLastAccessToken());

        response = client.send(request);
        assertEquals(response.getResult(), request.getName());
        String latestAccessToken = client.getTokenCookie();
        assertNotSame(latestAccessToken, initialAccessToken);
    }
}
