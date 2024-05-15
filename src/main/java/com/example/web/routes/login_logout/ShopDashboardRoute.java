package com.example.web.routes.login_logout;

import com.example.dao.BuyerDAO;
import com.example.dao.ProductDAO;
import com.example.dao.SaleDetailDAO;
import com.example.dao.SalesDAO;
import com.example.models.BuyerPurchaseDetail;
import com.example.salesFunctions.SalesDataConverter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopDashboardRoute implements TemplateViewRoute {
    private final SaleDetailDAO saleDetailDAO;
    private final SalesDAO salesDAO;
    public ShopDashboardRoute(SaleDetailDAO saleDetailDAO, SalesDAO salesDAO) {
        this.saleDetailDAO = saleDetailDAO;
        this.salesDAO = salesDAO;
    }

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {

        Map<String, Object> data = new HashMap<>();
        data.put("buyers", BuyerDAO.getForDashboard());
        data.put("products", ProductDAO.getForDashboard());
        Map<String,Object> dashboard = new HashMap<>();
        dashboard.put("dashboard", SalesDAO.all());
        Map<String,Object> chartData = new HashMap<>();
        chartData.put("chartData" , ProductDAO.getForChart());

        List<BuyerPurchaseDetail> totalPriceByDate = SalesDAO.allTotalPriceByDate();
        String totalPriceJson = SalesDataConverter.convertToJson(totalPriceByDate);

        List<BuyerPurchaseDetail> totalQuantityByDate = SalesDAO.allTotalQuantityByDate();
        String totalQuantityJson = SalesDataConverter.convertToJson(totalQuantityByDate);

        //System.out.println(data);
        //System.out.println("Ovo je dashboard: " + dashboard);
        //System.out.println("Ovo je chartData: " + chartData);
        //System.out.println("Ovo je totalQuantityByDate:" + totalQuantityByDate);
        //System.out.println("Ovo je totalPriceByDate:" + totalPriceByDate);
        Map<String, Object> model = new HashMap<>();

        model.put("totalPriceJson", totalPriceJson);
        model.put("totalQuantityJson", totalQuantityJson);
        model.put("chartData",chartData);
        model.put("data", data);
        model.put("dashboard",dashboard);
        //System.out.println(model);
        //System.out.println("Chart data size: " + chartData.size()); // Check if chartData is not null

        return new ModelAndView(model, "shop_dashboard");

    }
}
