package com.exadel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Page {

    protected WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"menu-item-1829\"]/a")
    private WebElement practiceAreaLink;

    @CacheLookup
    @FindBy(xpath = "//*[@id='menu-item-1816']")
    private WebElement applicationDevelopmentLink;

    @CacheLookup
    @FindBy(xpath = "//a")
    private List<WebElement> allLinks;

    public List<WebElement> getAllLinks() {
        return allLinks;
    }
    public WebElement getApplicationDevelopmentLink() {
        return applicationDevelopmentLink;
    }

    public WebElement getPracticeAreaLink() {
        return practiceAreaLink;
    }

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}

