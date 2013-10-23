package com.epam.davydov.selenium.utils;

import static org.openqa.selenium.remote.BrowserType.*;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opera.core.systems.OperaDriver;

public class MyWebDriverFactory {
	private static WebDriver driver;
	private static WebDriverWait wait;

	private MyWebDriverFactory() {
		
	}

	public static WebDriver getInstance(String browserType, int waitingTimeout) {
		if (driver != null) {
			return driver;
		}
		switch (browserType) {
		case OPERA:
			initializeOperaDriver();
			break;
		case CHROME:
			initializeChromeDriver();
			break;
		case IE:
			initializeIEDriver();
			break;
		case FIREFOX:
		default:
			initializeFFDriver();
		}
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, waitingTimeout);
		return driver;
	}

	public static void dispose() {
		if (driver != null) {
			try {
				driver.close();
			} catch (Exception ignored) {
			} finally {
				driver.quit();
			}
		}
	}

	public static boolean isElementPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}

	public static void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (TimeoutException e) {
		}
		return;
	}

	private static void initializeOperaDriver() {
		System.setProperty("opera.binary", "C:\\Program Files\\Opera\\17.0.1241.45\\opera.exe");
		driver = new OperaDriver();
	}

	private static void initializeIEDriver() {
		System.setProperty("webdriver.ie.driver", Settings.getIEDriverPath());
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(ieCapabilities);
	}

	private static void initializeChromeDriver() {
		System.setProperty("webdriver.chrome.driver", Settings.getChromeDriverPath());
		driver = new ChromeDriver();
	}

	private static void initializeFFDriver() {
		FirefoxProfile firefoxProfile = new FirefoxProfile(new File(Settings.getFFProfilePath()));
		driver = new FirefoxDriver(firefoxProfile);
	}
}