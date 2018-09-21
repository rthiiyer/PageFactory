/**
 * 
 */
package com.axone.devintest.AxoneAutomation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author Aarthi.Iyer
 *
 */
public class TestBase {

    private WebDriver driver = null;
    private Properties prop = null;


    // Constructor of the class
    public TestBase() {
	// Read the input.properties
	String path = System.getProperty("user.dir") + "/src/test/resources/input.properties";
	setProp(new Properties());
	try {
	    InputStream fis = new FileInputStream(path);
	    prop.load(fis);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    // handling different driver
    // UI interactions like Opening, navigating to URL, clicking on a button etc
    // validation methods like whether element is present or not, verify title
    // method etc
    // various reporting method , like report pass, report fail, report error,
    // screenshot etc
    // synchronisation waits


    public void launchBrowser(final String browserType) {

	if (browserType.equalsIgnoreCase("chrome")) {
	    System.setProperty("webdriver.chrome.driver", "user.dir" + "/src/test/resources/drivers/chromedriver.exe");
	    setDriver(new ChromeDriver());
	} else if (browserType.equalsIgnoreCase("firefox")) {
	    System.setProperty("webdriver.gecko.driver", "user.dir" + "/src/test/resources/drivers/geckodriver");
	    setDriver(new FirefoxDriver());
	} else if (browserType.equalsIgnoreCase("Internet Explorer")) {
	    System.setProperty("webdriver.ie.driver", "user.dir" + "/src/test/resources/drivers/IEDriverServer.exe");
	    setDriver(new InternetExplorerDriver());
	} else {
	    System.out.println("Invalid Browser type mentioned");
	}
    }

    public void navigateToURL() {

    }

    public void clickButton() {

    }

    public void typeText() {

    }

    public void isElementPresent() {

    }

    public void verifyTitle() {

    }

    public void isTextPresent() {

    }

    public void reportPass() {

    }

    public WebDriver getDriver() {
	return driver;
    }

    public void setDriver(WebDriver driver) {
	this.driver = driver;
    }

    public Properties getProp() {
	return prop;
    }

    public void setProp(Properties prop) {
	this.prop = prop;
    }

}
