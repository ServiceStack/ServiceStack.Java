//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.techstacks

import android.app.Application
import android.test.ApplicationTestCase
import junit.framework.Assert
import net.servicestack.android.AndroidLogProvider
import net.servicestack.android.AndroidServiceClient
import net.servicestack.client.AsyncSuccess
import net.servicestack.client.Log
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class TechStacksServiceTestsAsync : ApplicationTestCase<Application>(Application::class.java) {
    init {
        Log.Instance = AndroidLogProvider("ZZZ")
    }

    internal var client = AndroidServiceClient("https://www.techstacks.io")

    @Throws(InterruptedException::class)
    fun test_Can_GET_TechStacks_Overview() {
        val signal = CountDownLatch(1)

        client.getAsync<OverviewResponse>(Overview(), AsyncSuccess<OverviewResponse> {
            assertOverviewResponse(it)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_GET_TechStacks_AppOverview_Async() {
        val signal = CountDownLatch(1)

        client.getAsync<AppOverviewResponse>(AppOverview(), AsyncSuccess<AppOverviewResponse> {
            Assert.assertNotNull(it)
            Assert.assertTrue(it.topTechnologies.size > 0)
            Assert.assertTrue(it.allTiers.size > 0)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_GET_TechStacks_Overview_with_relative_url_Async() {
        val signal = CountDownLatch(1)

        client.getAsync("/overview", OverviewResponse::class.java, AsyncSuccess<OverviewResponse> {
            assertOverviewResponse(it)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_GET_TechStacks_Overview_with_absolute_url_Async() {
        val signal = CountDownLatch(1)

        client.getAsync("http://techstacks.io/overview", OverviewResponse::class.java, AsyncSuccess<OverviewResponse> {
            assertOverviewResponse(it)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_GET_GetTechnology_with_params_Async() {
        val requestDto = GetTechnology()
        requestDto.slug = "servicestack"

        val signal = CountDownLatch(1)

        client.getAsync<GetTechnologyResponse>(requestDto, AsyncSuccess<GetTechnologyResponse> {
            assertGetTechnologyResponse(it)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_GET_GetTechnology_with_url_Async() {
        val signal = CountDownLatch(1)

        client.getAsync("/technology/servicestack", GetTechnologyResponse::class.java, AsyncSuccess<GetTechnologyResponse> {
            assertGetTechnologyResponse(it)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_call_FindTechnologies_AutoQuery_Service_Async() {
        val request = FindTechnologies()
        request.name = "ServiceStack"

        val signal = CountDownLatch(1)

        client.getAsync<QueryResponse<Technology>>(request, AsyncSuccess<QueryResponse<Technology>> {
            Assert.assertEquals(1, it.results.size)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() {
        val request = FindTechnologies()
        request.take = 5

        val signal = CountDownLatch(1)

        client.getAsync<QueryResponse<Technology>>(request, hashMapOf(Pair("DescriptionContains", "framework")),
            AsyncSuccess<QueryResponse<Technology>> {
                Assert.assertEquals(5, it.results.size)
                signal.countDown()
            })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    companion object {

        fun assertOverviewResponse(r: OverviewResponse) {
            Assert.assertNotNull(r)
            Assert.assertTrue(r.topUsers.size > 0)
            Assert.assertTrue(r.topTechnologies.size > 0)
            Assert.assertTrue(r.latestTechStacks.size > 0)
            Assert.assertTrue(r.latestTechStacks[0].technologyChoices.size > 0)
            Assert.assertTrue(r.topTechnologiesByTier.size > 0)
        }

        fun assertGetTechnologyResponse(r: GetTechnologyResponse) {
            Assert.assertNotNull(r)
            Assert.assertEquals("ServiceStack", r.technology?.name)
            Assert.assertTrue(r.technologyStacks.size > 0)
        }
    }
}