package com.epam.core;

import com.epam.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertTrue;


public class GuiComponents {

    public static WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(GuiComponents.class);

    public static void webLinkClick(WebElement element)
    {
        if(waitForElement(element).isDisplayed()) {
            element.click();
            logger.info(element + " is clicked");
        }
    }

    public static void webButtonClick(WebElement element)
    {
        if(waitForElement(element).isDisplayed()) {
            element.click();
            logger.info(element + " is clicked");
        }
        else
        {
            logger.info(element + " is not displayed clicked");
        }
    }

    public static void navigateUrl(String url)
    {
        driver.navigate().to(url);
        logger.info("Navigated to" + url);
    }

    public static void webEditSetText(WebElement element, String text)
    {
        if(waitForElement(element).isDisplayed()) {
            element.sendKeys(text);
            logger.info("Entered text: " + text + "under field "+ element);
        }
    }

    public static WebElement waitForElement(WebElement element)
    {
        return (new WebDriverWait(driver, Constants.FIELD_VISIBILITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void checkAssertion(String actual, String expected)
    {
        try {
            assertTrue(expected.equalsIgnoreCase(actual));
            logger.info("Expected: "+ expected + "is matching with " + actual );

        }catch(AssertionError ex){
            logger.info("Expected: "+ expected + "is not matching with " + actual );
        }

    }
}