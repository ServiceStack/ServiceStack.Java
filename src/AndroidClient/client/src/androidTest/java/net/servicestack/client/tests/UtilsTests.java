package net.servicestack.client.tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import net.servicestack.client.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UtilsTests extends ApplicationTestCase<Application> {
    public UtilsTests() {
        super(Application.class);
    }

    public void test_Can_parse_Date_with_SubMillis(){
        Date date = Utils.parseDate("2015-03-27T03:41:41.987375+00:00");

        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        assertEquals("2015-03-27T03:41:41.987", dateFmt.format(date));
    }

    public void test_Can_stripSubMillis(){
        assertEquals("2015-03-27T03:41:41.987+00:00",Utils.stripSubMillis("2015-03-27T03:41:41.9873754+00:00"));
        assertEquals("2015-03-27T03:41:41.987+00:00",Utils.stripSubMillis("2015-03-27T03:41:41.987375+00:00"));
        assertEquals("2015-03-27T03:41:41.987+00:00",Utils.stripSubMillis("2015-03-27T03:41:41.98737+00:00"));
        assertEquals("2015-03-27T03:41:41.987+00:00",Utils.stripSubMillis("2015-03-27T03:41:41.9873+00:00"));
        assertEquals("2015-03-27T03:41:41.987+00:00",Utils.stripSubMillis("2015-03-27T03:41:41.987+00:00"));
    }

    //https://www.ntosic.net/2015/05/integrating-net-and-java-convert-system-guid-to-java-util-uuid/
    public void test_Can_convert_MS_Guid_to_Java_UUID(){
        String guidStr = "4E07D932-8D1A-4CE1-9314-7AC7826E8966";
        String uuidStr = "32D9074E-1A8D-E14C-9314-7AC7826E8966";

        UUID uuid = Utils.fromGuidString(guidStr);
        assertEquals(uuidStr, uuid.toString().toUpperCase());

        assertEquals(guidStr.replaceAll("-",""), Utils.toGuidString(uuid).toUpperCase());
    }
}
