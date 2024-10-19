//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.

package net.servicestack.client;

import io.techstacks.dto;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;

public class TestServiceTests extends TestCase {
    public TestServiceTests() {
        //Log.Instance = new AndroidLogProvider("ZZZ");
    }

    JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net");
//    JsonServiceClient client = new JsonServiceClient("http://10.0.2.2:2020");

    public void test_Can_GET_Hello(){
        testdtos.Hello request = new testdtos.Hello()
                .setName("World");

        testdtos.HelloResponse response = client.get(request);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_Can_send_escaped_chars_in_String(){
        testdtos.Hello request = new testdtos.Hello()
                .setName("Number='001'");

        testdtos.HelloResponse response = client.get(request);

        assertEquals("Hello, Number='001'!", response.getResult());
    }

    public void test_does_fire_Request_and_Response_Filters(){

        JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net");

        final ArrayList<String> events = new ArrayList<>();

        JsonServiceClient.GlobalRequestFilter = new ConnectionFilter() {
            @Override public void exec(HttpURLConnection conn) {
                events.add("GlobalRequestFilter");
            }
        };
        JsonServiceClient.GlobalResponseFilter = new ConnectionFilter() {
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

        testdtos.Hello request = new testdtos.Hello()
                .setName("World");

        testdtos.HelloResponse response = client.get(request);

        assertEquals("Hello, World!", response.getResult());

        String results = strJoin(", ", events);

        assertEquals("RequestFilter, GlobalRequestFilter, ResponseFilter, GlobalResponseFilter", results);
    }

    public void test_Can_GET_Hello_with_CustomPath(){
        testdtos.HelloResponse response = client.get("/hello/World", testdtos.HelloResponse.class);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_Can_POST_Hello_with_CustomPath(){
        testdtos.HelloResponse response = client.post("/hello", new testdtos.Hello().setName("World"), testdtos.HelloResponse.class);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_Can_GET_Hello_with_CustomPath_raw(){
        HttpURLConnection response = client.get("/hello/World");
        String json = Utils.readToEnd(response);

        assertEquals("{\"result\":\"Hello, World!\"}", json);
    }

    public void test_Can_POST_Hello_with_CustomPath_raw(){
        HttpURLConnection response = client.post("/hello", Utils.toUtf8Bytes("Name=World"), MimeTypes.FormUrlEncoded);
        String json = Utils.readToEnd(response);

        assertEquals("{\"result\":\"Hello, World!\"}", json);
    }

    public void test_Can_POST_test_HelloAllTypes(){
        testdtos.HelloAllTypes request = createHelloAllTypes();
        testdtos.HelloAllTypesResponse response = client.post(request);
        assertHelloAllTypesResponse(response, request);
    }

    public void test_Can_PUT_test_HelloAllTypes(){
        testdtos.HelloAllTypes request = createHelloAllTypes();
        testdtos.HelloAllTypesResponse response = client.put(request);
        assertHelloAllTypesResponse(response, request);
    }

    public void test_Can_Serailize_AllTypes(){
        String json = client.getGson().toJson(createAllTypes());
        Log.i(json);
    }

    public void test_Does_handle_404_Error() {
        JsonServiceClient testClient = new JsonServiceClient("https://test.servicestack.net");

        final Exception[] globalError = new Exception[1]; //Wow Java, you suck.
        final Exception[] localError = new Exception[1];
        WebServiceException thrownError = null;

        JsonServiceClient.GlobalExceptionFilter = new ExceptionFilter() {
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

        testdtos.ThrowType request = new testdtos.ThrowType()
            .setType("NotFound")
            .setMessage("not here");

        try {
            testdtos.ThrowTypeResponse response = testClient.put(request);
        }
        catch (WebServiceException webEx){
            thrownError = webEx;
        }

        assertNotNull(globalError[0]);
        assertNotNull(localError[0]);
        assertNotNull(thrownError);

        ResponseStatus status = thrownError.getResponseStatus();

        assertEquals("NotFound", status.getErrorCode());
        assertEquals("not here", status.getMessage());
        assertNotNull(status.getStackTrace());
    }

    @Ignore //test fails in CI
    public void IGNORE_test_Does_handle_401_Error_with_empty_ResponseBody() {
        JsonServiceClient testClient = new JsonServiceClient("https://test.servicestack.net");

        final Exception[] globalError = new Exception[1]; //Wow Java, you suck.
        final Exception[] localError = new Exception[1];
        WebServiceException thrownError = null;

        JsonServiceClient.GlobalExceptionFilter = new ExceptionFilter() {
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

        testdtos.TestAuth request = new testdtos.TestAuth();

        try {
            testdtos.TestAuthResponse response = testClient.send(request);
        }
        catch (WebServiceException webEx){
            thrownError = webEx;
        }

        assertNotNull(globalError[0]);
        assertNotNull(localError[0]);
        assertNotNull(thrownError);

        ResponseStatus status = thrownError.getResponseStatus();
        assertNull(status);
    }

    public void test_Does_handle_ValidationException(){
        testdtos.ThrowValidation request = new testdtos.ThrowValidation()
            .setEmail("invalidemail");

        try {
            client.post(request);
            fail("Should throw");
        } catch (WebServiceException webEx){
            ResponseStatus status = webEx.getResponseStatus();

            assertNotNull(status);
            assertEquals(3, status.getErrors().size());

            assertEquals(status.getErrors().get(0).getErrorCode(), status.getErrorCode());
            assertEquals(status.getErrors().get(0).getMessage(), status.getMessage());

            assertEquals("InclusiveBetween", status.getErrors().get(0).getErrorCode());
            assertEquals("'Age' must be between 1 and 120. You entered 0.", status.getErrors().get(0).getMessage());
            assertEquals("Age", status.getErrors().get(0).getFieldName());

            assertEquals("NotEmpty", status.getErrors().get(1).errorCode);
            assertEquals("'Required' must not be empty.", status.getErrors().get(1).getMessage());
            assertEquals("Required", status.getErrors().get(1).getFieldName());

            assertEquals("Email", status.getErrors().get(2).errorCode);
            assertEquals("'Email' is not a valid email address.", status.getErrors().get(2).getMessage());
            assertEquals("Email", status.getErrors().get(2).getFieldName());
        }
    }

    public void test_Can_POST_valid_ThrowValidation_request() {
        testdtos.ThrowValidation request = new testdtos.ThrowValidation()
            .setAge(21)
            .setRequired("foo")
            .setEmail("my@gmail.com");

        testdtos.ThrowValidationResponse response = client.post(request);

        assertNotNull(response);
        assertEquals(request.getAge(), response.getAge());
        assertEquals(request.getRequired(), response.getRequired());
        assertEquals(request.getEmail(), response.getEmail());
    }

    @Ignore //test fails in CI
    public void IGNORE_test_does_handle_auth_failure() {
        JsonServiceClient techStacksClient = new JsonServiceClient("https://techstacks.io");
        int errorCode = 0;
        try {
            dto.LockTechStack request = new dto.LockTechStack();
            request.setTechnologyStackId((long)6);
            dto.LockStackResponse res = techStacksClient.post(request);
            fail("Should throw");
        } catch(WebServiceException ex) {
            //private StatusCode has correct code, response status is null due to empty response body.
            errorCode = ex.getStatusCode();
        }
        assertEquals(errorCode,401);
    }

    public void test_Can_send_ReturnVoid(){
        final List<String> sentMethods = new ArrayList<>();
        client.RequestFilter = new ConnectionFilter() {
            @Override
            public void exec(HttpURLConnection conn) {
                sentMethods.add(conn.getRequestMethod());
            }
        };

        client.send(new testdtos.HelloReturnVoid().setId(1));
        assertEquals(HttpMethods.Post, sentMethods.get(sentMethods.size() - 1));
        client.get(new testdtos.HelloReturnVoid().setId(2));
        assertEquals(HttpMethods.Get, sentMethods.get(sentMethods.size() - 1));
        client.post(new testdtos.HelloReturnVoid().setId(3));
        assertEquals(HttpMethods.Post, sentMethods.get(sentMethods.size() - 1));
        client.put(new testdtos.HelloReturnVoid().setId(4));
        assertEquals(HttpMethods.Put, sentMethods.get(sentMethods.size() - 1));
        client.delete(new testdtos.HelloReturnVoid().setId(5));
        assertEquals(HttpMethods.Delete, sentMethods.get(sentMethods.size() - 1));
    }

    public void test_Can_get_response_as_Raw_String(){
        String response = client.get(new testdtos.HelloString().setName("World"));
        assertEquals("World", response);
    }

    public void test_Can_get_response_as_Raw_Bytes() {
        byte[] response = client.get("/json/reply/HelloString?Name=World", byte[].class);
        assertEquals("World", Utils.fromUtf8Bytes(response));
    }

    public void test_Can_get_response_as_Stream() throws IOException {
        InputStream is = client.get("/json/reply/HelloString?Name=World", InputStream.class);
        byte[] response = Utils.readBytesToEnd(is);
        is.close();
        assertEquals("World", Utils.fromUtf8Bytes(response));
    }

    public void test_Can_post_data_and_read_as_Stream() throws IOException {
        testdtos.ReturnStream req = new testdtos.ReturnStream();
        req.setData(Utils.fromByteArray("QUJD")); // base64("ABC")

        InputStream is = client.post(req);
        byte[] response = Utils.readBytesToEnd(is);
        is.close();
        assertEquals("ABC", Utils.fromUtf8Bytes(response));
    }

    /* TEST HELPERS */

    public static testdtos.HelloAllTypes createHelloAllTypes(){
        testdtos.HelloAllTypes to = new testdtos.HelloAllTypes()
            .setName("name")
            .setAllTypes(createAllTypes())
            .setAllCollectionTypes(createAllCollectionTypes());
        return to;
    }

    public static void assertHelloAllTypesResponse(testdtos.HelloAllTypesResponse actual, testdtos.HelloAllTypes expected) {
        assertNotNull(actual);
        assertAllTypes(actual.allTypes, expected.allTypes);
        assertAllCollectionTypes(actual.allCollectionTypes, expected.allCollectionTypes);
    }

    public static testdtos.AllTypes createAllTypes() {
        testdtos.AllTypes to = new testdtos.AllTypes()
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
            .setKeyValuePair(new testdtos.KeyValuePair<String,String>().setKey("foo").setValue("bar"))
            .setStringList(Utils.createList("A", "B", "C"))
            .setStringArray(Utils.createList("D", "E", "F"))
            .setStringMap(Utils.createMap("A", "D", "B", "E", "C", "F"))
            .setIntStringMap(Utils.createMap(1, "A", 2, "B", 3, "C"))
            .setSubType(new testdtos.SubType().setId(1).setName("name"));

        return to;
    }

    public static testdtos.AllCollectionTypes createAllCollectionTypes(){
        testdtos.AllCollectionTypes to = new testdtos.AllCollectionTypes()
            .setIntArray(Utils.createList(1, 2, 3))
            .setIntList(Utils.createList(4, 5, 6))
            .setStringArray(Utils.createList("A", "B", "C"))
            .setStringList(Utils.createList("D","E","F"))
            .setByteArray(Utils.fromByteArray("QUJD")) //ABC
            .setPocoArray(Utils.createList(createPoco("pocoArray")))
            .setPocoList(Utils.createList(createPoco("pocoList")))
            .setPocoLookup(Utils.createMap("A", Utils.createList(createPoco("B"), createPoco("C"))))
            .setPocoLookupMap(Utils.createMap("A", Utils.createList(Utils.createMap("B", createPoco("C")), Utils.createMap("D", createPoco("E")))));
        return to;
    }

    public static testdtos.Poco createPoco(String name){
        return new testdtos.Poco().setName(name);
    }

    public static void assertAllTypes(testdtos.AllTypes actual, testdtos.AllTypes expected) {
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
        assertEquals(expected.getKeyValuePair().getKey(), actual.getKeyValuePair().getKey());
        assertEquals(expected.getKeyValuePair().getValue(), actual.getKeyValuePair().getValue());
        assertEquals(expected.getChar(), actual.getChar());
        assertEquals(expected.getStringArray(), actual.getStringArray());
        assertEquals(expected.getStringList(), actual.getStringList());

        assertEquals(expected.getStringMap(), actual.getStringMap());
        assertEquals(expected.getIntStringMap(), actual.getIntStringMap());

        assertEquals(expected.getSubType().getId(), actual.getSubType().getId());
        assertEquals(expected.getSubType().getName(), actual.getSubType().getName());
    }

    public static void assertAllCollectionTypes(testdtos.AllCollectionTypes actual, testdtos.AllCollectionTypes expected) {
        assertEquals(expected.getIntArray(), actual.getIntArray());
        assertEquals(expected.getIntList(), actual.getIntList());
        assertEquals(expected.getStringArray(), actual.getStringArray());
        assertEquals(expected.getStringList(), actual.getStringList());
        assertArrayEquals(expected.getByteArray(), actual.getByteArray());
        assertPocoEquals(expected.getPocoArray(), actual.getPocoArray());
        assertPocoEquals(expected.getPocoList(), actual.getPocoList());

        assertPocoLookupEquals(expected.getPocoLookup(), actual.getPocoLookup());
        assertPocoLookupMapEquals(expected.getPocoLookupMap(), actual.getPocoLookupMap());
    }

    public static void assertPocoEquals(List<testdtos.Poco> expected, List<testdtos.Poco> actual){
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertPocoEquals(expected.get(i), actual.get(i));
        }
    }

    public static void assertPocoLookupEquals(HashMap<String,ArrayList<testdtos.Poco>> expected, HashMap<String,ArrayList<testdtos.Poco>> actual){
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    public static void assertPocoLookupMapEquals(HashMap<String,ArrayList<HashMap<String,testdtos.Poco>>> expected, HashMap<String,ArrayList<HashMap<String,testdtos.Poco>>> actual){
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    public static void assertPocoEquals(ArrayList<HashMap<String, testdtos.Poco>> expected, ArrayList<HashMap<String, testdtos.Poco>> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertPocoEquals(expected.get(i), actual.get(i));
        }
    }

    public static void assertPocoEquals(HashMap<String, testdtos.Poco> expected, HashMap<String, testdtos.Poco> actual) {
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    public static void assertPocoEquals(testdtos.Poco expected, testdtos.Poco actual){
        assertNotNull(actual);
        assertEquals(actual.getName(), expected.getName());
    }

    public static String strJoin(String sSep,ArrayList<String> aArr) {
        StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = aArr.size(); i < il; i++) {
            if (i > 0)
                sbStr.append(sSep);
            sbStr.append(aArr.get(i));
        }
        return sbStr.toString();
    }
}
