package com.epam.davydov.pn.config;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.BrowserType.*;

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

	public static WebDriver getDriver(String hub, Capabilities capabilities) {
		count++;
		if (driver == null) {
			return newWebDriver(hub, capabilities);
		}
		String newKey = capabilities.toString() + ":" + hub;
		if (!newKey.equals(key)) {
			dismissDriver();
			key = newKey;
			return newWebDriver(hub, capabilities);
		}
		try {
			driver.getCurrentUrl();
		} catch (Throwable t) {
			t.printStackTrace();
			return newWebDriver(hub, capabilities);
		}
		if (count >= restartFrequency) {
			dismissDriver();
			return newWebDriver(hub, capabilities);
		}
		return driver;
	}

	public static WebDriver getDriver(Capabilities capabilities) {
		return getDriver(defaultHub, capabilities);
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

	private static WebDriver newWebDriver(String hub, Capabilities capabilities) {
		driver = (hub == null) ? newLocalDriver(capabilities) : newRemoteDriver(hub, capabilities);
		key = capabilities.toString() + ":" + hub;
		count = 0;
		return driver;
	}

	private static WebDriver newRemoteDriver(String hub, Capabilities capabilities) {
		try {
			return new RemoteWebDriver(new URL(hub), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new Error("Could not connect to WebDriver hub", e);
		}
	}

	private static WebDriver newLocalDriver(Capabilities capabilities) {
		try {
			switch (capabilities.getBrowserName()) {
			case IE:
				driver = newIEDriver(capabilities);
				break;
			case CHROME:
				driver = newCromeDriver(capabilities);
				break;
			case OPERA:
				driver = newOperaDriver(capabilities);
				break;
			case FIREFOX:
			default:
				driver = newFireFoxDriver(capabilities);
			}
			driver.manage().window().maximize();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}

	private static WebDriver newFireFoxDriver(Capabilities capabilities) {
		((DesiredCapabilities) capabilities).setCapability(FirefoxDriver.PROFILE,
				Settings.getFFProfilePath());
		return new FirefoxDriver(capabilities);
	}

	private static WebDriver newOperaDriver(Capabilities capabilities) {
		System.setProperty("opera.binary", Settings.getOperaBinaryPath());
		return new OperaDriver(capabilities);
	}

	private static WebDriver newCromeDriver(Capabilities capabilities) {
		System.setProperty("webdriver.chrome.driver", Settings.getChromeDriverPath());
		return new ChromeDriver();
	}

	private static WebDriver newIEDriver(Capabilities capabilities) {
		System.setProperty("webdriver.ie.driver", Settings.getIEDriverPath());
		((DesiredCapabilities) capabilities).setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(capabilities);
	}	
}