package com.example.web.routes.cart;

import com.google.gson.JsonObject;
import java.time.LocalDate;

public class CartItem {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private LocalDate dateOfSale;
    private double totalPrice;
    private int totalQuantity;
    private JsonObject totalPricePerProduct;

    public CartItem() {
    }

    public CartItem(int productId, String productName,
                    double price, int quantity, LocalDate dateOfSale,
                    double totalPrice, int totalQuantity, JsonObject totalPricePerProduct) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.dateOfSale = dateOfSale;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
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

    public JsonObject getTotalPricePerProduct() {
        return totalPricePerProduct;
    }

    public void setTotalPricePerProduct(JsonObject totalPricePerProduct) {
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public CartItem ClearCart() {
        return new CartItem();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", dateOfSale=" + dateOfSale +
                ", totalPrice=" + totalPrice +
                ", totalQuantity=" + totalQuantity +
                ", totalPricePerProduct=" + totalPricePerProduct +
                '}';
    }
}