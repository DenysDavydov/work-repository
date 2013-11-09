package com.epam.davydov.pn.tests;

import static com.epam.davydov.pn.helpers.core.BaseHelper.log;
import static java.lang.String.format;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.config.Settings;
import com.epam.davydov.pn.config.WebDriverFactory;
import com.epam.davydov.pn.pages.HomePage;
import com.epam.davydov.pn.pages.Page;

public class TestBase {
	protected WebDriver driver;

	@BeforeSuite
	protected void setUp() {
		driver = WebDriverFactory.getDriver();
	}

	protected HomePage openHomePage() {
		String homePageURL = Settings.getHomePageURL();
		log(format("Open home page (\"%s\")<br>", homePageURL));
		driver.get(homePageURL);
		return PageFactory.getPage(HomePage.class);
	}

	protected <P extends Page> P navigateBack(Class<P> pageClass) {
		log("Driver navigate back<br>");
		driver.navigate().back();
		return PageFactory.getPage(pageClass);
	}
}