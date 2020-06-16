package com.w2a.zoho.PageObjects;

import com.w2a.zoho.Base.BaseTest;
import com.w2a.zoho.ExtentListeners.ExtentListeners;
import com.w2a.zoho.utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<T>{
    protected WebDriver driver;

    public BasePage() {
        driver = DriverManager.getDriver();

    }

    public T  openPage(Class<T> clazz){
        T page = null;
        driver = DriverManager.getDriver();
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 20);
        page = PageFactory.initElements(driver,clazz);
        PageFactory.initElements(ajaxElementLocatorFactory, page);
        ExpectedCondition pageLoadCondition= ((BasePage)page).getPageLoadCondition();
        waitForPageLoad(pageLoadCondition);
        return page;
    }

    private void waitForPageLoad(ExpectedCondition pageLoadCondition){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    protected abstract ExpectedCondition getPageLoadCondition();
    public void click(WebElement element, String elementName){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        logInfo("Clicking on " + elementName);
    }
    public void type(WebElement element, String value, String elementName){
        element.sendKeys(value);
        logInfo("Typing in " + elementName + " entered the value as " + value);
    }
    public void logInfo(String message){
        ExtentListeners.testReport.get().info(message);
    }
}
