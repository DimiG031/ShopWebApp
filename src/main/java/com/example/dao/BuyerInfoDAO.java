package com.example.dao;

public class BuyerInfoDAO {

    private int buyerId;
    private String fullName;
    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "BuyerInfoDAO{" +
                "buyerId=" + buyerId +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
