package com.example.web.routes.product;

import com.example.dao.ProductDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.Map;

public class ProductDashboardRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("products", ProductDAO.all());

        Map<String,Object> chartData = new HashMap<>();
        chartData.put("chartData" , ProductDAO.all());
        Map<String, Object> model = new HashMap<>();
        model.put("data" , data);
        model.put("chartData",chartData);
        return new ModelAndView(model, "product_dashboard");
    }
}
