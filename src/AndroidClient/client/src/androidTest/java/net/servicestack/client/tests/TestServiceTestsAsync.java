package net.servicestack.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.android.*;
import net.servicestack.client.*;

import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import net.servicestack.client.tests.testdtos.*;

public class TestServiceTestsAsync extends ApplicationTestCase<Application> {
    public TestServiceTestsAsync() {
        super(Application.class);
        Log.Instance = new AndroidLogProvider("ZZZ");
    }

    AndroidServiceClient client = new AndroidServiceClient("http://test.servicestack.net");

    public void test_Can_POST_Test_HelloAllTypes_async() throws InterruptedException {
        final HelloAllTypes request = TestServiceTests.createHelloAllTypes();

        final CountDownLatch signal = new CountDownLatch(1);

        client.postAsync(request, new AsyncResult<HelloAllTypesResponse>() {
            @Override
            public void success(HelloAllTypesResponse response) {
                TestServiceTests.assertHelloAllTypesResponse(response, request);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    public void test_Can_PUT_Test_HelloAllTypes_async() throws InterruptedException {
        final HelloAllTypes request = TestServiceTests.createHelloAllTypes();

        final CountDownLatch signal = new CountDownLatch(1);

        client.putAsync(request, new AsyncResult<HelloAllTypesResponse>() {
            @Override
            public void success(HelloAllTypesResponse response) {
                TestServiceTests.assertHelloAllTypesResponse(response, request);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }


    public void test_Does_handle_404_Error_Async() throws InterruptedException {
        AndroidServiceClient testClient = new AndroidServiceClient("http://test.servicestack.net");

        final Exception[] globalError = new Exception[1]; //Wow Java, you suck.
        final Exception[] localError = new Exception[1];
        final WebServiceException[] thrownError = {null};

        AndroidServiceClient.GlobalExceptionFilter = new ExceptionFilter() {
            @Override
            public void exec(HttpURLConnection res, Exception ex) {
                globalError[0] = ex;
            }
        };

        testClient.ExceptionFilter = new ExceptionFilter() {
            @Override
            public void exec(HttpURLConnection res, Exception ex) {
                localError[0] = ex;
            }
        };

        ThrowType request = new ThrowType()
            .setType("NotFound")
            .setMessage("not here");


        final CountDownLatch signal = new CountDownLatch(1);

        testClient.putAsync(request, new AsyncResult<ThrowTypeResponse>() {
            @Override
            public void success(ThrowTypeResponse response) {
                fail("should not succeed");
            }

            @Override
            public void error(Exception ex) {
                thrownError[0] = (WebServiceException) ex;
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));

        assertNotNull(globalError[0]);
        assertNotNull(localError[0]);
        assertNotNull(thrownError[0]);

        ResponseStatus status = thrownError[0].getResponseStatus();

        assertEquals("not here", status.getErrorCode());
        assertEquals("not here", status.getMessage());
        assertNotNull(status.getStackTrace());
    }

    public void test_Does_handle_ValidationException_Async(){
        ThrowValidation request = new ThrowValidation()
            .setEmail("invalidemail");

        final CountDownLatch signal = new CountDownLatch(1);

        client.postAsync(request, new AsyncResult<ThrowValidationResponse>() {
            @Override
            public void success(ThrowValidationResponse response) {
                fail("should not succeed");
            }

            @Override
            public void error(Exception ex) {
                WebServiceException webEx = (WebServiceException)ex;
                ResponseStatus status = webEx.getResponseStatus();

                assertNotNull(status);
                assertEquals(3, status.getErrors().size());

                assertEquals(status.getErrors().get(0).getErrorCode(), status.getErrorCode());
                assertEquals(status.getErrors().get(0).getMessage(), status.getMessage());

                assertEquals("InclusiveBetween", status.getErrors().get(0).errorCode);
                assertEquals("'Age' must be between 1 and 120. You entered 0.", status.getErrors().get(0).getMessage());
                assertEquals("Age", status.getErrors().get(0).getFieldName());

                assertEquals("NotEmpty", status.getErrors().get(1).errorCode);
                assertEquals("'Required' should not be empty.", status.getErrors().get(1).getMessage());
                assertEquals("Required", status.getErrors().get(1).getFieldName());

                assertEquals("Email", status.getErrors().get(2).errorCode);
                assertEquals("'Email' is not a valid email address.", status.getErrors().get(2).getMessage());
                assertEquals("Email", status.getErrors().get(2).getFieldName());
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });
    }


    /* TEST HELPERS */
}