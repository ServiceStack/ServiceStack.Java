//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

//String Utils
class S {
    public static String[] splitOnFirst(String strVal, char needle) {
        if (strVal == null) return new String[0];
        int pos = strVal.indexOf(needle);
        return pos == -1
                ? new String[] { strVal }
                : new String[] { strVal.substring(0, pos), strVal.substring(pos + 1) };
    }

    public static String[] splitOnLast(String strVal, char needle) {
        if (strVal == null) return new String[0];
        int pos = strVal.lastIndexOf(needle);
        return pos == -1
            ? new String[] { strVal }
            : new String[] { strVal.substring(0, pos), strVal.substring(pos + 1) };
    }
}
