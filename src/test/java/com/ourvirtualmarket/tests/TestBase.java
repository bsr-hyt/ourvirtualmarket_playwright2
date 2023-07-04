package com.ourvirtualmarket.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.microsoft.playwright.Page;
import com.ourvirtualmarket.utilities.ConfigurationReader;
import com.ourvirtualmarket.utilities.PlaywrightManager;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestBase {
    protected static Page page;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected ExtentHtmlReporter htmlReporter;

    @BeforeSuite
    public void beforeSuite() {
        // ExtentReports nesnesini ve raporlayıcıyı oluşturma
        htmlReporter = new ExtentHtmlReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public static void setUp() {
        PlaywrightManager.initializePlaywright();
        page = PlaywrightManager.getPage();
    }

    @AfterClass
    public static void tearDown() {
        PlaywrightManager.closePlaywright();
    }

    @BeforeMethod
    public void setUpTest() {
        String url = ConfigurationReader.get("url");
        page.navigate(url);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        // Her testin sonunda rapora sonucu ekleme
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            // Ekran görüntüsü alma ve rapora ekleme
            if (page != null) {
                String screenshotName = result.getName() + ".png";
                Path screenshotPath = Paths.get("screenshots", screenshotName);
                page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

                test.addScreenCaptureFromPath(screenshotPath.toString());
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip(result.getThrowable());
        } else {
            test.pass("Test passed");
        }
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}

