//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.

package net.servicestack.android.checkweb;

import com.google.gson.Gson;

import junit.framework.TestCase;

import net.servicestack.android.checkweb.dtos.*;

import java.util.ArrayList;
import java.util.List;

public class GsonTests extends TestCase {

    public GsonTests() {

    }

    public void test_Gson() {
        System.out.println("=========== HELLO JSON ============");

        String json = "{\n" +
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

        Gson gson = new Gson();
        PostList list = gson.fromJson(json, PostList.class);

        System.out.println("JSON: " + gson.toJson(list));
    }

    public class PostList {
        private List<PostContainer> posts = new ArrayList<>();

        public List<PostContainer> getPostContainterList() {
            return posts;
        }
    }

    class PostContainer {
        Posts post;
        public Posts getPost() {
            return post;
        }
    }

    public class Posts {
        String message;
        String time;
        String username;
    }

    public static class dto
    {
        public static class Foo {
            Integer fooId;
            String fooName;
        }
        public static class Bar {
            Integer barId;
            String barName;
            Foo foo;
        }
    }

    class NestedPojo {
        dto.Foo foo;
        dto.Bar bar;
        public Integer version = 1;
    }

    class NestedList extends ArrayList<NestedPojo> {

    }
    public static interface MetadataTestChild
    {
        public String name = null;
//        ArrayList<MetadataTestNestedChild> results;
    }

    public void test_Can_serialize_nested_classes() {
        NestedPojo o = new NestedPojo();
        o.foo = new dto.Foo();
        o.foo.fooId = 1;
        o.foo.fooName = "foo";

        o.bar = new dto.Bar();
        o.bar.barId = 2;
        o.bar.barName = "bar";

        List<NestedPojo> list = new ArrayList<>();
        list.add(o);
        list.add(o);
        list.add(o);

        Gson gson = new Gson();
        System.out.println("JSON LIST: " + gson.toJson(list));

        Class a = NestedPojo.class;
    }

    public void test_Can_deserialize_Hello() {
        String json = "{\"Result\":\"World\"}\n";

        Gson gson = new Gson();

        HelloResponse response = gson.fromJson(json, HelloResponse.class);

        assertEquals("World", response.getResult());
    }
}
