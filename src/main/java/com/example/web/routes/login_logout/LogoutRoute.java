package com.example.web.routes.login_logout;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class LogoutRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        request.session().removeAttribute("user");
        response.redirect("/login");
        return null;
    }
}
