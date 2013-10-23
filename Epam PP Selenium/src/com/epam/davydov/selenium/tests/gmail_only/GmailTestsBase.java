package com.epam.davydov.selenium.tests.gmail_only;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.davydov.selenium.data.DataProvider;
import com.epam.davydov.selenium.data.Mail;
import com.epam.davydov.selenium.data.User;
import com.epam.davydov.selenium.gmail_pages.MailHomePage;
import com.epam.davydov.selenium.utils.PageFactory;
import com.epam.davydov.selenium.utils.WebDriverFactory;

public class GmailTestsBase {
	private static final String BASE_URL = "http://www.google.com/intl/ru/mail/help/about.html";

	protected WebDriver driver;
	protected User validUser;
	protected Mail uniqueMail;

	@BeforeClass
	protected void beforeClass() {
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		driver.get(BASE_URL);
		validUser = DataProvider.getValidUser();
	}

	@AfterClass
	protected void afterClass() {
		WebDriverFactory.dismissDriver();
	}

	protected MailHomePage openMailHomePage() {
		return PageFactory.getPage(driver, MailHomePage.class);
	}
}