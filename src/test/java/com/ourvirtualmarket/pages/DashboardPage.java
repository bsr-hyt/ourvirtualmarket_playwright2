package com.ourvirtualmarket.pages;

import com.microsoft.playwright.Page;

public class DashboardPage {
    private Page page;
    public String loginBtnOnHomePage = "//a[@class='link-lg']";

    public String closePopUpBtn = "//button[@title='Close']";



    public DashboardPage(Page page) {
        this.page = page;
    }

    public void closePopUp() {
        page.click(closePopUpBtn);
    }

    public boolean isLogoutButtonVisible() {
        page.waitForTimeout(2000);
        return page.isVisible(loginBtnOnHomePage);
    }
}
