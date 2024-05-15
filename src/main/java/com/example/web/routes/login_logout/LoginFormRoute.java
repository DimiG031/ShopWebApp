package com.example.web.routes.login_logout;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;

public class LoginFormRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        if (request.queryParams("error") != null) {
            if (request.queryParams("error").equals("invalid_credentials")) {
                data.put("error", "Invalid username and/or password.");
            } else if (request.queryParams("error").equals("no_session")) {
                data.put("error", "You need to login in access this page.");
            } else if (request.queryParams("error").equals("no_privilege")) {
                data.put("error", "You don't have sufficient privileges to access the page.");
            } else {
                data.put("error", "Unknown error: " + request.queryParams("error"));
            }
        }
        return new ModelAndView(data, "login_form");
    }
}
