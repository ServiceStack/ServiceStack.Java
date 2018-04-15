//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.techstacks

import junit.framework.Assert
import junit.framework.TestCase
import net.servicestack.client.JsonServiceClient
import net.servicestack.client.Utils
import java.io.IOException
import java.util.ArrayList

class TechStacksServiceTests : TestCase() {

    internal var client = JsonServiceClient("https://www.techstacks.io")

    fun test_Can_GET_TechStacks_Overview() {
        val response = client.get<OverviewResponse>(Overview())

        //        Log.i("ZZZ-TEST", client.getGson().toJson(response.getTopTechnologiesByTier()));

        assertOverviewResponse(response)
    }

    fun test_Can_GET_TechStacks_AppOverview() {
        val r = client.get<AppOverviewResponse>(AppOverview())
        Assert.assertNotNull(r)
        Assert.assertTrue(r.topTechnologies.size > 0)
        Assert.assertTrue(r.allTiers.size > 0)
    }

    fun test_Can_GET_TechStacks_Overview_with_relative_url() {
        val response = client.get<OverviewResponse>("/overview", OverviewResponse::class.java)
        assertOverviewResponse(response)
    }

    fun test_Can_GET_TechStacks_Overview_with_absolute_url() {
        val response = client.get<OverviewResponse>("http://techstacks.io/overview", OverviewResponse::class.java)
        assertOverviewResponse(response)
    }

    fun test_Can_GET_GetTechnology_with_params() {
        val requestDto = GetTechnology()
        requestDto.slug = "servicestack"

        val response = client.get<GetTechnologyResponse>(requestDto)
        assertGetTechnologyResponse(response)
    }

    fun test_Can_GET_GetTechnology_with_url() {
        val response = client.get<GetTechnologyResponse>("/technology/servicestack", GetTechnologyResponse::class.java)
        assertGetTechnologyResponse(response)
    }

    fun test_Can_call_FindTechnologies_AutoQuery_Service() {
        val request = FindTechnologies()
        request.name = "ServiceStack"

        val response = client.get<QueryResponse<Technology>>(request)

        Assert.assertEquals(1, response.results.size)
    }

    fun test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() {
        val request = FindTechnologies()
        request.take = 5

        val response = client.get<QueryResponse<Technology>>(request,
                Utils.createMap("DescriptionContains", "framework"))

        Assert.assertEquals(5, response.results.size)
    }

    fun test_Can_serialize_Empty_Option() {
        val dto = Option()

        val json = client.toJson(dto)

        Assert.assertEquals("{}", json)
    }

    fun test_Can_deserialize_Empty_Option() {
        val json = "{\"name\":null,\"title\":null,\"value\":null}"

        val dto = client.fromJson(json, Option::class.java) as Option

        Assert.assertNull(dto.name)
        Assert.assertNull(dto.title)
        Assert.assertNull(dto.value)
    }

    fun test_Can_serialize_Full_Option() {
        val dto = Option()
        dto.name = "name"
        dto.title = "title"
        dto.value = TechnologyTier.ProgrammingLanguage

        val json = client.toJson(dto)

        Assert.assertEquals("{\"name\":\"name\",\"title\":\"title\",\"value\":\"ProgrammingLanguage\"}", json)
    }

    fun test_Can_deserialize_Full_Option() {
        val json = "{\"name\":\"name\",\"title\":\"title\",\"value\":\"ProgrammingLanguage\"}"

        val dto = client.fromJson(json, Option::class.java) as Option

        Assert.assertEquals("name", dto.name)
        Assert.assertEquals("title", dto.title)
        Assert.assertEquals(TechnologyTier.ProgrammingLanguage, dto.value)
    }

