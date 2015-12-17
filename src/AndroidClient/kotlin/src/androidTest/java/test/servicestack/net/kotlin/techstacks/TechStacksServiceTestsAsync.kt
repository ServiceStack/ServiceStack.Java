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

    internal var client = AndroidServiceClient("http://techstacks.io")

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
            Assert.assertTrue(it.TopTechnologies.size > 0)
            Assert.assertTrue(it.AllTiers.size > 0)
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
        requestDto.Slug = "servicestack"

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
        request.Name = "ServiceStack"

        val signal = CountDownLatch(1)

        client.getAsync<QueryResponse<Technology>>(request, AsyncSuccess<QueryResponse<Technology>> {
            Assert.assertEquals(1, it.Results.size)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() {
        val request = FindTechnologies()
        request.Take = 5

        val signal = CountDownLatch(1)

        client.getAsync<QueryResponse<Technology>>(request, hashMapOf(Pair("DescriptionContains", "framework")),
            AsyncSuccess<QueryResponse<Technology>> {
                Assert.assertEquals(5, it.Results.size)
                signal.countDown()
            })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    companion object {

        fun assertOverviewResponse(r: OverviewResponse) {
            Assert.assertNotNull(r)
            Assert.assertTrue(r.TopUsers.size > 0)
            Assert.assertTrue(r.TopTechnologies.size > 0)
            Assert.assertTrue(r.LatestTechStacks.size > 0)
            Assert.assertTrue(r.LatestTechStacks[0].TechnologyChoices.size > 0)
            Assert.assertTrue(r.TopTechnologiesByTier.size > 0)
        }

        fun assertGetTechnologyResponse(r: GetTechnologyResponse) {
            Assert.assertNotNull(r)
            Assert.assertEquals("ServiceStack", r.Technology?.Name)
            Assert.assertTrue(r.TechnologyStacks.size > 0)
        }
    }
}