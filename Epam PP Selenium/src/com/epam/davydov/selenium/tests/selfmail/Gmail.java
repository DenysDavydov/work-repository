package com.epam.davydov.selenium.tests.selfmail;

import static com.epam.davydov.selenium.utils.Settings.*;
import static com.epam.davydov.selenium.utils.MyWebDriverFactory.*;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;

import com.epam.davydov.selenium.utils.*;

public class Gmail {
	private static final String BASE_URL = "http://mail.google.com/mail/&scc=1&ltmpl=default&ltmplcache=2&emr=1";
	private WebDriver driver;

	@Test
	public void test_Login() {
		this.doLogin(getUsername(), getUserpass());
		By link_MailAccountsManager = By.cssSelector("[title='testuser5867@gmail.com']");
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
		driver.findElement(By.cssSelector("[href^='h'][href$=\"#inbox\"]")).click();
		By mail_Expected = By
				.xpath(String
						.format("//tr[.//span[@email='%s'] and .//b[starts-with(.,'%s')] and .//span[contains(.,'%s')]]",
								getDestinationEmail(), getMailSubject(), getMailBody()));
		assertTrue(isElementPresent(mail_Expected));
	}

	@Test
	public void test_Logout() {
		this.doLogout();
		By input_EmailField = By.id("Email");
		By input_PasswordField = By.id("Passwd");

		assertTrue(isElementPresent(input_EmailField));
		assertTrue(isElementPresent(input_PasswordField));
	}

	@BeforeClass
	public void setUp() {
		driver = MyWebDriverFactory.getInstance(BrowserType.OPERA, 5);
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
		driver.findElement(By.id("Email")).sendKeys(username);
		driver.findElement(By.id("Passwd")).sendKeys(userpass);

		driver.findElement(By.id("signIn")).click();
		waitForElementPresent(By.cssSelector("[title='testuser5867@gmail.com']"));
	}

	private void createNewMail(String to, String subject, String mailBody) {
		driver.findElement(By.cssSelector(".z0>div")).click();
		waitForElementPresent(By.name("to"));

		driver.findElement(By.name("to")).sendKeys(to);
		driver.findElement(By.name("subjectbox")).sendKeys(subject);
		driver.findElement(By.cssSelector("[class$='editable']>iframe")).sendKeys(mailBody);

		driver.findElement(By.cssSelector("[data-tooltip*='Enter']")).click();
		waitForElementPresent(By.xpath("//div[text()[contains(., 'Письмо отправлено.')]]"));
	}

	private void doLogout() {
		driver.findElement(By.cssSelector("[title='testuser5867@gmail.com']")).click();
		driver.findElement(By.cssSelector("[href*=logout][target=_top]")).click();
		waitForElementPresent(By.id("Email"));
	}
}