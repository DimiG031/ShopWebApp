package com.example.web.routes.login_logout;

import com.example.models.User;
import com.example.utils.LoginRegisterUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class LoginSubmitRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        User user = LoginRegisterUtil.login(username, password);
        if (user == null) {
            response.redirect("/login?error=invalid_credentials");
        } else {
            request.session(true).attribute("user", user);
            response.redirect("/admin/");
        }
        return null;
    }
}
