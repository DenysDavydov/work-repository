package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;

import com.epam.davydov.pn.helpers.factory.PageFactory;
import org.testng.Reporter;

public class HomePage extends Page {
	private static final String NAVIGATE_TO_LOCATOR_PATTERN = "//a[contains(.,'%s')]";

	public CatalogPage navigateTo(String catecory) {
		By categoryButton = By.xpath(String.format(NAVIGATE_TO_LOCATOR_PATTERN, catecory));
		
        Reporter.log("Opening \"" + catecory + "\" category<br>");
        getElement(categoryButton).click();
        
		return PageFactory.getPage(driver, CatalogPage.class);
	}
}