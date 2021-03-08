package net.servicestack.client;

import java.util.UUID;

public class Guid {
    public static UUID parse(String guid) {
        return Utils.fromGuidString(guid);
    }
    public static String toString(UUID uuid) {
        return Utils.toGuidString(uuid);
    }
}
