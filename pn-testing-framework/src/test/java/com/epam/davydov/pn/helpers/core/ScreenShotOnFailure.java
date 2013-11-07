package com.epam.davydov.pn.helpers.core;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.epam.davydov.pn.config.WebDriverFactory;

public class ScreenShotOnFailure extends TestListenerAdapter {
	@Override
	public void onTestFailure(ITestResult tr) {
		WebDriver driver = WebDriverFactory.getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__hh_mm_ssaa");

		String destDirName = "target/surefire-reports/screenshots/";
		String destFileName = dateFormat.format(new Date()) + ".png";

		new File(destDirName).mkdirs();
		File destFile = new File(destDirName + "/" + destFileName);

		try {
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log(format("<a href=%s><img src=%s width=200 height=150></a><br>", destFile, destFile));
	}
}
