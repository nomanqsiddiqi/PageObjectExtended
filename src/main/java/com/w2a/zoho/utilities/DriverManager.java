package com.w2a.zoho.utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    public static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }
}
