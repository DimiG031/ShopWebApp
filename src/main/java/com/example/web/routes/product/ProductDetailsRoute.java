package com.example.web.routes.product;

import com.example.dao.ProductDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailsRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("products", ProductDAO.all());

        return new ModelAndView(data, "product_details");
    }
}
