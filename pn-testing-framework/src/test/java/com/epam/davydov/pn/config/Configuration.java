package com.epam.davydov.pn.config;

public class Configuration {
	public static String getBrowserType() {
		return System.getProperty("target.browser");
	}
	
	public static String getBaseURL() {
		return System.getProperty("home.page.url");
	}
}
