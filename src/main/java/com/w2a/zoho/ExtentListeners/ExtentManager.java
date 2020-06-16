	package com.w2a.zoho.ExtentListeners;

	import java.io.File;
	import java.io.IOException;
	import java.util.Date;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.configuration.Theme;
	import com.w2a.zoho.utilities.DriverManager;

	public class ExtentManager {

		private static ExtentReports extent;
		public static String screenshotPath;
		public static String screenshotName;

			public static ExtentReports createInstance(String fileName) {

				ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
				htmlReporter.config().setTheme(Theme.DARK);
				htmlReporter.config().setDocumentTitle(fileName);
				htmlReporter.config().setEncoding("utf-8");
				htmlReporter.config().setReportName(fileName);

				extent = new ExtentReports();
				extent.attachReporter(htmlReporter);
				extent.setSystemInfo("Automation Tester", System.getProperty("user.name"));
				extent.setSystemInfo("Operating System: ", System.getProperty("os.name"));
				extent.setSystemInfo("Organization", "MobileLive");
				extent.setSystemInfo("Build no", "W2A-1234");

				return extent;
			}

			public static void captureScreenshot() {

				File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

				Date d = new Date();
				screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
				screenshotPath = System.getProperty("user.dir") + "\\reports\\";
				try {
					FileUtils.copyFile(scrFile, new File(screenshotPath + screenshotName));
				} catch (IOException e) {
					System.out.println(e.getMessage());

				}
			}
		}
