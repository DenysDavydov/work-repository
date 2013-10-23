package com.pn.tests;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.pn.pages.HomePage;
import com.pn.utils.PageFactory;
import com.pn.utils.WebDriverFactory;

public class _Test {
	private static final String BASE_URL = "http://pn.com.ua/";
	protected WebDriver driver;

	@BeforeClass
	public void beforeSuite() {
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		driver.get(BASE_URL);
	}

	protected HomePage openHomePage() {
		return PageFactory.getPage(driver, HomePage.class);
	}
}