package test.servicestack.net.kotlin.techstacks

import junit.framework.TestCase

import net.servicestack.client.JsonServiceClient
import net.servicestack.client.Utils

import java.io.FileInputStream
import java.io.IOException
import java.util.ArrayList

import junit.framework.Assert

class TechStacksServiceTests : TestCase() {

    internal var client = JsonServiceClient("http://techstacks.io")

    fun test_Can_GET_TechStacks_Overview() {
        val response = client.get<OverviewResponse>(Overview())

        //        Log.i("ZZZ-TEST", client.getGson().toJson(response.getTopTechnologiesByTier()));

        assertOverviewResponse(response)
    }

    fun test_Can_GET_TechStacks_AppOverview() {
        val r = client.get<AppOverviewResponse>(AppOverview())
        Assert.assertNotNull(r)
        Assert.assertTrue(r.TopTechnologies!!.size > 0)
        Assert.assertTrue(r.AllTiers!!.size > 0)
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
        requestDto.Slug = "servicestack"

        val response = client.get<GetTechnologyResponse>(requestDto)
        assertGetTechnologyResponse(response)
    }

    fun test_Can_GET_GetTechnology_with_url() {
        val response = client.get<GetTechnologyResponse>("/technology/servicestack", GetTechnologyResponse::class.java)
        assertGetTechnologyResponse(response)
    }

    fun test_Can_call_FindTechnologies_AutoQuery_Service() {
        val request = FindTechnologies()
        request.Name = "ServiceStack"

        val response = client.get<QueryResponse<Technology>>(request)

        Assert.assertEquals(1, response.Results?.size)
    }

    fun test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() {
        val request = FindTechnologies()
        request.Take = 5

        val response = client.get<QueryResponse<Technology>>(request,
                Utils.createMap("DescriptionContains", "framework"))

        Assert.assertEquals(5, response.Results?.size)
    }

    fun test_Can_serialize_Empty_Option() {
        val dto = Option()

        val json = client.toJson(dto)

        Assert.assertEquals("{}", json)
    }

    fun test_Can_deserialize_Empty_Option() {
        val json = "{\"name\":null,\"title\":null,\"value\":null}"

        val dto = client.fromJson(json, Option::class.java) as Option

        Assert.assertNull(dto.Name)
        Assert.assertNull(dto.Title)
        Assert.assertNull(dto.Value)
    }

    fun test_Can_serialize_Full_Option() {
        val dto = Option()
        dto.Name = "name"
        dto.Title = "title"
        dto.Value = TechnologyTier.ProgrammingLanguage

        val json = client.toJson(dto)

        Assert.assertEquals("{\"name\":\"name\",\"title\":\"title\",\"value\":\"ProgrammingLanguage\"}", json)
    }

    fun test_Can_deserialize_Full_Option() {
        val json = "{\"name\":\"name\",\"title\":\"title\",\"value\":\"ProgrammingLanguage\"}"

        val dto = client.fromJson(json, Option::class.java) as Option

        Assert.assertEquals("name", dto.Name)
        Assert.assertEquals("title", dto.Title)
        Assert.assertEquals(TechnologyTier.ProgrammingLanguage, dto.Value)
    }

