package com.epam.davydov.pn.helpers.factory;

import com.epam.davydov.pn.helpers.core.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

import com.epam.davydov.pn.components.Component;

public class ComponentFactory {
	private static final int TIMEOUT = Settings.getAjaxFactoryWaitTimeout();

	public static <C extends Component> Component getComponent(WebDriver driver, Class<C> ComponentClass) {
		try {
			C component = ComponentClass.newInstance();
			PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), component);
			return component;
		} catch (InstantiationException | IllegalAccessException e) {
			Reporter.log("Failed to instantiate component" + ComponentClass.getName());
			return null;
		}
	}
}