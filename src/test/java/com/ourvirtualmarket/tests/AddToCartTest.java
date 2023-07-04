package com.ourvirtualmarket.tests;

import com.ourvirtualmarket.pages.DashboardPage;
import com.ourvirtualmarket.pages.LoginPage;
import com.ourvirtualmarket.pages.ProductPage;
import com.ourvirtualmarket.utilities.BrowserUtils;
import org.testng.annotations.Test;

public class AddToCartTest extends TestBase {

    DashboardPage dashboardPage;
    LoginPage loginpage;
    ProductPage productPage;

    @Test
    public void addToCartTest() {
        dashboardPage = new DashboardPage(page);
        loginpage = new LoginPage(page);
        productPage = new ProductPage(page);
        test = extent.createTest("Add To Cart Button Test");
        loginpage.login();
        dashboardPage.clickYourStoreBtn();
        dashboardPage.closePopUp();
        dashboardPage.navigateToModule("Networking");
        productPage.navigateWithProductNumber("3");
        productPage.assertProductPageIsVisible();
        productPage.assertProductPageDetails("ASUS ROG STRIX GS-AX3000","$156.00"," In Stock");
        productPage.assertButtons();
        page.click(productPage.addToCartButton);
        productPage.assertPopUpForAddedProduct();
        productPage.closeThePopUpForAddedProduct();
        page.hover(productPage.myCartIcon);
        productPage.verifyTheProductInMyCartIcon();
    }


}
