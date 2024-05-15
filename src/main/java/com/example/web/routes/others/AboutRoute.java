package com.example.web.routes.others;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public class AboutRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView( model,"about");
    }

}
