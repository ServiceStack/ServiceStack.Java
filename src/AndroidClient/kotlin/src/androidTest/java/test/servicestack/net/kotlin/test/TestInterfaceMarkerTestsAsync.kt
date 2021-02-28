//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.test

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals
import net.servicestack.android.AndroidServiceClient
import net.servicestack.client.AsyncSuccess
import net.servicestack.client.HttpMethods
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class TestInterfaceMarkerTestsAsync {

    internal var client = AndroidServiceClient("http://test.servicestack.net")

    @Throws(InterruptedException::class)
    fun test_Does_SendDefault_as_POST() {
        val signal = CountDownLatch(1)

        var request = SendDefault()
        request.id = 1

        client.sendAsync<SendVerbResponse>(request, AsyncSuccess<SendVerbResponse> {
            assertEquals(1, it.id)
            assertEquals(HttpMethods.Post, it.requestMethod)
            assertEquals("/json/reply/SendDefault", it.pathInfo)
            signal.countDown()
        })

        assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendRestGet_as_GET_using_Predefined_Route() {
        val signal = CountDownLatch(1)

        var request = SendRestGet()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            assertEquals(1, it.id!!)
            assertEquals(HttpMethods.Get, it?.requestMethod)
            assertEquals("/json/reply/SendRestGet", it?.pathInfo)
            signal.countDown()
        })

        assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendGet_as_GET() {
        val signal = CountDownLatch(1)

        var request = SendGet()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            assertEquals(1, it.id!!)
            assertEquals(HttpMethods.Get, it.requestMethod!!)
            assertEquals("/json/reply/SendGet", it.pathInfo!!)
            signal.countDown()
        })

        assertTrue(signal.await(500, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendPost_as_POST() {
        val signal = CountDownLatch(1)

        var request = SendPost()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            assertEquals(1, it.id!!)
            assertEquals(HttpMethods.Post, it.requestMethod)
            assertEquals("/json/reply/SendPost", it.pathInfo)
            signal.countDown()
        })

        assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_SendPut_as_PUT() {
        val signal = CountDownLatch(1)

        var request = SendPut()
        request.id = 1
        client.sendAsync<SendVerbResponse>(request,AsyncSuccess<SendVerbResponse> {
            assertEquals(1, it.id!!)
            assertEquals(HttpMethods.Put, it.requestMethod)
            assertEquals("/json/reply/SendPut", it.pathInfo)
            signal.countDown()
        })

        assertTrue(signal.await(5, TimeUnit.SECONDS))
    }
}
