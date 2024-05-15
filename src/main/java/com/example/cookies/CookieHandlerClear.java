package com.example.cookies;

import spark.Response;

public class CookieHandlerClear {
    public static void clearCookie(Response response, String cookieName) {
        response.cookie(cookieName, "", -1);
    }
}
