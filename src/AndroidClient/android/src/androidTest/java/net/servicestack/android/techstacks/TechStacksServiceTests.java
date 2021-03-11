package net.servicestack.android.techstacks;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.TestCase;

import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.Utils;
import net.servicestack.client.WebServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static net.servicestack.android.techstacks.dtos.AppOverview;
import static net.servicestack.android.techstacks.dtos.AppOverviewResponse;
import static net.servicestack.android.techstacks.dtos.FindTechnologies;
import static net.servicestack.android.techstacks.dtos.GetTechnology;
import static net.servicestack.android.techstacks.dtos.GetTechnologyResponse;
import static net.servicestack.android.techstacks.dtos.LockStackResponse;
import static net.servicestack.android.techstacks.dtos.LockTechStack;
import static net.servicestack.android.techstacks.dtos.Option;
import static net.servicestack.android.techstacks.dtos.Overview;
import static net.servicestack.android.techstacks.dtos.OverviewResponse;
import static net.servicestack.android.techstacks.dtos.QueryResponse;
import static net.servicestack.android.techstacks.dtos.TechStackDetails;
import static net.servicestack.android.techstacks.dtos.Technology;
import static net.servicestack.android.techstacks.dtos.TechnologyInStack;
import static net.servicestack.android.techstacks.dtos.TechnologyInfo;
import static net.servicestack.android.techstacks.dtos.TechnologyTier;

@RunWith(AndroidJUnit4.class)
public class TechStacksServiceTests extends TestCase {
    public TechStacksServiceTests() {

    }

    JsonServiceClient client = new JsonServiceClient("https://www.techstacks.io");

    @Test
    public void test_Can_GET_TechStacks_Overview(){
        OverviewResponse response = client.get(new Overview());

//        Log.i("ZZZ-TEST", client.getGson().toJson(response.getTopTechnologiesByTier()));

        assertOverviewResponse(response);
    }

    @Test
    public void test_Can_GET_TechStacks_AppOverview(){
        AppOverviewResponse r = client.get(new AppOverview());
        assertNotNull(r);
        assertTrue(r.getTopTechnologies().size() > 0);
        assertTrue(r.getAllTiers().size() > 0);
    }

    @Test
    public void test_Can_GET_TechStacks_Overview_with_relative_url() {
        OverviewResponse response = client.get("/overview", OverviewResponse.class);
        assertOverviewResponse(response);
    }

    @Test
    public void test_Can_GET_TechStacks_Overview_with_absolute_url() {
        OverviewResponse response = client.get("https://www.techstacks.io/overview", OverviewResponse.class);
        assertOverviewResponse(response);
    }

    @Test
    public void test_Can_GET_GetTechnology_with_params() {
        GetTechnology requestDto = new GetTechnology()
            .setSlug("servicestack");

        GetTechnologyResponse response = client.get(requestDto);
        assertGetTechnologyResponse(response);
    }

    @Test
    public void test_Can_GET_GetTechnology_with_url() {
        GetTechnologyResponse response = client.get("/technology/servicestack", GetTechnologyResponse.class);
        assertGetTechnologyResponse(response);
    }

    @Test
    public void test_Can_call_FindTechnologies_AutoQuery_Service() {
        FindTechnologies request = new FindTechnologies()
            .setName("ServiceStack");

        QueryResponse<Technology> response = client.get(request);

        assertEquals(1, response.getResults().size());
    }

    @Test
    public void test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() {
        FindTechnologies request = (FindTechnologies) new FindTechnologies()
            .setTake(5);

        QueryResponse<Technology> response = client.get(request,
            Utils.createMap("DescriptionContains","framework"));

        assertEquals(5, response.getResults().size());
    }

    @Test
    public void test_Can_serialize_Empty_Option() {
        Option dto = new Option();

        String json = client.toJson(dto);

        assertEquals("{}", json);
    }

    @Test
    public void test_Can_deserialize_Empty_Option() {
        String json = "{\"name\":null,\"title\":null,\"value\":null}";

        Option dto = (Option)client.fromJson(json, Option.class);

        assertNull(dto.getName());
        assertNull(dto.getTitle());
        assertNull(dto.getValue());
    }

    @Test
    public void test_Can_serialize_Full_Option() {
        Option dto = new Option()
            .setName("name")
            .setTitle("title")
            .setValue(TechnologyTier.ProgrammingLanguage);

        String json = client.toJson(dto);

        assertEquals("{\"name\":\"name\",\"title\":\"title\",\"value\":\"ProgrammingLanguage\"}", json);
    }

    @Test
    public void test_Can_deserialize_Full_Option() {
        String json = "{\"name\":\"name\",\"title\":\"title\",\"value\":\"ProgrammingLanguage\"}";

        Option dto = (Option)client.fromJson(json, Option.class);

        assertEquals("name", dto.getName());
        assertEquals("title", dto.getTitle());
        assertEquals(TechnologyTier.ProgrammingLanguage, dto.getValue());
    }

    @Test
    public void test_does_handle_auth_failure() {
        JsonServiceClient techStacksClient = new JsonServiceClient("https://techstacks.io/");
        int errorCode = 0;
        try {
            LockTechStack request = new LockTechStack();
            request.setTechnologyStackId((long)6);
            LockStackResponse res = techStacksClient.post(request);
            fail("Should throw");
        } catch(WebServiceException ex) {
            //private StatusCode has correct code, response status is null due to empty response body.
            errorCode = ex.getStatusCode();
        }
        assertEquals(errorCode, 401);
    }

