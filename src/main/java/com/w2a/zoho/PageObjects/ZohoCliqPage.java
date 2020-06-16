package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoCliqPage extends BasePage {

    @FindBy(css = ".clrdark:first-child")
    public WebElement pageHeading;
    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(pageHeading);
    }
}
