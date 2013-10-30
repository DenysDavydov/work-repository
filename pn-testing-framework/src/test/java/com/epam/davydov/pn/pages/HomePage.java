package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.epam.davydov.pn.config.PageFactory;

public class HomePage extends Page {
	private static final String CATEGORY_BUTTON = "//div[@class='home-page-cloud']//a[contains(.,'%s')]";

	public CatalogPage navigateTo(String catecory) {
		Reporter.log("Navigate to \"" + catecory + "\" category");
		By categoryButton = By.xpath(String.format(CATEGORY_BUTTON, catecory));		
        getElement(categoryButton).click();
        
		return PageFactory.getPage(driver, CatalogPage.class);
	}
}