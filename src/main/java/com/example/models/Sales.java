package com.example.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Sales implements Serializable {

    private Product product;

    public Sales(Product product) {
        this.product = product;
    }

    private int sale_id;

    private int buyer_id;
    private int quantity;
    private double total_price;
    private LocalDate sale_date;

    private String total;

    public Sales() {
    }

    public Sales(int sale_id, double total_price) {
        this.sale_id = sale_id;
        this.total_price = total_price;
    }

    public Sales(int buyer_id, int quantity, double total_price, LocalDate sale_date) {

        this.buyer_id = buyer_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.sale_date = sale_date;
    }

    public Sales(int sale_id, int buyer_id, int quantity, double total_price, LocalDate sale_date, String total) {
        this.sale_id = sale_id;
        this.buyer_id = buyer_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.sale_date = sale_date;
        this.total = total;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public LocalDate getSale_date() {
        return sale_date;
    }

    public void setSale_date(LocalDate sale_date) {
        this.sale_date = sale_date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int calculateQuantitySold(Product product) {
        if (this.product.getProduct_id() == product.getProduct_id()) {
            return quantity;
        } else {
            return 0;
        }
    }

    public double calculateTotalPricePerProduct(Product product) {
        if (this.product.getProduct_id() == product.getProduct_id()) {
            return quantity * product.getPrice();
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "Sales{" +
                "sale_id=" + sale_id +
                ", buyer_id=" + buyer_id +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", sale_date=" + sale_date +
                ", total='" + total + '\'' +
                '}';
    }
}

