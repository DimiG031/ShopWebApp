package com.example.salesFunctions;

import com.example.dao.BuyerDAO;
import com.example.dao.ProductDAO;
import com.example.dao.SaleDetailDAO;
import com.example.dao.SalesDAO;
import com.example.models.Buyer;
import com.example.models.Product;
import com.example.models.SaleDetail;
import com.example.models.Sales;
import com.example.web.routes.cart.CartItem;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.util.List;

public class SaleService {
    private int buyerId;
    private LocalDate saleDate;

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public LocalDate getSaleDate(LocalDate saleDate) {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void processSale(List<CartItem> cartItems) {
        //System.out.println("Processing sale...");
        //System.out.println("BuyerId: " + getBuyerId());

        Sales newSale = new Sales();
        newSale.setBuyer_id(getBuyerId());
        newSale.setSale_date(getSaleDate(saleDate));
        double totalSalePrice = 0.0;
        int totalSaleQuantity = 0;
        boolean saleSaved = SalesDAO.save(newSale);
        int totalBuyerQuantity = 0;

        for (CartItem cartItem : cartItems) {
            int productId = cartItem.getProductId();
            String productName = cartItem.getProductName();
            double price = cartItem.getPrice();
            int quantity = cartItem.getQuantity();
            LocalDate dateOfSale = cartItem.getDateOfSale();
            double totalPrice = cartItem.getTotalPrice();
            int totalQuantity = cartItem.getTotalQuantity();
            JsonObject totalPricePerProduct = cartItem.getTotalPricePerProduct();

            totalSalePrice = totalPrice;
            totalSaleQuantity = totalQuantity;
            totalBuyerQuantity += quantity;

            Product product = ProductDAO.one(productId);
            if (product != null) {
                product.setQuantity(product.getQuantity() - quantity);
                ProductDAO.update(product);
            } else {
                System.out.println("Product with ID" + productId + "not found in database.");
            }

            if (saleSaved)
                try {
                    SaleDetail saleDetail = new SaleDetail(newSale.getSale_id(), productId, quantity, price * quantity);
                    boolean detailSaved = SaleDetailDAO.createSaleDetail(newSale.getSale_id(), saleDetail, quantity, price * quantity);
                    if (detailSaved) {
                        System.out.println("SaleDetail saved successfully: " + saleDetail);
                    } else {
                        System.out.println("Error saving SaleDetail: " + saleDetail);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            else {
                System.out.println("Save new Sale is not successfully!!!");
            }

        }

        newSale.setTotal_price(totalSalePrice);
        newSale.setQuantity(totalSaleQuantity);
        SalesDAO.update(newSale);

        Buyer buyer = BuyerDAO.one(getBuyerId());
        if (buyer != null) {
            buyer.setTotal_purchases(buyer.getTotal_purchases() + 1);
            buyer.setTotal_quantity(buyer.getTotal_quantity() + totalSaleQuantity);
            BuyerDAO.update(buyer);
        } else {
            System.out.println("Buyer with ID" + buyerId + "not found in database");
        }
        // Save the sale and get the result
       // System.out.println("This is new Sale:" + newSale);
        if (saleSaved) {
            System.out.println("Sale processed successfully.");
        } else {
            System.out.println("Error saving sale.");
        }
        cartItems.clear();

    }

    public void resetSale() {
        setBuyerId(0);
        setSaleDate(null);

    }
}
