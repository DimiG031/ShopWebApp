package com.example.models;

public class BuyerInfo {
    private int buyer_id;
    private String full_name;

    public BuyerInfo(int buyer_id, String full_name) {
        this.buyer_id = buyer_id;
        this.full_name = full_name;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "BuyerInfo{" +
                "buyer_id=" + buyer_id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
