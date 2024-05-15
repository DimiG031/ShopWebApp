package com.example.dao;

import com.example.models.*;

import java.util.*;

public class BuyerDAO {

    public static ArrayList<Buyer> all() {
        return (ArrayList<Buyer>) JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT * FROM buyers;").mapToBean(Buyer.class).list());
    }

    public static ArrayList<Buyer> getForDashboard() {
        return (ArrayList<Buyer>) JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT first_name, last_name FROM buyers ;")
                .mapToBean(Buyer.class).list());
    }

    public List<BuyerInfo> getAllBuyers() {
        return JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT buyer_id, CONCAT(first_name, ' ', last_name) AS full_name " +
                        "FROM buyers")
                .map((rs, ctx) -> new BuyerInfo(rs.getInt("buyer_id"),
                        rs.getString("full_name")))
                .list());
    }

    public static Map<String, Object> getFullNameByBuyerId(int buyer_id) {
        return JdbiConnection.get().withHandle(handle -> {
            List<Map<String, Object>> results = handle.createQuery(
                            "SELECT buyer_id, first_name, last_name FROM buyers WHERE buyer_id = :buyer_id;")
                    .bind("buyer_id", buyer_id)
                    .mapToMap()
                    .list();

            if (!results.isEmpty()) {
                Map<String, Object> buyerInfo = new HashMap<>();
                buyerInfo.put("buyerId", results.get(0).get("buyer_id"));
                buyerInfo.put("fullName", results.get(0).get("first_name") + " " + results.get(0).get("last_name"));
                return buyerInfo;
            } else {
                return null;
            }
        });
    }

    public static Buyer one(int buyer_id) {
        return JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT * FROM buyers WHERE buyer_id = :buyer_id").
                bind("buyer_id", buyer_id).mapToBean(Buyer.class).first());
    }

    public static boolean save(Buyer buyer) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("INSERT INTO buyers (first_name, last_name) VALUES ( :first_name, :last_name)")
                .bindBean(buyer).execute());
        return rows_affected > 0;
    }

    public static boolean update(Buyer buyer) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("""
                        UPDATE buyers SET
                        first_name = :first_name,
                        last_name = :last_name, total_purchases = :total_purchases,
                        total_quantity = :total_quantity
                        WHERE buyer_id = :buyer_id""")
                .bindBean(buyer).execute());
        return rows_affected > 0;
    }

    public static boolean delete(int buyer_id) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("DELETE FROM buyers WHERE buyer_id = :buyer_id")
                .bind("buyer_id", buyer_id).execute());
        return rows_affected > 0;
    }

    public static ArrayList<BuyerPurchaseDetail> getBuyerDetailPurchases(int buyerId) {
        return (ArrayList<BuyerPurchaseDetail>) JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT products.productName, sales.sale_id, sale_detail.quantity_sold, " +
                        "sale_detail.total_price_per_product, sales.sale_date " +
                        "FROM sale_detail " +
                        "INNER JOIN sales ON sales.sale_id = sales.sale_id " +
                        "INNER JOIN products ON sale_detail.product_id = products.product_id " +
                        "WHERE sales.buyer_id = :buyerId")
                .bind("buyerId", buyerId)
                .map((rs, ctx) -> new BuyerPurchaseDetail(
                        rs.getInt("sale_id"),
                        rs.getString("productName"),
                        rs.getInt("quantity_sold"),
                        rs.getDouble("total_price_per_product"),
                        rs.getDate("sale_date").toLocalDate()
                ))
                .list());
    }

    public static ArrayList<BuyerPurchaseDetail> getBuyerDetails(int buyerId) {
        return (ArrayList<BuyerPurchaseDetail>) JdbiConnection.get().withHandle(handle -> handle.createQuery(
                        "SELECT s.sale_id, s.sale_date, COUNT(DISTINCT sd.product_id) AS product_count " +
                                "FROM sales s " +
                                "JOIN sale_detail sd ON s.sale_id = sd.sale_id " +
                                "WHERE s.buyer_id = :buyerId " +
                                "GROUP BY s.sale_id, s.sale_date")
                .bind("buyerId", buyerId)
                .map((rs, ctx) -> new BuyerPurchaseDetail(
                        rs.getInt("sale_id"),
                        rs.getDate("sale_date").toLocalDate(),
                        rs.getInt("product_count")
                ))
                .list());
    }

    public static ArrayList<Sales> getBuyerTotalPrice(int buyerId) {
        return (ArrayList<Sales>) JdbiConnection.get().withHandle(handle -> handle.createQuery(
                        "SELECT total_price FROM sales WHERE buyer_id = :buyerId")
                .bind("buyerId", buyerId)
                .mapToBean(Sales.class)
                .list());
    }

}