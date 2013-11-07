package com.epam.davydov.pn.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static final String CONFIG_FILE = "framework.properties";
	private static Properties properties;

	static {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		try {
			properties = new Properties();
			properties.load(new FileReader(CONFIG_FILE));
		} catch (FileNotFoundException e) {
			System.out.println(CONFIG_FILE + " file not found");
		} catch (IOException e) {
			System.out.println("IO error has occurred");
		}
	}

	public static String getFFProfilePath() {
		return properties.getProperty("ff.profile.path");
	}

	public static String getChromeDriverPath() {
		return properties.getProperty("chromedriver.path");
	}

	public static String getIEDriverPath() {
		return properties.getProperty("iedriver.path");
	}
	
	public static String getOperaBinaryPath() {
		return properties.getProperty("opera.binary.path");
	}

	public static int getAjaxFactoryWaitTimeout() {
		return Integer.parseInt(properties.getProperty("ajax.factory.wait.timeout"));
	}
	
	public static String getHomePageURL(){
		return properties.getProperty("home.page.url");
	}

	public static String getInputDataDir() {
		return properties.getProperty("input.data.dir");
	}

	public static String getBrowserType() {
		return properties.getProperty("browser.type");
	}
}