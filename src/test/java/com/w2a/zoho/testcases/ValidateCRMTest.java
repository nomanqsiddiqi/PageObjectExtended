package com.w2a.zoho.testcases;

import com.w2a.zoho.Base.BaseTest;
import com.w2a.zoho.PageObjects.ZohoHomePage;
import com.w2a.zoho.utilities.Constants;
import com.w2a.zoho.utilities.DataProviders;
import com.w2a.zoho.utilities.DataUtil;
import com.w2a.zoho.utilities.ExcelReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ValidateCRMTest extends BaseTest {
    private String url="https://www.zoho.com/";

    @Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
    public void validateCRMTest(Hashtable<String, String> data)  {

        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("master", "ValidateCRMTest", data.get("Runmode"), excel);
        log.info("Inside Login Test");
        openBrowser(data.get("browser"));
        logInfo("Launched Browser: " + data.get("browser"));
        new ZohoHomePage().open(url).gotoLogin().doLoginAsValidUser(getDefaultUserName(), getDefaultPassword()).selectApp("subscription");
    }

    @AfterMethod
    public void tearDown(){
        logInfo("Login Test completed !!!");
        closeBrowser();
    }
}
