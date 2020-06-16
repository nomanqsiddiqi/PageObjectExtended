package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoCRMPage extends BasePage{

    @FindBy(css = "#showCompanyInfoBtn")
    public WebElement getStartedForFree_btn;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(getStartedForFree_btn);
    }
}