    @Throws(IOException::class)
    fun test_Can_deserialize_Overview() {

        //Latest Android Update broke test resources
        val stream = this.javaClass.classLoader.getResourceAsStream("assets/overview.json");
        val json = Utils.readToEnd(stream, "UTF-8");

        val dto = client.fromJson(json, OverviewResponse::class.java) as OverviewResponse

        Assert.assertEquals(6, dto.TopUsers?.size)
        val topUser = dto.TopUsers!![0]
        Assert.assertEquals("demisbellot", topUser.UserName)
        Assert.assertEquals("http:\\/\\/pbs.twimg.com\\/profile_images\\/1765666853\\/image1326949938_normal.png", topUser.AvatarUrl)
        Assert.assertEquals(61, topUser.StacksCount)


        Assert.assertEquals(20, dto.TopTechnologies?.size)
        val topTech = dto.TopTechnologies!![0]
        Assert.assertEquals(TechnologyTier.Data, topTech.Tier)
        Assert.assertEquals("redis", topTech.Slug)
        Assert.assertEquals("Redis", topTech.Name)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/redis-logo.png", topTech.LogoUrl)
        Assert.assertEquals(35, topTech.StacksCount)


        val latestStacks = dto.LatestTechStacks
        Assert.assertEquals(20, latestStacks?.size)

        val techstacks = latestStacks!![0]
        Assert.assertEquals(1, techstacks.Id!!)
        Assert.assertEquals("TechStacks Website", techstacks.Name)
        Assert.assertEquals("ServiceStack", techstacks.VendorName)
        Assert.assertTrue(techstacks.Description!!.startsWith("This Website! "))
        Assert.assertEquals("http://techstacks.io", techstacks.AppUrl)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/screenshots/techstacks.png", techstacks.ScreenshotUrl)
        Assert.assertEquals(Utils.parseDate("2015-01-01T17:33:58.9892560"), techstacks.Created)
        Assert.assertEquals("layoric", techstacks.CreatedBy)
        Assert.assertEquals(Utils.parseDate("2015-01-12T23:34:12.4516410"), techstacks.LastModified)
        Assert.assertEquals("layoric", techstacks.LastModifiedBy)
        Assert.assertTrue(techstacks.IsLocked!!)
        Assert.assertEquals("2", techstacks.OwnerId)
        Assert.assertEquals("techstacks-website", techstacks.Slug)
        Assert.assertEquals(Utils.parseDate("2015-01-12T23:34:12.4516410"), techstacks.LastStatusUpdate)

        val techstackChoices = techstacks.TechnologyChoices
        Assert.assertEquals(10, techstackChoices?.size)
        val techChoice = techstackChoices!![0]
        Assert.assertEquals(1, techChoice.TechnologyId!!)
        Assert.assertEquals(1, techChoice.TechnologyStackId!!)
        Assert.assertEquals(2, techChoice.Id!!)
        Assert.assertEquals("ServiceStack", techChoice.Name)
        Assert.assertEquals("Service Stack", techChoice.VendorName)
        Assert.assertEquals("https://servicestack.net", techChoice.VendorUrl)
        Assert.assertEquals("https://servicestack.net", techChoice.ProductUrl)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/servicestack-logo.png", techChoice.LogoUrl)
        Assert.assertEquals(Utils.parseDate("2014-12-28T08:49:20.9542550"), techChoice.Created)
        Assert.assertEquals("demisbellot", techChoice.CreatedBy)
        Assert.assertEquals(Utils.parseDate("2014-12-28T08:49:20.9542550"), techChoice.LastModified)
        Assert.assertEquals("demisbellot", techChoice.LastModifiedBy)
        Assert.assertEquals("1", techChoice.OwnerId)
        Assert.assertEquals("servicestack", techChoice.Slug)
        Assert.assertTrue(techChoice.LogoApproved!!)
        Assert.assertFalse(techChoice.IsLocked!!)
        Assert.assertEquals(TechnologyTier.Server, techChoice.Tier)


        Assert.assertEquals(9, dto.TopTechnologiesByTier?.size)
        val langs = dto.TopTechnologiesByTier!![TechnologyTier.ProgrammingLanguage]
        Assert.assertEquals(3, langs?.size)
        val lang = langs!![0]
        Assert.assertEquals(TechnologyTier.ProgrammingLanguage, lang.Tier)
        Assert.assertEquals("python", lang.Slug)
        Assert.assertEquals("Python", lang.Name)
        Assert.assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/python-logo.png", lang.LogoUrl)
        Assert.assertEquals(25, lang.StacksCount)

        //        let toJson = dtos.toJson()
        //        println(toJson)
    }

    companion object {
        /* TEST HELPERS */

        fun assertOverviewResponse(r: OverviewResponse) {
            Assert.assertNotNull(r)
            Assert.assertTrue(r.TopUsers!!.size > 0)
            Assert.assertTrue(r.TopTechnologies!!.size > 0)
            Assert.assertTrue(r.LatestTechStacks!!.size > 0)
            Assert.assertTrue(r.LatestTechStacks!![0].TechnologyChoices!!.size > 0)
            Assert.assertTrue(r.TopTechnologiesByTier!!.size > 0)
        }

        fun assertGetTechnologyResponse(r: GetTechnologyResponse) {
            Assert.assertNotNull(r)
            Assert.assertEquals("ServiceStack", r.Technology?.Name)
            Assert.assertTrue(r.TechnologyStacks!!.size > 0)
        }
    }
}