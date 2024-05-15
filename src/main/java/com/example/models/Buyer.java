package com.example.models;

import java.io.Serializable;

public class Buyer implements Serializable {

    private int buyer_id;
    private String first_name;
    private String last_name;
    private int total_purchases;
    private int total_quantity;
    public String full_name;


    public Buyer() {
    }

    public Buyer(int buyer_id, String first_name, String last_name) {
        this.buyer_id = buyer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.full_name = first_name + " " + last_name;
    }

    public Buyer(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }


    public Buyer(int buyer_id, String first_name, String last_name, int total_purchases, int total_quantity) {
        this.buyer_id = buyer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.total_purchases = total_purchases;
        this.total_quantity = total_quantity;
    }

    public Buyer(int buyer_id, String first_name, String last_name, int total_purchases, int total_quantity, String full_name) {
        this.buyer_id = buyer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.total_purchases = total_purchases;
        this.total_quantity = total_quantity;
        this.full_name = full_name;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
        updateFullName();
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
        updateFullName();
    }

    public int getTotal_purchases() {
        return total_purchases;
    }

    public void setTotal_purchases(int total_purchases) {
        this.total_purchases = total_purchases;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    private void updateFullName() {
        this.full_name = this.first_name + " " + this.last_name;
    }

    public String getFull_name() {
        return this.first_name + " " + this.last_name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyer_id=" + buyer_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", total_purchases=" + total_purchases +
                ", total_quantity=" + total_quantity +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
