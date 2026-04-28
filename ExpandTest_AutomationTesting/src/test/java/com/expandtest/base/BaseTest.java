package com.expandtest.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.expandtest.utils.ConfigReader;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<WebDriver>();

    protected ConfigReader config;

    public static WebDriver getDriverStatic() {
        return driver.get();
    }

   
    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {

        config = new ConfigReader();

        String browser = config.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");

            Map<String, Object> prefs =
                    new HashMap<String, Object>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

            driver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            driver.set(new FirefoxDriver());
        }

        getDriver().manage().window().maximize();

        getDriver().get(config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}