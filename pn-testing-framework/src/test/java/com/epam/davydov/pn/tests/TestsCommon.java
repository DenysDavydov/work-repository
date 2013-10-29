package com.epam.davydov.pn.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.epam.davydov.pn.helpers.core.Settings;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.helpers.factory.WebDriverFactory;
import com.epam.davydov.pn.pages.HomePage;
import com.epam.davydov.pn.pages.Page;

public class TestsCommon {
	protected WebDriver driver;

	@BeforeSuite
	protected void setUp() {
		Reporter.log("Initialize web driver");
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
	}

	protected HomePage openHomePage() {
		String homePageURL = Settings.getHomePageURL();
		Reporter.log("Open home page (" + homePageURL + ")");
		driver.get(homePageURL);
		return PageFactory.getPage(driver, HomePage.class);
	}	

	public <P extends Page> P navigateBack(Class<P> pageClass) {
		Reporter.log("Driver navigate back");
		driver.navigate().back();
		return PageFactory.getPage(driver, pageClass);
	}
}