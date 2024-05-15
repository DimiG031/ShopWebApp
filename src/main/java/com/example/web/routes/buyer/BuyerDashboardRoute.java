package com.example.web.routes.buyer;

import com.example.dao.BuyerDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;

public class BuyerDashboardRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("buyers", BuyerDAO.all());
        return new ModelAndView(data, "buyer_dashboard");
    }
}
