package com.epam.davydov.selenium.tests.selfmail;

import static com.epam.davydov.selenium.utils.Settings.getDestinationEmail;
import static com.epam.davydov.selenium.utils.Settings.getMailBody;
import static com.epam.davydov.selenium.utils.Settings.getMailSubject;
import static com.epam.davydov.selenium.utils.Settings.getUsername;
import static com.epam.davydov.selenium.utils.Settings.getUserpass;
import static com.epam.davydov.selenium.utils.MyWebDriverFactory.isElementPresent;
import static com.epam.davydov.selenium.utils.MyWebDriverFactory.waitForElementPresent;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.davydov.selenium.utils.MyWebDriverFactory;

public class IUA {
	private static final String BASE_URL = "http://www.i.ua/";
	private WebDriver driver;

	@Test
	public void test_Login() {
		this.doLogin(getUsername(), getUserpass());
		By button_NewMessage = By.cssSelector("p.make_message");
		assertTrue(isElementPresent(button_NewMessage));
	}

	@Test()
	public void test_NewMailCreation() {
		this.createNewMail(getDestinationEmail(), getMailSubject(), getMailBody());
		By message_MailHasBeenSent = By
				.xpath("//div[text()[contains(.,'Письмо успешно отправлено адресатам')]]");
		assertTrue(isElementPresent(message_MailHasBeenSent));
	}

	@Test
	public void test_InboxContains_NewSelfMail() {
		driver.findElement(By.cssSelector("[href*=INBOX]")).click();
		By mail_Expected = By.xpath(String.format(
				"//a[.//span[starts-with(.,'Fake')] and .//span[starts-with(.,'%s')]]",
				getMailSubject()));
		assertTrue(isElementPresent(mail_Expected));
	}

	@Test
	public void test_Logout() {
		this.doLogout();
		By input_EmailField = By.name("login");
		By input_PasswordField = By.name("pass");

		assertTrue(isElementPresent(input_EmailField));
		assertTrue(isElementPresent(input_PasswordField));
	}
	
	@BeforeClass
	public void setUp() {
		driver = MyWebDriverFactory.getInstance(BrowserType.FIREFOX, 5);
		driver.get(BASE_URL);
	}
	
	@AfterClass
	public void tearDown() {
		try {
			Thread.sleep(15);
			MyWebDriverFactory.dispose();
		} catch (InterruptedException e) {
		}
	}

	private void doLogin(String username, String userpass) {
		driver.findElement(By.name("login")).clear();
		driver.findElement(By.name("login")).sendKeys("fake_user");
		driver.findElement(By.name("pass")).clear();
		driver.findElement(By.name("pass")).sendKeys("password1");
		driver.findElement(By.cssSelector("[name=lform]>p>input")).click();
		waitForElementPresent(By.cssSelector("p.make_message"));
	}

	private void createNewMail(String destinationEmail, String mailSubject, String mailBody) {
		driver.findElement(By.cssSelector("p.make_message > a")).click();
		waitForElementPresent(By.id("to"));
		driver.findElement(By.id("to")).clear();
		driver.findElement(By.id("to")).sendKeys("fake_user@i.ua");
		driver.findElement(By.name("subject")).clear();
		driver.findElement(By.name("subject")).sendKeys("Selenium IDE Task");
		driver.findElement(By.id("text")).clear();
		driver.findElement(By.id("text")).sendKeys("Text of the test task");
		driver.findElement(By.name("send")).click();
		waitForElementPresent(By
				.xpath("//div[text()[contains(.,'Письмо успешно отправлено адресатам')]]"));
	}

	private void doLogout() {
		driver.findElement(By.cssSelector("[href*=logout]")).click();
		waitForElementPresent(By.name("login"));
	}
}