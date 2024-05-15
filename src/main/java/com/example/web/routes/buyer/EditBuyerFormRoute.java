package com.example.web.routes.buyer;

import com.example.dao.BuyerDAO;
import com.example.models.Buyer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;

public class EditBuyerFormRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        int buyer_id = Integer.parseInt(request.params("buyer_id"));
        Buyer buyer = BuyerDAO.one(buyer_id);
        HashMap<String, Object> data = new HashMap<>();
        data.put("buyer", buyer);
        return new ModelAndView(data, "edit_buyer");
    }
}
