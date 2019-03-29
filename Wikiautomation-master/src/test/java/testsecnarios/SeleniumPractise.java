package testsecnarios;

import com.epam.core.DriverFactory;
import com.epam.core.GuiComponents;
import com.epam.utils.WebEventListener;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumPractise {


    private EventFiringWebDriver e_driver;
    private WebEventListener eventListener;

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

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);

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


        //###### Basic authentication



    }


    @Ignore
    @Test
    public void uploadFiles() throws InterruptedException, IOException {


        GuiComponents.navigateUrl("http://the-internet.herokuapp.com/");
        GuiComponents.webLinkClick(driver.findElement(fileupload));
        GuiComponents.webButtonClick(driver.findElement(chooseFile));
        //driver.switchTo().activeElement().sendKeys("C:\\Users\\Balakrishna_Tulaband\\Desktop\\paperless-reexecution.docx");
        Thread.sleep(1000);

        Runtime.getRuntime().exec("C:\\Users\\Balakrishna_Tulaband\\Downloads\\autoit-v3\\install\\Examples\\chromeOpen.exe");

       /* Actions a = new Actions(driver);
        a.sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build().perform();

        a.keyDown(driver.findElement(chooseFile), Keys.SHIFT)
                .sendKeys(driver.findElement(chooseFile),"hai")
                .keyUp(driver.findElement(chooseFile),Keys.SHIFT)
                .build().perform();*/


        //driver.switchTo().defaultContent();

        //Thread.sleep(1000);
        //driver.switchTo().activeElement().sendKeys("C:\\Users\\Balakrishna_Tulaband\\Desktop\\paperless-reexecution.docx");

        //driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        Thread.sleep(1000);
        // driver.close();


    }


    @Ignore
    @Test
    public void scrollingexample() throws InterruptedException {

        GuiComponents.navigateUrl("http://www.globalsqa.com/angularJs-protractor/Scrollable/");

        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[17]/td[4]"));


        System.out.println("Status Code is " + RestAssured.get("http://www.globalsqa.com/angularJs-protractor/Scrollable/").getStatusCode());

        //WebElement e = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[11]/td[4]"));

        WebElement e = driver.findElement(By.xpath("//*[@class=\"table-container\"]//tbody/tr[last()-15]"));


        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", e);


        //js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
        // js.executeScript("window.scrollByLines(2)");



        Thread.sleep(5000);
        //js.executeScript(“window.scrollTo(0,document.body.scrollHeight)”);

        //((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");

        //((JavascriptExecutor)driver).executeScript("window.scrollBy(200,300)");

        Assert.assertTrue(element.getText().equalsIgnoreCase("$2,563.63"));



    }

    @Ignore
    @Test
    public void brokenLinks()
    {
        GuiComponents.navigateUrl("https://www.toolsqa.com/selenium-webdriver/scroll-element-view-selenium-javascript/");
        List<WebElement> al = driver.findElements(By.tagName("a"));



        for( WebElement e : al)
        {

            if(e.getText()== "")
            {
                //System.out.println(e.findElement(By.xpath("/span")).getText());
            }
            else
            {
                System.out.println(e.getText());
            }
        }
    }

    @Ignore
    @Test
    public void webdriverListener() throws IOException {

        e_driver.get("https://www.toolsqa.com/selenium-webdriver/scroll-element-view-selenium-javascript/");
        /*WebElement ele = e_driver.findElement(By.id("primary-menu1"));
        ele.click();*/

        File f = ((TakesScreenshot)e_driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(f, new File("C:\\Users\\Balakrishna_Tulaband\\Desktop\\TR_EXP_MSC_Paperless_001_1\\"+System.currentTimeMillis()+".png"));
        //FileUtils.copyFile(f, new File("/test/java/screenshots/"+System.currentTimeMillis()+".png"));

    }


    @Ignore
    @Test
    public void printAllChildNode()
    {
        e_driver.get("file:///C:/Users/Balakrishna_Tulaband/Desktop/uploadcheck.html");

        List<WebElement> liChilds = e_driver.findElements(By.xpath("//ul[@id='tree1']//following::a"));



        for(WebElement ele : liChilds)
        {
            String parentlinkTxt = (ele.findElement(By.xpath("parent::node()/."))).getText();
            String childLinkTxt = ele.getText();
            System.out.println("Parent link is : " + parentlinkTxt + "\n" + "child Link is : " + childLinkTxt);
            System.out.println("");
        }

        System.out.println("Totol childs in the un ordered list is :" + liChilds.size());
    }


    @Test
    public void asertions()
    {
        String s1= "BALA";
        String s2 = "BALA";

        Assert.assertEquals("Both are same", s1, s2);
    }

    @After
    public void Teardown()
    {
        e_driver.quit();
        driver.quit();

    }



}
