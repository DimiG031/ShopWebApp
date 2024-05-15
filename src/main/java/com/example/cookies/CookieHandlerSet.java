package com.example.cookies;

import spark.Response;

public class CookieHandlerSet {

    public static void setCookie(Response response, String cookieName, String cookieValue) {
        response.cookie(cookieName, cookieValue);
    }
}
