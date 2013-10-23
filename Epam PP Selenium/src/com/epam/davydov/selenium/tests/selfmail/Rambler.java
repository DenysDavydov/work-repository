package com.epam.davydov.selenium.tests.selfmail;

import static com.epam.davydov.selenium.utils.Settings.getDestinationEmail;
import static com.epam.davydov.selenium.utils.Settings.getMailBody;
import static com.epam.davydov.selenium.utils.Settings.getMailSubject;
import static com.epam.davydov.selenium.utils.Settings.getUsername;
import static com.epam.davydov.selenium.utils.Settings.getUserpass;
import static com.epam.davydov.selenium.utils.MyWebDriverFactory.isElementPresent;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.davydov.selenium.utils.MyWebDriverFactory;

public class Rambler {
	private static final String BASE_URL = "https://mail.rambler.ru/";
	private WebDriver driver;

	@Test
	public void test_Login() {
		this.doLogin(getUsername(), getUserpass());
		By link_MailAccountsManager = By.cssSelector(".r-topline__username");
		assertTrue(isElementPresent(link_MailAccountsManager));
	}

	@Test()
	public void test_NewMailCreation() {
		this.createNewMail(getDestinationEmail(), getMailSubject(), getMailBody());
		By popupMessage_MailHasBeenSent = By
				.xpath("//div[text()[contains(., 'Письмо отправлено.')]]");
		assertTrue(isElementPresent(popupMessage_MailHasBeenSent));
	}
	
	@Test
	public void test_InboxContains_NewSelfMail() {
		// TODO Auto-generated method stub
	}

	@Test
	public void test_Logout() {
		// TODO Auto-generated method stub
	}
	
	@BeforeClass
	public void setUp() {
		driver = MyWebDriverFactory.getInstance(BrowserType.FIREFOX, 5);
		driver.get(BASE_URL);
	}
	
	@AfterClass
	public void tearDown() {
		MyWebDriverFactory.dispose();
	}
	
	private void doLogin(String username, String userpass) {
		// TODO Auto-generated method stub		
	}

	private void createNewMail(String destinationEmail, String mailSubject, String mailBody) {
		// TODO Auto-generated method stub
	}
}