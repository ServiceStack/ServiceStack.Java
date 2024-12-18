//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.servicestack.func.Function;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;

import static net.servicestack.func.Func.last;

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

    static final String KotlinAnnotationClass = "kotlin.jvm.internal.KotlinClass";
    static final String KotliMetadataClass = "kotlin.Metadata";

    public static boolean isKotlinClass(Class type)
    {
        for (Annotation attr : type.getAnnotations()){
            String annotationName = attr.annotationType().getName();
            if (KotlinAnnotationClass.equals(annotationName) || KotliMetadataClass.equals(annotationName))
                return true;
        }
        return false;
    }

    public static Field[] getSerializableFields(Class type){
        List<Field> fields = new ArrayList<Field>();
        boolean isKotlin = isKotlinClass(type);
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            if (c == Object.class)
                break;

            for (Field f : c.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers()))
                    continue;
                if (!Modifier.isPublic(f.getModifiers())){
                    if (isKotlin){ //Kotlin class convention has private backing fields
                        f.setAccessible(true); //Needed to access private fields
                    } else {
                        continue;
                    }
                }

                fields.add(f);
            }
        }
        return fields.toArray(new Field[fields.size()]);
    }

    public static String stripQuotes(String str) {
        String result = str;
        if(str.indexOf("\"") == 0 && str.lastIndexOf("\"") == str.length() - 1) {
            result = str.substring(1,str.length()-1);
        }
        return result;
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

    private static final ThreadLocal<SimpleDateFormat> iso8601FormatterWithMs = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        }
    };

    public final static int isoDateLength = "YYYY-MM-DDT00:00:00+00:00".length();
    public final static int isoDateWithMsLength = "YYYY-MM-DDT00:00:00.000+00:00".length();

    public final static int isoDateWithSubMsMin = "YYYY-MM-DDT00:00:00.0000+00:00".length();
    public final static int isoDateWithSubMsMax = "YYYY-MM-DDT00:00:00.0000000+00:00".length();

    public static String stripSubMillis(String iso8601string){
        if (iso8601string.length() < isoDateWithSubMsMin || iso8601string.length() > isoDateWithSubMsMax)
            return iso8601string;

        String[] parts = splitOnFirst(iso8601string, '.');

        String suffix = parts[1].substring(parts[1].length() - 6); //+00:00
        String ms = parts[1].substring(0, 3);

        return parts[0] + "." + ms + suffix;
    }

    public static Date fromIsoDateString(String iso8601string){
        if (iso8601string == null)
            return null;

        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            s = stripSubMillis(s);

            if (s.length() == isoDateWithMsLength)
                return iso8601FormatterWithMs.get().parse(s);

            return iso8601Formatter.get().parse(s);

        } catch (Exception e) {
            return ParseManual(iso8601string);
        }
    }

    public static Date ParseManual(String dateTimeStr)
    {
        if (dateTimeStr == null || dateTimeStr.length() < "yyyy-MM-dd".length())
            return null;

        if (dateTimeStr.endsWith("Z"))
            dateTimeStr = dateTimeStr.substring(0, dateTimeStr.length() - 1);

        String[] parts = dateTimeStr.split("T");
        if (parts.length == 1)
            parts = Utils.splitOnFirst(dateTimeStr, ' ');

        String[] dateParts = parts[0].split("-");
        int hh = 0, min = 0, ss = 0, ms = 0;
        double subMs = 0;
        int offsetMultiplier = 0;

        if (parts.length == 1)
        {
            return new Date(
                Utils.tryParseInt(dateParts[0]) - 1900,
                Utils.tryParseInt(dateParts[1]) - 1,
                Utils.tryParseInt(dateParts[2]),
                0, 0, 0);
        }
        else if (parts.length == 2)
        {
            String[] timeStringParts = parts[1].split("\\+");
            if (timeStringParts.length == 2)
            {
                offsetMultiplier = -1;
            }
            else
            {
                timeStringParts = parts[1].split("-");
                if (timeStringParts.length == 2)
                {
                    offsetMultiplier = 1;
                }
            }

            String timeOffset = timeStringParts.length == 2 ? timeStringParts[1] : null;
            String[] timeParts = timeStringParts[0].split(":");

            if (timeParts.length == 3)
            {
                Integer val = null;
                if ((val = Utils.tryParseInt(timeParts[0])) != null)
                    hh = val;

                if ((val = Utils.tryParseInt(timeParts[1])) != null)
                    min = val;

                String[] secParts = timeParts[2].split("\\.");

                if ((val = Utils.tryParseInt(secParts[0])) != null)
                    ss = val;

                if (secParts.length == 2)
                {
                    String msStr = String.format("%03d", Utils.tryParseInt(secParts[1]));
                    ms = Utils.tryParseInt(msStr.substring(0, 3));

                    if (msStr.length() > 3)
                    {
                        String subMsStr = msStr.substring(3);
//                        subMs = Utils.tryParseDouble(subMsStr) / Math.pow(10, subMsStr.length());
                    }
                }
            }

            Date dateTime = new Date(
                Utils.tryParseInt(dateParts[0]) - 1900,
                Utils.tryParseInt(dateParts[1]) - 1,
                Utils.tryParseInt(dateParts[2]),
                hh, min, ss);

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTime);

            if (ms > 0){
                cal.add(Calendar.MILLISECOND, ms);
            }

//            if (subMs != 0)
//                dateTime=dateTime.AddMilliseconds(subMs); //Doesn't support sub millis

            if (offsetMultiplier != 0 && timeOffset != null)
            {
                timeParts = timeOffset.split(":");
                if (timeParts.length == 2)
                {
                    hh = Utils.tryParseInt(timeParts[0]);
                    min = Utils.tryParseInt(timeParts[1]);
                }
                else
                {
                    hh = Utils.tryParseInt(timeOffset.substring(0, 2));
                    min = Utils.tryParseInt(timeOffset.substring(2));
                }

                cal.add(Calendar.HOUR, offsetMultiplier * hh);
                cal.add(Calendar.MINUTE, offsetMultiplier * min);
            }

            dateTime = cal.getTime();
            return dateTime;
        }

        return null;
    }

    /*String Utils*/
    public static boolean isEmpty(final String string)
    {
        return string == null || string.length() == 0;
    }

    public static boolean isNullOrEmpty(final String string)
    {
        return string == null || string.length() == 0;
    }

    public static boolean isNullOrWhiteSpace(final String string)
    {
        return string == null || string.length() == 0 || string.trim().length() == 0;
    }

    public static String[] splitOnFirst(String strVal, char needle) {
        return splitOnFirst(strVal, needle, 0);
    }

    public static String[] splitOnFirst(String strVal, char needle, int start) {
        if (strVal == null) return new String[0];
        int pos = strVal.indexOf(needle, start);
        return pos == -1
                ? new String[] { strVal }
                : new String[] { strVal.substring(0, pos), strVal.substring(pos + 1) };
    }

    public static String[] splitOnFirst(String strVal, String needle) {
        return splitOnFirst(strVal, needle, 0);
    }

    public static String[] splitOnFirst(String strVal, String needle, int start) {
        if (strVal == null) return new String[0];
        int pos = strVal.indexOf(needle, start);
        return pos == -1
                ? new String[] { strVal }
                : new String[] { strVal.substring(0, pos), strVal.substring(pos + needle.length()) };
    }

    public static String[] splitOnLast(String strVal, char needle) {
        return splitOnLast(strVal, needle, strVal.length());
    }

    public static String[] splitOnLast(String strVal, char needle, int start) {
        if (strVal == null) return new String[0];
        int pos = strVal.lastIndexOf(needle, start);
        return pos == -1
                ? new String[] { strVal }
                : new String[] { strVal.substring(0, pos), strVal.substring(pos + 1) };
    }

    public static String[] splitOnLast(String strVal, String needle) {
        return splitOnLast(strVal, needle, strVal.length());
    }

    public static String[] splitOnLast(String strVal, String needle, int start) {
        if (strVal == null) return new String[0];
        int pos = strVal.lastIndexOf(needle, start);
        return pos == -1
                ? new String[] { strVal }
                : new String[] { strVal.substring(0, pos), strVal.substring(pos + needle.length()) };
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

    public static String readToEnd(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            return readToEnd(conn);
        } catch (Exception e) {
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
        closeQuietly(reader);
        return text;
    }

    public static byte[] readBytesToEnd(String url) {
        try {
            URL heartbeatUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)heartbeatUrl.openConnection();
            return readBytesToEnd(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readBytesToEnd(HttpURLConnection response){
        try {
            return readBytesToEnd(response.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readBytesToEnd(InputStream stream) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(1024);
        final BufferedInputStream bufferedStream = new BufferedInputStream(stream, 8192);
        try {
            final byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = bufferedStream.read(buffer)) > 0) {
                bytes.write(buffer, 0, bytesRead);
            }
            return bytes.toByteArray();
        } finally {
            closeQuietly(bufferedStream);
        }
    }

    public static String getUnderlyingContentType(String contentType) {
        return splitOnFirst(contentType, ';')[0].trim().toLowerCase();
    }

    public static boolean matchesContentType(String contentType, String matchesContentType) {
        return getUnderlyingContentType(contentType).equals(getUnderlyingContentType(matchesContentType));
    }

    public static String sanitizeVarName(String name){
        return name.replaceAll("_", "").toLowerCase();
    }

    public static ResponseStatus createResponseStatus(Object obj) {
        if (obj instanceof JsonObject){
            return createResponseStatus((JsonObject)obj);
        }
        return null;
    }

    public static ResponseStatus createResponseStatus(JsonObject obj) {
        ResponseStatus status = new ResponseStatus();
        for (Map.Entry<String, JsonElement> jsonElementEntry : obj.entrySet()) {
            String key = jsonElementEntry.getKey();
            String varName = Utils.sanitizeVarName(key);

            try {
                Object field = obj.get(key);

                if (varName.toLowerCase().equals("errorcode")) {
                    status.setErrorCode(getAsStringOrNull(jsonElementEntry.getValue()));
                } else if (varName.toLowerCase().equals("message")) {
                    status.setMessage(getAsStringOrNull(jsonElementEntry.getValue()));
                } else if (varName.toLowerCase().equals("stacktrace")) {
                    status.setStackTrace(getAsStringOrNull(jsonElementEntry.getValue()));
                } else if (varName.toLowerCase().equals("errors")) {

                    if (field instanceof JsonArray){
                        JsonArray jFields = (JsonArray)field;

                        ArrayList<ResponseError> errors = new ArrayList<>();
                        for(JsonElement fieldElement : jFields) {
                            JsonObject fieldObj = fieldElement.getAsJsonObject();
                            ResponseError fieldError = new ResponseError();
                            for (Map.Entry<String, JsonElement> entry : fieldObj.entrySet()) {
                                String fieldKey = entry.getKey();
                                String fieldName = Utils.sanitizeVarName(fieldKey);

                                if (fieldName.toLowerCase().equals("errorcode")) {
                                    fieldError.setErrorCode(getAsStringOrNull(entry.getValue()));
                                } else if (fieldName.toLowerCase().equals("message")) {
                                    fieldError.setMessage(getAsStringOrNull(entry.getValue()));
                                } else if (fieldName.toLowerCase().equals("fieldname")) {
                                    fieldError.setFieldName(getAsStringOrNull(entry.getValue()));
                                }

                            }
                            errors.add(fieldError);
                        }

                        status.setErrors(errors);
                    }

                    status.setStackTrace(obj.get(key).toString());
                }
            } catch (JsonParseException e) {
                Log.e(e);
            }
        }

        return status;
    }

    private static String getAsStringOrNull(JsonElement jsonElement) {
        return jsonElement.isJsonNull() ? null : jsonElement.getAsString();
    }

    public static <T> ArrayList<T> toList(Iterable<T> iterable) {
        ArrayList<T> to = new ArrayList<>();
        for (T item : iterable) {
            to.add(item);
        }
        return to;
    }

    public static <T> ArrayList<T> asList(T... params) {
        ArrayList<T> to = new ArrayList<>();
        to.addAll(Arrays.asList(params));
        return to;
    }

    public static <T> ArrayList<T> createList(T... params) {
        ArrayList<T> to = new ArrayList<>();
        to.addAll(Arrays.asList(params));
        return to;
    }

    public static <K,V> HashMap<K,V> createMap(K k1, V v1) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        return to;
    }

    public static <K,V> HashMap<K,V> createMap(K k1, V v1, K k2, V v2) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        to.put(k2, v2);
        return to;
    }

    public static <K,V> HashMap<K,V> createMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        HashMap<K,V> to = new HashMap<>();
        to.put(k1, v1);
        to.put(k2, v2);
        to.put(k3, v3);
        return to;
    }

    public static boolean equals(String s1, String s2){
        if (s1 == null)
            return s2 == null;
        if (s2 == null)
            return s1 == null;
        return s1.equals(s2);
    }

    public static String trimStart(String text, char character) {
        if (text == null || text.length() == 0) return "";

        int i = 0;
        while (text.charAt(i) == character) {
            i++;
        }
        return text.substring(i).trim();
    }

    public static String trimEnd(String text, char character) {
        if (text == null || text.length() == 0) return "";

        int i = text.length() - 1;
        while (text.charAt(i) == character){
            if (--i < 0) {
                return "";
            }
        }
        return text.substring(0, i + 1).trim();
    }

    public static String toHumanFriendlyUrl(String url){
        if (url == null) return null;

        url = trimEnd(last(splitOnFirst(url, "://")), '/');
        return url;
    }

    public static <K,V> HashMap<K,ArrayList<V>> createMap(ArrayList<V> xs, Function<V,K> f){
        HashMap<K,ArrayList<V>> to = new HashMap<>();
        if (xs == null) return to;

        for (V val : xs){
            K key = f.apply(val);

            ArrayList<V> list = to.get(key);
            if (list == null)
                to.put(key, list = new ArrayList<V>());

            list.add(val);
        }

        return to;
    }

    // From .NET DateTime (WCF JSON or ISO Date) to JVM Date
    public static Date fromDateTime(String jsonDate) {
        String str = jsonDate.startsWith("\\")
                ? jsonDate.substring(1)
                : jsonDate;

        if (str.startsWith(wcfJsonPrefix)) {
            String body = splitOnLast(splitOnFirst(str, '(')[1], ')')[0];
            String unixTimeStr = splitOnFirst(body,'-', 1)[0];
            unixTimeStr = splitOnFirst(unixTimeStr,'+', 1)[0];
            long unixTime = Long.parseLong(unixTimeStr);
            return new Date(unixTime);
        }

        return fromIsoDateString(jsonDate);
    }

    // From JVM Date to .NET DateTime (WCF JSON Date)
    public static String toDateTime(Date date) {
        return "/Date(" + date.getTime() + "-0000)/";
    }

    // From .NET TimeSpan (XSD Duration) to Java TimeSpan
    public static TimeSpan fromTimeSpan(String xsdDuration) {
        return TimeSpan.parse(xsdDuration);
    }

    // From Java TimeSpan to .NET TimeSpan (XSD Duration)
    public static String toTimeSpan(TimeSpan timeSpan) {
        return TimeSpan.toString(timeSpan);
    }

    // From .NET Guid to JVM UUID
    public static UUID fromGuid(String guid) {
        return Guid.parse(guid);
    }

    // From JVM UUID to .NET Guid
    public static String toGuid(UUID uuid) {
        return Guid.toString(uuid);
    }

    // From .NET byte[] (Base64 String) to JVM signed byte[]
    public static byte[] fromByteArray(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    // From JVM signed byte[] to .NET byte[] (Base64 String)
    public static String toByteArray(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String toBase64String(String source) {
        return toByteArray(Utils.toUtf8Bytes(source));
    }

    public static String addQueryParam(String url, String key, String value) {
        return addQueryParam(url, key, value, true);
    }

    public static String addQueryParam(String url, String key, String val, boolean encode) {
        if (url == null || url.length() == 0)
            return null;
        if (val == null)
            val = "";

        String prefix = "";
        if (!url.endsWith("?") && !url.endsWith("&")) {
            prefix = url.indexOf('?') == -1 ? "?" : "&";
        }
        try {
            return url + prefix + key + "=" + (encode ? URLEncoder.encode(val, "UTF-8") : val);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String EMPTY = "";

    public static String toString(Object o, String nullDefault) {
        return (o != null) ? o.toString() : nullDefault;
    }

    public static String join(final String[] array, final String separator) {
        if (array == null)
            return null;

        return join(Arrays.asList(array), separator);
    }

    public static String join(final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(final Iterator<?> iterator, final String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            final String result = toString(first, "");
            return result;
        }

        final StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String repeat(String string, int count) {

        if (count <= 1) {
            return (count == 0) ? "" : string;
        }

        final int len = string.length();
        final long longSize = (long) len * (long) count;
        final int size = (int) longSize;
        if (size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }

        final char[] array = new char[size];
        string.getChars(0, len, array, 0);
        int n;
        for (n = len; n < size - n; n <<= 1) {
            System.arraycopy(array, 0, array, n, n);
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);
    }

    public static int sum(Collection<Integer> nums) {
        int sum = 0;
        for (int number : nums) {
            sum += number;
        }
        return sum;
    }

    public static StringBuilder appendLine(StringBuilder sb) {
        sb.append(System.lineSeparator());
        return sb;
    }

    public static String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String unescapeHtml(String html){
        return html
            .replace("&lt;","<")
            .replace("&gt;",">")
            .replace("&amp;","&")
            .replace("&#39;","\'")
            .replace("&quot;","\"");
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {}
        }
    }
}
