package com.example.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int product_id;
    private String productName;
    private double price;
    private int quantity;
    private int totalQuantity;

    public Product() {
    }

    public Product(int product_id, String productName, double price, int quantity) {
        this.product_id = product_id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String productName, int totalQuantity) {
        this.productName = productName;
        this.totalQuantity = totalQuantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
