package com.ourvirtualmarket.tests;

import com.ourvirtualmarket.utilities.BrowserUtils;
import com.ourvirtualmarket.utilities.ConfigurationReader;
import com.ourvirtualmarket.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;

    @Test
    public void loginWithValidCredentials() {
        loginPage = new LoginPage(page);
        test = extent.createTest("Positive Login Function Test");
        loginPage.login();
        Assert.assertTrue(page.isVisible(loginPage.logoutLink), "Logout button is not visible.");
        test.pass("login to account with valid email and valid password");
        }


    @Test
    public void loginWithInvalidCredentials() {
        loginPage = new LoginPage(page);
        test = extent.createTest("Negative Login Function Test");
        loginPage.login("kraft@", ConfigurationReader.get("password"));
        Assert.assertTrue(page.isVisible(loginPage.registerHead), "Logout button is not visible.");
        test.pass("login to account with invalid email and valid password");
    }
}
