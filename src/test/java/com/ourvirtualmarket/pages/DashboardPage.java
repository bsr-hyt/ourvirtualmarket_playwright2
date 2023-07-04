package com.ourvirtualmarket.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class DashboardPage {
    private Page page;
    public String loginBtnOnHomePage = "//a[@class='link-lg']";

    public String closePopUpBtn = "button.popup-close";
    public String yourStoreBtn = "img.lazyautosizes.lazyloaded";



    public DashboardPage(Page page) {
        this.page = page;
    }

    public void closePopUp() {
        page.waitForSelector(closePopUpBtn);
        page.waitForTimeout(3000);
        page.click(closePopUpBtn);
    }

    public void clickYourStoreBtn() {
        page.click(yourStoreBtn);
    }

    /**
     <h1> Navigate To Module </h1>
     @param tab  Home, Special Offers, Television, Networking, Health & Beauty
     Gitmek istediğiniz kategori ismini parametreye girerek gitmenizi sağlar
     @author Büşra
     @see <a href = "https://ourvirtualmarket.com/">Our Virtual Market</a>
      *  */
    public void navigateToModule(String tab){
        page.locator("//li/a/strong[contains(.,'"+tab+"')]").click();
    }

    public boolean isLogoutButtonVisible() {
        page.waitForTimeout(2000);
        return page.isVisible(loginBtnOnHomePage);
    }
}
