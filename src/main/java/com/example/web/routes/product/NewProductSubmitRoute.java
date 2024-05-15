package com.example.web.routes.product;

import com.example.dao.ProductDAO;
import com.example.models.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import javax.servlet.MultipartConfigElement;
import java.util.HashMap;

public class NewProductSubmitRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        Product product = new Product();
        product.setProductName(request.queryParams("productName"));
        product.setPrice(Double.parseDouble(request.queryParams("price")));
        product.setQuantity(Integer.parseInt(request.queryParams("quantity")));

        boolean isSaved = ProductDAO.save(product);

        HashMap<String, Object> data = new HashMap<>();
        if (isSaved) {
            data.put("status", "success");
            data.put("message", "Product successfully saved!");
        } else {
            data.put("status", "error");
            data.put("message", "Error saving product.");
        }
        return new ModelAndView(data, "operation_status");
    }
}
