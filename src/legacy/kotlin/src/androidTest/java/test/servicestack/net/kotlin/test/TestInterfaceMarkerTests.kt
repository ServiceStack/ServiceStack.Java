//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.test

import junit.framework.Assert
import junit.framework.TestCase
import net.servicestack.client.HttpMethods
import net.servicestack.client.JsonServiceClient

class TestInterfaceMarkerTests : TestCase() {

    internal var client = JsonServiceClient("https://test.servicestack.net")

    fun test_Does_SendDefault_as_POST() {
        val request = SendDefault()
        request.id = 1
        val response = client.send(request)
        Assert.assertEquals(1, response.id!!)
        Assert.assertEquals(HttpMethods.Post, response.requestMethod)
        Assert.assertEquals("/json/reply/SendDefault", response.pathInfo)
    }

    fun test_Does_SendRestGet_as_GET_using_Predefined_Route() {
        val request = SendRestGet()
        request.id = 1
        val response = client.send(request)
        Assert.assertEquals(1, response.id!!)
        Assert.assertEquals(HttpMethods.Get, response.requestMethod)
        Assert.assertEquals("/json/reply/SendRestGet", response.pathInfo)
    }

    fun test_Does_SendGet_as_GET() {
        val request = SendGet()
        request.id = 1
        val response = client.send(request)
        Assert.assertEquals(1, response.id!!)
        Assert.assertEquals(HttpMethods.Get, response.requestMethod)
        Assert.assertEquals("/json/reply/SendGet", response.pathInfo)
    }

    fun test_Does_SendPost_as_POST() {
        val request = SendPost()
        request.id = 1
        val response = client.send(request)
        Assert.assertEquals(1, response.id!!)
        Assert.assertEquals(HttpMethods.Post, response.requestMethod)
        Assert.assertEquals("/json/reply/SendPost", response.pathInfo)
    }

    fun test_Does_SendPut_as_PUT() {
        val request = SendPut()
        request.id = 1
        val response = client.send(request)
        Assert.assertEquals(1, response.id!!)
        Assert.assertEquals(HttpMethods.Put, response.requestMethod)
        Assert.assertEquals("/json/reply/SendPut", response.pathInfo)
    }
}
