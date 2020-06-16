package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoConnectPage extends BasePage{

    @FindBy(xpath = "//input[@value='Next']")
    public WebElement nextbutton;
    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(nextbutton);
    }
}
