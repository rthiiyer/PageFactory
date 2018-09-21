/**
 * 
 */
package com.axone.devintest.AxoneAutomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.axone.devintest.AxoneAutomation.base.TestBase;
import com.axone.devintest.AxoneAutomation.pages.SignInPage;

/**
 * @author Aarthi.Iyer
 *
 */
public class SignIn extends TestBase {

    @Test
    public void TestA() {
	PageFactory.initElements(getDriver(), SignInPage.class);
	
	

    }

}
