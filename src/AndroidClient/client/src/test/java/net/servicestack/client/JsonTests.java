//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class JsonTests extends TestCase {
    public JsonTests() {

    }

    public void test_Does_serialize_TimeSpan(){
        assertEquals("P1D", new TimeSpan(1,0,0,0).toXsdDuration());
        assertEquals("PT1H", new TimeSpan(1,0,0).toXsdDuration());
        assertEquals("PT1M", new TimeSpan(0,1,0).toXsdDuration());
        assertEquals("PT1S", new TimeSpan(0,0,1).toXsdDuration());
        assertEquals("PT0.001S", new TimeSpan(0,0,0,0,1).toXsdDuration());
        assertEquals("P1DT1H1M1.001S", new TimeSpan(1, 1, 1, 1, 1).toXsdDuration());

        assertEquals("PT0.0000001S", new TimeSpan(1 / TimeSpan.TicksPerSecond).toXsdDuration());
        assertEquals("PT0S", new TimeSpan(0).toXsdDuration());
        assertEquals("P3650D", new TimeSpan(10 * 365 * 24 * 60 * 60).toXsdDuration());
    }

    public void test_Does_deserialize_TimeSpan(){
        assertEquals(new TimeSpan(1,0,0,0), TimeSpan.fromXsdDuration("P1D"));
        assertEquals(new TimeSpan(1,0,0), TimeSpan.fromXsdDuration("PT1H"));
        assertEquals(new TimeSpan(0,1,0), TimeSpan.fromXsdDuration("PT1M"));
        assertEquals(new TimeSpan(0,0,1), TimeSpan.fromXsdDuration("PT1S"));
        assertEquals(new TimeSpan(0, 0, 0, 0, 1), TimeSpan.fromXsdDuration("PT0.001S"));
        assertEquals(new TimeSpan(1, 1, 1, 1, 1), TimeSpan.fromXsdDuration("P1DT1H1M1.001S"));

        assertEquals(new TimeSpan(1 / TimeSpan.TicksPerSecond), TimeSpan.fromXsdDuration("PT0.0000001S"));
        assertEquals(new TimeSpan(0), TimeSpan.fromXsdDuration("PT0S"));
        assertEquals(new TimeSpan(10 * 365 * 24 * 60 * 60), TimeSpan.fromXsdDuration("P3650D"));
    }

    public void test_Does_serialize_DateTime_in_QueryString() throws UnsupportedEncodingException {
        JsonServiceClient client = new JsonServiceClient("https://test.servicestack.net");
        testDateDtos.HelloDateTime request = new testDateDtos.HelloDateTime()
                .setDateTime(Utils.fromDateTime("2001-01-01T01:01:01Z"));

        String url = client.createUrl(request);

        assertEquals("https://test.servicestack.net/api/HelloDateTime?dateTime=" + URLEncoder.encode("/Date(978310861000-0000)/", "UTF-8"), url);
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
