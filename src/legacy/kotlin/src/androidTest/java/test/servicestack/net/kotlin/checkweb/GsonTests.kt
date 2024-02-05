//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package test.servicestack.net.kotlin.checkweb

import com.google.gson.Gson

import java.util.ArrayList

import junit.framework.TestCase
import test.servicestack.net.kotlin.checkweb.HelloResponse

class GsonTests : TestCase() {

    fun test_Gson() {
        println("=========== HELLO JSON ============")

        val json = "{\n" +
            "  \"posts\": [\n" +
            "    {\n" +
            "      \"post\": {\n" +
            "        \"username\": \"John\",\n" +
            "        \"message\": \"I'm back\",\n" +
            "        \"time\": \"2010-5-6 7:00:34\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"post\": {\n" +
            "        \"username\": \"Smith\",\n" +
            "        \"message\": \"I've been waiting\",\n" +
            "        \"time\": \"2010-4-6 10:30:26\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

        val gson = Gson()
        val list = gson.fromJson(json, PostList::class.java)

        println("JSON: " + gson.toJson(list))
    }

    inner class PostList {
        internal val postContainterList: List<PostContainer> = ArrayList()
    }

    internal inner class PostContainer {
        var post: Posts? = null
    }

    inner class Posts {
        internal var message: String? = null
        internal var time: String? = null
        internal var username: String? = null
    }

    class dto {
        class Foo {
            internal var fooId: Int? = null
            internal var fooName: String? = null
        }

        class Bar {
            internal var barId: Int? = null
            internal var barName: String? = null
            internal var foo: Foo? = null
        }
    }

    internal inner class NestedPojo {
        var foo: dto.Foo? = null
        var bar: dto.Bar? = null
        var version: Int? = 1
    }

    internal inner class NestedList : ArrayList<NestedPojo>()
    interface MetadataTestChild {
        companion object {
            val name: String? = null
        }
        //        ArrayList<MetadataTestNestedChild> results;
    }

    fun test_Can_serialize_nested_classes() {
        var o = NestedPojo()
        o.foo = dto.Foo()
        o.foo?.fooId = 1
        o.foo?.fooName = "foo"

        o.bar = dto.Bar()
        o.bar?.barId = 2
        o.bar?.barName = "bar"

        val list = ArrayList<NestedPojo>()
        list.add(o)
        list.add(o)
        list.add(o)

        val gson = Gson()
        println("JSON LIST: " + gson.toJson(list))

        val a = NestedPojo::class.java
    }

    fun test_Can_deserialize_Hello() {
        val json = "{\"Result\":\"World\"}\n"

        val gson = Gson()

        val response = gson.fromJson<HelloResponse>(json, HelloResponse::class.java)

        assertEquals("World", response.Result)
    }
}
