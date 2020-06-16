package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoLoginPage extends BasePage{

    @FindBy(css = "#login_id")
    public WebElement email;

    @FindBy(css ="#password")
    public WebElement pass;

    @FindBy(xpath = "//form[@id='login']//button[@id='nextbtn']")
    public WebElement nextbtn;

    @FindBy(css= "form#login > #nextbtn")
    public WebElement signIn;

    public ZohoLoginPage doLoginAsInvalidUser(String userName, String password){

        System.out.println("Email ID: " +userName+ " and password:  " +password);
        type(email,userName, "User Name");
        click(nextbtn, "Next button");
        type(pass,password, "Password");
        click(signIn, "Sign In button");
        return this;
    }

    public ZohoAppPage doLoginAsValidUser(String userName, String password){

        System.out.println("Email ID: " +userName+ " and password:  " +password);
        type(email,userName, "User Name");
        click(nextbtn, "Next button");
        type(pass,password, "Password");
        click(signIn, "Sign In button");
        return (ZohoAppPage) openPage(ZohoAppPage.class);
    }


    @Override
    protected ExpectedCondition getPageLoadCondition() {

        return ExpectedConditions.visibilityOf(nextbtn);
    }
}
