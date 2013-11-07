package com.epam.davydov.pn.config;

public class Configuration {
	public static String getBrowserType() {
		return System.getProperty("target.browser");
	}
}
