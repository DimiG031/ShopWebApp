package com.example.models;

import com.google.gson.JsonObject;

public class SaleDetail {
    private int detailId;
    private int saleId;
    private int productId;
    private String productName;
    private int quantitySold;
    private double totalPricePerProduct;

    public SaleDetail() {
    }

    public SaleDetail(int productId, int quantitySold, double totalPricePerProduct) {
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public SaleDetail(int detailId, int saleId, int productId, int quantitySold, double totalPricePerProduct) {
        this.detailId = detailId;
        this.saleId = saleId;
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public SaleDetail(int detailId, int saleId, int productId, String productName,
                      int quantitySold, double totalPricePerProduct) {
        this.detailId = detailId;
        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public SaleDetail(int saleId, int productId, int quantitySold, double totalPricePerProduct) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public SaleDetail(int detailId, int saleId, JsonObject totalPricePerProduct, int quantitySold) {
        this.detailId = detailId;
        this.saleId = saleId;
        this.quantitySold = quantitySold;


        if (totalPricePerProduct != null && totalPricePerProduct.has("productId") && totalPricePerProduct.has("price_per_product")) {
            this.productId = totalPricePerProduct.get("productId").getAsInt();
            this.totalPricePerProduct = totalPricePerProduct.get("price_per_product").getAsDouble();
        } else {
            this.productId = -1;
            this.totalPricePerProduct = 0.0;
        }
    }

    public SaleDetail(int saleId, String productName, int quantitySold, double totalPricePerProduct) {
        this.saleId = saleId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "SaleDetail{" +
                "detailId=" + detailId +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantitySold=" + quantitySold +
                ", totalPricePerProduct=" + totalPricePerProduct +
                '}';
    }
}