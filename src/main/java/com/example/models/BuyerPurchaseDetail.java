package com.example.models;

import java.time.LocalDate;

public class BuyerPurchaseDetail {
    private int saleId;
    private String productName;
    private int quantitySold;
    private double totalPricePerProduct;
    private LocalDate saleDate;
    private int product_count;

    private double total_price;
    private int buyer_id;
    private String fullName;
    private int quantity;
    private int quantity_sum;
    private double total_price_sum;



    public BuyerPurchaseDetail(int saleId ,String productName, int quantitySold, double totalPricePerProduct, LocalDate saleDate) {
        this.saleId = saleId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
        this.saleDate = saleDate;
    }

    public BuyerPurchaseDetail(int saleId, LocalDate saleDate, int product_count) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.product_count = product_count;
    }

    public BuyerPurchaseDetail(String productName, int quantitySold, double totalPricePerProduct) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public BuyerPurchaseDetail(int saleId, LocalDate saleDate, int product_count, double total_price, int buyer_id, String fullName) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.product_count = product_count;
        this.total_price = total_price;
        this.buyer_id = buyer_id;
        this.fullName = fullName;
    }

    public BuyerPurchaseDetail(LocalDate saleDate, double total_price, int quantity) {
        this.saleDate = saleDate;
        this.total_price = total_price;
        this.quantity = quantity;
    }

    public BuyerPurchaseDetail(LocalDate saleDate, int quantity_sum) {
        this.saleDate = saleDate;
        this.quantity_sum = quantity_sum;
    }

    public BuyerPurchaseDetail(LocalDate saleDate, double total_price_sum) {
        this.saleDate = saleDate;
        this.total_price_sum = total_price_sum;
    }

    public BuyerPurchaseDetail() {
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getTotalPricePerProduct() {
        return totalPricePerProduct;
    }

    public void setTotalPricePerProduct(double totalPricePerProduct) {
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getQuantity_sum() {
        return quantity_sum;
    }

    public void setQuantity_sum(int quantity_sum) {
        this.quantity_sum = quantity_sum;
    }

    public double getTotal_price_sum() {
        return total_price_sum;
    }

    public void setTotal_price_sum(double total_price_sum) {
        this.total_price_sum = total_price_sum;
    }

    @Override
    public String toString() {
        return "BuyerPurchaseDetail{" +
                "saleId=" + saleId +
                ", productName='" + productName + '\'' +
                ", quantitySold=" + quantitySold +
                ", totalPricePerProduct=" + totalPricePerProduct +
                ", saleDate=" + saleDate +
                ", product_count=" + product_count +
                ", total_price=" + total_price +
                ", buyer_id=" + buyer_id +
                ", fullName='" + fullName + '\'' +
                ", quantity=" + quantity +
                ", quantity_sum=" + quantity_sum +
                ", total_price_sum=" + total_price_sum +
                '}';
    }
}
