package com.exadel.pages;

import com.exadel.enumeration.PageLocation;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class SwitchToTest extends TestBase {
    public static Page page;


    public static PageLocation pageLocation;

    @BeforeClass
    public static void initElements() {
        page = PageFactory.initElements(webDriver, Page.class);
    }

    @Before
    public void loadPage() {
        webDriver.get("https://www.exadel.com");
    }

    @Test
    public void testTabs() throws IOException {
        webDriver.findElement(By.xpath("/html/body/footer/div[1]/div/div[1]/a[1]")).click();
        ArrayList<String> tabs = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1)); //switches to new tab
        webDriver.close();
        webDriver.switchTo().window(tabs.get(0)); // switch back to main screen
        webDriver.get("https://www.news.google.com");
    }

}
