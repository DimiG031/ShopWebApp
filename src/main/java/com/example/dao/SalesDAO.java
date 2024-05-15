package com.example.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import com.example.models.BuyerPurchaseDetail;
import com.example.models.Sales;
import org.jdbi.v3.core.Jdbi;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalesDAO {
    private static final Jdbi jdbi = JdbiConnection.get();

    public static List<Sales> all() {
        return (List<Sales>) JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT * FROM sales;").mapToBean(Sales.class).list());
    }

    public static List<BuyerPurchaseDetail> allSales() {
        return JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT * FROM sales;").mapToBean(BuyerPurchaseDetail.class).list());
    }

    public static List<BuyerPurchaseDetail> allTotalPriceByDate() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT sale_date, SUM(total_price) AS total_price_sum " +
                        "FROM sales " +
                        "GROUP BY sale_date;")
                .mapToBean(BuyerPurchaseDetail.class)
                .list());
    }

    public static List<BuyerPurchaseDetail> allTotalQuantityByDate() {
        return JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT sale_date, SUM(quantity) AS quantity_sum " +
                        "FROM sales " +
                        "GROUP BY sale_date;")
                .map((rs, ctx) -> {
                    try {
                        LocalDate saleDate = rs.getObject("sale_date", LocalDate.class);
                        int quantitySum = rs.getInt("quantity_sum");
                        return new BuyerPurchaseDetail(saleDate, quantitySum);
                    } catch (SQLException e) {
                        throw new IllegalStateException("Error mapping ResultSet to BuyerPurchaseDetail", e);
                    }
                })
                .list());
    }


    public static boolean save(Sales sales) {
        Optional<Integer> generatedId = jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO sales (buyer_id, quantity,total_price, sale_date) " +
                        "VALUES ( :buyer_id, :quantity,:total_price, :sale_date)")
                .bindBean(sales).executeAndReturnGeneratedKeys()
                .mapTo(Integer.class)
                .findFirst());
        generatedId.ifPresent(sales::setSale_id);

        return generatedId.isPresent();
    }

    public static int saveAndGetSaleId(Sales sales) {
        int saleId = 0;
        boolean saved = save(sales);
        if (saved) {
            saleId = sales.getSale_id();
        }
        return saleId;
    }

    public static boolean update(Sales sales) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("""
                        UPDATE sales SET
                        buyer_id = :buyer_id,
                        quantity = :quantity, 
                        total_price = :total_price,
                        sale_date = :sale_date
                        WHERE sale_id = :sale_id""")
                .bindBean(sales).execute());
        return rows_affected > 0;
    }


    public static boolean delete(int sales_id) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("DELETE FROM sales WHERE sales_id = :sales_id")
                .bind("sales_id", sales_id).execute());
        return rows_affected > 0;
    }

    public static ArrayList<Sales> getTotalPriceForSale(int saleId) {
        return (ArrayList<Sales>) JdbiConnection.get().withHandle(handle -> handle.createQuery(
                        "SELECT sale_id, total_price FROM sales WHERE sale_id = :saleId")
                .bind("saleId", saleId)
                .mapToBean(Sales.class)
                .list());
    }

    public static ArrayList<BuyerPurchaseDetail> getDetailsForSales(int buyerId) {
        return (ArrayList<BuyerPurchaseDetail>) JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT sales.sale_id, sales.buyer_id, sales.sale_date,sales.total_price, COUNT(DISTINCT sd.product_id) AS product_count " +
                        "FROM sales " +
                        "INNER JOIN sale_detail sd ON sales.sale_id = sd.sale_id " +
                        "WHERE sales.buyer_id = :buyerId " +
                        "GROUP BY sales.sale_id, sales.buyer_id, sales.sale_date, sales.total_price")
                .bind("buyerId", buyerId)
                .mapToBean(BuyerPurchaseDetail.class)
                        .list());
    }
}