    @Throws(IOException::class)
    fun test_Can_deserialize_Overview() {

        //Latest Android Update broke test resources
        val stream = this.javaClass.classLoader.getResourceAsStream("assets/overview.json");
        val json = Utils.readToEnd(stream, "UTF-8");

        val dto = client.fromJson(json, OverviewResponse::class.java) as OverviewResponse

        Assert.assertEquals(20, dto.topUsers.size)
        val topUser = dto.topUsers[0]
        Assert.assertEquals("demisbellot", topUser.userName)
        Assert.assertEquals("http://pbs.twimg.com/profile_images/1765666853/image1326949938_normal.png", topUser.avatarUrl)
        Assert.assertEquals(95, topUser.stacksCount)


        Assert.assertEquals(50, dto.topTechnologies.size)
        val topTech = dto.topTechnologies[0]
        Assert.assertEquals(TechnologyTier.Data, topTech.tier)
        Assert.assertEquals("redis", topTech.slug)
        Assert.assertEquals("Redis", topTech.name)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/redis-logo.png", topTech.logoUrl)
        Assert.assertEquals(73, topTech.stacksCount)


        val latestStacks = dto.latestTechStacks
        Assert.assertEquals(20, latestStacks.size)

        val techstacks = latestStacks[0]
        Assert.assertEquals(1, techstacks.id!!)
        Assert.assertEquals("TechStacks Website", techstacks.name)
        Assert.assertEquals("ServiceStack", techstacks.vendorName)
        Assert.assertTrue(techstacks.description!!.startsWith("The original TechStacks Website"))
        Assert.assertEquals("http://angular.techstacks.io", techstacks.appUrl)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/screenshots/techstacks.png", techstacks.screenshotUrl)
        Assert.assertEquals(Utils.parseDate("2015-01-01T17:33:58.9892560"), techstacks.created)
        Assert.assertEquals("layoric", techstacks.createdBy)
        Assert.assertEquals(Utils.parseDate("2018-03-23T03:18:38.9958030"), techstacks.lastModified)
        Assert.assertEquals("mythz", techstacks.lastModifiedBy)
        Assert.assertTrue(techstacks.isLocked!!)
        Assert.assertEquals("2", techstacks.ownerId)
        Assert.assertEquals("techstacks-website", techstacks.slug)
        Assert.assertEquals(Utils.parseDate("2018-03-23T03:12:44.0426320"), techstacks.lastStatusUpdate)

        val techstackChoices = techstacks.technologyChoices
        Assert.assertEquals(11, techstackChoices.size)
        val techChoice = techstackChoices[0]
        Assert.assertEquals(1, techChoice.technologyId!!)
        Assert.assertEquals(1, techChoice.technologyStackId!!)
        Assert.assertEquals(2, techChoice.id!!)
        Assert.assertEquals("ServiceStack", techChoice.name)
        Assert.assertEquals("ServiceStack", techChoice.vendorName)
        Assert.assertEquals("https://servicestack.net", techChoice.vendorUrl)
        Assert.assertEquals("https://servicestack.net", techChoice.productUrl)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/servicestack-logo.png", techChoice.logoUrl)
        Assert.assertEquals(Utils.parseDate("2014-12-28T08:49:20.9542550"), techChoice.created)
        Assert.assertEquals("demisbellot", techChoice.createdBy)
        Assert.assertEquals(Utils.parseDate("2018-03-14T06:01:13.9571660"), techChoice.lastModified)
        Assert.assertEquals("mythz", techChoice.lastModifiedBy)
        Assert.assertEquals("1", techChoice.ownerId)
        Assert.assertEquals("servicestack", techChoice.slug)
        Assert.assertTrue(techChoice.logoApproved!!)
        Assert.assertFalse(techChoice.isLocked!!)
        Assert.assertEquals(TechnologyTier.Server, techChoice.tier)


        Assert.assertEquals(9, dto.topTechnologiesByTier.size)
        val langs = dto.topTechnologiesByTier[TechnologyTier.ProgrammingLanguage.toString()]
        Assert.assertEquals(5, langs!!.size)
        val lang = langs[0]
        Assert.assertEquals(TechnologyTier.ProgrammingLanguage, lang.tier)
        Assert.assertEquals("python", lang.slug)
        Assert.assertEquals("Python", lang.name)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/python-logo.png", lang.logoUrl)
        Assert.assertEquals(47, lang.stacksCount)

        //        let toJson = dtos.toJson()
        //        println(toJson)
    }

    companion object {
        /* TEST HELPERS */

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