package com.example.web.routes.product;

import com.example.dao.ProductDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;

public class ProductDeleteRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        int product_id = Integer.parseInt(request.params("product_id"));
        boolean isDeleted = ProductDAO.delete(product_id);

        if (isDeleted) {
            data.put("status", "success");
            data.put("message", "Product successfully deleted!");
        } else {
            data.put("status", "error");
            data.put("message", "Error deleting product.");
        }
        return new ModelAndView(data, "operation_status");
    }
}
