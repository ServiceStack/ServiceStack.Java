package net.servicestack.idea;

public class DepConfig {
    public static final String servicestackGroupId = "net.servicestack";
    public static final String androidPackageId = "android";
    //Fallback version of dependencies if GitHub tags can't be checked.
    public static final String clientPackageId = "client";
    public static String servicestackVersion = "1.0.43";

    public static final String gsonGroupId = "com.google.code.gson";
    public static final String gsonPackageId = "gson";
    public static String gsonVersion = "2.8.6";

    public static void setServiceStackVersion(String version) {
        servicestackVersion = version;
    }

    public static String getClientVersionString() {
        return servicestackGroupId + ":" + clientPackageId + ":" + servicestackVersion;
    }

}