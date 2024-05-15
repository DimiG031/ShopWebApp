package com.example.dao;

import com.example.models.SaleDetail;
import com.example.models.Sales;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;


public class SaleDetailDAO {
    private static final Jdbi jdbi = JdbiConnection.get();

    public static boolean createSaleDetail(long saleId, SaleDetail saleDetail, int quantitySold, double totalPricePerProduct) {
        return jdbi.withHandle(handle -> {
            try {
                Sales sales = handle.createQuery("SELECT * FROM sales WHERE sale_id = :saleId")
                        .bind("saleId", saleId)
                        .mapToBean(Sales.class)
                        .findOne()
                        .orElse(null);

                if (sales == null) {
                    System.out.println("Sales object not found for saleId: " + saleId);
                    return false;
                }

                int rowsAffected = handle.createUpdate("INSERT INTO sale_detail (sale_id, product_id, quantity_sold, total_price_per_product) " +
                                "VALUES (:saleId, :productId, :quantitySold, :totalPricePerProduct)")
                        .bind("saleId", saleId)
                        .bind("productId", saleDetail.getProductId())
                        .bind("quantitySold", quantitySold)
                        .bind("totalPricePerProduct", totalPricePerProduct)
                        .execute();
                return rowsAffected > 0;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        });
    }

    public static boolean save(SaleDetail saleDetail) {
        try {
            int rowsAffected = jdbi.withHandle(handle ->
                    handle.createUpdate("INSERT INTO sale_detail (sale_id, product_id, quantity_sold, total_price_per_product) " +
                                    "VALUES (:saleId, :productId, :quantitySold, :totalPricePerProduct)")
                            .bind("saleId", saleDetail.getSaleId())
                            .bind("productId", saleDetail.getProductId())
                            .bind("quantitySold", saleDetail.getQuantitySold())
                            .bind("totalPricePerProduct", saleDetail.getTotalPricePerProduct())
                            .execute());

            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static ArrayList<SaleDetail> getSaleDetailInfo(int saleId) {
        return (ArrayList<SaleDetail>) JdbiConnection.get().withHandle(handle -> handle.createQuery(
                        "SELECT sd.detail_id, sd.sale_id, sd.product_id, p.productName, sd.quantity_sold, sd.total_price_per_product " +
                                "FROM sale_detail sd " +
                                "JOIN products p ON sd.product_id = p.product_id " +
                                "WHERE sd.sale_id = :saleId")
                .bind("saleId", saleId)
                .map((rs, ctx) -> new SaleDetail(

                        rs.getInt("sale_id"),

                        rs.getString("productName"),
                        rs.getInt("quantity_sold"),
                        rs.getDouble("total_price_per_product")
                ))
                .list());
    }
}