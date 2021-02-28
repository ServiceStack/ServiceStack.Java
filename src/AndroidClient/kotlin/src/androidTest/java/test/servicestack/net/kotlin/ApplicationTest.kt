package test.servicestack.net.kotlinapp

import com.google.gson.Gson
import junit.framework.TestCase.assertTrue
import net.servicestack.android.AndroidLogProvider
import net.servicestack.android.AndroidServiceClient
import net.servicestack.client.Log
import org.junit.BeforeClass
import org.junit.Test
import test.servicestack.net.kotlin.techstacks.Technology
import test.servicestack.net.kotlin.techstacks.TechnologyTier

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
public class AndroidServiceClientTests {

    @BeforeClass
    fun setUp() {
        Log.Instance = AndroidLogProvider("ZZZ");

        val client = AndroidServiceClient("https://www.techstacks.io")
    }

    @Test
    fun test_Fail() {
        assertTrue(true)
    }

    @Test
    fun test_Can_Serialize_Technology(){
        val dto = Technology();
        dto.name = "Test";
        dto.tier = TechnologyTier.ProgrammingLanguage;

        var gson = Gson()

        val json = gson.toJson(dto);

        Log.d("JSON: $json")

        val fromJson = gson.fromJson(json, Technology::class.java)

        Log.d("Name: ${fromJson.name}, Tier: ${fromJson.tier}")
    }
}