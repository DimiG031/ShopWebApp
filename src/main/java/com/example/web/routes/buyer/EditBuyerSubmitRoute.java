package com.example.web.routes.buyer;

import com.example.dao.BuyerDAO;
import com.example.models.Buyer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import javax.servlet.MultipartConfigElement;
import java.util.HashMap;

public class EditBuyerSubmitRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        int buyer_id = Integer.parseInt(request.queryParams("buyer_id"));
        Buyer buyer = BuyerDAO.one(buyer_id);

        buyer.setFirst_name(request.queryParams("first_name"));
        buyer.setLast_name(request.queryParams("last_name"));
        buyer.setTotal_purchases(Integer.parseInt(request.queryParams("total_purchases")));
        buyer.setTotal_quantity(Integer.parseInt(request.queryParams("total_quantity")));
        boolean isSaved = BuyerDAO.update(buyer);

        HashMap<String, Object> data = new HashMap<>();
        if (isSaved) {
            data.put("status", "success");
            data.put("message", "Buyer successfully updated!");
        } else {
            data.put("status", "error");
            data.put("message", "Error updating buyer.");
        }
        return new ModelAndView(data, "operation_status");
    }
}
