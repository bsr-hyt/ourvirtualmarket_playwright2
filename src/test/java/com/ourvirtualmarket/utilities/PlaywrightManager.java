package com.ourvirtualmarket.utilities;

import com.microsoft.playwright.*;

public class PlaywrightManager {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;

    private PlaywrightManager() {}

    /** getPage() metodu;
     * Playwright ile oluşturulan tarayıcıda yeni bir sayfa (page) oluşturmak için kullanılan bir yardımcı metoddur.
     * Bu metodun amacı, her seferinde yeni bir sayfa oluşturmak için tekrar tekrar initializePlaywright metodunu çağırmaktan kaçınmaktır.
     * Eğer zaten bir tarayıcı ve tarayıcı bağlamı (context) mevcutsa, bu mevcut olanlar kullanılır ve yeni bir sayfa oluşturulur.
     * context.newPage() çağrısı ile yeni bir sayfa (page) oluşturulur ve oluşturulan sayfa (page) geri döndürülür.
     * */
    public static Page getPage() {
        if (playwright == null || browser == null || context == null) {
            initializePlaywright();
        }
        return context.newPage();
    }


    //initializePlaywright() metodu Playwright'ı başlatır, belirtilen tarayıcıyı seçer ve bir tarayıcı bağlamı(context) oluşturur.
    public static void initializePlaywright() {
        try {
            playwright = Playwright.create();
            String browserType = ConfigurationReader.get("browser");

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false); // Tarayıcının başlatılmasını headless olarak ayarla

            if (browserType.equalsIgnoreCase("chrome")) {
                browser = playwright.chromium().launch(options);
            } else if (browserType.equalsIgnoreCase("firefox")) {
                browser = playwright.firefox().launch(options);
            } else if (browserType.equalsIgnoreCase("webkit")) {
                browser = playwright.webkit().launch(options);
            } else {
                throw new IllegalArgumentException("Invalid browser type specified in configuration.properties file.");
            }
            context = browser.newContext();
        } catch (PlaywrightException e) {
            e.printStackTrace();
        }
    }

    //closePlaywright() metodu ise tarayıcıyı ve Playwright örneğini kapatır.
    public static void closePlaywright() {
        browser.close();
        playwright.close();
    }

}

