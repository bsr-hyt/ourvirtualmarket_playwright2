package com.ourvirtualmarket.pages;

import com.microsoft.playwright.*;
import com.ourvirtualmarket.utilities.ConfigurationReader;

public class LoginPage {
    private Page page;
    private DashboardPage dashboardPage;


    //String Locators:
    public String inputEmailBox = "#email";
    public String password = "#pass";
    public String loginbtn = "#send2";
    public String logoutLink = "//a[@class='link-lg']";
    public String registerHead = "//div[@class='tt_popup_login']";

   /** ElementHandle sınıfını kullanarak locate etmek istiyorsan metodun içerisinde yap.
       Çünkü element ve element2 değişkenlerini tanımladığında page örneği henüz atanmamış oluyor. Ve bu yüzden NullPointerException hatası alıyorsun.

       ElementHandle element = page.querySelector("#email");
       ElementHandle element2 = page.querySelector("#pass");
    */

   //constructor oluşturma
    public LoginPage(Page page) {
        this.page = page;
        this.dashboardPage = new DashboardPage(page);
    }

    //page actions/methods:
    public void login() {
        dashboardPage.closePopUp();
        page.click(logoutLink);
        page.fill(inputEmailBox, ConfigurationReader.get("userEmail"));
        page.fill(password, ConfigurationReader.get("password"));
        page.click(loginbtn);
        page.waitForSelector(logoutLink);
    }

    public void login(String email, String passwordd) {
        dashboardPage.closePopUp();
        page.click(logoutLink);
        page.fill(inputEmailBox, email);
        page.fill(password, passwordd);
        page.click(loginbtn);
        page.waitForSelector(logoutLink);
    }



}



