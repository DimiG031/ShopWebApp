package com.example.cookies;

import spark.Request;

public class CookieHandlerGet {
    public static String getCookie(Request request, String cookieName) {
        return request.cookie(cookieName);
    }

    public static int getCookieAsInt(Request request, String cookieName) {
        String cookieValue = getCookie(request, cookieName);
        try {
            return Integer.parseInt(cookieValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
