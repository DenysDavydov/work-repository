package com.pn.utils;

import static org.openqa.selenium.support.PageFactory.initElements;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.pn.pages.Page;

public class PageFactory {
	private static final int TIMEOUT = Settings.getAjaxFactoryWaitTimeout();

	public static <P extends Page> P getPage(WebDriver driver, Class<P> pageClass) {
		P page = instantiatePage(driver, pageClass);
		initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), page);
		page.driver = driver;
		if (!page.isOnThisPage()) {
			page.tryToOpen();
			if (!page.isOnThisPage()) {
				throw new Error("Can't open page " + pageClass);
			}
		}
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
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}