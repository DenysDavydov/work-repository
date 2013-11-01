package com.epam.davydov.pn.tests;

import static org.openqa.selenium.remote.BrowserType.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.config.Settings;
import com.epam.davydov.pn.config.WebDriverFactory;
import com.epam.davydov.pn.pages.HomePage;
import com.epam.davydov.pn.pages.Page;

public class Test_Base {
	protected WebDriver driver;

	@BeforeSuite
	protected void setUp() {
		switch (Settings.getBrowserType()) {
		case CHROME:
			driver = WebDriverFactory.getDriver(DesiredCapabilities.chrome());
			break;
		case IE:
			driver = WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer());
			break;
		case OPERA:
			driver = WebDriverFactory.getDriver(DesiredCapabilities.opera());
			break;
		case FIREFOX:
		default:
			driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
			break;
		}
	}

	protected HomePage openHomePage() {
		String homePageURL = Settings.getHomePageURL();
		Reporter.log("Open home page (" + homePageURL + ")");
		driver.get(homePageURL);
		return PageFactory.getPage(driver, HomePage.class);
	}

	protected <P extends Page> P navigateBack(Class<P> pageClass) {
		Reporter.log("Driver navigate back");
		driver.navigate().back();
		return PageFactory.getPage(driver, pageClass);
	}
}