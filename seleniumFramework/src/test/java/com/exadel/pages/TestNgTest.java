package com.exadel.pages;


import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestNgTest extends TestBase {

    private static final Logger LOGGER = Logger.getLogger(TestNgTest.class.getName());

    @BeforeGroups("shopping")
    public void beforeGroups() {
        LOGGER.log(Level.INFO, "@BeforeGroups");
    }

    @AfterGroups("shopping")
    public void afterGroups() {
        LOGGER.log(Level.INFO, "@AfterGroups");
    }

    @BeforeClass
    public void beforeClass() {
        LOGGER.log(Level.INFO, "@BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.log(Level.INFO, "@AfterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        LOGGER.log(Level.INFO, "@BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        LOGGER.log(Level.INFO, "@AfterMethod");
    }

    @Test(threadPoolSize = 4, invocationCount = 10000)
    public void runTest1() throws InterruptedException {
        LOGGER.log(Level.INFO, "@Test - runTest1");
        Assert.assertTrue(true);
    }

    @Test(dependsOnGroups = "shopping", groups = "not shopping", enabled = true)
    public void runTest2() {
        LOGGER.log(Level.INFO, "@Test - runTest2");
        Assert.assertTrue(true);
    }

    @Test(dependsOnGroups = "not shopping", groups = "other shopping group", enabled = false)
    public void runTest3() {
        LOGGER.log(Level.INFO, "@Test - runTest3");
        Assert.assertTrue(false);
    }

    @Test
    public void divisionWithException() {
        int i = 1 / 0;
    }
}