    @Test
    public void test_Can_deserialize_Overview() throws IOException {
        if ("1".equals("1"))
            return; //Ignore until we work out how to add resources to android test only

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("overview.json");
        String json = Utils.readToEnd(stream, "UTF-8");

        OverviewResponse dto = (OverviewResponse)client.fromJson(json, OverviewResponse.class);

        assertEquals(6, dto.getTopUsers().size());
        assertEquals("demisbellot", dto.getTopUsers().get(0).getUserName());
        assertEquals("http:\\/\\/pbs.twimg.com\\/profile_images\\/1765666853\\/image1326949938_normal.png", dto.getTopUsers().get(0).getAvatarUrl());
        assertEquals(61, (int)dto.getTopUsers().get(0).getStacksCount());


        assertEquals(20 ,dto.getTopTechnologies().size());
        TechnologyInfo topTech = dto.getTopTechnologies().get(0);
        assertEquals(TechnologyTier.Data, topTech.getTier());
        assertEquals("redis", topTech.getSlug());
        assertEquals("Redis", topTech.getName());
        assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/redis-logo.png", topTech.getLogoUrl());
        assertEquals(35, (int)topTech.getStacksCount());


        ArrayList<TechStackDetails> latestStacks = dto.getLatestTechStacks();
        assertEquals(20, latestStacks.size());

        TechStackDetails techstacks = latestStacks.get(0);
        assertEquals(1, (long)techstacks.getId());
        assertEquals("TechStacks Website", techstacks.getName());
        assertEquals("ServiceStack", techstacks.getVendorName());
        assertTrue(techstacks.description.startsWith("This Website! "));
        assertEquals("https://techstacks.io", techstacks.getAppUrl());
        assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/screenshots/techstacks.png", techstacks.getScreenshotUrl());
        assertEquals(Utils.fromDateTime("2015-01-01T17:33:58.9892560"), techstacks.getCreated());
        assertEquals("layoric", techstacks.getCreatedBy());
        assertEquals(Utils.fromDateTime("2015-01-12T23:34:12.4516410"), techstacks.getLastModified());
        assertEquals("layoric", techstacks.getLastModifiedBy());
        assertTrue(techstacks.getIsLocked());
        assertEquals("2", techstacks.getOwnerId());
        assertEquals("techstacks-website", techstacks.getSlug());
        assertEquals(Utils.fromDateTime("2015-01-12T23:34:12.4516410"), techstacks.getLastStatusUpdate());

        ArrayList<TechnologyInStack> techstackChoices = techstacks.getTechnologyChoices();
        assertEquals(10, techstackChoices.size());
        TechnologyInStack techChoice = techstackChoices.get(0);
        assertEquals(1, (long)techChoice.getTechnologyId());
        assertEquals(1, (long)techChoice.getTechnologyStackId());
        assertEquals(2, (long)techChoice.getId());
        assertEquals("ServiceStack", techChoice.getName());
        assertEquals("Service Stack", techChoice.getVendorName());
        assertEquals("https://servicestack.net", techChoice.getVendorUrl());
        assertEquals("https://servicestack.net", techChoice.getProductUrl());
        assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/servicestack-logo.png", techChoice.getLogoUrl());
        assertEquals(Utils.fromDateTime("2014-12-28T08:49:20.9542550"), techChoice.getCreated());
        assertEquals("demisbellot", techChoice.getCreatedBy());
        assertEquals(Utils.fromDateTime("2014-12-28T08:49:20.9542550"), techChoice.getLastModified());
        assertEquals("demisbellot", techChoice.getLastModifiedBy());
        assertEquals("1", techChoice.getOwnerId());
        assertEquals("servicestack", techChoice.getSlug());
        assertTrue(techChoice.isLogoApproved());
        assertFalse(techChoice.getIsLocked());
        assertEquals(TechnologyTier.Server, techChoice.getTier());


        assertEquals(9, dto.getTopTechnologiesByTier().size());
        ArrayList<TechnologyInfo> langs = dto.getTopTechnologiesByTier().get(TechnologyTier.ProgrammingLanguage);
        assertEquals(3, langs.size());
        TechnologyInfo lang = langs.get(0);
        assertEquals(TechnologyTier.ProgrammingLanguage, lang.getTier());
        assertEquals("python", lang.getSlug());
        assertEquals("Python", lang.getName());
        assertEquals("https://raw.githubusercontent.com/ServiceStack/Assets/master/img/livedemos/techstacks/python-logo.png", lang.getLogoUrl());
        assertEquals(25, (int)lang.getStacksCount());

//        let toJson = dtos.toJson()
//        println(toJson)
    }
    /* TEST HELPERS */

    public static void assertOverviewResponse(OverviewResponse r) {
        assertNotNull(r);
        assertTrue(r.getTopUsers().size() > 0);
        assertTrue(r.getTopTechnologies().size() > 0);
        assertTrue(r.getLatestTechStacks().size() > 0);
        assertTrue(r.getLatestTechStacks().get(0).getTechnologyChoices().size() > 0);
        assertTrue(r.getTopTechnologiesByTier().size() > 0);
    }

    public static void assertGetTechnologyResponse(GetTechnologyResponse r) {
        assertNotNull(r);
        assertEquals("ServiceStack", r.getTechnology().getName());
        assertTrue(r.getTechnologyStacks().size() > 0);
    }
}