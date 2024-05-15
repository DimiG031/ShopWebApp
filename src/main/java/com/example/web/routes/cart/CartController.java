package com.example.web.routes.cart;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    private List<CartItem> cart;

    public CartController() {
        this.cart = new ArrayList<>();
    }

    public void addToCart(CartItem item) {
        cart.add(item);
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public void getData(JsonArray cartArray, String saleDateString) {
        LocalDate saleDate = LocalDate.parse(saleDateString);

        if (cartArray != null) {
            for (JsonElement element : cartArray) {
                JsonObject itemObject = element.getAsJsonObject();
                int productId = itemObject.get("id").getAsInt();
                //System.out.println(productId);
                String productName = itemObject.get("name").getAsString();
                //System.out.println(productName);
                double price = itemObject.get("price").getAsDouble();
                //System.out.println(price);
                int quantity = itemObject.get("quantity").getAsInt();
                //System.out.println(quantity);
                LocalDate dateOfSale = saleDate;
                //System.out.println(dateOfSale);
                double totalPrice = itemObject.get("totalPrice").getAsDouble();
                //System.out.println(totalPrice);
                int totalQuantity = itemObject.get("totalQuantity").getAsInt();
                //System.out.println(totalQuantity);


                CartItem cartItem = new CartItem(productId, productName, price, quantity, dateOfSale, totalPrice,
                        totalQuantity, itemObject.getAsJsonObject("totalPricePerProduct"));
                addToCart(cartItem);

               // System.out.println("This is carArray from CartController" + cartArray);
               // System.out.println("This is cartItem from CartController" + cartItem);
            }
        } else {
            System.out.println("Cart array is null");
        }
    }

    public void clearCart() {
        cart.clear();
    }

    private void processTotalPricePerProduct(JsonObject totalPricePerProduct) {
        if (totalPricePerProduct != null) {
            for (String key : totalPricePerProduct.keySet()) {
                JsonElement valueElement = totalPricePerProduct.get(key);
                if (valueElement != null && valueElement.isJsonPrimitive() && valueElement.getAsJsonPrimitive().isString()) {
                    double value = Double.parseDouble(valueElement.getAsString());
                    System.out.println("Key: " + key + ", Value: " + value);
                }
            }
        } else {
            System.out.println("totalPricePerProduct is null");
        }
    }
}