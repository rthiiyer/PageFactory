package com.axone.devintest.AxoneAutomation.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.axone.devintest.AxoneAutomation.selenium.constants.IWaitTimeConstants;

/**
 * All the common selenium utility methods are written here
 * 
 * @author Karthik.Gandhinathan
 *
 */
public class SeleniumUtil {

    /** Log4j Logger. */
    private static final Logger log = LogManager.getLogger(SeleniumUtil.class);

    /**
     * getText - fetches the visible innerText for the provided WebElement
     * 
     * @param locator
     * @param driver
     * @return String - visible innerText value of the locator
     */
    public static String getText(final WebElement elementTextToGet, final WebDriver driver) {
	if (!elementTextToGet.isEnabled()) {
	    log.error("The element to get the Text in not enabled: " + elementTextToGet);
	    Assert.fail("The element to get the Text in not enabled: " + elementTextToGet);
	}
	return elementTextToGet.getText();

    }

    public static void navigateBack(final WebDriver driver) {
	driver.navigate().back();
    }

    /**
     * getElement method finds a particular object in a page according to the
     * locator specified.
     *
     * @param locator
     *            is the property of the object in id, name, xpath, css, class,
     *            linkText, tagName, etc.
     * @param driver
     *            instance of WebDriver
     * @return {@link WebElement}
     * @author Karthik.Gandhinathan
     */
    public static WebElement getElement(final String locator, final WebDriver driver) {

	WebElement element = null;
	// wait until the object becomes available
	SynchronisationUtil.synchroniseUntilTheElementIsOnThePageByXPath(driver, locator);
	if (locator.contains("xpath")) {
	    element = driver.findElement(By.xpath(locator));
	} else if (locator.contains("id")) {
	    element = driver.findElement(By.id(locator));
	} else if (locator.contains("name")) {
	    element = driver.findElement(By.name(locator));
	} else if (locator.contains("css")) {
	    element = driver.findElement(By.cssSelector(locator));
	} else if (locator.contains("class")) {
	    element = driver.findElement(By.className(locator));
	} else if (locator.contains("linkText")) {
	    element = driver.findElement(By.linkText(locator));
	} else if (locator.contains("tag")) {
	    element = driver.findElement(By.tagName(locator));
	} else {
	    log.info("locator specified is invalid: " + locator);
	}

	return element;

    }

    // reporting methods

    /*    *//**
	     * to report to both log and to the extent report simultaneously
	     * 
	     * @param log
	     *            - {@link Logger}
	     * @param test
	     *            - {@link ExtentTest}
	     * @param status
	     *            - info, error, fail, warn, fatal
	     * @param message
	     *            - String description of the message to log
	     *//*
	        * public static void reportLog(final Logger log, final ExtentTest test, final
	        * String status, final Object message) {
	        * 
	        * if (status.equalsIgnoreCase("info")) { log.info(message);
	        * test.log(LogStatus.INFO, message.toString()); } else if
	        * (status.equalsIgnoreCase("error")) { log.info(message);
	        * test.log(LogStatus.ERROR, message.toString()); } else if
	        * (status.equalsIgnoreCase("fail")) { log.info(message);
	        * test.log(LogStatus.FAIL, message.toString()); } else if
	        * (status.equalsIgnoreCase("warn")) { log.info(message);
	        * test.log(LogStatus.WARNING, message.toString()); } else if
	        * (status.equalsIgnoreCase("fatal")) { log.info(message);
	        * test.log(LogStatus.FATAL, message.toString()); } else if
	        * (status.equalsIgnoreCase("pass")) { log.info(message);
	        * test.log(LogStatus.PASS, message.toString()); } else {
	        * log.info("Invalid log input provided"); test.log(LogStatus.INFO,
	        * "Invalid log input provided"); }
	        * 
	        * }
	        */

    /**
     * Resize window to the position you want
     * 
     * @param driver
     * @param x
     *            co-ordinates in int
     * @param y
     *            co-ordinates in int
     */
    public static void reSizeWindow(final WebDriver driver, final int x, final int y) {
	driver.manage().window().setPosition(new Point(0, 0));
	driver.manage().window().setSize(new Dimension(x, y));
    }

    // methods to handle different web objects

    // browser handling methods

    // navigation methods

