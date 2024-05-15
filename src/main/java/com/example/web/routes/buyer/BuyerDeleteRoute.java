package com.example.web.routes.buyer;

import com.example.dao.BuyerDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;

public class BuyerDeleteRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        int buyer_id = Integer.parseInt(request.params("buyer_id"));
        boolean isDeleted = BuyerDAO.delete(buyer_id);

        if (isDeleted) {
            data.put("status", "success");
            data.put("message", "Buyer successfully deleted!");
        } else {
            data.put("status", "error");
            data.put("message", "Error deleting buyer.");
        }
        return new ModelAndView(data, "operation_status");
    }
}
