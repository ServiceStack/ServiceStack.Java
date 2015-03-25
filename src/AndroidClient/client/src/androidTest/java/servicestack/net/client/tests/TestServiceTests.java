//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package servicestack.net.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.android.AndroidLogProvider;
import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.Log;
import net.servicestack.client.MimeTypes;
import net.servicestack.client.TimeSpan;
import net.servicestack.client.Utils;

import servicestack.net.client.tests.testdtos.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TestServiceTests extends ApplicationTestCase<Application> {
    public TestServiceTests() {
        super(Application.class);
        Log.Instance = new AndroidLogProvider("ZZZ");
    }

    JsonServiceClient client = new JsonServiceClient("http://test.servicestack.net");
//    JsonServiceClient client = new JsonServiceClient("http://10.0.2.2:2020");

    public void test_Can_GET_Hello(){
        Hello request = new Hello()
                .setName("World");

        HelloResponse response = client.get(request);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_Can_GET_Hello_with_CustomPath(){
        HelloResponse response = client.get("/hello/World", HelloResponse.class);

        assertEquals("Hello, World!", response.getResult());
    }

    public void test_Can_POST_Hello_with_CustomPath(){
        HelloResponse response = client.post("/hello", new Hello().setName("World"), HelloResponse.class);

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
        HelloAllTypes request = createHelloAllTypes();
        HelloAllTypesResponse response = client.post(request);
        assertHelloAllTypesResponse(response, request);
    }

    public void test_Can_PUT_test_HelloAllTypes(){
        HelloAllTypes request = createHelloAllTypes();
        HelloAllTypesResponse response = client.put(request);
        assertHelloAllTypesResponse(response, request);
    }

    public void test_Can_Serailize_AllTypes(){
        String json = client.getGson().toJson(createAllTypes());
        Log.i(json);
    }

    /* TEST HELPERS */

    HelloAllTypes createHelloAllTypes(){
        HelloAllTypes to = new HelloAllTypes()
            .setName("name")
            .setAllTypes(createAllTypes())
            .setAllCollectionTypes(createAllCollectionTypes());
        return to;
    }
    private void assertHelloAllTypesResponse(HelloAllTypesResponse actual, HelloAllTypes expected) {
        assertNotNull(actual);
        assertAllTypes(actual.allTypes, expected.allTypes);
        assertAllCollectionTypes(actual.allCollectionTypes, expected.allCollectionTypes);
    }

    AllTypes createAllTypes() {
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
            .setStringList(createList("A", "B", "C"))
            .setStringArray(createList("D", "E", "F"))
            .setStringMap(createMap("A", "D", "B", "E", "C", "F"))
            .setIntStringMap(createMap(1, "A", 2, "B", 3, "C"))
            .setSubType(new SubType().setId(1).setName("name"));

        return to;
    }

    AllCollectionTypes createAllCollectionTypes(){
        AllCollectionTypes to = new AllCollectionTypes()
            .setIntArray(createList(1, 2, 3))
            .setIntList(createList(4, 5, 6))
            .setStringArray(createList("A", "B", "C"))
            .setStringList(createList("D","E","F"))
            .setPocoArray(createList(createPoco("pocoArray")))
            .setPocoList(createList(createPoco("pocoList")))
            .setPocoLookup(createMap("A", createList(createPoco("B"), createPoco("C"))))
            .setPocoLookupMap(createMap("A", createList(createMap("B", createPoco("C")), createMap("D", createPoco("E")))));
        return to;
    }

    Poco createPoco(String name){
        return new Poco().setName(name);
    }

    private void assertAllTypes(AllTypes actual, AllTypes expected) {
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

    private void assertAllCollectionTypes(AllCollectionTypes actual, AllCollectionTypes expected) {
        assertEquals(expected.getIntArray(), actual.getIntArray());
        assertEquals(expected.getIntList(), actual.getIntList());
        assertEquals(expected.getStringArray(), actual.getStringArray());
        assertEquals(expected.getStringList(), actual.getStringList());
        assertPocoEquals(expected.getPocoArray(), actual.getPocoArray());
        assertPocoEquals(expected.getPocoList(), actual.getPocoList());

        assertPocoLookupEquals(expected.getPocoLookup(), actual.getPocoLookup());
        assertPocoLookupMapEquals(expected.getPocoLookupMap(), actual.getPocoLookupMap());
    }

    void assertPocoEquals(List<Poco> expected, List<Poco> actual){
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertPocoEquals(expected.get(i), actual.get(i));
        }
    }

    void assertPocoLookupEquals(HashMap<String,ArrayList<Poco>> expected, HashMap<String,ArrayList<Poco>> actual){
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    void assertPocoLookupMapEquals(HashMap<String,ArrayList<HashMap<String,Poco>>> expected, HashMap<String,ArrayList<HashMap<String,Poco>>> actual){
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    private void assertPocoEquals(ArrayList<HashMap<String, Poco>> expected, ArrayList<HashMap<String, Poco>> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertPocoEquals(expected.get(i), actual.get(i));
        }
    }

    private void assertPocoEquals(HashMap<String, Poco> expected, HashMap<String, Poco> actual) {
        assertEquals(expected.size(), actual.size());
        for (String key : actual.keySet()) {
            assertPocoEquals(expected.get(key), actual.get(key));
        }
    }

    void assertPocoEquals(Poco expected, Poco actual){
        assertNotNull(actual);
        assertEquals(actual.getName(), expected.getName());
    }

    <T> ArrayList<T> createList(T... params) {
        ArrayList<T> to = new ArrayList<>();
        to.addAll(Arrays.asList(params));
        return to;
    }

    <K,V> HashMap<K,V> createMap(K k1, V v1) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        return to;
    }

    <K,V> HashMap<K,V> createMap(K k1, V v1, K k2, V v2) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        to.put(k2, v2);
        return to;
    }

    <K,V> HashMap<K,V> createMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        to.put(k2, v2);
        to.put(k3, v3);
        return to;
    }
}
