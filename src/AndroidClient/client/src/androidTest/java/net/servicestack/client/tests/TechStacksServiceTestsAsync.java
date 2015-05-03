package net.servicestack.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.android.AndroidLogProvider;
import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.Log;
import net.servicestack.client.Utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.techstacks.dto.*;

public class TechStacksServiceTestsAsync extends ApplicationTestCase<Application> {
    public TechStacksServiceTestsAsync() {
        super(Application.class);
        Log.Instance = new AndroidLogProvider("ZZZ");
    }

    AndroidServiceClient client = new AndroidServiceClient("http://techstacks.io");

    public void test_Can_GET_TechStacks_Overview() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(new Overview(), new AsyncResult<OverviewResponse>(){
            @Override
            public void success(OverviewResponse response) {
                TechStacksServiceTests.assertOverviewResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Can_GET_TechStacks_AppOverview_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(new AppOverview(), new AsyncResult<AppOverviewResponse>(){
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

    public void test_Can_GET_TechStacks_Overview_with_relative_url_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("/overview", OverviewResponse.class, new AsyncResult<OverviewResponse>() {
            @Override
            public void success(OverviewResponse response) {
                TechStacksServiceTests.assertOverviewResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Can_GET_TechStacks_Overview_with_absolute_url_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("http://techstacks.io/overview", OverviewResponse.class, new AsyncResult<OverviewResponse>() {
            @Override
            public void success(OverviewResponse response) {
                TechStacksServiceTests.assertOverviewResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Can_GET_GetTechnology_with_params_Async() throws InterruptedException {
        GetTechnology requestDto = new GetTechnology()
            .setSlug("servicestack");

        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(requestDto, new AsyncResult<GetTechnologyResponse>() {
            @Override
            public void success(GetTechnologyResponse response) {
                TechStacksServiceTests.assertGetTechnologyResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Can_GET_GetTechnology_with_url_Async() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync("/technology/servicestack", GetTechnologyResponse.class, new AsyncResult<GetTechnologyResponse>() {
            @Override
            public void success(GetTechnologyResponse response) {
                TechStacksServiceTests.assertGetTechnologyResponse(response);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

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
}