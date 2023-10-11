//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlinapp.test

import junit.framework.Assert
import junit.framework.TestCase
import net.servicestack.client.ConnectionFilter
import net.servicestack.client.JsonServiceClient
import net.servicestack.client.WebServiceException
import test.servicestack.net.kotlin.test.EchoTypes
import test.servicestack.net.kotlin.test.Hello
import java.util.*

class JsonServiceClientTests : TestCase() {
    //10.0.2.2 = loopback
    //http://developer.android.com/tools/devices/emulator.html
    internal var client = JsonServiceClient("https://test.servicestack.net")

    fun test_can_GET_HelloAll() {
        val request = Hello()
        request.name = "World"

        val response = client.get(request)

        Assert.assertEquals("Hello, World!", response.result)
    }

    fun test_can_use_request_filter() {
        val passTest = arrayOf(false)
        val localTestClient = JsonServiceClient("https://test.servicestack.net/")

        localTestClient.RequestFilter = ConnectionFilter { passTest[0] = true }

        val request = Hello()
        request.name = "World"

        val response = localTestClient.get(request)

        Assert.assertEquals("Hello, World!", response.result)
        Assert.assertTrue(passTest[0])
    }

    fun test_does_process_missing_service_correctly() {
        val localTestClient = JsonServiceClient("https://servicestack.net/")

        try {
            localTestClient.get(EchoTypes())
            Assert.fail("Should throw")
        } catch (ex: WebServiceException) {
            Assert.assertEquals(ex.statusCode, 405)
        }

    }

    fun test_can_serialize_dates_correctly_via_get_request() {
        val client = JsonServiceClient("https://test.servicestack.net/")

        val request = EchoTypes()
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2015)
        cal.set(Calendar.MONTH, Calendar.JANUARY)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        val dateRepresentation = cal.time
        val date = dateRepresentation

        request.dateTime = date
        val response = client.get(request)
        Assert.assertTrue(response != null)
        Assert.assertEquals(request.dateTime, response!!.dateTime)
    }
}
