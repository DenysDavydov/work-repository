package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.epam.davydov.pn.helpers.factory.PageFactory;

public class HomePage extends Page {
	private static final String NAVIGATE_TO_LOCATOR_PATTERN = "//a[contains(.,'%s')]";

	public CatalogPage navigateTo(String catecory) {
		By categoryButton = By.xpath(String.format(NAVIGATE_TO_LOCATOR_PATTERN, catecory));
		
        Reporter.log("Opening \"" + catecory + "\" category");
        getElement(categoryButton).click();
        
		return PageFactory.getPage(driver, CatalogPage.class);
	}
}