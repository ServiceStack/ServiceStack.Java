//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.test

import android.app.Application
import android.test.ApplicationTestCase
import android.text.TextUtils
import junit.framework.Assert
import net.servicestack.android.AndroidLogProvider
import net.servicestack.android.AndroidServiceClient
import net.servicestack.client.*
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class TestServiceTestsAsync : ApplicationTestCase<Application>(Application::class.java) {
    init {
        Log.Instance = AndroidLogProvider("ZZZ")
    }

    internal var client = AndroidServiceClient("http://test.servicestack.net")

    @Throws(InterruptedException::class)
    fun test_does_fire_Request_and_Response_Filters_Async() {

        val client = AndroidServiceClient("http://test.servicestack.net")

        val events = ArrayList<String>()

        AndroidServiceClient.GlobalRequestFilter = ConnectionFilter { events.add("GlobalRequestFilter") }
        AndroidServiceClient.GlobalResponseFilter = ConnectionFilter { events.add("GlobalResponseFilter") }

        client.RequestFilter = ConnectionFilter { events.add("RequestFilter") }
        client.ResponseFilter = ConnectionFilter { events.add("ResponseFilter") }

        val request = Hello()
        request.name = "World"

        val signal = CountDownLatch(1)

        client.getAsync<HelloResponse>(request, AsyncSuccess<HelloResponse> {
            Assert.assertEquals("Hello, World!", it.result)

            val results = TextUtils.join(", ", events)

            Assert.assertEquals("RequestFilter, GlobalRequestFilter, ResponseFilter, GlobalResponseFilter", results)

            AndroidServiceClient.GlobalRequestFilter = null
            AndroidServiceClient.GlobalResponseFilter = null
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_POST_Test_HelloAllTypes_async() {
        val request = createHelloAllTypes()

        val signal = CountDownLatch(1)

        client.postAsync<HelloAllTypesResponse>(request, AsyncSuccess<HelloAllTypesResponse> {
            assertHelloAllTypesResponse(it, request)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Can_PUT_Test_HelloAllTypes_async() {
        val request = createHelloAllTypes()

        val signal = CountDownLatch(1)

        client.putAsync<HelloAllTypesResponse>(request, AsyncSuccess<HelloAllTypesResponse> {
            assertHelloAllTypesResponse(it, request)
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))
    }

    @Throws(InterruptedException::class)
    fun test_Does_handle_404_Error_Async() {
        val testClient = AndroidServiceClient("http://test.servicestack.net")

        var globalError:Exception? = null
        var localError:Exception? = null
        var thrownError:WebServiceException? = null

        AndroidServiceClient.GlobalExceptionFilter = ExceptionFilter { res, ex -> globalError = ex }

        testClient.ExceptionFilter = ExceptionFilter { res, ex -> localError = ex }

        val request = ThrowType()
        request.Type = "NotFound"
        request.message = "not here"


        val signal = CountDownLatch(1)

        testClient.putAsync<ThrowTypeResponse>(request, AsyncSuccess<ThrowTypeResponse> {
            Assert.fail("should not succeed")
        }, AsyncError {
            thrownError = it as WebServiceException
            signal.countDown()
        })

        Assert.assertTrue(signal.await(5, TimeUnit.SECONDS))

        Assert.assertNotNull(globalError)
        Assert.assertNotNull(localError)
        Assert.assertNotNull(thrownError)

        val status = thrownError!!.responseStatus!!

        Assert.assertEquals("NotFound", status.errorCode)
        Assert.assertEquals("not here", status.message)
        Assert.assertNotNull(status.stackTrace)
    }

    fun test_Does_handle_ValidationException_Async() {
        val request = ThrowValidation()
        request.email = "invalidemail"

        val signal = CountDownLatch(1)

        client.postAsync<ThrowValidationResponse>(request, AsyncSuccess<ThrowValidationResponse> {
            Assert.fail("should not succeed")
        }, AsyncError {
            val webEx = it as WebServiceException
            val status = webEx.responseStatus!!

            Assert.assertEquals(3, status.errors.size)

            Assert.assertEquals(status.errors[0].errorCode, status.errorCode)
            Assert.assertEquals(status.errors[0].message, status.message)

            Assert.assertEquals("InclusiveBetween", status.errors[0].errorCode)
            Assert.assertEquals("'Age' must be between 1 and 120. You entered 0.", status.errors[0].message)
            Assert.assertEquals("Age", status.errors[0].fieldName)

            Assert.assertEquals("NotEmpty", status.errors[1].errorCode)
            Assert.assertEquals("'Required' should not be empty.", status.errors[1].message)
            Assert.assertEquals("Required", status.errors[1].fieldName)

            Assert.assertEquals("Email", status.errors[2].errorCode)
            Assert.assertEquals("'Email' is not a valid email address.", status.errors[2].message)
            Assert.assertEquals("Email", status.errors[2].fieldName)
            signal.countDown()
        })
    }

    fun test_Can_send_ReturnVoid_Async() {
        val signal = CountDownLatch(1)

        val sentMethods = ArrayList<String>()
        client.RequestFilter = ConnectionFilter { conn -> sentMethods.add(conn.requestMethod) }

        var request = HelloReturnVoid()
        request.id = 1

        client.sendAsync(request, {
            Assert.assertEquals(HttpMethods.Post, sentMethods[sentMethods.size - 1])
            signal.countDown()
        })
    }

    fun test_Can_delete_ReturnVoid_Async() {
        val signal = CountDownLatch(1)

        val sentMethods = ArrayList<String>()
        client.RequestFilter = ConnectionFilter { conn -> sentMethods.add(conn.requestMethod) }

        var request = HelloReturnVoid()
        request.id = 1

        client.deleteAsync(request, {
            Assert.assertEquals(HttpMethods.Delete, sentMethods[sentMethods.size - 1])
            signal.countDown()
        })
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
            Assert.assertNotNull(actual)
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
            Assert.assertEquals(expected.id, actual.id)
            Assert.assertEquals(expected.Byte, actual.Byte)
            Assert.assertEquals(expected.Short, actual.Short)
            Assert.assertEquals(expected.Int, actual.Int)
            Assert.assertEquals(expected.Long, actual.Long)
            Assert.assertEquals(expected.uShort, actual.uShort)
            Assert.assertEquals(expected.uLong, actual.uLong)
            Assert.assertEquals(expected.Float, actual.Float)
            Assert.assertEquals(expected.Double, actual.Double)
            Assert.assertEquals(expected.decimal, actual.decimal)
            Assert.assertEquals(expected.string, actual.string)
            Assert.assertEquals(expected.dateTime, actual.dateTime)
            Assert.assertEquals(expected.timeSpan, actual.timeSpan)
            Assert.assertEquals(expected.guid, actual.guid)
            Assert.assertEquals(expected.Char, actual.Char)
            Assert.assertEquals(expected.stringArray, actual.stringArray)
            Assert.assertEquals(expected.stringList, actual.stringList)

            Assert.assertEquals(expected.stringMap, actual.stringMap)
            Assert.assertEquals(expected.intStringMap, actual.intStringMap)

            Assert.assertEquals(expected.subType?.id, actual.subType?.id)
            Assert.assertEquals(expected.subType?.name, actual.subType?.name)
        }

        fun assertAllCollectionTypes(actual: AllCollectionTypes, expected: AllCollectionTypes) {
            Assert.assertEquals(expected.intArray, actual.intArray)
            Assert.assertEquals(expected.intList, actual.intList)
            Assert.assertEquals(expected.stringArray, actual.stringArray)
            Assert.assertEquals(expected.stringList, actual.stringList)
            assertPocoEquals(expected.pocoArray!!, actual.pocoArray!!)
            assertPocoEquals(expected.pocoList!!, actual.pocoList!!)

            assertPocoLookupEquals(expected.pocoLookup!!, actual.pocoLookup!!)
            assertPocoLookupMapEquals(expected.pocoLookupMap!!, actual.pocoLookupMap!!)
        }

        fun assertPocoEquals(expected: List<Poco>, actual: List<Poco>) {
            Assert.assertEquals(expected.size, actual.size)
            for (i in actual.indices) {
                assertPocoEquals(expected[i], actual[i])
            }
        }

        fun assertPocoLookupEquals(expected: HashMap<String, ArrayList<Poco>>, actual: HashMap<String, ArrayList<Poco>>) {
            Assert.assertEquals(expected.size, actual.size)
            for (key in actual.keys) {
                assertPocoEquals(expected[key]!!, actual[key]!!)
            }
        }

        fun assertPocoLookupMapEquals(expected: HashMap<String, ArrayList<HashMap<String, Poco>>>, actual: HashMap<String, ArrayList<HashMap<String, Poco>>>) {
            Assert.assertEquals(expected.size, actual.size)
            for (key in actual.keys) {
                assertPocoEquals(expected[key]!!, actual[key]!!)
            }
        }

        fun assertPocoEquals(expected: ArrayList<HashMap<String, Poco>>, actual: ArrayList<HashMap<String, Poco>>) {
            Assert.assertEquals(expected.size, actual.size)
            for (i in actual.indices) {
                assertPocoEquals(expected[i], actual[i])
            }
        }

        fun assertPocoEquals(expected: HashMap<String, Poco>, actual: HashMap<String, Poco>) {
            Assert.assertEquals(expected.size, actual.size)
            for (key in actual.keys) {
                assertPocoEquals(expected[key]!!, actual[key]!!)
            }
        }

        fun assertPocoEquals(expected: Poco, actual: Poco) {
            Assert.assertNotNull(actual)
            Assert.assertEquals(actual.name, expected.name)
        }
    }
}