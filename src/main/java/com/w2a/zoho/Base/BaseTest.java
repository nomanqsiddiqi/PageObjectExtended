package com.w2a.zoho.Base;

import com.w2a.zoho.ExtentListeners.ExtentListeners;
import com.w2a.zoho.PageObjects.BasePage;
import com.w2a.zoho.utilities.DriverFactory;
import com.w2a.zoho.utilities.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;
    protected Properties config = new Properties();
    private FileInputStream fis;
    protected static Logger log = Logger.getLogger(BasePage.class);
    String sourcePath="C:\\tools\\workspace\\PageObjectExtended\\reports\\";
    String destPath ="C:\\tools\\workspace\\PageObjectExtended\\archived";
    boolean grid = false;
    private String defaultUserName;
    private String defaultPassword;

    public String getDefaultUserName() {
        return defaultUserName;
    }

    public void setDefaultUserName(String defaultUserName) {
        this.defaultUserName = defaultUserName;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @BeforeSuite
    public void setupFramework() throws IOException {
        configureLogging();
        moveToArchivelog(sourcePath,destPath);
        DriverFactory.setGridPath("http://192.168.2.19:4444/wd/hub");
        DriverFactory.setConfigPropertyFile(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
        try {
            fis = new FileInputStream(DriverFactory.getConfigPropertyFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(fis);
            log.info("Configuration file loaded !!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logInfo(String message){
        ExtentListeners.testReport.get().info(message);
    }
    public void moveToArchivelog(String srcFilePath, String destFilePath) throws IOException {
        File srcDir = new File(srcFilePath);
        File destDir = new File(destFilePath);
        FileUtils.copyDirectory(srcDir, destDir);
        FileUtils.cleanDirectory(srcDir);
    }

    public void configureLogging(){
        String log4jConfigFile = System.getProperty("user.dir") + "//src//test//resources//properties//log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
    }
    public void openBrowser(String browser) {

        if(System.getenv("ExecutionType ") != null && System.getenv("ExecutionType ").equalsIgnoreCase("Grid")){
            grid = true;
        }
        DriverFactory.setIsRemote(grid);

        if(DriverFactory.getIsRemote()){
            System.out.println(browser + " browser initiated - Remote WebDriver ");
            DesiredCapabilities caps = new DesiredCapabilities();
            if(browser.equalsIgnoreCase("chrome")){
                caps = DesiredCapabilities.chrome();
                caps.setBrowserName("chrome");
                caps.setPlatform(Platform.ANY);
            }else if(browser.equalsIgnoreCase("firefox")){
                caps = DesiredCapabilities.firefox();
                caps.setBrowserName("firefox");
                caps.setPlatform(Platform.ANY);
            }else if(browser.equalsIgnoreCase("ie")){
                caps = DesiredCapabilities.internetExplorer();
                caps.setBrowserName("iexplorer");
                caps.setPlatform(Platform.WINDOWS);
            }
            try {
                driver = new RemoteWebDriver(new URL("http://192.168.2.58:4444/wd/hub"), caps);
                log.info("Starting the grid session !!!");
                log.info(browser + " browser launched - Remote webdriver !!!");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(browser + " browser initiated - Local WebDriver ");
            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if(browser.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else if(browser.equalsIgnoreCase("ie")){
                WebDriverManager.iedriver().setup();
                Capabilities capabilities;
                driver = new InternetExplorerDriver();
            }
            log.info(browser + " browser launched - local webdriver!!!!");
        }

        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        setDefaultUserName(config.getProperty("defaultUserName"));
        setDefaultPassword(config.getProperty("defaultPassword"));

    }

    public void closeBrowser(){
        if(DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            logInfo("closed the browser successfully !!!");
            log.info("Text Execution Completed !!!");
        }
    }
}
