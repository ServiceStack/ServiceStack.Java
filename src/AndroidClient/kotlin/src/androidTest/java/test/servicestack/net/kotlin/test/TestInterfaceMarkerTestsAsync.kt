//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.test

import android.app.Application
import android.test.ApplicationTestCase
import junit.framework.Assert
import net.servicestack.android.AndroidServiceClient
import net.servicestack.client.AsyncSuccess
import net.servicestack.client.HttpMethods
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class TestInterfaceMarkerTestsAsync : ApplicationTestCase<Application>(Application::class.java) {

    internal var client = AndroidServiceClient("http://test.servicestack.net")

    @Throws(InterruptedException::class)
    fun test_Does_SendDefault_as_POST() {
        val signal = CountDownLatch(1)

        var request = SendDefault()
        request.id = 1

        client.sendAsync<SendVerbResponse>(request, AsyncSuccess<SendVerbResponse> {
            Assert.assertEquals(1, it.id)
            Assert.assertEquals(HttpMethods.Post, it.requestMethod)
            Assert.assertEquals("/json/reply/SendDefault", it.pathInfo)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendRestGet_as_GET_using_Predefined_Route() {
        val signal = CountDownLatch(1)

        var request = SendRestGet()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            Assert.assertEquals(1, it.id!!)
            Assert.assertEquals(HttpMethods.Get, it?.requestMethod)
            Assert.assertEquals("/json/reply/SendRestGet", it?.pathInfo)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendGet_as_GET() {
        val signal = CountDownLatch(1)

        var request = SendGet()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            Assert.assertEquals(1, it.id!!)
            Assert.assertEquals(HttpMethods.Get, it.requestMethod!!)
            Assert.assertEquals("/json/reply/SendGet", it.pathInfo!!)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(500, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendPost_as_POST() {
        val signal = CountDownLatch(1)

        var request = SendPost()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            Assert.assertEquals(1, it.id!!)
            Assert.assertEquals(HttpMethods.Post, it.requestMethod)
            Assert.assertEquals("/json/reply/SendPost", it.pathInfo)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendPut_as_PUT() {
        val signal = CountDownLatch(1)

        var request = SendPut()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            Assert.assertEquals(1, it.id!!)
            Assert.assertEquals(HttpMethods.Put, it.requestMethod)
            Assert.assertEquals("/json/reply/SendPut", it.pathInfo)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }
}
