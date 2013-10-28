package com.epam.davydov.pn.helpers.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static final String CONFIG_PATH = "wd.properties";
	private static Properties properties;

	static {
		try {
			properties = new Properties();
			properties.load(new FileReader(CONFIG_PATH));
		} catch (FileNotFoundException e) {
			System.out.println("Configuration file not found");
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

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getUserpass() {
		return properties.getProperty("userpass");
	}

	public static String getDestinationEmail() {
		return properties.getProperty("dest.email");
	}

	public static String getMailSubject() {
		return properties.getProperty("mail.subject");
	}

	public static String getMailBody() {
		return properties.getProperty("mail.body");
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
}