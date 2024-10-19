//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.

package test.servicestack.net.kotlin.test

import junit.framework.Assert
import junit.framework.TestCase
import net.servicestack.client.*
import test.servicestack.net.kotlin.techstacks.LockTechStack
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

class TestServiceTests : TestCase() {

    internal var client = JsonServiceClient("https://test.servicestack.net")
    //    JsonServiceClient client = new JsonServiceClient("http://10.0.2.2:2020");

    fun test_Can_GET_Hello() {
        val request = Hello()
        request.name = "World"

        val response = client.get<HelloResponse>(request)

        assertEquals("Hello, World!", response.result)
    }

    fun test_does_fire_Request_and_Response_Filters() {

        val client = JsonServiceClient("https://test.servicestack.net")

        val events = ArrayList<String>()

        JsonServiceClient.GlobalRequestFilter = ConnectionFilter { events.add("GlobalRequestFilter") }
        JsonServiceClient.GlobalResponseFilter = ConnectionFilter { events.add("GlobalResponseFilter") }

        client.RequestFilter = ConnectionFilter { events.add("RequestFilter") }
        client.ResponseFilter = ConnectionFilter { events.add("ResponseFilter") }

        val request = Hello()
        request.name = "World"

        val response = client.get<HelloResponse>(request)

        assertEquals("Hello, World!", response.result)

        val results = strJoin(", ", events)

        assertEquals("RequestFilter, GlobalRequestFilter, ResponseFilter, GlobalResponseFilter", results)
    }

    fun test_Can_GET_Hello_with_CustomPath() {
        val response = client.get<HelloResponse>("/hello/World", HelloResponse::class.java)

        assertEquals("Hello, World!", response.result)
    }

    fun test_Can_POST_Hello_with_CustomPath() {
        val request = Hello()
        request.name = "World"

        val response = client.post<HelloResponse>("/hello", request, HelloResponse::class.java)

        assertEquals("Hello, World!", response.result)
    }

    fun test_Can_GET_Hello_with_CustomPath_raw() {
        val response = client.get("/hello/World")
        val json = Utils.readToEnd(response)

        assertEquals("{\"result\":\"Hello, World!\"}", json)
    }

    fun test_Can_POST_Hello_with_CustomPath_raw() {
        val response = client.post("/hello", Utils.toUtf8Bytes("Name=World"), MimeTypes.FormUrlEncoded)
        val json = Utils.readToEnd(response)

        assertEquals("{\"result\":\"Hello, World!\"}", json)
    }

    fun test_Can_POST_test_HelloAllTypes() {
        val request = createHelloAllTypes()
        val response = client.post<HelloAllTypesResponse>(request)
        assertHelloAllTypesResponse(response, request)
    }

    fun test_Can_PUT_test_HelloAllTypes() {
        val request = createHelloAllTypes()
        val response = client.put<HelloAllTypesResponse>(request)
        assertHelloAllTypesResponse(response, request)
    }

    fun test_Can_Serailize_AllTypes() {
        val json = client.gson.toJson(createAllTypes())
        Log.i(json)
    }

    fun test_Does_handle_404_Error() {
        val testClient = JsonServiceClient("https://test.servicestack.net")

        var globalError:Exception? = null
        var localError:Exception? = null
        var thrownError: WebServiceException? = null

        JsonServiceClient.GlobalExceptionFilter = ExceptionFilter { res, ex -> globalError = ex }

        testClient.ExceptionFilter = ExceptionFilter { res, ex -> localError = ex }

        val request = ThrowType()
        request.Type = "NotFound"
        request.message = "not here"

        try {
            val response = testClient.put<ThrowTypeResponse>(request)
        } catch (webEx: WebServiceException) {
            thrownError = webEx
        }

        assertNotNull(globalError)
        assertNotNull(localError)
        assertNotNull(thrownError)

        val status = thrownError!!.responseStatus

        assertEquals("NotFound", status.errorCode)
        assertEquals("not here", status.message)
        assertNotNull(status.stackTrace)
    }

    fun test_Does_handle_ValidationException() {
        val request = ThrowValidation()
        request.email = "invalidemail"

        try {
            client.post<ThrowValidationResponse>(request)
            fail("Should throw")
        } catch (webEx: WebServiceException) {
            val status = webEx.responseStatus

            assertNotNull(status)
            assertEquals(3, status.getErrors().size)

            assertEquals(status.errors[0].errorCode, status.errorCode)
            assertEquals(status.errors[0].message, status.message)

            assertEquals("InclusiveBetween", status.errors[0].errorCode)
            assertEquals("'Age' must be between 1 and 120. You entered 0.", status.errors[0].message)
            assertEquals("Age", status.errors[0].fieldName)

            assertEquals("NotEmpty", status.errors[1].errorCode)
            assertEquals("'Required' must not be empty.", status.errors[1].message)
            assertEquals("Required", status.errors[1].fieldName)

            assertEquals("Email", status.errors[2].errorCode)
            assertEquals("'Email' is not a valid email address.", status.errors[2].message)
            assertEquals("Email", status.errors[2].fieldName)
        }

    }

