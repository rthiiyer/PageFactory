/**
 * 
 */
package com.axone.devintest.AxoneAutomation.testcases;

import org.testng.annotations.Test;

import com.axone.devintest.AxoneAutomation.base.TestBase;

/**
 * @author Aarthi.Iyer
 *
 */
public class CreateAccount extends TestBase {

    // launch browser
    // navigate to selenium practice website
    // click on Signin button
    // Enter email id
    // click on create account button
    // enter the information in the create account form
    // click on submit button

    @Test
    public void emailIdValidationTest() {
	launchBrowser("chrome");
	navigateToURL();

    }

}
