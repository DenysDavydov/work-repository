package com.epam.davydov.pn.tests;

import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.helpers.factory.WebDriverFactory;
import com.epam.davydov.pn.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class TestsCommon {
	private static final String HOME_PAGE_URL = "http://pn.com.ua/";
	protected WebDriver driver;

	protected void beforeClass() {
        Reporter.log("Initialiazing web driver");
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
	}

	protected HomePage openHomePage() {
        Reporter.log("Opening home page<br>");
		driver.get(HOME_PAGE_URL);
		return PageFactory.getPage(driver, HomePage.class);
	}
}