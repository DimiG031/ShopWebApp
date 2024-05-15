package com.example.web.routes.product;

import com.example.dao.ProductDAO;
import com.example.models.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import javax.servlet.MultipartConfigElement;
import java.util.HashMap;

public class EditProductSubmitRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        int product_id = Integer.parseInt(request.queryParams("product_id"));
        Product product = ProductDAO.one(product_id);

        product.setProductName(request.queryParams("productName"));
        product.setPrice(Double.parseDouble(request.queryParams("price")));
        product.setQuantity(Integer.parseInt(request.queryParams("quantity")));
        boolean isSaved = ProductDAO.update(product);

        HashMap<String, Object> data = new HashMap<>();
        if (isSaved) {
            data.put("status", "success");
            data.put("message", "Product successfully updated!");
        } else {
            data.put("status", "error");
            data.put("message", "Error updating product.");
        }
        return new ModelAndView(data, "operation_status");
    }
}
