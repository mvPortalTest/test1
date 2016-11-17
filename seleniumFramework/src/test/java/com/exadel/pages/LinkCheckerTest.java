package com.exadel.pages;

import com.exadel.enumeration.PageLocation;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class LinkCheckerTest extends TestBase {

    public static Page page;


    public static PageLocation pageLocation;

    @BeforeClass
    public static void initElements() {
        page = PageFactory.initElements(webDriver, Page.class);
    }

    @Before
    public void loadPage() {
        loadPage(PageLocation.EXADEL_HOME);
    }

    @Test
    public void testLinks() throws IOException {
        checkStatusCode(page.getAllLinks());
    }

    private void checkStatusCode(List<WebElement> elements) throws IOException {
        List<String> allLinks = new ArrayList<String>();
        List<String> failedLinks = new ArrayList<String>();
        for (WebElement tagA : elements) {
            allLinks.add(tagA.getAttribute("href"));
        }
        for (String url : allLinks) {
            if (url != null && url.contains("http")) {
                HttpClient client = new DefaultHttpClient();
                HttpGet response = new HttpGet(url);
                HttpResponse httpResp = client.execute(response);
                int code = httpResp.getStatusLine().getStatusCode();
                if (code != 200) {
                    failedLinks.add("Status code " + code + ". For URL: " + url);
                }
            }
        }
        if (!failedLinks.isEmpty()) {
            for (String message : failedLinks) {
                System.out.println(message);
            }
            fail("There are " + failedLinks.size() + " broken links. Please see below for more information.");
        }
    }
}