    fun test_Can_POST_valid_ThrowValidation_request() {
        val request = ThrowValidation()
        request.age = 21
        request.required = "foo"
        request.email = "my@gmail.com"

        val response = client.post<ThrowValidationResponse>(request)

        assertNotNull(response)
        assertEquals(request.age, response.age)
        assertEquals(request.required, response.required)
        assertEquals(request.email, response.email)
    }

    fun test_does_handle_auth_failure() {
        val techStacksClient = JsonServiceClient("https://techstacks.io")
        var errorCode = 0
        try {
            val request = LockTechStack()
            request.technologyStackId = 6.toLong()
            val res = techStacksClient.post(request)
            fail("Should throw")
        } catch (ex: WebServiceException) {
            //private StatusCode has correct code, response status is null due to empty response body.
            errorCode = ex.statusCode
        }

        assertEquals(errorCode, 401)
    }

    fun test_Can_send_ReturnVoid() {
        val sentMethods = ArrayList<String>()
        client.RequestFilter = ConnectionFilter { conn -> sentMethods.add(conn.requestMethod) }

        val request = HelloReturnVoid()
        request.id = 1

        client.send(request)
        assertEquals(HttpMethods.Post, sentMethods[sentMethods.size - 1])
        request.id = 2
        client.get(request)
        assertEquals(HttpMethods.Get, sentMethods[sentMethods.size - 1])
        request.id = 3
        client.post(request)
        assertEquals(HttpMethods.Post, sentMethods[sentMethods.size - 1])
        request.id = 4
        client.put(request)
        assertEquals(HttpMethods.Put, sentMethods[sentMethods.size - 1])
        request.id = 5
        client.delete(request)
        assertEquals(HttpMethods.Delete, sentMethods[sentMethods.size - 1])
    }

    fun test_Can_get_response_as_Raw_String() {
        val request = HelloString()
        request.name = "World"
        val response = client.get(request)
        assertEquals("World", response)
    }

    fun test_Can_get_response_as_Raw_Bytes() {
        val response = client.get<ByteArray>("/json/reply/HelloString?Name=World", ByteArray::class.java)
        assertEquals("World", Utils.fromUtf8Bytes(response))
    }

