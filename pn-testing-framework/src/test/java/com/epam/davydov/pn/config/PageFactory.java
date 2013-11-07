package com.epam.davydov.pn.config;

import static org.openqa.selenium.support.PageFactory.initElements;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.epam.davydov.pn.pages.Page;

public class PageFactory {
	private static final int TIMEOUT = Settings.getAjaxFactoryWaitTimeout();
	private static WebDriver driver = WebDriverFactory.getDriver();

	public static <P extends Page> P getPage(Class<P> pageClass) {
		P page = instantiatePage(pageClass);
		initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), page);
		page.driver = driver;
		return page;
	}

	private static <P> P instantiatePage(Class<P> pageClassToProxy) {
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