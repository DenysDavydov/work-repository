package com.epam.davydov.pn.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.helpers.factory.WebDriverFactory;
import com.epam.davydov.pn.pages.HomePage;

public class TestsCommon {
	private static final String HOME_PAGE_URL = "http://pn.com.ua/";
	protected WebDriver driver;

	@BeforeSuite
	protected void setUp() {
		Reporter.log("Initialize web driver");
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
	}

	protected HomePage openHomePage() {
		Reporter.log("Open home page (" + HOME_PAGE_URL + ")");
		driver.get(HOME_PAGE_URL);
		return PageFactory.getPage(driver, HomePage.class);
	}
}