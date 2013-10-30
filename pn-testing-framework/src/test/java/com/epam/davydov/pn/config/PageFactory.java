package com.epam.davydov.pn.config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.openqa.selenium.support.PageFactory.initElements;

public class PageFactory {
	private static final int TIMEOUT = Settings.getAjaxFactoryWaitTimeout();

	public static <P extends com.epam.davydov.pn.pages.Page> P getPage(WebDriver driver, Class<P> pageClass) {
		P page = instantiatePage(driver, pageClass);
		initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), page);
		page.driver = driver;		
		return page;
	}

	private static <P> P instantiatePage(WebDriver driver, Class<P> pageClassToProxy) {
		try {
			try {
				Constructor<P> constructor = pageClassToProxy.getConstructor(WebDriver.class);
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClassToProxy.newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
    }
}