    /**
     * this method enables clicking on the WebElement only if it is enabled. Else it
     * would fail
     * 
     * * @param elementToClick - the WebElement you want to click
     */
    public static void clickElementAssert(final WebElement elementToClick, final WebDriver driver) {

	SynchronisationUtil.synchroniseUntilTheElementIsDisplayedEnabledAndClickable(driver, elementToClick);
	if (!elementToClick.isEnabled()) {
	    log.error("The element to click in not enabled: " + elementToClick);
	    Assert.fail("The element to click in not enabled: " + elementToClick);
	}
	elementToClick.click();
    }

    public static void clickElementNoAssert(WebElement elementToClick) {
	elementToClick.click();
    }

    public static void typeValuesInATextBox(WebElement elementToTypeValuesInto, String valueToType,
	    final WebDriver driver) {
	SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, elementToTypeValuesInto);
	if (!elementToTypeValuesInto.isEnabled()) {
	    log.error("The element to click in not enabled: " + elementToTypeValuesInto);
	    Assert.fail("The element to click in not enabled: " + elementToTypeValuesInto);
	}
	highlightElement(elementToTypeValuesInto, driver);
	elementToTypeValuesInto.sendKeys(valueToType);

    }

    public static void clickCheckBox(final WebElement checkBoxWebElement, final String shouldBeSelected,
	    final WebDriver driver) {
	SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, checkBoxWebElement);
	if (shouldBeSelected.toUpperCase().equals("OFF")) {
	    if (checkBoxWebElement.isSelected()) {
		checkBoxWebElement.click();
	    }
	} else if (!checkBoxWebElement.isSelected()) {
	    checkBoxWebElement.click();
	}

    }

    /**
     * the method checks and returns whether the WebElement object is enabled or not
     * 
     * @param elementToVerify
     * @param driver
     * @return
     */
    public static boolean isElementEnabled(final WebElement elementToVerify, final WebDriver driver) {
	SynchronisationUtil.synchroniseUntilTheElementIsDisplayedAndEnabled(driver, elementToVerify,
		IWaitTimeConstants.GLOBAL_WAIT_TIME);
	return elementToVerify.isEnabled();
    }

    /**
     * This method is to clear the entered values in a TextBox or a TextArea
     * 
     * @param elementValueToClear
     * @param driver
     */
    public static void clearValuesInATextBox(final WebElement elementValueToClear, final WebDriver driver) {
	SynchronisationUtil.synchroniseUntilTheElementIsDisplayedAndEnabled(driver, elementValueToClear,
		IWaitTimeConstants.GLOBAL_WAIT_TIME);
	elementValueToClear.clear();
    }

    /**
     * <p>
     * isElementExists method finds whether the specified WebElement object exists
     * in a page or not without an assert.
     * </p>
     *
     * @param element
     *            is the WebElement of the object to find.
     * @return boolean return if the element exists this does not produce an assert.
     */
    public static boolean isElementExists(WebElement element) {
	try {
	    element.getText();
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    /**
     * dropDownSelectionByValueAppearing - this method selects the option specified
     * based on the visible text appearing.
     *
     * @author Karthik.Gandhinathan
     * @param dropDownList
     *            specifies the WebElement to identify the dropdown
     * @param valueToSelect
     *            specifies the option value to be selected
     */
    public static void selectDropDownByValueAppearing(final WebElement dropDownList, final String valueToSelect) {
	if (isElementExists(dropDownList)) {
	    Select s = new Select(dropDownList);
	    s.selectByVisibleText(valueToSelect);
	} else {
	    log.info("Element non-selectable: " + dropDownList);
	}
    }

    public static void selectDropDownOfTypeMdSelectByValueAppearing(final WebElement dropDownMdSelectList,
	    final String valueToSelect, final WebDriver driver) {
	final List<WebElement> optionsToSelect = dropDownMdSelectList.findElements(By.tagName("md-option"));
	for (final WebElement option : optionsToSelect) {
	    if (option.getText().equals(valueToSelect)) {
		System.out.println("Trying to select text: " + valueToSelect);
		option.click();
		break;
	    }
	}

    }

    /**
     * getAttributeValue - this method fetches the attribute value of a particular
     * web element .
     *
     * @author Mohd. Jeeshan
     * @param elementValueToGet
     *            specifies the WebElement from which you need to get the attribute
     *            value.
     * @param attributeValue
     *            specifies the attribute value name
     * @param driver
     *            specifies the driver
     * 
     *            return a string
     */
    public static String getAttributeValue(final WebElement elementValueToGet, String attributeValue,
	    final WebDriver driver) {
	if (elementValueToGet.getAttribute(attributeValue) == null) {
	    log.error("Invalid Attribute for the field:" + attributeValue);
	    Assert.fail("Invalid Attribute for the field:" + attributeValue);
	}
	return elementValueToGet.getAttribute(attributeValue);

    }

    /**
     * testAllUpperCase - this method checks whether all characters in a string are
     * in Upper Case .
     *
     * @author Mohd. Jeeshan
     * @param stringToCheck
     *            string to check for Upper Case
     * 
     *
     *            return a boolean value false or true
     */

    public static boolean testAllUpperCase(String stringToCheck) {
	for (int i = 0; i < stringToCheck.length(); i++) {
	    char c = stringToCheck.charAt(i);
	    if (c >= 97 && c <= 122) {
		return false;
	    }
	}

	return true;
    }

    /**
     * <p>
     * isElementNotExists method finds whether the specified WebElement object
     * exists in a page or not without an assert.
     * </p>
     *
     * @param element
     *            is the WebElement of the object to find.
     * @return boolean return if the element exists this does not produce an assert.
     */
    public static boolean isElementNotExists(final WebElement element) {
	try {
	    element.getText();
	    return false;
	} catch (NoSuchElementException e) {
	    return true;
	}
    }

    /**
     * <p>
     * sendKeyTab method finds performs the keyboard Tab key operation
     * </p>
     *
     * @param element
     *            is the WebElement of the object to find.
     */
    public static void sendKeyTab(final WebElement element) {
	element.sendKeys(Keys.TAB);
    }

    /**
     * <p>
     * getSelectedOptionFromDropdown method gives the value selected from drop down
     * </p>
     *
     * @param element
     *            is the WebElement of the object
     * 
     *            returns the selected value
     */

    public static String getSelectedOptionFromDropdown(final WebElement dropDownList) {
	Select select = new Select(dropDownList);
	/*
	 * List<WebElement> options = fieldvalue.getOptions(); for (WebElement item :
	 * options) {
	 * 
	 * if (item.getText().equals(valueToget)) { return item.getText().toString();
	 * 
	 * } }
	 */

	return select.getFirstSelectedOption().getText().toString();

    }

    public static void clickOnRandomDayInCalendar(final List<WebElement> listElement) {
	String[] elements = new String[listElement.size()];
	for (int i = 0; i < listElement.size(); i++)
	    elements[i] = listElement.get(i).getText().toString();

	int rnd = new Random().nextInt(elements.length);

	for (int i = 0; i < listElement.size(); i++) {
	    if (listElement.get(i).getText().toString().equals(elements[rnd])) {
		listElement.get(i).click();
		break;
	    }
	}
    }

    public static String getCurrentURL(final WebDriver driver) {
	return driver.getCurrentUrl();
    }

    public static boolean checkStringContains(final String originalString, final String stringToComapre) {
	boolean flag = false;
	if (originalString.contains(stringToComapre)) {
	    flag = true;
	    return flag;
	}

	return flag;

    }

    /**
     * <p>
     * generateRandomString method generate the string of variable length
     * </p>
     *
     * @param stringLength
     *            is the length of the string
     */
    public static String generateRandomString(int stringLength) {
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder stringBuilder = new StringBuilder();
	Random random = new Random();
	for (int i = 0; i < stringLength; i++) {
	    char c = chars[random.nextInt(chars.length)];
	    stringBuilder.append(c);
	}
	String randomString = stringBuilder.toString();
	return randomString.toUpperCase();
    }

    /**
     * <p>
     * verifyDisabled method verified whether webelement is disabled or not
     * </p>
     *
     * @param element
     *            & driver
     * 
     */
    public static boolean verifyDisabled(final WebElement element, final WebDriver driver) {
	boolean disabled = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].disabled",
		element);
	return disabled;
    }

    /**
     * <p>
     * highlightElement method highlights a webElement
     * </p>
     *
     * @param element
     *            & driver
     * 
     */
    public static void highlightElement(final WebElement element, final WebDriver driver) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].style.border='3px dotted blue'", element);
    }

    /**
     * <p>
     * scrollIntoView method scrolls the element in the view
     * </p>
     *
     * @param element
     * 
     * 
     */
    public static void scrollIntoView(final WebElement element, final WebDriver driver) {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * @param parameter
     *            time in milliseconds you want to wait for
     * 
     * @author Mohd Jeeshan
     * 
     */

    public static void waitForSpecificTime(final long timeToWait) {

	try {
	    Thread.sleep(timeToWait);
	} catch (InterruptedException e) {

	    e.printStackTrace();
	}

    }

    /**
     * This methods generates today's date
     * 
     * @return today's date
     * 
     * @author Mohd Jeeshan
     * 
     */

    public static String generateTodaysDate() {

	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("ddMMM");
	return formatter.format(date);

    }

    /**
     * <p>
     * clickElementInDropDownButton method clicks an element in a dropdown
     * </p>
     *
     * @param eleDropDown
     *            is the element for the dropdown ,eleItemList list of the
     *            WebElement type, Webdriver
     * 
     * @author Mohd Jeeshan
     */
    public static void clickElementInDropDownButton(final WebElement eleDropDown, final List<WebElement> eleItemList,
	    final String eleToSelect, final WebDriver driver) {
	SynchronisationUtil.synchroniseUntilTheElementIsDisplayedEnabledAndClickable(driver, eleDropDown);
	SeleniumUtil.clickElementAssert(eleDropDown, driver);
	int listSize = eleItemList.size();
	for (int i = 0; i < listSize; i++) {
	    if (eleItemList.get(i).getText().toString().contains(eleToSelect)) {
		eleItemList.get(i).click();
		break;
	    }

	}

    }

    /**
     * <p>
     * clickExpectedElementFromSmartSearch method clicks the expected item in a
     * smart search variable length
     * </p>
     *
     * @param options
     *            is the option list in smart search ,eleToSelect:element to select
     *            in item list , Webdriver
     * 
     * @author Mohd Jeeshan
     */
    public static void clickExpectedElementFromSmartSearch(final List<WebElement> options, final String eleToSelect,
	    final WebDriver driver) {

	int listSize = options.size();
	for (int i = 0; i < listSize; i++) {
	    if (options.get(i).getText().toString().equalsIgnoreCase(eleToSelect)) {
		options.get(i).click();
		break;
	    }

	}

    }

    /**
     * <p>
     * This method refresh browser window
     * 
     * </p>
     *
     * @param options
     *            Webdriver instance
     * 
     * @author Mohd Jeeshan
     */
    public static void refreshBrowserWindow(final WebDriver driver) {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("history.go(0);");

    }

    /**
     * <p>
     * Instead of forcing users to wait for a script to download before the page
     * renders. This function will execute an asynchronous piece of JavaScript in
     * the context of the currently selected window.
     * </p>
     *
     * @param options
     *            Webdriver instance
     * 
     * @author Mohd Jeeshan
     */
    public static void executeAsyncScript(final WebDriver driver) {

	long startTime = System.currentTimeMillis();

	((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

	log.info("Elapsed time: " + (System.currentTimeMillis() - startTime));
    }

    /*
     * <p> waitWhileElementHasAttributeValue function will wait while the element
     * contains the attribute value. It checks the page every second and once the
     * value of the attribute changes </p>
     * 
     * @param options String locator, String attribute: attribute name for the
     * WebElement String value :value of the the attribute Webdriver : driver
     * instance
     * 
     * @author Mohd Jeeshan
     */

    public void waitUntilElementHasAttributeValue(final String locator, final String attribute, final String value,
	    final WebDriver driver) {
	while (getElement(locator, driver).getAttribute(attribute).contains(value)) {
	    int timeout = 15;
	    if (timeout > 0) {
		timeout--;
		try {
		    System.out.println(attribute + "t" + value);
		    Thread.sleep(1000);
		} catch (Exception e) {
		}
	    }
	}
    }

    /*
     * <p> This method generates a random integer value between a specified range
     * </p>
     * 
     * @param options max : Maximum range min:Minimum Range
     * 
     * @author Mohd Jeeshan
     */

    public static String getRandomNumberBetweenRange(final int max, final int min) {
	Random random = new Random();
	int randomNum = random.nextInt((max - min) + 1) + min;
	return String.valueOf(randomNum);
    }

    /*
     * <p> This method fetch the reference number from the URL </p>
     * 
     * @param options WebDriver
     * 
     * @author Mohd Jeeshan
     */

    public static String storeReferenceNumberFromURL(final WebDriver driver) {
	String strCurrentURL = driver.getCurrentUrl();
	String[] arrCurrentURL = strCurrentURL.split("/");
	String refNumber = arrCurrentURL[arrCurrentURL.length - 1];

	return refNumber;

    }

    /**
     * switchToRecentlyOpenedWindow - method which allows the focus to switch to the
     * recently opened new window and also returns the MainWindowId from where the
     * newWindow opened.
     *
     * @author Karthik.Gandhinathan
     *
     * @return String - id of the window from where the newWindow opened
     */
    public static String switchToRecentlyOpenedWindow(final WebDriver driver) {

	Set<String> windowIds = driver.getWindowHandles();
	Iterator<String> iterator = windowIds.iterator();

	String mainWinID = iterator.next();
	String newwinID = iterator.next();

	log.info("No. of windows: --> " + windowIds.size());
	if (windowIds.size() == 2) {
	    // getting the focus into the New window
	    driver.switchTo().window(newwinID);
	}

	return mainWinID;
    }

    /**
     * this method allows the focus to switch to the passed in iFrame
     * 
     * @author Karthik.Gandhinathan
     * 
     */
    public static void switchToIFrame(final WebElement iFrameElement, final WebDriver driver) {
	driver.switchTo().frame(iFrameElement);
    }

    /**
     * this method helps to double click on the specified row number of a webTable.
     * 
     * @author Karthik.Gandhinathan
     * @param table
     *            - table WebElement
     * @param rowNum
     *            - rowNumber to double click
     */
    public static void doubleClickOnWebTable(final WebElement table, final int rowNum, final WebDriver driver) {
	List<WebElement> rows = table.findElements(By.tagName("tr"));
	List<WebElement> cells = rows.get(rowNum).findElements(By.tagName("td"));
	Actions action = new Actions(driver);
	action.moveToElement(cells.get(1)).doubleClick().perform();
    }

    /**
     * This method Clicks on the WebTable for the specified table's row.
     *
     * @author Karthik.Gandhinathan
     * @param tableXPath
     *            is the XPath Value to identify the webTable
     * @param rowNumber
     *            is the row you want to click the table
     */
    public static void clickOnWebTable(final WebElement table, final int rowNumber) {

	try {
	    List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
	    List<WebElement> cells = rows.get(rowNumber).findElements(By.tagName("td"));
	    cells.get(1).click();
	} catch (IndexOutOfBoundsException ioe) {
	    log.info("The rowNumber you have specified seems to be incorrect. Please specify the correct rowNumber.");
	} catch (Exception e) {
	    e.printStackTrace(System.err);
	}
    }

    /**
     * getRowCountForWebTable - this method returns the Row count value for the
     * given webtable.
     *
     * @author Karthik.Gandhinathan
     * @return int
     * @param tableXPath
     *            is the XPath Value to identify the webTable.
     **/
    public static int getRowCountForWebTable(final WebElement table) {
	int rowCount = 0;
	try {
	    List<WebElement> rows = table.findElements(By.tagName("tr"));
	    rowCount = rows.size() - 1;
	} catch (Exception e) {
	    e.printStackTrace(System.err);
	}
	return rowCount;

    }

    /**
     * method which searches the WebElement provided and returns a boolean
     * 
     * @param elementToSearch
     * @param driver
     * @return boolean
     */
    public static boolean isRecordExistsInAWebTable(final WebElement elementToSearch, WebDriver driver) {
	boolean retVal = false;
	SynchronisationUtil.synchroniseUntilTheElementIsDisplayedEnabledAndClickable(driver, elementToSearch);
	try {
	    Thread.sleep(3000);
	} catch (InterruptedException e) {
	    e.printStackTrace(System.err);
	}
	List<WebElement> rows = elementToSearch.findElements(By.xpath("//tbody/tr"));
	if (rows.size() > 0) {
	    retVal = true;
	} else {
	    retVal = false;
	}

	return retVal;
    }

}
