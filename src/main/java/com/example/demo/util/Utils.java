package com.example.demo.util;

public class Utils {

    private static final String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";


    public static boolean isIPv4Match(String ipV4) {
        if (ipV4 == null) {
            return false;
        }
        return ipV4.matches(PATTERN);
    }


}
