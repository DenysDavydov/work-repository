package com.epam.davydov.selenium.tests.gmail_only;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.epam.davydov.selenium.data.DataProvider;
import com.epam.davydov.selenium.gmail_pages.SignInPage;
import com.epam.davydov.selenium.utils.PageFactory;
import com.epam.davydov.selenium.utils.WebDriverFactory;

public class Test_SignIn_DifferentBrowsers {
	private static final String BASE_URL = "http://mail.google.com/mail/&scc=1&ltmpl=default&ltmplcache=2&emr=1";

	@Test
	public void test_IE() {
		test(WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer()));
	}

	@Test
	public void test_Chrome() {
		test(WebDriverFactory.getDriver(DesiredCapabilities.chrome()));
	}

	@Test
	public void test_Firefox() {
		test(WebDriverFactory.getDriver(DesiredCapabilities.firefox()));
	}

	private void test(WebDriver driver) {
		driver.get(BASE_URL);		
		PageFactory.getPage(driver, SignInPage.class)
		.signInAs(DataProvider.getValidUser())
		.createNewMail(DataProvider.getUniqueMail());
	}
}