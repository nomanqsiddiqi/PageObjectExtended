package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoAppPage extends BasePage {


    @FindBy(xpath = "//*[contains(text(),'CRM')]")
    public WebElement crm;

    @FindBy(xpath = "//*[contains(text(),'Connect')]")
    public WebElement connect;

    @FindBy(xpath = "//*[contains(text(),'Cliq')]")
    public WebElement cliq;

    @FindBy(xpath = "//*[contains(text(),'SalesIQ')]")
    public WebElement salesIQ;

    @FindBy(xpath = "//*[contains(text(),'Subscriptions')]")
    public WebElement subscriptions;

    public ZohoCRMPage gotoCRM(){
        click(cliq, "Cliq");
        return (ZohoCRMPage) openPage(ZohoCRMPage.class);
    }
    public ZohoConnectPage gotoConnect(){
        click(connect, "Connect");
        return (ZohoConnectPage) openPage(ZohoConnectPage.class);
    }
    public ZohoCliqPage gotoCliq(){
        click(cliq, "Cliq");
        return (ZohoCliqPage) openPage(ZohoCliqPage.class);
    }
    public ZohoSalesIQPage gotoSalesIQ(){
        click(salesIQ, "Sales IQ");
        return (ZohoSalesIQPage) openPage(ZohoSalesIQPage.class);
    }
    public ZohoSubscriptionsPage gotoSubscriptions(){
        click(subscriptions, "Subscriptions");
        return (ZohoSubscriptionsPage) openPage(ZohoSubscriptionsPage.class);
    }

    public void selectApp(String appName) {

        System.out.println("Clicking on the app icon: " + appName);
        switch (appName) {
            case "crm":
                gotoCRM();
                break;
            case "connect":
                gotoConnect();
                break;
            case "cliq":
                gotoCliq();
                break;
            case "salesIQ":
               gotoSalesIQ();
                break;
            case "subscription":
                gotoSubscriptions();
                break;
            default:
                System.out.println("link name is not listed in the App page !!!");
                break;
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(crm);
    }
}
