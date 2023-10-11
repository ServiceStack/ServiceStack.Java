package net.servicestack.android.techstacks;

import android.support.test.runner.AndroidJUnit4;

import net.servicestack.android.AndroidLogProvider;
import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.Log;
import net.servicestack.client.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static net.servicestack.android.techstacks.dtos.AppOverview;
import static net.servicestack.android.techstacks.dtos.AppOverviewResponse;
import static net.servicestack.android.techstacks.dtos.FindTechnologies;
import static net.servicestack.android.techstacks.dtos.GetTechnology;
import static net.servicestack.android.techstacks.dtos.GetTechnologyResponse;
import static net.servicestack.android.techstacks.dtos.Overview;
import static net.servicestack.android.techstacks.dtos.OverviewResponse;
import static net.servicestack.android.techstacks.dtos.QueryResponse;
import static net.servicestack.android.techstacks.dtos.Technology;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TechStacksServiceTestsAsync {
    public TechStacksServiceTestsAsync() {
        Log.Instance = new AndroidLogProvider("ZZZ");
    }

    AndroidServiceClient client = new AndroidServiceClient("https://techstacks.io");

    @Test
    public void test_Can_GET_TechStacks_Overview() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(new Overview(), new AsyncResult<OverviewResponse>(){
            @Override
            public void success(OverviewResponse response) {
                assertOverviewResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_GET_TechStacks_AppOverview_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(new AppOverview(), new AsyncResult<AppOverviewResponse>() {
            @Override
            public void success(AppOverviewResponse r) {
                assertNotNull(r);
                assertTrue(r.getTopTechnologies().size() > 0);
                assertTrue(r.getAllTiers().size() > 0);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_GET_TechStacks_Overview_with_relative_url_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("/overview", OverviewResponse.class, new AsyncResult<OverviewResponse>() {
            @Override
            public void success(OverviewResponse response) {
                assertOverviewResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_GET_TechStacks_Overview_with_absolute_url_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("https://techstacks.io/overview", OverviewResponse.class, new AsyncResult<OverviewResponse>() {
            @Override
            public void success(OverviewResponse response) {
                assertOverviewResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_GET_GetTechnology_with_params_Async() throws InterruptedException {
        GetTechnology requestDto = new GetTechnology()
            .setSlug("servicestack");

        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(requestDto, new AsyncResult<GetTechnologyResponse>() {
            @Override
            public void success(GetTechnologyResponse response) {
                assertGetTechnologyResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_GET_GetTechnology_with_url_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("/technology/servicestack", GetTechnologyResponse.class, new AsyncResult<GetTechnologyResponse>() {
            @Override
            public void success(GetTechnologyResponse response) {
                assertGetTechnologyResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_call_FindTechnologies_AutoQuery_Service_Async() throws InterruptedException {
        FindTechnologies request = new FindTechnologies()
            .setName("ServiceStack");

        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(request, new AsyncResult<QueryResponse<Technology>>() {
            @Override
            public void success(QueryResponse<Technology> response) {
                assertEquals(1, response.getResults().size());

            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_call_FindTechnologies_AutoQuery_Implicit_Service() throws InterruptedException {
        FindTechnologies request = (FindTechnologies) new FindTechnologies()
            .setTake(5);

        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(request, Utils.createMap("DescriptionContains", "framework"),
            new AsyncResult<QueryResponse<Technology>>() {
                @Override
                public void success(QueryResponse<Technology> response) {
                    assertEquals(5, response.getResults().size());
                }

                @Override
                public void complete() {
                    signal.countDown();
                }
            });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

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