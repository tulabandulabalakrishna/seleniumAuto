package testsecnarios;

import com.epam.core.DriverFactory;
import com.epam.core.GuiComponents;
import com.epam.utils.PropertiesLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class WikiSearch{

    public WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(GuiComponents.class);

    public static class ElementLocators {

        static final String WIKI_SEARCH_TEXTFIELD_ID = "searchInput";
        //static final By WIKI_SEARCH_TEXTFIELD_ID1 = By.id("searchInput");
        static final String SEARCH_SUGGESTIONS_ID = "typeahead-suggestions";
        static final String SELECT_TITLE_XPATH = "//*[@title='Object-oriented programming']";
        static final String GRID_EXPAND_XPATH = "//*[@id='Software_engineering']/preceding-sibling::span/a";
        static final String AUTHOR_SELECT_XPATH = "//*[@id='Software_engineering']//ancestor::tbody//*[@title='Grady Booch']";
    }

    @FindBy(id = ElementLocators.WIKI_SEARCH_TEXTFIELD_ID)
    private WebElement wikiSearchTextField;

    @FindBy(id = ElementLocators.SEARCH_SUGGESTIONS_ID)
    private WebElement searchSuggestions;

    @FindBy(xpath = ElementLocators.SELECT_TITLE_XPATH)
    private WebElement selectTitle;

    @FindBy(xpath = ElementLocators.GRID_EXPAND_XPATH)
    private WebElement gridExpand;

    @FindBy(xpath = ElementLocators.AUTHOR_SELECT_XPATH)
    private WebElement authorSelect;


    /**
     * This setUp method will fetch the browser from application properties is for searching some text in wikipedia
     * and then checking the proper navigation of links and
     * validating the content according to the user provided
     * data
     **/

    @Before
    public void setUp() {
       // PropertiesLoader.load();
       //String browser = PropertiesLoader.get("browser");
        logger.info("Setting up the default configuration");

        String browser=System.getProperty("browser");
        try {
            driver = new DriverFactory(browser).getDriver();
            GuiComponents.driver = driver;
            PageFactory.initElements(driver, this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is for searching some text in wikipedia
     * and then checking the proper navigation of links and
     * validating the content according to the user provided
     * data
     **/

    @Test
    public void wikisearchvaliations() {

        GuiComponents.navigateUrl("https://www.wikipedia.org/");

       // GuiComponents.webEditSetText(driver.findElement(WIKI_SEARCH_TEXTFIELD_ID1), );
        GuiComponents.webEditSetText(wikiSearchTextField, "OOP");

        List<WebElement> optionsToSelect = (GuiComponents.waitForElement(searchSuggestions)).findElements(By.tagName("a"));

        GuiComponents.webLinkClick(optionsToSelect.get(0));
        GuiComponents.checkAssertion(driver.getCurrentUrl(), "https://en.wikipedia.org/wiki/OOP");
        GuiComponents.webLinkClick(selectTitle);
        GuiComponents.checkAssertion(driver.getCurrentUrl(), "https://en.wikipedia.org/wiki/Object-oriented_programming");
        GuiComponents.webLinkClick(gridExpand);
        GuiComponents.webLinkClick(authorSelect);
    }

    @After
    public void aftermethod() {
        driver.quit();
        logger.info("Browser is closed");

    }

}