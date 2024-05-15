package com.example.web.routes.sales;

import com.example.cookies.CookieHandlerGet;
import com.example.dao.BuyerDAO;
import com.example.dao.ProductDAO;
import com.example.dao.SalesDAO;
import com.example.salesFunctions.SaleService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public class NewSalesFormRoute implements TemplateViewRoute {

    private final BuyerDAO buyerDAO;
    private final ProductDAO productDAO;
    private final SalesDAO salesDAO;
    private final SaleService saleService;

    public NewSalesFormRoute(BuyerDAO buyerDAO, ProductDAO productDAO, SalesDAO salesDAO, SaleService saleService) {
        this.buyerDAO = buyerDAO;
        this.productDAO = productDAO;
        this.salesDAO = salesDAO;
        this.saleService = saleService;
    }

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        Map<String, Object> dataAll = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> data2 = new HashMap<>();
        Map<String, Object> data3 = new HashMap<>();

        String fullName = CookieHandlerGet.getCookie(request, "selected_full_name");
        int buyerId = CookieHandlerGet.getCookieAsInt(request, "selected_buyer_id");
        //System.out.println("This is buyerId from NewSalesFormRoute" + buyerId);

        data.put("fullName", fullName);
        data.put("buyers", BuyerDAO.all());
        //System.out.println("This is buyerId from NewSalesFormRoute" + buyerId);
        //System.out.println("This is fullName from NewSalesFormRoute" + fullName);

        data2.put("products", ProductDAO.all());
        //System.out.println();
        data3.put("sales", SalesDAO.all());

        dataAll.put("data", data);
        dataAll.put("data2", data2);
        dataAll.put("data3", data3);

        return new ModelAndView(dataAll, "new_sales");
    }
}
