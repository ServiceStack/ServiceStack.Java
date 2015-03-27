package servicestack.net.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import net.servicestack.client.JsonServiceClient;

import java.util.HashMap;

import io.techstacks.dto.*;

public class TechStacksServiceTests extends ApplicationTestCase<Application> {
    public TechStacksServiceTests() {
        super(Application.class);
    }

    JsonServiceClient client = new JsonServiceClient("http://techstacks.io");

    public void test_Can_GET_TechStacks_Overview(){
        OverviewResponse response = client.get(new Overview());

//        Log.i("ZZZ-TEST", client.getGson().toJson(response.getTopTechnologiesByTier()));

        assertOverviewResponse(response);
    }

    public void test_Can_GET_TechStacks_AppOverview(){
        AppOverviewResponse r = client.get(new AppOverview());
        assertNotNull(r);
        assertTrue(r.getTopTechnologies().size() > 0);
        assertTrue(r.getAllTiers().size() > 0);
    }

    public void test_Can_GET_TechStacks_Overview_with_relative_url() {
        OverviewResponse response = client.get("/overview", OverviewResponse.class);
        assertOverviewResponse(response);
    }

    public void test_Can_GET_TechStacks_Overview_with_absolute_url() {
        OverviewResponse response = client.get("http://techstacks.io/overview", OverviewResponse.class);
        assertOverviewResponse(response);
    }

    public void test_Can_GET_GetTechnology_with_params() {
        GetTechnology requestDto = new GetTechnology()
            .setSlug("servicestack");

        GetTechnologyResponse response = client.get(requestDto);
        assertGetTechnologyResponse(response);
    }

    public void test_Can_GET_GetTechnology_with_url() {
        GetTechnologyResponse response = client.get("/technology/servicestack", GetTechnologyResponse.class);
        assertGetTechnologyResponse(response);
    }

    public void test_Can_call_FindTechnologies_AutoQuery_Service() {
        FindTechnologies request = new FindTechnologies()
            .setName("ServiceStack");

        QueryResponse<Technology> response = client.get(request);

        assertEquals(1, response.getResults().size());
    }

    public void test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() {
        FindTechnologies request = (FindTechnologies) new FindTechnologies()
            .setTake(5);

        QueryResponse<Technology> response = client.get(request,
                createMap("DescriptionContains","framework"));

        assertEquals(5, response.getResults().size());
    }

    /* TEST HELPERS */

    private void assertOverviewResponse(OverviewResponse r) {
        assertNotNull(r);
        assertTrue(r.getTopUsers().size() > 0);
        assertTrue(r.getTopTechnologies().size() > 0);
        assertTrue(r.getLatestTechStacks().size() > 0);
        assertTrue(r.getLatestTechStacks().get(0).getTechnologyChoices().size() > 0);
        assertTrue(r.getTopTechnologiesByTier().size() > 0);
    }

    private void assertGetTechnologyResponse(GetTechnologyResponse r) {
        assertNotNull(r);
        assertEquals("ServiceStack", r.getTechnology().getName());
        assertTrue(r.getTechnologyStacks().size() > 0);
    }

    <K,V> HashMap<K,V> createMap(K k1, V v1) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        return to;
    }

}