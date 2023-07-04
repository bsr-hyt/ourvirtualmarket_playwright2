package com.ourvirtualmarket.utilities;

import com.microsoft.playwright.Page;
import org.testng.Assert;

public class BrowserUtils {

    public static void softAssertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            System.out.println("Assert hatası: " + e.getMessage());
        }
    }

    public static void softAssertElementVisible(Page page, String selector, String message) {
        try {
            boolean isVisible = page.isVisible(selector);
            Assert.assertTrue(isVisible, message);
        } catch (AssertionError e) {
            System.out.println("Element görünür değil: " + selector);
        }
    }


    public static boolean isElementVisible(Page page, String selector) {
        return page.isVisible(selector);
    }

    public static void performSearch(Page page, String selector, String searchTerm) {
        page.fill(selector, searchTerm);
        page.press(selector, "Enter");
        page.waitForTimeout(1000);
    }

    public static String getTextContent(Page page, String selector) {
        return page.textContent(selector);
    }

}
