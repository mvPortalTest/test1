package com.exadel.pages;

import com.exadel.enumeration.PageLocation;
import com.exadel.pages.HomePage;
import com.exadel.pages.Page;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class HomePageTest extends TestBase {

    public static Page page;

    public static HomePage homePage;

    public static PageLocation pageLocation;

    @BeforeClass
    public static void initElements() throws MalformedURLException {
        page = PageFactory.initElements(webDriver, Page.class);
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    @Before
    public void loadPage() {
        loadPage(PageLocation.EXADEL_HOME);
    }

    @Test
    public void testVisibilityElementAfterHover() {
        mouseOverElement(homePage.getPracticeAreaLink());
        waitForElementDisplayed("//*[@id='menu-item-1816']");
        homePage.getApplicationDevelopmentLink().click();
        Assert.assertEquals(websiteUrl + PageLocation.APPLICATION_DEVELOPMENT.getUrl(), webDriver.getCurrentUrl());
    }
}