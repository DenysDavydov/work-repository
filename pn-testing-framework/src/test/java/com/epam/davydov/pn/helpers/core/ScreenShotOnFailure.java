package com.epam.davydov.pn.helpers.core;

import static com.epam.davydov.pn.helpers.core.BaseHelper.log;

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
import org.testng.TestListenerAdapter;

import com.epam.davydov.pn.config.WebDriverFactory;

public class ScreenShotOnFailure extends TestListenerAdapter {
	private static final String HIPERLINK_IMAGE = "<a href=\"%s\"><img src=\"%<s\" width=200 height=150></a><br>";

	@Override
	public void onTestFailure(ITestResult tr) {
		WebDriver driver = WebDriverFactory.getDriver();
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__hh_mm_ssaa");

		String dstDirName = "target/surefire-reports/html/screenshots/";
		String dstFileName = dateFormat.format(new Date()) + ".png";

		String relativeFileName = "screenshots/" + dstFileName;

		new File(dstDirName).mkdirs();
		File destFile = new File(dstDirName + "/" + dstFileName);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			log("IO exception has occured");
		}
		log(HIPERLINK_IMAGE, relativeFileName);
	}
}
