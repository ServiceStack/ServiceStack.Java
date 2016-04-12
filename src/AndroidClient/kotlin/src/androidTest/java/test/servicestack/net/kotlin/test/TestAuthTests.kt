package test.servicestack.net.kotlin.test

import junit.framework.Assert
import junit.framework.TestCase
import net.servicestack.client.JsonServiceClient
import net.servicestack.client.WebServiceException

/**
 * Created by mythz on 1/3/2016.
 */
class TestAuthTests : TestCase() {

    fun CreateClient(): JsonServiceClient {
        return JsonServiceClient("http://test.servicestack.net")
    }

    fun test_AuthRequired_returns_401() {
        try {
            val client = CreateClient()
            client.get(TestAuth())
            Assert.fail("should throw")
        } catch (ex: WebServiceException) {
            Assert.assertEquals(401, ex.statusCode)
            Assert.assertEquals("Unauthorized", ex.statusDescription)
        }
    }

    fun test_does_send_BasicAuthHeaders() {
        val client = CreateClient()
        client.setCredentials("test", "test")
        client.alwaysSendBasicAuthHeaders = true

        val response = client.get(TestAuth())

        assertEquals("1", response.userId)
        assertEquals("test", response.userName)
        assertEquals("test DisplayName", response.displayName)
        Assert.assertNotNull(response.sessionId)
    }

    fun test_does_transparently_send_BasicAuthHeader_on_401_response() {
        val client = CreateClient()
        client.setCredentials("test", "test")

        val response = client.get(TestAuth())

        assertEquals("1", response.userId)
        assertEquals("test", response.userName)
        assertEquals("test DisplayName", response.displayName)
        Assert.assertNotNull(response.sessionId)
    }

    fun test_can_authenticate_with_CredentialsAuth() {
        val client = CreateClient()

        var request = Authenticate()
        request.provider = "credentials"
        request.userName = "test"
        request.password = "test"

        val authResponse = client.post(request)

        Assert.assertEquals("1", authResponse.userId)
        Assert.assertEquals("test", authResponse.userName)
        Assert.assertNotNull(authResponse.sessionId)

        val response = client.get(TestAuth())

        assertEquals("1", response.userId)
        assertEquals("test", response.userName)
        assertEquals("test DisplayName", response.displayName)
        Assert.assertNotNull(response.sessionId)
    }
}
