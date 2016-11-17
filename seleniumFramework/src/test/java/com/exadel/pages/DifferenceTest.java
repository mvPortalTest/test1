package com.exadel.pages;

import com.exadel.enumeration.PageLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class DifferenceTest extends TestBase {


    public static Page page;

    public static PageLocation pageLocation;

    @BeforeClass
    public static void initElements() throws MalformedURLException {
        page = PageFactory.initElements(webDriver, Page.class);
    }

    @Before
    public void loadPage() {
        loadPage(PageLocation.EXADEL_HOME);
    }

    @Test
    public void testDifference() {
        System.out.println(page.getPracticeAreaLink().getAttribute("href"));
        System.out.println(page.getPracticeAreaLink().getText());
        System.out.println(page.getPracticeAreaLink().getCssValue("color"));
        System.out.println(page.getPracticeAreaLink().getLocation());
        System.out.println(page.getPracticeAreaLink().getCssValue("display"));
        System.out.println(page.getPracticeAreaLink().getTagName());
        System.out.println(page.getPracticeAreaLink().getSize());
    }
}