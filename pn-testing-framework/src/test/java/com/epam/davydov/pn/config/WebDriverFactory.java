package com.epam.davydov.pn.config;

import static com.epam.davydov.pn.helpers.core.BaseHelper.log;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.IE;
import static org.openqa.selenium.remote.BrowserType.OPERA;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.opera.core.systems.OperaDriver;

public class WebDriverFactory {
	private static String defaultHub = null;
	private static int restartFrequency = Integer.MAX_VALUE;
	private static String key = null;
	private static int count = 0;
	private static WebDriver driver;

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				dismissDriver();
			}
		});
	}

	public static void setDefaultHub(String newDefaultHub) {
		defaultHub = newDefaultHub;
	}

	public static void setRestartFrequency(int newRestartFrequency) {
		restartFrequency = newRestartFrequency;
	}

	public static WebDriver getDriver(String hub) {
		String browserType = Settings.getBrowserType();
		count++;
		if (driver == null) {
			return newWebDriver(hub, browserType);
		}
		String newKey = browserType + ":" + hub;
		if (!newKey.equals(key)) {
			dismissDriver();
			key = newKey;
			return newWebDriver(hub, browserType);
		}
		try {
			driver.getCurrentUrl();
		} catch (Throwable t) {
			t.printStackTrace();
			return newWebDriver(hub, browserType);
		}
		if (count >= restartFrequency) {
			dismissDriver();
			return newWebDriver(hub, browserType);
		}
		return driver;
	}

	public static WebDriver getDriver() {
		return getDriver(defaultHub);
	}

	public static void dismissDriver() {
		if (driver != null) {
			try {
				driver.quit();
				driver = null;
				key = null;
			} catch (WebDriverException ex) {
			}
		}
	}

	private static WebDriver newWebDriver(String hub, String browserType) {
		driver = (hub == null) ? newLocalDriver(browserType) : newRemoteDriver(hub, browserType);
		key = browserType + ":" + hub;
		count = 0;
		return driver;
	}

	private static WebDriver newRemoteDriver(String hub, String browserType) {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browserType);
			return new RemoteWebDriver(new URL(hub), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new Error("Could not connect to WebDriver hub", e);
		}
	}

	private static WebDriver newLocalDriver(String browserType) {
		try {
			log("Init \"%s\" web driver<br>", browserType);
			switch (browserType) {
			case IE:
				driver = newIEDriver();
				break;
			case CHROME:
				driver = newCromeDriver();
				break;
			case OPERA:
				driver = newOperaDriver();
				break;
			case FIREFOX:
			default:
				driver = newFireFoxDriver();
			}
			maximixeBrowserWindow(browserType);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}

	private static void maximixeBrowserWindow(String browserType) {
		if (!browserType.equals(OPERA)) {
			driver.manage().window().maximize();
		} 
	}

	private static WebDriver newFireFoxDriver() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(FirefoxDriver.PROFILE, Settings.getFFProfilePath());
		return new FirefoxDriver(capabilities);
	}

	private static WebDriver newOperaDriver() {
		System.setProperty("opera.binary", Settings.getOperaBinaryPath());
		return new OperaDriver();
	}

	private static WebDriver newCromeDriver() {
		System.setProperty("webdriver.chrome.driver", Settings.getChromeDriverPath());
		return new ChromeDriver();
	}

	private static WebDriver newIEDriver() {
		System.setProperty("webdriver.ie.driver", Settings.getIEDriverPath());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(capabilities);
	}
}