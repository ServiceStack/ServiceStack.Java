package net.servicestack.client;

import java.util.Date;

public class DateTime {
    public static Date parse(String date) {
        return Utils.parseDate(date);
    }
}
