package com.axone.devintest.AxoneAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class SignInPage {

    @FindBy(how = How.ID, using = "email_create")
    public WebElement emailaddress;

    @FindBy(how = How.ID, using = "SubmitCreate")
    public WebElement btn_createaccount;




}
