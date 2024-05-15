package com.example.web.routes.sales;
import com.example.cookies.CookieHandlerSet;
import com.example.dao.BuyerDAO;
import com.example.dao.BuyerInfoDAO;
import com.example.dao.ProductDAO;
import com.example.dao.SalesDAO;
import com.example.models.BuyerInfo;
import com.example.models.BuyerPurchaseDetail;
import com.example.models.Sales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SalesDashboardRoute implements TemplateViewRoute {
    private final BuyerDAO buyerDAO;

    public SalesDashboardRoute(BuyerDAO buyerDAO) {
        this.buyerDAO = buyerDAO;
    }

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {

        List<BuyerInfo> buyers = buyerDAO.getAllBuyers();

        List<BuyerInfoDAO> buyerInfoDAOList = new ArrayList<>();
        for (BuyerInfo buyer : buyers) {
            BuyerInfoDAO buyerInfoDAO = new BuyerInfoDAO();
            buyerInfoDAO.setBuyerId(buyer.getBuyer_id());
            buyerInfoDAO.setFullName(buyer.getFull_name());
            buyerInfoDAOList.add(buyerInfoDAO);
        }

        String selectedFullName = request.queryParams("selected_full_name");
        CookieHandlerSet.setCookie(response, "selected_full_name", selectedFullName);

        Map<String, Object> data = new HashMap<>();
        data.put("buyers", buyerInfoDAOList);
        //System.out.println(buyerInfoDAOList);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("products", ProductDAO.all());

        List<Sales> salesAll = SalesDAO.all();
        List<BuyerPurchaseDetail> salesDetails = new ArrayList<>();

        for (Sales sale : salesAll) {
            List<BuyerPurchaseDetail> saleDetailsForId = SalesDAO.getDetailsForSales(sale.getSale_id());
            salesDetails.addAll(saleDetailsForId);
        }

        Map<Integer, String> buyerIdToFullNameMap = new HashMap<>();

        for (BuyerPurchaseDetail sale : salesDetails) {
            Map<String, Object> buyerInfo = BuyerDAO.getFullNameByBuyerId(sale.getBuyer_id());
            if (buyerInfo != null) {
                String fullName = (String) buyerInfo.get("fullName");
                buyerIdToFullNameMap.put(sale.getBuyer_id(), fullName);
            }
        }

        for (BuyerPurchaseDetail sale : salesDetails) {
            String fullName = buyerIdToFullNameMap.getOrDefault(sale.getBuyer_id(), "Unknown");
            sale.setFullName(fullName);
        }

        //System.out.println(salesDetails);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("sales", salesDetails);

        Map<String, Object> dataAll = new HashMap<>();
        dataAll.put("data", data);
        dataAll.put("data2", data2);
        dataAll.put("data3", data3);
        return new ModelAndView(dataAll, "sales_dashboard");
    }
}
