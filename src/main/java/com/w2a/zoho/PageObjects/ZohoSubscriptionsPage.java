package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoSubscriptionsPage extends BasePage{

    @FindBy(xpath = "//div[contains(text(),'Saas')]")
    public WebElement industryTypeSaas;
    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(industryTypeSaas);
    }
}
