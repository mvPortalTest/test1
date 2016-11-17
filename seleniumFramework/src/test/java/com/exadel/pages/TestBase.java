package com.exadel.pages;

import com.exadel.enumeration.PageLocation;
import com.exadel.util.PropertyLoader;
import com.exadel.vo.BrowserVo;
import com.exadel.webdriver.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This is the base class for all the various test classes.
 *
 * @author <a href="mailto:akirilchik@exadel.com">Alexey Kirilchik</a>
 */
public abstract class TestBase {

    /* ----- CONSTANTS ----- */
    private static final String MODULE = TestBase.class.getName();

    /* ----- FIELDS ----- */
    protected static WebDriver webDriver;
    protected static String gridHubUrl;
    protected static String websiteUrl;
    protected static BrowserVo browser;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    Date date = new Date();

    @Rule
    public TestName name = new TestName();

    /**
     * Initialize all the necessary properties and setup the WebDriver.
     */
    @BeforeClass
    public static void init() {
        websiteUrl = PropertyLoader.loadProperty("site.url");
        gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

        browser = new BrowserVo(PropertyLoader.loadProperty("browser.name"),
                PropertyLoader.loadProperty("browser.version"),
                PropertyLoader.loadProperty("browser.platform"));

        String username = PropertyLoader.loadProperty("user.username");
        String password = PropertyLoader.loadProperty("user.password");

        webDriver = WebDriverFactory.getInstance(gridHubUrl, browser, username, password);
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @After
    public void takeScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/dev/screenshots/screenshot " + name.getMethodName() + " " + dateFormat.format(date) + ".png"));
    }

    /**
     * Does all the tear down work by quitting the webDriver.
     */
    @AfterClass
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    /**
     * This will load the page in WebDriver of the PageLocation that is
     * supplied.
     *
     * @param pageLocation The {@link PageLocation}
     */
    protected static void loadPage(PageLocation pageLocation) {
        webDriver.get(websiteUrl + pageLocation.getUrl());
    }

    /**
     * This moves the mouse over the element specified.
     *
     * @param element The {@link org.openqa.selenium.WebElement} to mouse over.
     */
    public static void mouseOverElement(WebElement element) {
        Actions action = new Actions(webDriver);
        action.moveToElement(element).build().perform();
    }

    /**
     * This drag and drop element.
     *
     * @param element The {@link org.openqa.selenium.WebElement} to mouse over.
     */
    public static void dragAndDropElement(WebElement element, WebElement target) {
        Actions action = new Actions(webDriver);
        action.dragAndDrop(element, target).build().perform();
    }

    /**
     * This causes WebDriver to wait for an element to be displayed.
     *
     * @param xpathExpression The {@link WebElement} to wait for
     */
    public static void waitForElementDisplayed(String xpathExpression) {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
    }
}