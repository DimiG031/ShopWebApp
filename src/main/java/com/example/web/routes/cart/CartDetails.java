package com.example.web.routes.cart;

public class CartDetails {
}
















































/*package com.example.web.routes.cart;

import com.google.gson.JsonObject;

import java.util.Map;

public class CartDetails {
    private double totalPrice;
    private int totalQuantity;
    private Map<Integer,Double> totalPricePerProduct;
    private int IDForProduct;
    private double totalPricePerP;

    public CartDetails(double totalPrice, int totalQuantity, Map<Integer, Double> totalPricePerProduct) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.totalPricePerProduct = totalPricePerProduct;

    }

    public CartDetails(double totalPrice, int totalQuantity, int IDForProduct, double totalPricePerP) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.IDForProduct = IDForProduct;
        this.totalPricePerP = totalPricePerP;
    }

    public CartDetails(double totalPrice, int totalQuantity, Map<Integer, Double> totalPricePerProduct, int IDForProduct, double totalPricePerP) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.totalPricePerProduct = totalPricePerProduct;
        this.IDForProduct = IDForProduct;
        this.totalPricePerP = totalPricePerP;
    }

    public CartDetails(int IDForProduct, double totalPricePerP) {
        this.IDForProduct = IDForProduct;
        this.totalPricePerP = totalPricePerP;
    }

    public CartDetails(double totalPrice, int totalQuantity, JsonObject totalPricePerProduct) {

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Map<Integer, Double> getTotalPricePerProduct() {
        return totalPricePerProduct;
    }

    public void setTotalPricePerProduct(Map<Integer, Double> totalPricePerProduct) {
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public int getIDForProduct() {
        return IDForProduct;
    }

    public void setIDForProduct(int IDForProduct) {
        this.IDForProduct = IDForProduct;
    }

    public double getTotalPricePerP() {
        return totalPricePerP;
    }

    public void setTotalPricePerP(double totalPricePerP) {
        this.totalPricePerP = totalPricePerP;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "totalPrice=" + totalPrice +
                ", totalQuantity=" + totalQuantity +
                ", totalPricePerProduct=" + totalPricePerProduct +
                '}';
    }
}
*/