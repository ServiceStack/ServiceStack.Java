//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

// Generic Utils
public class Utils {

    public static Integer tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Double tryParseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void reverse(byte[] bytes) {
        if (bytes == null)
            return;

        int i = 0;
        int j = bytes.length - 1;
        byte hold;
        while (j > i) {
            hold = bytes[j];
            bytes[j] = bytes[i];
            bytes[i] = hold;
            j--;
            i++;
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String toHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] fromHex(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                        + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    public static String toGuidString(UUID uuid) {
        byte[] guidBytes = toGuidBytes(uuid);
        String to = toHex(guidBytes);
        return to;
    }

    public static UUID fromGuidString(String guid) {
        guid = guid.replaceAll("-","");
        byte[] guidBytes = fromHex(guid);
        UUID to = fromGuidBytes(guidBytes);
        return to;
    }

    public static UUID fromGuidBytes(byte[] guidBytes) {
        ByteBuffer buf = ByteBuffer.wrap(guidBytes);

        byte[] first4 = new byte[4];
        buf.get(first4);
        reverse(first4);

        byte[] second2 = new byte[2];
        buf.get(second2);
        reverse(second2);

        byte[] third2 = new byte[2];
        buf.get(third2);
        reverse(third2);

        long lsb = buf.getLong();

        buf = ByteBuffer.wrap(new byte[8])
            .put(first4)
            .put(second2)
            .put(third2);

        buf.rewind();
        long msb = buf.getLong();

        return new UUID(msb, lsb);
    }

    public static byte[] toGuidBytes(UUID theUuid) {
        ByteBuffer first8 = ByteBuffer.allocate(8);
        first8.putLong(theUuid.getMostSignificantBits());
        first8.rewind();

        byte[] first4 = new byte[4];
        first8.get(first4);
        reverse(first4);

        byte[] second2 = new byte[2];
        first8.get(second2);
        reverse(second2);

        byte[] third2 = new byte[2];
        first8.get(third2);
        reverse(third2);

        ByteBuffer converted16 = ByteBuffer.allocate(16)
            .put(first4)
            .put(second2)
            .put(third2);

        ByteBuffer last8 = ByteBuffer.allocate(8);
        last8.putLong(theUuid.getLeastSignificantBits());
        last8.rewind();

        converted16.put(last8);

        return converted16.array();
    }

    static final String wcfJsonPrefix = "/Date(";

    private static final ThreadLocal<SimpleDateFormat> iso8601Formatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        }
    };

    public static String toJsonDate(Date date) {
        return "/Date(" + date.getTime() + "-0000)/";
    }

    public static Date parseDate(String string) {
        String str = string.startsWith("\\")
            ? string.substring(1)
            : string;

        if (str.startsWith(wcfJsonPrefix)) {
            String body = splitOnLast(splitOnFirst(str, '(')[1], ')')[0];
            String unixTimeStr = splitOnLast(body.replace('+', '-'), '-')[0];
            long unixTime = Long.parseLong(unixTimeStr);
            return new Date(unixTime);
        }

        return fromIsoDateString(string);
    }

    public static Date fromIsoDateString(String iso8601string){
        if (iso8601string == null)
            return null;

        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            return iso8601Formatter.get().parse(s);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Invalid length");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /*String Utils*/
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

    public static String combinePath(String basePath, String withPath){
        if (basePath == null)
            basePath = "";
        if (withPath == null)
            withPath = "";

        String prefix = basePath.endsWith("/")
                ? basePath
                : basePath + "/";

        String suffix = withPath.startsWith("/")
                ? withPath.substring(1)
                : withPath;

        return prefix + suffix;
    }

    public static String fromUtf8Bytes(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] toUtf8Bytes(String string) {
        try {
            return string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readToEnd(HttpURLConnection response){
        try {
            return readToEnd(response.getInputStream(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readToEnd(InputStream stream, final String charsetName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charsetName));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String text = sb.toString();
        reader.close();
        return text;
    }
}
