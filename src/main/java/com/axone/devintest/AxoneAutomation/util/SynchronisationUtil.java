package com.axone.devintest.AxoneAutomation.util;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axone.devintest.AxoneAutomation.selenium.constants.IWaitTimeConstants;

/**
 * dynamic synchronisation utility methods for selenium waits
 * 
 * @author Karthik.Gandhinathan
 *
 */
public class SynchronisationUtil {

    /** Access to logging. */
    private static final Logger log = LogManager.getLogger(SynchronisationUtil.class);

    /**
     * Method to wait until the element is enables/clickable.
     * 
     * @deprecated now use the method found in
     *             synchroniseForElementByLinkTextToNoLongerBeVisible(final String
     *             elementTextKey)
     * @param elementTextKey
     *            String
     *
     *            public static static void
     *            waitForElementByLinkTextToNoLongerBeVisible(final String
     *            elementTextKey) {
     *            synchroniseForElementByLinkTextToNoLongerBeVisible(elementTextKey);
     *            }
     */

    /**
     * Method to wait until the element is enables/clickable.
     *
     * @param elementTextKey
     *            String
     */
    public static void synchroniseForElementByLinkTextToNoLongerBeVisible(final WebDriver driver,
	    final String elementTextKey) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(elementTextKey)));
    }

    /**
     * synchronisation method to wait until the objectToBeVisible for the time limit
     * specified. If the object does not becomes visible within the
     * GLOBAL_SYNCHRONISATION_TIMEOUT time then TimeOutException will be thrown.
     *
     * @author Karthik.Gandhinathan
     * @param xPathOfobjectToBeVisible
     *            xpathOf the Object you want to check whether its visible or not
     */
    public static void synchroniseUntilTheObjectIsVisible(final WebDriver driver,
	    final String xPathOfobjectToBeVisible) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	try {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathOfobjectToBeVisible)));
	} catch (TimeoutException te) {
	    log.info("Timed out waiting for " + xPathOfobjectToBeVisible);
	    throw te;
	}
    }

    /**
     * synchroniseUntilWebElementHasNewValue has new attribute value.
     * 
     * @param element
     *            WebElement
     * @param attribute
     *            String
     * @param value
     *            String
     */
    public static void synchroniseUntilWebElementHasNewValue(final WebDriver driver, final WebElement element,
	    final String attribute, final String value) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	try {
	    wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	} catch (TimeoutException te) {
	    log.info("Timed out waiting for attribute to be " + value);
	    throw te;
	}
    }

    /**
     * synchroniseUntilWebElementHasOneOfTheTwoValues has one of the two values.
     * Used for a switch one way then back.
     * 
     * @param element
     *            WebElement
     * @param attribute
     *            String
     * @param value1
     *            String
     * @param value2
     *            String
     */
    public static void synchroniseUntilWebElementHasOneOfTheTwoValues(final WebDriver driver, final WebElement element,
	    final String attribute, final String value1, final String value2) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	try {
	    wait.until(ExpectedConditions.or(ExpectedConditions.attributeToBe(element, attribute, value1),
		    ExpectedConditions.attributeToBe(element, attribute, value2)));
	} catch (TimeoutException te) {
	    log.info("Timed out waiting for attribute to change");
	    throw te;
	}
    }

    /**
     * synchronisation method to wait until the objectToBeVisible for the time limit
     * specified. If the object does not becomes visible within the
     * GLOBAL_SYNCHRONISATION_TIMEOUT time then TimeOutException will be thrown.
     *
     * @param classOfElement
     *            By.classname of the Element you want to check whether its visible
     *            or not
     */
    public static void synchroniseUntilTheObjectIsVisibleByClass(final WebDriver driver, final String classOfElement) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	try {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(classOfElement)));
	} catch (TimeoutException te) {
	    log.info("Timed out waiting for " + classOfElement);
	    throw te;
	}
    }

    /**
     * Synchronization method to check that an element is on the page.
     *
     * @param xpath
     *            is the class for the element on the page.
     */
    public static void synchroniseUntilTheElementIsOnThePageByXPath(final WebDriver driver, final String xpath) {
	synchroniseUntilTheElementIsOnThePageByXPath(driver, xpath, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
    }

    /**
     * Synchronization method to check that an element is on the page.
     *
     * @param xpathElement
     *            is the class for the element on the page.
     * @param timeout
     *            the time in seconds to wait for
     */
    public static void synchroniseUntilTheElementIsOnThePageByXPath(final WebDriver driver, final String xpathElement,
	    final int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathElement)));
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     */
    public static void synchroniseUntilTheElementIsOnThePage(final WebDriver driver,
	    final String xpathOfObjectToBeDisplayedAndEnabled) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_WAIT_TIME_MAX);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfObjectToBeDisplayedAndEnabled)));
    }

    /**
     * Method to check whether a text exists in an element.
     *
     * @param xpathElement
     *            is the class for the element on the page.
     * @param textSearch
     *            is the text to search if it is populated.
     */
    public static void synchroniseAndSelectTextWhenPresentByXpath(final WebDriver driver, final String xpathElement,
	    final String textSearch) {
	try {
	    WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpathElement), textSearch));
	} catch (NoSuchElementException e) {
	    log.info("Text not present for selection - skipping the selection: " + textSearch);
	    throw e;
	} catch (TimeoutException e) {
	    log.info("Timeout has occurred - Text not present for selection" + xpathElement);
	    throw e;
	}
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     * @param timeout
     *            Timeout
     */
    public static void synchroniseUntilTheElementIsOnThePage(final WebDriver driver,
	    final String xpathOfObjectToBeDisplayedAndEnabled, final int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfObjectToBeDisplayedAndEnabled)));
    }

    /**
     * visibilityOf (WebElement)
     * 
     * @param driver
     * @param elementToBeAvailable
     * @param timeout
     */
    public static void synchroniseUntilTheWebElementIsOnThePage(final WebDriver driver,
	    final WebElement elementToBeAvailable, final int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.visibilityOf(elementToBeAvailable));
    }

    /**
     * Synchronization method to check that an element populated the given
     * textSearch.
     *
     * @param xpathOfElement
     *            is the class for the element on the page.
     * @param textSearch
     *            is the text to search if it is populated.
     * @param timeout
     *            the time in seconds to wait for
     */
    public static void synchroniseUntilTheElementPopulateValues(final WebDriver driver, final String xpathOfElement,
	    final String textSearch, final int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpathOfElement), textSearch));
    }

    /**
     * Synchronization method to wait until page with the specified title is loaded.
     * If the page does not load within the GLOBAL_SYNCHRONISATION_TIMEOUT then
     * TimeOutException will be thrown.
     *
     * @param pageTitle
     *            - the expected title
     */
    public static void synchroniseUntilThePageWithTitleLoads(final WebDriver driver, final String pageTitle) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.titleContains(pageTitle));
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     */
    public static void synchroniseUntilTheElementIsDisplayedAndEnabled(final WebDriver driver,
	    final String xpathOfObjectToBeDisplayedAndEnabled) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfObjectToBeDisplayedAndEnabled)));
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     * @param timeoutSeconds
     *            the number of seconds to wait for the element
     */
    public static void synchroniseUntilTheElementIsDisplayedAndEnabled(final WebDriver driver,
	    final String xpathOfObjectToBeDisplayedAndEnabled, final int timeoutSeconds) {
	WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfObjectToBeDisplayedAndEnabled)));
    }

    public static void synchroniseUntilTheElementIsDisplayedAndEnabled(final WebDriver driver,
	    final WebElement webElementObjectToBeDisplayedAndEnabled, final int timeoutSeconds) {
	WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
	wait.until(ExpectedConditions.elementToBeClickable(webElementObjectToBeDisplayedAndEnabled));
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param webElement
     *            is the WebElement for the object to be displayed.
     */
    public static void synchroniseUntilTheElementIsDisplayedEnabledAndClickable(final WebDriver driver,
	    final WebElement webElement) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param element
     *            is the WebElement for the object to be displayed.
     */
    public static void synchroniseUntilTheElementIsVisible(final WebDriver driver, final WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Synchronization method to check that an element is no longer visible.
     *
     * @param element
     *            is the WebElement for the object no longer to be displayed.
     */
    public static void synchroniseUntilTheElementIsNotVisible(final WebDriver driver, final WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    /**
     * Synchronization method to check that an element is not visible and enabled.
     *
     * @param timeToWait
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     */
    public static void synchroniseUntilTheElementIsNotOnThePage(final WebDriver driver,
	    final String xpathOfObjectToBeDisplayedAndEnabled) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.not(
		ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOfObjectToBeDisplayedAndEnabled))));
    }

    /**
     *
     * Wait methods which indicate a chosen time limit by parameter.
     *
     */

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param cssSelector
     *            is the cssSelector for the object to be displayed.
     */
    public static void waitUntilTheElementIsOnThePageCssSelector(final WebDriver driver, final String cssSelector) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    /**
     * Synchronization method to check that an element is visible and enabled such
     * that you can click it.
     *
     * @param cssSelector
     *            is the cssSelector for the object to be displayed.
     * @param elementsNumber
     *            Number of elements to be available.
     */
    public static void waitUntilTheElementsOnTheCssSelectorByElements(final WebDriver driver, final String cssSelector,
	    final int elementsNumber) {
	WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssSelector), elementsNumber));
    }

    /**
     * Method to check whether an element exists.
     *
     * @param id
     *            is the xpath id of the object you want to check
     * @return boolean
     */
    public boolean isElementPresent(final WebDriver driver, final By id) {
	try {
	    // MC - Switch to using explicit timeouts as you can't mix and match
	    WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	    wait.until(ExpectedConditions.presenceOfElementLocated(id));
	} catch (NoSuchElementException e) {
	    log.info("Unable to find element: " + id.toString());
	    return false;
	} catch (TimeoutException e) {
	    log.info("Timeout has occurred for element" + id.toString());
	    return false;
	}

	return true;
    }

    /**
     * Method to wait until the element is visible in provided time.
     *
     * @author Mahendra.Vidyarthi
     * @param elementXpathkey
     *            String parameter for the element to be visible
     * @param waitTime
     *            a long parameter for the wait time.
     */
    public static void webDriverWaitForElement(final WebDriver driver, final long waitTime,
	    final String elementXpathkey) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpathkey)));
    }

    /**
     * Synchronization method to check that an element is no longer visible.
     *
     * @param id
     *            is the String id for this WebElement.
     */
    public static void synchroniseUntilTheElementById(final WebDriver driver, final String id) {
	try {
	    WebDriverWait wait = new WebDriverWait(driver, IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	} catch (NoSuchElementException e) {
	    log.info("Unable to find element By id: " + id.toString());
	    return;
	} catch (TimeoutException e) {
	    log.info("Timeout has occurred for element by id" + id.toString());
	    return;
	}
    }

    /**
     * Method to wait until the correct page title element is visible in provided
     * time.
     *
     * @param pageTitle
     *            String the text expecting in the page title
     * @param waitTime
     *            long the time we are waiting for
     */
    public static void webDriverWaitForTitleElement(final WebDriver driver, final long waitTime,
	    final String pageTitle) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.titleIs(pageTitle));
    }

    /**
     * Method to wait until the element is shows text in the provided time.
     *
     * @param waitTime
     *            long
     * @param elementXpathkey
     *            String
     * @param elementText
     *            String
     */
    public static void webDriverWaitForElementText(final WebDriver driver, final long waitTime,
	    final String elementXpathkey, final String elementText) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	WebElement element = driver.findElement(By.xpath(elementXpathkey));
	wait.until(ExpectedConditions.textToBePresentInElement(element, elementText));
    }

    /**
     * Method to wait until the element is enables/clickable.
     *
     * @param waitTime
     *            long
     * @param elementXpathkey
     *            String
     */
    public static void waitForElementToEnable(final WebDriver driver, final long waitTime,
	    final String elementXpathkey) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	WebElement element = driver.findElement(By.xpath(elementXpathkey));
	wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait method to check that an element is visible and enabled such that you can
     * click it.
     *
     * @param waitTime
     *            the max time to wait
     * @param xpath
     *            is the cssSelector for the object to be displayed.
     * @param elementsNumber
     *            Number of elements to be available.
     */
    public static void waitUntilTheElementsOnThePageByElements(final WebDriver driver, final long waitTime,
	    final String xpath, final int elementsNumber) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpath), elementsNumber));
    }

    /**
     * waitUntilTheElementHasTextState the element either has text or has no text.
     * 
     * @param waitTime
     *            long
     * @param xpath
     *            String
     * @param hasText
     *            boolean
     */
    public static void waitUntilTheElementHasTextState(final WebDriver driver, final long waitTime, final String xpath,
	    final boolean hasText) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(new ExpectedCondition<Boolean>() {
	    public Boolean apply(final WebDriver driver) {
		if (hasText) {
		    return driver.findElement(By.xpath(xpath)).getText().length() != 0;
		} else {
		    return driver.findElement(By.xpath(xpath)).getText().length() == 0;
		}
	    }
	});
    }

    /**
     * waitUntilTheElementIsEnabledDisplayedAndSelected the element either has text
     * or has no text.
     * 
     * @param waitTime
     *            long
     * @param xpath
     *            String
     * @param enbldSelected
     *            boolean
     */
    public static void waitUntilTheElementIsEnabledDisplayedAndSelected(final WebDriver driver, final long waitTime,
	    final String xpath, final boolean enbldSelected) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(new ExpectedCondition<Boolean>() {
	    public Boolean apply(final WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.isDisplayed() && element.isEnabled() && element.isSelected();
	    }
	});
    }

    /**
     * Synchronization method to wait until the page with the specified title is
     * loaded. If the page does not load within the time of the parameter then
     * TimeOutException will be thrown.
     *
     * @param waitTime
     *            - long numeric value
     * @param pageTitle
     *            - the expected title
     */
    public static void waitUntilThePageWithTitleLoads(final WebDriver driver, final long waitTime,
	    final String pageTitle) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.titleContains(pageTitle));
    }

    /**
     * wait method to check that an element is visible and enabled such that you can
     * click it.
     *
     * @param waitTime
     *            - long numeric value
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     */
    public static void waitUntilTheElementIsOnThePage(final WebDriver driver, final long waitTime,
	    final String xpathOfObjectToBeDisplayedAndEnabled) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfObjectToBeDisplayedAndEnabled)));
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM
     * of a page, is visible. Visibility means that the element is not only
     * displayed but also has a height and width that is greater than 0.
     * 
     * @param driver
     * @param waitTime
     *            - long numeric value
     * @param element
     *            - expected WebElement to be visible on the page
     */
    public static void waitUntilTheElementIsOnThePage(final WebDriver driver, final long waitTime,
	    final WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * wait method to check that an element is visible and enabled such that you can
     * click it.
     *
     * @param waitTime
     *            - long numeric value
     * @param xpathOfObjectToBeDisplayedAndEnabled
     *            is the xpath for the object to be displayed.
     */
    public static void waitUntilTheElementIsClickable(final WebDriver driver, final long waitTime,
	    final String xpathOfObjectToBeDisplayedAndEnabled) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfObjectToBeDisplayedAndEnabled)));
    }

    /**
     * Sets the amount of time to wait for a page load to complete before throwing
     * an error
     * 
     * @param driver
     * @author Karthik.Gandhinathan
     * 
     */
    public static void waitUntilThePageLoadsCompletely(final WebDriver driver, final long waitTime) {
	driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);

    }

    public static void waitUntilAllTheElementsAreVisible(final WebDriver driver, final long waitTime,
	    final List<WebElement> listOfElements) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.visibilityOfAllElements(listOfElements));
    }

}

