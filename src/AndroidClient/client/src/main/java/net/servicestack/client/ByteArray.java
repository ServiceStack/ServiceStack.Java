package net.servicestack.client;

import java.util.Base64;

public class ByteArray {
    public static byte[] parse(String base64) {
        return Base64.getDecoder().decode(base64);
    }
    public static String toString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
