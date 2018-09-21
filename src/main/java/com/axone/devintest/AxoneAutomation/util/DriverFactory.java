package com.axone.devintest.AxoneAutomation.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.axone.devintest.AxoneAutomation.selenium.constants.IGlobalConstants;

/**
 * The anchor point for handling the drivers, is responsible for the desired
 * WebDriver instance.
 * 
 * @author Karthik Gandhinathan
 */
public class DriverFactory {

    // Logger instance
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    /**
     * Returns a driver matching the parameters passed.
     *
     * @param browserType
     *            - name of the browser
     * @param browserVersion
     *            - version of the browser
     * @param platformName
     *            - desired platform
     * @param isGridEnabled
     *            - whether grid is enabled or not
     * @param gridHubUrl
     *            - host of the GridHub
     * @return {@link WebDriver}
     */
    public static WebDriver getDriver(final String browserType, final String browserVersion, final String platformName,
	    final boolean isGridEnabled, final String gridHubUrl) {
	WebDriver driver = null;
	if (!isGridEnabled) {
	    if (browserType.equalsIgnoreCase("Firefox")) {
		driver = initializeFirefoxHeadless(false);
	    } else if (browserType.equalsIgnoreCase("   ")) {
		driver = initializeFirefoxHeadless(true);
	    } else if (browserType.equalsIgnoreCase("IE")) {
		driver = intializeIE();
	    } else if (browserType.equalsIgnoreCase("Chrome")) {
		driver = initializeChromeHeadless(false);
	    } else if (browserType.equalsIgnoreCase("Edge")) {
		driver = initializeEdge();
	    } else if (browserType.equalsIgnoreCase("ChromeHeadless")) {
		driver = initializeChromeHeadless(true);
	    } else {
		log.error("Unrecognized browserName specified. Please check environment.properties config");
		throw new RuntimeException("Unrecognized browserName specified: " + browserType);
	    }

	} else {
	    // code to handle Grid
	    DesiredCapabilities capability = new DesiredCapabilities(browserType, browserVersion,
		    Platform.fromString(platformName));
	    try {
		capability.setJavascriptEnabled(true);
		driver = new RemoteWebDriver(new URL(gridHubUrl), capability);
	    } catch (MalformedURLException e) {
		log.error("Malformed URL Exception " + e.getMessage());
	    }

	}
	return driver;
    }

    /**
     * initialises the Edgedriver
     */
    protected static WebDriver initializeEdge() {
	WebDriver driver;
	System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + IGlobalConstants.EDGE_DRIVER_PATH);
	driver = new EdgeDriver();
	return driver;
    }

    /**
     * initialises the InternetExplorerDriver
     */
    protected static WebDriver intializeIE() {
	WebDriver driver;
	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + IGlobalConstants.IE_DRIVER_PATH);
	driver = new InternetExplorerDriver();
	return driver;
    }

    /**
     * initialises the ChromeDriver
     */
    private static ChromeDriver initializeChromeHeadless(final boolean headless) {
	if (!headless && isWindows()) {
	    System.setProperty("webdriver.chrome.driver",
		    System.getProperty("user.dir") + IGlobalConstants.CHROME_DRIVER_PATH);
	    return new ChromeDriver();
	} else if (headless && isWindows()) {
	    System.setProperty("webdriver.chrome.driver",
		    System.getProperty("user.dir") + IGlobalConstants.CHROME_DRIVER_PATH);
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    options.addArguments("window-size=1200x600");
	    return new ChromeDriver(options);
	} else {
	    System.setProperty("webdriver.chrome.driver",
		    System.getProperty("user.dir") + IGlobalConstants.CHROME_LINUX_DRIVER_PATH);
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    options.addArguments("window-size=1200x600");
	    return new ChromeDriver(options);
	}
    }

    /**
     * initialises the FirefoxDriver
     */
    private static FirefoxDriver initializeFirefoxHeadless(final boolean headless) {
	if (!headless && isWindows()) {
	    System.setProperty("webdriver.gecko.driver",
		    System.getProperty("user.dir") + IGlobalConstants.GECKO_DRIVER_PATH);
	    return new FirefoxDriver();
	} else {
	    System.setProperty("webdriver.gecko.driver",
		    System.getProperty("user.dir") + IGlobalConstants.GECKO_DRIVER_LINUX_PATH);
	    FirefoxOptions options = new FirefoxOptions();
	    options.addArguments("headless");
	    options.addArguments("window-size=1200x600");
	    return new FirefoxDriver(options);
	}
    }

    /**
     * the method which determines the user environment is Windows or Non-Windows
     * returns true when its Windows OS
     * 
     * @return {@link boolean}
     */
    private static boolean isWindows() {
	return StringUtils.contains(System.getProperty("os.name"), IGlobalConstants.WINDOWS_OS);
    }

}