package com.example.web.routes.buyer;

import com.example.dao.BuyerDAO;
import com.example.models.Buyer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import javax.servlet.MultipartConfigElement;
import java.util.HashMap;

public class NewBuyerSubmitRoute implements TemplateViewRoute {


    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        Buyer buyer = new Buyer();

        buyer.setFirst_name(request.queryParams("first_name"));
        buyer.setLast_name(request.queryParams("last_name"));

        boolean isSaved = BuyerDAO.save(buyer);

        HashMap<String, Object> data = new HashMap<>();
        if (isSaved) {
            data.put("status", "success");
            data.put("message", "Buyer successfully saved!");
        } else {
            data.put("status", "error");
            data.put("message", "Error saving buyer.");
        }
        return new ModelAndView(data, "operation_status");
    }
}