    companion object {

        /* TEST HELPERS */

        fun createHelloAllTypes(): HelloAllTypes {
            val to = HelloAllTypes()
            to.name = "name"
            to.allTypes = createAllTypes()
            to.allCollectionTypes = createAllCollectionTypes()
            return to
        }

        fun assertHelloAllTypesResponse(actual: HelloAllTypesResponse, expected: HelloAllTypes) {
            assertNotNull(actual)
            assertAllTypes(actual.allTypes!!, expected.allTypes!!)
            assertAllCollectionTypes(actual.allCollectionTypes!!, expected.allCollectionTypes!!)
        }

        fun createAllTypes(): AllTypes {
            val to = AllTypes()
            to.id = 1
            to.Char = "c"
            to.Byte = 2.toShort()
            to.Short = 3.toShort()
            to.Int = 4
            to.Long = 5.toLong()
            to.uShort = (6)
            to.uInt = 7.toLong()
            to.uLong = BigInteger.valueOf(8)
            to.Float = 1.1.toFloat()
            to.Double = 2.2
            to.decimal = BigDecimal("3.0")
            to.string = "string"
            to.dateTime = Date(101, 0, 1)
            to.dateTimeOffset = Date(101, 0, 1)
            to.timeSpan = TimeSpan().addHours(1)
            to.guid = UUID.randomUUID()
            to.stringList = Utils.createList("A", "B", "C")
            to.stringArray = Utils.createList("D", "E", "F")
            to.stringMap = Utils.createMap("A", "D", "B", "E", "C", "F")
            to.intStringMap = Utils.createMap(1, "A", 2, "B", 3, "C")
            to.subType = SubType()
            to.subType!!.id = (1)
            to.subType!!.name = "name"

            return to
        }

        fun createAllCollectionTypes(): AllCollectionTypes {
            val to = AllCollectionTypes()
            to.intArray = Utils.createList(1, 2, 3)
            to.intList = Utils.createList(4, 5, 6)
            to.stringArray = Utils.createList("A", "B", "C")
            to.stringList = Utils.createList("D", "E", "F")
            to.pocoArray =Utils.createList<Poco>(createPoco("pocoArray"))
            to.pocoList = Utils.createList<Poco>(createPoco("pocoList"))
            to.pocoLookup = Utils.createMap("A", Utils.createList<Poco>(createPoco("B"), createPoco("C")))
            to.pocoLookupMap = Utils.createMap("A", Utils.createList(Utils.createMap("B", createPoco("C")), Utils.createMap("D", createPoco("E"))))
            return to
        }

        fun createPoco(name: String): Poco {
            val to = Poco()
            to.name = name
            return to
        }

        fun assertAllTypes(actual: AllTypes, expected: AllTypes) {
            assertEquals(expected.id, actual.id)
            assertEquals(expected.Byte, actual.Byte)
            assertEquals(expected.Short, actual.Short)
            assertEquals(expected.Int, actual.Int)
            assertEquals(expected.Long, actual.Long)
            assertEquals(expected.uShort, actual.uShort)
            assertEquals(expected.uLong, actual.uLong)
            assertEquals(expected.Float, actual.Float)
            assertEquals(expected.Double, actual.Double)
            assertEquals(expected.decimal, actual.decimal)
            assertEquals(expected.string, actual.string)
            assertEquals(expected.dateTime, actual.dateTime)
            assertEquals(expected.timeSpan, actual.timeSpan)
            assertEquals(expected.guid, actual.guid)
            assertEquals(expected.Char, actual.Char)
            assertEquals(expected.stringArray, actual.stringArray)
            assertEquals(expected.stringList, actual.stringList)

            assertEquals(expected.stringMap, actual.stringMap)
            assertEquals(expected.intStringMap, actual.intStringMap)

            assertEquals(expected.subType?.id, actual.subType?.id)
            assertEquals(expected.subType?.name, actual.subType?.name)
        }

        fun assertAllCollectionTypes(actual: AllCollectionTypes, expected: AllCollectionTypes) {
            assertEquals(expected.intArray, actual.intArray)
            assertEquals(expected.intList, actual.intList)
            assertEquals(expected.stringArray, actual.stringArray)
            assertEquals(expected.stringList, actual.stringList)
            assertPocoEquals(expected.pocoArray!!, actual.pocoArray!!)
            assertPocoEquals(expected.pocoList!!, actual.pocoList!!)

            assertPocoLookupEquals(expected.pocoLookup!!, actual.pocoLookup!!)
            assertPocoLookupMapEquals(expected.pocoLookupMap!!, actual.pocoLookupMap!!)
        }

        fun assertPocoEquals(expected: List<Poco>, actual: List<Poco>) {
            assertEquals(expected.size, actual.size)
            for (i in actual.indices) {
                assertPocoEquals(expected[i], actual[i])
            }
        }

        fun assertPocoLookupEquals(expected: HashMap<String, ArrayList<Poco>>, actual: HashMap<String, ArrayList<Poco>>) {
            assertEquals(expected.size, actual.size)
            for (key in actual.keys) {
                assertPocoEquals(expected[key]!!, actual[key]!!)
            }
        }

        fun assertPocoLookupMapEquals(expected: HashMap<String, ArrayList<HashMap<String, Poco>>>, actual: HashMap<String, ArrayList<HashMap<String, Poco>>>) {
            assertEquals(expected.size, actual.size)
            for (key in actual.keys) {
                assertPocoEquals(expected[key]!!, actual[key]!!)
            }
        }

        fun assertPocoEquals(expected: ArrayList<HashMap<String, Poco>>, actual: ArrayList<HashMap<String, Poco>>) {
            assertEquals(expected.size, actual.size)
            for (i in actual.indices) {
                assertPocoEquals(expected[i], actual[i])
            }
        }

        fun assertPocoEquals(expected: HashMap<String, Poco>, actual: HashMap<String, Poco>) {
            assertEquals(expected.size, actual.size)
            for (key in actual.keys) {
                assertPocoEquals(expected[key]!!, actual[key]!!)
            }
        }

        fun assertPocoEquals(expected: Poco, actual: Poco) {
            assertNotNull(actual)
            assertEquals(actual.name, expected.name)
        }

        fun strJoin(sSep: String, aArr: ArrayList<String>): String {
            val sbStr = StringBuilder()
            var i = 0
            val il = aArr.size
            while (i < il) {
                if (i > 0)
                    sbStr.append(sSep)
                sbStr.append(aArr[i])
                i++
            }
            return sbStr.toString()
        }
    }
}//Log.Instance = new AndroidLogProvider("ZZZ");
