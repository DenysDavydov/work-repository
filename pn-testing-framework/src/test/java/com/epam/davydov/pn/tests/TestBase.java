package com.epam.davydov.pn.tests;

import static java.lang.String.format;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.IE;
import static org.openqa.selenium.remote.BrowserType.OPERA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.epam.davydov.pn.config.Configuration;
import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.config.WebDriverFactory;
import com.epam.davydov.pn.pages.HomePage;
import com.epam.davydov.pn.pages.Page;

public class TestBase {
	protected WebDriver driver;

	@BeforeSuite
	protected void setUp() {
		switch (Configuration.getBrowserType()) {		
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
		String homePageURL = System.getProperty("home.page.url");
		Reporter.log(format("Open home page (\"%s\")<br>", homePageURL));
		driver.get(homePageURL);
		return PageFactory.getPage(driver, HomePage.class);
	}

	protected <P extends Page> P navigateBack(Class<P> pageClass) {
		Reporter.log("Driver navigate back<br>");
		driver.navigate().back();
		return PageFactory.getPage(driver, pageClass);
	}
}