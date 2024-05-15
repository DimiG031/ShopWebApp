package com.example.web.routes.buyer;

import com.example.dao.BuyerDAO;
import com.example.dao.SaleDetailDAO;
import com.example.dao.SalesDAO;
import com.example.models.Buyer;
import com.example.models.BuyerPurchaseDetail;
import com.example.models.SaleDetail;
import com.example.models.Sales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuyerDetailsRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        Map<String, Object> data = new HashMap<>();
        int buyerId = Integer.parseInt(request.params("buyer_id"));
        Buyer buyer = BuyerDAO.one(buyerId);
        data.put("buyer", buyer);
        //System.out.println("Ovo je buyer : " + buyer);
        ArrayList<Sales> totalPrice = new ArrayList<>();
        totalPrice.addAll(BuyerDAO.getBuyerTotalPrice(buyerId));
        if (totalPrice == null) {
            totalPrice = new ArrayList<>();
            // Add a placeholder Sale object with price 0
            Sales zeroSale = new Sales();
            zeroSale.setTotal_price(0);
            totalPrice.add(zeroSale);
        }
        double totalPriceSum = totalPrice.stream()
                .mapToDouble(sale -> sale.getTotal_price())
                .sum();
        //System.out.println(totalPriceSum);
        data.put("totalPrice", totalPrice);
        data.put("totalPriceSum", totalPriceSum);
        //System.out.println("Ovo je total price: " + totalPrice);
        ArrayList<BuyerPurchaseDetail> details = BuyerDAO.getBuyerDetailPurchases(buyerId);
        data.put("details", details);
        //System.out.println("Ovo je details : " + details);
        ArrayList<BuyerPurchaseDetail> firstDetail = BuyerDAO.getBuyerDetails(buyerId);
        data.put("firstDetail", firstDetail);
        //System.out.println("Ovo je firstDetail : " + firstDetail);

        List<Integer> saleIds = firstDetail.stream().map(BuyerPurchaseDetail::getSaleId).collect(Collectors.toList());
        data.put("saleId" ,saleIds);
        ArrayList<SaleDetail> secondDetail = new ArrayList<>();
        for (Integer saleId : saleIds) {
            //System.out.println("Ovo je saleId" + saleId);

            secondDetail.addAll(SaleDetailDAO.getSaleDetailInfo(saleId));
        }
        ArrayList<Sales> thirdDetail = new ArrayList<>();
        for (Integer saleId : saleIds) {
           // System.out.println(saleId);
            thirdDetail.addAll(SalesDAO.getTotalPriceForSale(saleId));
        }

        boolean allListsEmpty = firstDetail.isEmpty() && secondDetail.isEmpty() && thirdDetail.isEmpty();
        data.put("allListsEmpty", allListsEmpty);

        //System.out.println("Ovo je secondDetail : " + secondDetail);
        //System.out.println("Ovo je thirdDetail : " + thirdDetail);
        data.put("secondDetail", secondDetail);
        data.put("thirdDetail", thirdDetail);
        data.put("buyers", BuyerDAO.all());
        return new ModelAndView(data, "buyer_details");
    }
}
