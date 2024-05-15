package com.example.dao;

import com.example.models.Buyer;
import org.jdbi.v3.core.Jdbi;
import java.util.ArrayList;
import com.example.models.Product;

public class ProductDAO {
    private static final Jdbi jdbi = JdbiConnection.get();

    public static ArrayList<Product> all() {
        return (ArrayList<Product>) jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM products")
                        .mapToBean(Product.class)
                        .list());
    }


    public static Product one(int productId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM products WHERE product_id = :product_id")
                        .bind("product_id", productId)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null));
    }

    public static boolean save(Product product) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("INSERT INTO products (productName, price, quantity) VALUES ( :productName, :price , :quantity)")
                .bindBean(product).execute());
        return rows_affected > 0;
    }

    public static ArrayList<Product> getForDashboard() {
        return (ArrayList<Product>) JdbiConnection.get().withHandle(handle -> handle.createQuery("SELECT productName, price , quantity FROM products ;").mapToBean(Product.class).list());
    }

    public static boolean update(Product product) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("""
                        UPDATE products SET
                        productName = :productName,
                        price = :price,
                        quantity = :quantity
                        WHERE product_id = :product_id""")
                .bindBean(product).execute());
        return rows_affected > 0;
    }

    public static boolean delete(int productId) {
        int rows_affected = JdbiConnection.get().withHandle(handle -> handle.createUpdate("DELETE FROM products WHERE product_id = :product_id")
                .bind("product_id", productId)
                .execute());
        return rows_affected > 0;
    }


    public static ArrayList<Product> getProductsForBuyer(Buyer buyer) {
        return getProductsForBuyer(buyer.getBuyer_id());
    }

    public static ArrayList<Product> getProductsForBuyer(int buyer_id) {
        return (ArrayList<Product>) jdbi.withHandle(handle ->
                handle.createQuery("""
                                SELECT p.product_id, p.productName, p.price, p.quantity
                                FROM products p, buyers_products bp
                                WHERE (p.product_id = bp.product_id) AND
                                (bp.buyer_id = :buyer_id)""")
                        .bind("buyer_id", buyer_id)
                        .mapToBean(Product.class).list());
    }


    public static ArrayList<Product> getForChart() {
        try {
            ArrayList<Product> products = (ArrayList<Product>) jdbi.withHandle(handle ->
                    handle.createQuery("SELECT p.productName AS productName, SUM(d.quantity_sold) AS totalQuantity " +
                                    " FROM products p " +
                                    " INNER JOIN sale_detail d ON p.product_id = d.product_id " +
                                    " GROUP BY p.productName")
                            .mapToBean(Product.class)
                            .list());

           /* System.out.println("Number of products: " + products.size());
            for (Product product : products) {
                System.out.println("Product: " + product.getProductName() + ", Quantity: " + product.getTotalQuantity());
            }*/
            return products;
        } catch (Exception e) {
            System.err.println("Error fetching products for chart: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}

