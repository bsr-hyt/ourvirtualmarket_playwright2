package com.ourvirtualmarket.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.ourvirtualmarket.utilities.BrowserUtils;
import org.testng.Assert;

import java.beans.Visibility;
import java.util.List;

public class ProductPage {

    private Page page;

    public String productDescription= "div.tab-pane.active";
    public String productName= "//div[@class='title-product']/h1";
    public String addToCartButton= "#button-cart";
    public String buyNowBtn= "(//input[@value='Buy Now'])[1]";
    public String popUpOfAddedProduct= "//*[.=' Added to cart successfully. What is next?']";
    public String closePopUpOfAddedProduct= "//button[@class='close']";
    public String myCartIcon= "i.fa.fa-shopping-bag";


    String expectedProductName;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void navigateWithProductNumber(String productNumber){
        page.locator("(//div[@class='product-item-container']/div[@class='left-block'])[" + productNumber + "]").click();
    }

    public void assertProductPageIsVisible(){
        page.hover(productDescription);
        page.waitForSelector(productDescription);
        page.isVisible(productDescription);
        page.hover(productName);
        page.waitForSelector(productDescription);
    }

    public void assertProductPageDetails(String productName, String price, String availabilityStatus){
        expectedProductName = page.textContent("//div[@class='title-product']/h1");
        Assert.assertEquals(productName,expectedProductName);
        String expectedPrice = page.textContent("#price-old");
        Assert.assertEquals(price,expectedPrice.trim());
        String availability = page.textContent("//div[text()=' In Stock']");
        String expectedAvailabilityStatus = availability.substring(14);
        Assert.assertEquals(availabilityStatus,expectedAvailabilityStatus);
    }

    public void assertButtons(){
        Assert.assertTrue(page.isVisible(addToCartButton));
        Assert.assertTrue(page.isVisible(buyNowBtn));
    }

    public void assertPopUpForAddedProduct(){
        page.waitForSelector(popUpOfAddedProduct);
        BrowserUtils.softAssertElementVisible(page,popUpOfAddedProduct, "Element görünür olmalı");
    }

    public void closeThePopUpForAddedProduct(){
        page.waitForSelector(closePopUpOfAddedProduct);
        page.click(closePopUpOfAddedProduct);
    }

    public void verifyTheProductInMyCartIcon(){
        List<ElementHandle> listOfAddedProduct = page.querySelectorAll("//ul[@class='my-list']/li");
        for (int i = 0; i < listOfAddedProduct.size(); i++) {
            if (listOfAddedProduct.get(i).textContent().equals(expectedProductName)){
                Assert.assertEquals(listOfAddedProduct.get(i).textContent(),expectedProductName);
            }
        }
    }




}
