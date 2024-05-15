package com.example.web.routes.sales;

import com.example.cookies.CookieHandlerGet;
import com.example.salesFunctions.SaleService;
import com.example.web.routes.cart.CartController;
import com.example.web.routes.cart.CartItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import spark.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewSalesSubmitRoute implements TemplateViewRoute {
    private CartController cartController;
    private SaleService saleService;

    public NewSalesSubmitRoute(SaleService saleService, CartController cartController) {
        this.saleService = saleService;
        this.cartController = cartController;
    }

    public NewSalesSubmitRoute() {
    }

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        String requestBody = request.body();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        String saleDateString = jsonObject.get("dateOfSale").getAsString();
        LocalDate saleDate = LocalDate.parse(saleDateString, DateTimeFormatter.ISO_LOCAL_DATE);

        JsonArray cartArray = jsonObject.getAsJsonArray("cart");
        cartController.getData(cartArray, saleDateString);
        List<CartItem> cartItems = cartController.getCartItems();

        saleService.setBuyerId(CookieHandlerGet.getCookieAsInt(request, "selected_buyer_id"));
        saleService.setSaleDate(saleDate);
        saleService.processSale(cartItems);

        response.redirect("/admin/sales/");
        return null;
    }
}