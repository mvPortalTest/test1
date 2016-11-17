package com.exadel.webdriver;

import com.exadel.vo.BrowserVo;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This is the factory to instantiate a WebDriver for use. It will smartly
 * return an instance of a local driver or an instance of a remote web driver
 * via the GridHub2 specified which in itself will smartly return a web driver
 * or Selenium RC instance of the browser.
 *
 * @author <a href="mailto:akirilchik@exadel.com">Alexey Kirilchik</a>
 */
public class WebDriverFactory {

    /* ----- CONSTANTS ----- */

    // Browsers
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String OPERA = "opera";
    public static final String INTERNET_EXPLORER = "ie";
    public static final String IPHONE = "iphone";

    // Platforms
    public static final String WINDOWS = "windows";
    public static final String ANDROID = "android";
    public static final String XP = "xp";
    public static final String VISTA = "vista";
    public static final String MAC = "mac";
    public static final String LINUX = "linux";


    /* ----- FIELDS ----- */

    /* ----- CONSTRUCTORS ----- */


    /* ----- METHODS ----- */

    /**
     * Returns a {@link org.openqa.selenium.remote.RemoteWebDriver} instance from the provided URL of the
     * Grid Hub and the Browser instance.
     *
     * @param gridHubUrl grid hub URI
     * @param browser    Browser object containing info around the browser to hit
     * @param username   username for BASIC authentication on the page to test
     * @param password   password for BASIC authentication on the page to test
     * @return A {@link org.openqa.selenium.remote.RemoteWebDriver}
     */
    public static WebDriver getInstance(String gridHubUrl, BrowserVo browser,
                                        String username, String password) {

        String browserName = browser.getName();

        // In case there is no Hub
        if (StringUtils.isBlank(gridHubUrl)) {
            return getInstance(browserName, username, password);
        }

        WebDriver webDriver = null;
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);

        if (CHROME.equals(browserName)) {
            capability = DesiredCapabilities.chrome();
        } else if (FIREFOX.equals(browserName)) {
            capability = DesiredCapabilities.firefox();
        } else if (INTERNET_EXPLORER.equals(browserName)) {
            capability = DesiredCapabilities.internetExplorer();
            capability.setCapability(
                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        } else if (OPERA.equals(browserName)) {
            capability = DesiredCapabilities.opera();
        } else if (ANDROID.equals(browserName)) {
            capability = DesiredCapabilities.android();
            capability.setCapability("version", "4.4");
            capability.setCapability("deviceName", "Google Nexus 7C Emulator");
            capability.setCapability("device-orientation", "'landscape'");
        } else if (IPHONE.equals(browserName)) {
            capability = DesiredCapabilities.iphone();
        }

        capability = setVersionAndPlatform(capability, browser.getVersion(),
                browser.getPlatform());

        // Create Remote WebDriver
        try {
            webDriver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return webDriver;
    }

    /**
     * This is a factory method to return a WebDriver instance for the provided
     * browser type.
     *
     * @param browser  String representing the local browser to hit
     * @param username username for BASIC authentication on the page to test
     * @param password password for BASIC authentication on the page to test
     * @return WebDriver A {@link org.openqa.selenium.WebDriver} instance
     */
    public static WebDriver getInstance(String browser, String username,
                                        String password) {

        WebDriver webDriver = null;

        if (CHROME.equals(browser)) {

            setChromeDriver();
            webDriver = new ChromeDriver();

        } else if (FIREFOX.equals(browser)) {
            setFirefoxDriver();
            webDriver = new FirefoxDriver();

        } else if (INTERNET_EXPLORER.equals(browser)) {

            setIEDriver();
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            capability.setCapability(
                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            webDriver = new InternetExplorerDriver(capability);

        }

        return webDriver;
    }

    /**
     * Helper method to set version and platform for a specific browser.
     *
     * @param capability DesiredCapabilities object coming from the selected
     *                   browser
     * @param version    browser version
     * @param platform   browser platform
     * @return DesiredCapabilities
     */
    /**
     * Helper method to set version and platform for a specific browser.
     *
     * @param capability DesiredCapabilities object coming from the selected
     *                   browser
     * @param version    browser version
     * @param platform   browser platform
     * @return DesiredCapabilities
     */
    private static DesiredCapabilities setVersionAndPlatform(
            DesiredCapabilities capability, String version, String platform) {
        if (MAC.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.MAC);
        } else if (LINUX.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.LINUX);
        } else if (XP.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.XP);
        } else if (VISTA.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.VISTA);
        } else if (WINDOWS.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.WINDOWS);
        } else if (ANDROID.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.ANDROID);
        } else {
            capability.setPlatform(Platform.ANY);
        }

        if (version != null) {
            capability.setVersion(version);
        }
        return capability;
    }

    /**
     * Helper method to set ChromeDriver location into the right system property
     */
    private static void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "c:/dev/chromedriver.exe");
    }

    /**
     * Helper method to set IEDriver location into the right system property
     */
    private static void setIEDriver() {
        System.setProperty("webdriver.ie.driver", "c:/dev/IEDriverServer.exe");
    }

    /**
     * Helper method to set IEDriver location into the right system property
     */
    private static void setFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "c:/dev/geckodriver.exe");
    }
}
