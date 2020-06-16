package com.w2a.zoho.PageObjects;

import com.w2a.zoho.utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoHomePage extends BasePage{

    public WebDriver driver;
    @FindBy(css=".zh-login")
            public WebElement loginlink;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(loginlink);
    }

    public ZohoLoginPage gotoLogin(){
        click(loginlink, "Login link");
        return (ZohoLoginPage) openPage(ZohoLoginPage.class);
    }

    public ZohoHomePage open(String url){
        driver = DriverManager.getDriver();
        driver.navigate().to(url);
        logInfo("Navigate to " + url);
        return (ZohoHomePage) openPage(ZohoHomePage.class);
    }
}
