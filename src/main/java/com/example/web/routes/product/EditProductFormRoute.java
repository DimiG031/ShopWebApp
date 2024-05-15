package com.example.web.routes.product;

import com.example.dao.ProductDAO;
import com.example.models.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;

public class EditProductFormRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        int product_id = Integer.parseInt(request.params("product_id"));
        Product product = ProductDAO.one(product_id);
        HashMap<String, Object> data = new HashMap<>();
        data.put("product", product);
        return new ModelAndView(data, "edit_product");
    }
}
