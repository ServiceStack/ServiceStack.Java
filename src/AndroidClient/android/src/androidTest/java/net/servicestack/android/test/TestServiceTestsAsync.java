package net.servicestack.android.test;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.AsyncResultVoid;
import net.servicestack.client.ConnectionFilter;
import net.servicestack.client.ExceptionFilter;
import net.servicestack.client.HttpMethods;
import net.servicestack.client.ResponseStatus;
import net.servicestack.client.TimeSpan;
import net.servicestack.client.Utils;
import net.servicestack.client.WebServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static net.servicestack.android.test.dtos.AllCollectionTypes;
import static net.servicestack.android.test.dtos.AllTypes;
import static net.servicestack.android.test.dtos.Hello;
import static net.servicestack.android.test.dtos.HelloAllTypes;
import static net.servicestack.android.test.dtos.HelloAllTypesResponse;
import static net.servicestack.android.test.dtos.HelloResponse;
import static net.servicestack.android.test.dtos.HelloReturnVoid;
import static net.servicestack.android.test.dtos.Poco;
import static net.servicestack.android.test.dtos.SubType;
import static net.servicestack.android.test.dtos.ThrowType;
import static net.servicestack.android.test.dtos.ThrowTypeResponse;
import static net.servicestack.android.test.dtos.ThrowValidation;
import static net.servicestack.android.test.dtos.ThrowValidationResponse;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TestServiceTestsAsync {

    AndroidServiceClient client = new AndroidServiceClient("https://test.servicestack.net");

    @Test
    public void test_does_fire_Request_and_Response_Filters_Async() throws InterruptedException {

        AndroidServiceClient client = new AndroidServiceClient("https://test.servicestack.net");

        final ArrayList<String> events = new ArrayList<>();

        AndroidServiceClient.GlobalRequestFilter = new ConnectionFilter() {
            @Override public void exec(HttpURLConnection conn) {
                events.add("GlobalRequestFilter");
            }
        };
        AndroidServiceClient.GlobalResponseFilter = new ConnectionFilter() {
            @Override public void exec(HttpURLConnection conn) {
                events.add("GlobalResponseFilter");
            }
        };

        client.RequestFilter = new ConnectionFilter() {
            @Override public void exec(HttpURLConnection conn) {
                events.add("RequestFilter");
            }
        };
        client.ResponseFilter = new ConnectionFilter() {
            @Override public void exec(HttpURLConnection conn) {
                events.add("ResponseFilter");
            }
        };

        Hello request = new Hello()
                .setName("World");

        final CountDownLatch signal = new CountDownLatch(1);

        client.getAsync(request, new AsyncResult<HelloResponse>() {
            @Override public void success(HelloResponse response) {

                assertEquals("Hello, World!", response.getResult());

                String results = TextUtils.join(", ", events);

                assertEquals("RequestFilter, GlobalRequestFilter, ResponseFilter, GlobalResponseFilter", results);

                AndroidServiceClient.GlobalRequestFilter = null;
                AndroidServiceClient.GlobalResponseFilter = null;
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_POST_Test_HelloAllTypes_async() throws InterruptedException {
        final HelloAllTypes request = createHelloAllTypes();

        final CountDownLatch signal = new CountDownLatch(1);

        client.postAsync(request, new AsyncResult<HelloAllTypesResponse>() {
            @Override
            public void success(HelloAllTypesResponse response) {
                assertHelloAllTypesResponse(response, request);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Can_PUT_Test_HelloAllTypes_async() throws InterruptedException {
        final HelloAllTypes request = createHelloAllTypes();

        final CountDownLatch signal = new CountDownLatch(1);

        client.putAsync(request, new AsyncResult<HelloAllTypesResponse>() {
            @Override
            public void success(HelloAllTypesResponse response) {
                assertHelloAllTypesResponse(response, request);
            }

            @Override
            public void complete() {
                signal.countDown();
            }
        });

        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void test_Does_handle_404_Error_Async() throws InterruptedException {
        AndroidServiceClient testClient = new AndroidServiceClient("https://test.servicestack.net");

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

        assertEquals("NotFound", status.getErrorCode());
        assertEquals("not here", status.getMessage());
        assertNotNull(status.getStackTrace());
    }

    @Test
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

    @Test
    public void test_Can_send_ReturnVoid_Async(){
        final CountDownLatch signal = new CountDownLatch(1);

        final List<String> sentMethods = new ArrayList<>();
        client.RequestFilter = new ConnectionFilter() {
            @Override
            public void exec(HttpURLConnection conn) {
                sentMethods.add(conn.getRequestMethod());
            }
        };

        client.sendAsync(new HelloReturnVoid().setId(1), new AsyncResultVoid() {
            @Override
            public void success() {
                assertEquals(HttpMethods.Post, sentMethods.get(sentMethods.size() - 1));
                signal.countDown();
            }
        });
    }

    @Test
    public void test_Can_delete_ReturnVoid_Async(){
        final CountDownLatch signal = new CountDownLatch(1);

        final List<String> sentMethods = new ArrayList<>();
        client.RequestFilter = new ConnectionFilter() {
            @Override
            public void exec(HttpURLConnection conn) {
                sentMethods.add(conn.getRequestMethod());
            }
        };

        client.deleteAsync(new HelloReturnVoid().setId(1), new AsyncResultVoid() {
            @Override
            public void success() {
                assertEquals(HttpMethods.Delete, sentMethods.get(sentMethods.size() - 1));
                signal.countDown();
            }
        });
    }


    /* TEST HELPERS */
    public static HelloAllTypes createHelloAllTypes(){
        HelloAllTypes to = new HelloAllTypes()
                .setName("name")
                .setAllTypes(createAllTypes())
                .setAllCollectionTypes(createAllCollectionTypes());
        return to;
    }

    public static void assertHelloAllTypesResponse(HelloAllTypesResponse actual, HelloAllTypes expected) {
        assertNotNull(actual);
        assertAllTypes(actual.allTypes, expected.allTypes);
        assertAllCollectionTypes(actual.allCollectionTypes, expected.allCollectionTypes);
    }

    public static AllTypes createAllTypes() {
        AllTypes to = new AllTypes()
                .setId(1)
                .setChar("c")
                .setByte((short) 2)
                .setShort((short) 3)
                .setInt(4)
                .setLong((long) 5)
                .setUShort(6)
                .setUInt((long) 7)
                .setULong((BigInteger.valueOf(8)))
                .setFloat((float) 1.1)
                .setDouble(2.2)
                .setDecimal(new BigDecimal("3.0"))
                .setString("string")
                .setDateTime(new Date(101, 0, 1))
                .setDateTimeOffset(new Date(101, 0, 1))
                .setTimeSpan(new TimeSpan().addHours(1))
                .setGuid(UUID.randomUUID())
                .setStringList(Utils.createList("A", "B", "C"))
                .setStringArray(Utils.createList("D", "E", "F"))
                .setStringMap(Utils.createMap("A", "D", "B", "E", "C", "F"))
                .setIntStringMap(Utils.createMap(1, "A", 2, "B", 3, "C"))
                .setSubType(new SubType().setId(1).setName("name"));

        return to;
    }

    public static AllCollectionTypes createAllCollectionTypes(){
        AllCollectionTypes to = new AllCollectionTypes()
                .setIntArray(Utils.createList(1, 2, 3))
                .setIntList(Utils.createList(4, 5, 6))
                .setStringArray(Utils.createList("A", "B", "C"))
                .setStringList(Utils.createList("D","E","F"))
                .setPocoArray(Utils.createList(createPoco("pocoArray")))
                .setPocoList(Utils.createList(createPoco("pocoList")))
                .setPocoLookup(Utils.createMap("A", Utils.createList(createPoco("B"), createPoco("C"))))
                .setPocoLookupMap(Utils.createMap("A", Utils.createList(Utils.createMap("B", createPoco("C")), Utils.createMap("D", createPoco("E")))));
        return to;
    }

    public static Poco createPoco(String name){
        return new Poco().setName(name);
    }

    public static void assertAllTypes(AllTypes actual, AllTypes expected) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getByte(), actual.getByte());
        assertEquals(expected.getShort(), actual.getShort());
        assertEquals(expected.getInt(), actual.getInt());
        assertEquals(expected.getLong(), actual.getLong());
        assertEquals(expected.getUShort(), actual.getUShort());
        assertEquals(expected.getULong(), actual.getULong());
        assertEquals(expected.getFloat(), actual.getFloat());
        assertEquals(expected.getDouble(), actual.getDouble());
        assertEquals(expected.getDecimal(), actual.getDecimal());
        assertEquals(expected.getString(), actual.getString());
        assertEquals(expected.getDateTime(), actual.getDateTime());
        assertEquals(expected.getTimeSpan(), actual.getTimeSpan());
        assertEquals(expected.getGuid(), actual.getGuid());
        assertEquals(expected.getChar(), actual.getChar());
        assertEquals(expected.getStringArray(), actual.getStringArray());
        assertEquals(expected.getStringList(), actual.getStringList());

        assertEquals(expected.getStringMap(), actual.getStringMap());
        assertEquals(expected.getIntStringMap(), actual.getIntStringMap());

        assertEquals(expected.getSubType().getId(), actual.getSubType().getId());
        assertEquals(expected.getSubType().getName(), actual.getSubType().getName());
    }

    public static void assertAllCollectionTypes(AllCollectionTypes actual, AllCollectionTypes expected) {
        assertEquals(expected.getIntArray(), actual.getIntArray());
        assertEquals(expected.getIntList(), actual.getIntList());
        assertEquals(expected.getStringArray(), actual.getStringArray());
        assertEquals(expected.getStringList(), actual.getStringList());
        assertPocoEquals(expected.getPocoArray(), actual.getPocoArray());
        assertPocoEquals(expected.getPocoList(), actual.getPocoList());

        assertPocoLookupEquals(expected.getPocoLookup(), actual.getPocoLookup());
        assertPocoLookupMapEquals(expected.getPocoLookupMap(), actual.getPocoLookupMap());
    }

    public static void assertPocoEquals(List<Poco> expected, List<Poco> actual){
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertPocoEquals(expected.get(i), actual.get(i));
        }
    }

    public static void assertPocoLookupEquals(HashMap<String,ArrayList<Poco>> expected, HashMap<String,ArrayList<Poco>> actual){
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    public static void assertPocoLookupMapEquals(HashMap<String,ArrayList<HashMap<String, Poco>>> expected, HashMap<String,ArrayList<HashMap<String, Poco>>> actual){
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    public static void assertPocoEquals(ArrayList<HashMap<String, Poco>> expected, ArrayList<HashMap<String, Poco>> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertPocoEquals(expected.get(i), actual.get(i));
        }
    }

    public static void assertPocoEquals(HashMap<String, Poco> expected, HashMap<String, Poco> actual) {
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    public static void assertPocoEquals(Poco expected, Poco actual){
        assertNotNull(actual);
        assertEquals(actual.getName(), expected.getName());
    }
}