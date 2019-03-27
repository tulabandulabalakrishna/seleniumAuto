package testsecnarios;

import com.epam.core.DriverFactory;
import com.epam.core.GuiComponents;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumPractise {

    public WebDriver driver;
    public By otherTab = By.cssSelector("#navbar-brand-centered >ul[class='nav navbar-nav navbar-right'] >li:nth-child(4)");
    public By dragDp = By.cssSelector("#navbar-brand-centered >ul[class=\"nav navbar-nav navbar-right\"] >li:nth-child(4) >ul >li >a");
    public By dragSource = By.cssSelector("#todrag > span");
    public By dragtarget = By.cssSelector("#mydropzone");
    public By mouseOver1 = By.xpath("//*[@id='navbar-collapse-1']/ul/li[3]");
    public By mouseOver2 = By.cssSelector("#navbar-collapse-1 >ul >li.dropdown >ul >li:nth-child(5) >a");
    public By followTwit = By.xpath("//a[contains(text(),'Follow On Twitter')]");
    public By loginCell = By.id("username_or_email");
    public By paswordCell = By.id("password");
    public By submit = By.xpath("//*[@id=\"follow-login-form\"]/fieldset[2]/input");
    public By uploadFile = By.id("uploadfile");
    public By fileupload = By.xpath("//div[@id=\"content\"]//a[contains(text(),\"File Upload\")]");
    public By chooseFile = By.id("file-upload");


    /*public static void elementLocators()
    {
        otherTab = By.cssSelector("#navbar-brand-centered >ul[class='nav navbar-nav navbar-right'] >li:nth-child(4)");
        dragDp = By.cssSelector("#navbar-brand-centered >ul[class=\"nav navbar-nav navbar-right\"] >li:nth-child(4) >ul >li >a");
    }*/

    @Before
    public void setup()
    {
        String browser = System.getProperty("browser");
        DriverFactory.createBrowserInstance(browser);
        driver= DriverFactory.getDriver();
        //driver.manage().timeouts().implicitlyWait(15,TimeUnit.MILLISECONDS);
        GuiComponents.driver = driver;

    }

    @Ignore
    @Test
    public void dragDrop()
    {
        GuiComponents.navigateUrl("https://www.seleniumeasy.com/test");

        //System.out.println("other tabs locator values is " +otherTab );
        GuiComponents.webButtonClick(driver.findElement(otherTab));
        GuiComponents.webLinkClick(driver.findElement(dragDp));

        Actions actions = new Actions(driver);
        try {
            Thread.sleep(5000);
            /*actions.clickAndHold(driver.findElement(dragSource));
            actions.moveToElement(driver.findElement(dragtarget));
            actions.release(driver.findElement(dragtarget));
            actions.build().perform();*/
            actions.dragAndDrop(driver.findElement(dragSource), driver.findElement(dragtarget)).build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //actions.clickAndHold(driver.findElement(dragSource)).wait().

        //actions.moveToElement(driver.findElement(dragSource)).clickAndHold(driver.findElement(dragSource)).release(driver.findElement(dragtarget)).build().perform();

    }

    @Ignore
    @Test
    public void mouseOver()
    {
        GuiComponents.navigateUrl("https://www.bootply.com/render/6FC76YQ4Nh");

        Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        actions.moveToElement(driver.findElement(mouseOver1));
        actions.moveToElement(driver.findElement(mouseOver2));

        actions.build().perform();

    }

    @Ignore
    @Test
    public void seleniumSync()
    {

        long startTime = 0;
        long endTime = 0;
        long deltaTime = 0;

        //startTime = System.currentTimeMillis();
        GuiComponents.navigateUrl("http://www.bing.com");
        //endTime = System.currentTimeMillis();

        //deltaTime = endTime - startTime;
        //System.out.println("Get bing.com use " + deltaTime + " milliseconds.");

        //startTime = System.currentTimeMillis();
        //By byId = By.id("sb_form_q");

        /*WebDriverWait wait = new WebDriverWait(driver,1);
        wait.until(ExpectedConditions.visibilityOf( driver.findElement(byId)));*/

        //driver.findElement(byId);
        //endTime = System.currentTimeMillis();


        //deltaTime = endTime - startTime;

        //System.out.println("Find bing.com search box use " + deltaTime + " milliseconds.");


       /* FluentWait w = new FluentWait(driver);
        w.pollingEvery(10,TimeUnit.SECONDS);
        w.withTimeout(10,TimeUnit.SECONDS);
       // w.ignoring(ElementNotVisibleException.class);*/

        startTime = System.currentTimeMillis();
        System.out.println("Before going to try block");

//        try
//        {
            System.out.println("Entered in try block");
            // Find a not exist web element.
            //By byId1 = By.id("text-field-container-not-exist1");
            //List<WebElement> webEleList = driver.findElements(byId);
            GuiComponents.webButtonClick(driver.findElement(By.id("text-field-container-not-exist1")));
            System.out.println("In try block");
//        }catch(NoSuchElementException ex)
//        {
//            System.out.println("IN the catch block");
//            ex.printStackTrace();
//        }

        endTime = System.currentTimeMillis();

        deltaTime = endTime - startTime;

        System.out.println("Find bing.com not exist web element use " + deltaTime + " milliseconds.");

    }


    @Ignore
    @Test
    public void alertAndpopup()
    {
        GuiComponents.navigateUrl("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
        String mainWindow = driver.getWindowHandle();
        System.out.println("Main window handle is " +mainWindow );

        GuiComponents.webButtonClick(driver.findElement(followTwit));
        Set<String> childWindow1 = driver.getWindowHandles();

        for(String s: childWindow1)
        {
            if(!s.equalsIgnoreCase(mainWindow))
            {
                driver.switchTo().window(s);
                System.out.println("child window is " + s);
                GuiComponents.webEditSetText(driver.findElement(loginCell),"test@gmail.com");
                GuiComponents.webEditSetText(driver.findElement(paswordCell),"test@gmail.com");
                GuiComponents.webButtonClick(driver.findElement(submit));
                driver.close();

           }
        }


    }


    @Test
    public void uploadFiles() throws InterruptedException, IOException {
//        String file = "C:\\Users\\Balakrishna_Tulaband\\Desktop\\uploadcheck.html";
//        driver.get(file);
        GuiComponents.navigateUrl("http://the-internet.herokuapp.com/");
        GuiComponents.webLinkClick(driver.findElement(fileupload));
        GuiComponents.webButtonClick(driver.findElement(chooseFile));
        //driver.switchTo().activeElement().sendKeys("C:\\Users\\Balakrishna_Tulaband\\Desktop\\paperless-reexecution.docx");
        Thread.sleep(1000);

        Runtime.getRuntime().exec("C:\\Users\\Balakrishna_Tulaband\\Downloads\\autoit-v3\\install\\Examples\\chromeOpen.exe");
        //driver.close();

       /* Actions a = new Actions(driver);
        a.sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build().perform();*/


        //driver.switchTo().defaultContent();

        //Thread.sleep(1000);
        //driver.switchTo().activeElement().sendKeys("C:\\Users\\Balakrishna_Tulaband\\Desktop\\paperless-reexecution.docx");

        //driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        Thread.sleep(1000);
       // driver.close();


    }


    @After
    public void Teardown()
    {
        //driver.quit();

    }



}
