//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.client.TimeSpan;

public class JsonTests extends ApplicationTestCase<Application> {
    public JsonTests() {
        super(Application.class);
    }

    public void test_Does_serialize_TimeSpan(){
        assertEquals("P1D", new TimeSpan(1,0,0,0).toXsdDuration());
        assertEquals("PT1H", new TimeSpan(1,0,0).toXsdDuration());
        assertEquals("PT1M", new TimeSpan(0,1,0).toXsdDuration());
        assertEquals("PT1S", new TimeSpan(0,0,1).toXsdDuration());
        assertEquals("PT0.001S", new TimeSpan(0,0,0,0,1).toXsdDuration());
        assertEquals("P1DT1H1M1.001S", new TimeSpan(1, 1, 1, 1, 1).toXsdDuration());
    }

    public void test_Does_deserialize_TimeSpan(){
        assertEquals("P1D", new TimeSpan(1,0,0,0).toXsdDuration());
        assertEquals("PT1H", new TimeSpan(1,0,0).toXsdDuration());
        assertEquals("PT1M", new TimeSpan(0,1,0).toXsdDuration());
        assertEquals("PT1S", new TimeSpan(0,0,1).toXsdDuration());
        assertEquals("PT0.001S", new TimeSpan(0,0,0,0,1).toXsdDuration());
        assertEquals("P1DT1H1M1.001S", new TimeSpan(1,1,1,1,1).toXsdDuration());
    }

    public void test_Can_get_time_components(){
        TimeSpan allOnes =  new TimeSpan(1,1,1,1,1);

        assertEquals(1, allOnes.getDays());
        assertEquals(1, allOnes.getHours());
        assertEquals(1, allOnes.getMinutes());
        assertEquals(1, allOnes.getSeconds());
        assertEquals(1, allOnes.getMillis());
    }
}
