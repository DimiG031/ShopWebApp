package com.example;

import static spark.Spark.*;

import com.example.dao.*;
import com.example.models.User;
import com.example.models.UserType;

import com.example.salesFunctions.SaleService;
import com.example.utils.IniParser;
import com.example.web.routes.buyer.*;
import com.example.web.routes.cart.CartController;
import com.example.web.routes.login_logout.LoginFormRoute;
import com.example.web.routes.login_logout.LoginSubmitRoute;
import com.example.web.routes.login_logout.LogoutRoute;
import com.example.web.routes.login_logout.ShopDashboardRoute;
import com.example.web.routes.others.AboutRoute;
import com.example.web.routes.others.ContactRoute;
import com.example.web.routes.product.*;
import com.example.web.routes.sales.NewSalesFormRoute;

import com.example.web.routes.sales.NewSalesSubmitRoute;
import com.example.web.routes.sales.SalesDashboardRoute;
import com.example.web.templates.ThymeleafTemplateEngine;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        try {
            File iniFile = new File(System.getenv("ZADATAK_INI"));
            System.out.println(iniFile.getAbsolutePath());
            IniParser.getInstance().parse(iniFile);


            staticFiles.externalLocation(IniParser.getInstance().getValue("static/static_files"));
            ThymeleafTemplateEngine engine =
                    new ThymeleafTemplateEngine(IniParser.getInstance().getValue("static/templates"),
                            IniParser.getInstance().getValue("static/static_extension"));


            path("/admin", () -> {
                before("/*", (req, res) -> {
                    if (req.session().attribute("user") == null) {
                        res.redirect("/login?error=no_session");
                        return;
                    }
                    User user = req.session().attribute("user");
                    if (!user.getUser_type().equals(UserType.ADMINISTRATOR)) {
                        res.redirect("/login?error=no_privilege");
                        return;
                    }
                });

                get("/", new ShopDashboardRoute(new SaleDetailDAO(), new SalesDAO()), engine);

                // buyers
                get("/buyer/", new BuyerDashboardRoute(), engine);
                get("/buyer/details/:buyer_id", new BuyerDetailsRoute(), engine);
                get("/buyer/new", new NewBuyerFormRoute(), engine);
                post("/buyer/new", new NewBuyerSubmitRoute(), engine);
                get("/buyer/delete/:buyer_id", new BuyerDeleteRoute(), engine);
                get("/buyer/edit/:buyer_id", new EditBuyerFormRoute(), engine);
                post("/buyer/edit", new EditBuyerSubmitRoute(), engine);

                // products
                path("/product", () -> {
                    get("/", new ProductDashboardRoute(), engine);
                    get("/details/:buyer_id", new ProductDetailsRoute(), engine);
                    get("/new", new NewProductFormRoute(), engine);
                    post("/new", new NewProductSubmitRoute(), engine);
                    get("/delete/:product_id", new ProductDeleteRoute(), engine);
                    get("/edit/:product_id", new EditProductFormRoute(), engine);
                    post("/edit", new EditProductSubmitRoute(), engine);
                });

                //sales
                BuyerDAO buyerDAO = new BuyerDAO();
                ProductDAO productDAO = new ProductDAO();
                SalesDAO salesDAO = new SalesDAO();
                SaleService saleService = new SaleService();

                path("/sales", () -> {
                    get("/", new SalesDashboardRoute(buyerDAO), engine);
                    get("/new", new NewSalesFormRoute(buyerDAO, productDAO, salesDAO, saleService), engine);
                    post("/new", new NewSalesSubmitRoute(saleService, new CartController()), engine);
                });
                //others
                get("/about/", new AboutRoute(), engine);
                get("/contact/", new ContactRoute(), engine);
            });

            get("/login", new LoginFormRoute(), engine);
            post("/login", new LoginSubmitRoute(), engine);
            get("/logout", new LogoutRoute(), engine);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}