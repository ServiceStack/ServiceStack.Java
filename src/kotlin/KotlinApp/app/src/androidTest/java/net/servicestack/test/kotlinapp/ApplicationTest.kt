package net.servicestack.test.kotlinapp

import android.app.Application
import android.test.ApplicationTestCase

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
class ApplicationTest : ApplicationTestCase<Application>(Application::class.java) {
    fun test_Fail(){
        assertTrue(false)
    }
}