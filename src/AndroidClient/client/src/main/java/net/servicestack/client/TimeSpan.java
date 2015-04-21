//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public class TimeSpan {
    double durationSecs = 0;

    public TimeSpan(){}

    public TimeSpan(double durationSecs) {
        this.durationSecs = durationSecs;
    }

    public TimeSpan(int hours, int mins, int secs) {
        addHours(hours);
        addMinutes(mins);
        addSeconds(secs);
    }

    public TimeSpan(int days, int hours, int mins, int secs) {
        addDays(days);
        addHours(hours);
        addMinutes(mins);
        addSeconds(secs);
    }

    public TimeSpan(int days, int hours, int mins, int secs, int ms) {
        addDays(days);
        addHours(hours);
        addMinutes(mins);
        addSeconds(secs);
        addMillis(ms);
    }

    public TimeSpan addDays(int days){
        durationSecs += days * 24 * 60 * 60;
        return this;
    }

    double getTotalSeconds(){
        return durationSecs;
    }

    int getTotalHours(){
        return (int) durationSecs / 60 / 60;
    }

    int getTotalMinutes(){
        return (int) durationSecs / 60;
    }

    public int getDays(){
        return (int) durationSecs / 24 / 60 / 60;
    }

    public TimeSpan addHours(int hours){
        durationSecs += hours * 60 * 60;
        return this;
    }

    public int getHours(){
        return getTotalHours() - (getDays() * 24);
    }

    public TimeSpan addMinutes(int mins){
        durationSecs += mins * 60;
        return this;
    }

    public int getMinutes(){
        return getTotalMinutes() - ((getDays() * 24 * 60) + (getHours() * 60));
    }

    public TimeSpan addSeconds(int secs){
        durationSecs += secs;
        return this;
    }

    public int getSeconds(){
        return ((int) durationSecs) - ((getDays() * 24 * 60 * 60) + (getHours() * 60 * 60) + (getMinutes() * 60));
    }

    public TimeSpan addMillis(int ms){
        durationSecs += (double)ms / 1000;
        return this;
    }

    public int getMillis(){
        return (int)((durationSecs - (int)durationSecs) * 1000);
    }

    public static TimeSpan parse(String xsdDuration){
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        double ms = 0.0;

        String[] t = Utils.splitOnFirst(xsdDuration.substring(1),'T'); //strip P

        boolean hasTime = t.length == 2;

        String[] d = Utils.splitOnFirst(t[0],'D');
        if (d.length == 2) {
            Integer day = Utils.tryParseInt(d[0]);
            if (day != null) {
                days = day;
            }
        }

        if (hasTime) {
            String[] h = Utils.splitOnFirst(t[1], 'H');
            if (h.length == 2) {
                Integer hour = Utils.tryParseInt(h[0]);
                if (hour != null) {
                    hours = hour;
                }
            }

            String[] m = Utils.splitOnFirst(h[h.length -1], 'M');
            if (m.length == 2) {
                Integer min = Utils.tryParseInt(m[0]);
                if (min != null) {
                    minutes = min;
                }
            }

            String[] s = Utils.splitOnFirst(m[m.length - 1], 'S');
            if (s.length == 2) {
                Double millis = Utils.tryParseDouble(s[0]);
                if (millis != null){
                    ms = millis;
                }
            }

            seconds = (int)ms;
            ms -= seconds;
        }

        double totalSecs = 0
                + (days * 24 * 60 * 60)
                + (hours * 60 * 60)
                + (minutes * 60)
                + (seconds);

        double interval = totalSecs + ms;

        return new TimeSpan(interval);
    }

    public String toXsdDuration() {
        StringBuilder sb = new StringBuilder("P");

        double d = getTotalSeconds();

        int totalSeconds = (int) (d);
        int remainingMs = (int)((d - totalSeconds) * 1000);
        int sec = (totalSeconds >= 60 ? totalSeconds % 60 : totalSeconds);
        int min = (totalSeconds = (totalSeconds / 60)) >= 60 ? totalSeconds % 60 : totalSeconds;
        int hours = (totalSeconds = (totalSeconds / 60)) >= 24 ? totalSeconds % 24 : totalSeconds;
        int days = (totalSeconds = (totalSeconds / 24)) >= 30 ? totalSeconds % 30 : totalSeconds;

        if (days > 0) {
            sb.append(days + "D");
        }

        if (hours + min + sec + remainingMs > 0) {
            sb.append("T");
            if (hours > 0) {
                sb.append(hours + "H");
            }
            if (min > 0) {
                sb.append(min + "M");
            }

            if (remainingMs > 0) {
                sb.append(sec + "." + String.format("%03d", remainingMs) + "S");
            }
            else if (sec > 0) {
                sb.append(sec + "S");
            }
        }

        String xsdDuration = sb.toString();
        return xsdDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSpan))
            return false;

        TimeSpan timeSpan = (TimeSpan) o;

        if (Double.compare(timeSpan.durationSecs, durationSecs) != 0)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(durationSecs);
        return (int) (temp ^ (temp >>> 32));
    }
}
