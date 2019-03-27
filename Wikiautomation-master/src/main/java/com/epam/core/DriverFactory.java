package com.epam.core;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    public static WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public DriverFactory(String browser)
    {
        createBrowserInstance(browser);
    }
    public static void createBrowserInstance(String browser)
    {
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty( "webdriver.chrome.driver", "C:\\Users\\Balakrishna_Tulaband\\Downloads\\chromedriver_win32\\chromedriver.exe");
                //WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser !");
        }
        driver.manage().window().maximize();
        logger.info(browser + "Browser is created");
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

}
