package com.epam.davydov.pn.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

import com.epam.davydov.pn.pages.HomePage;
import com.epam.davydov.pn.util.Logger;
import com.epam.davydov.pn.util.PageFactory;
import com.epam.davydov.pn.util.WebDriverFactory;

public class _Test {
	private static final String BASE_URL = "http://pn.com.ua/";
	protected WebDriver driver;
	protected Logger logger;

	@BeforeClass
	public void beforeSuite() {
		logger=new Logger();
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		driver.get(BASE_URL);
	}

	protected HomePage openHomePage() {
		return PageFactory.getPage(driver, HomePage.class);
	}
}