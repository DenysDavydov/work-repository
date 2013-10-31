package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.epam.davydov.pn.config.PageFactory;

public class HomePage extends Page {
	private static final String CATEGORY_BUTTON = "//div[@class='home-page-cloud']//a[contains(.,'%s')]";

	@FindBy(id = "edit-name-1")
	private WebElement searchField;
	@FindBy(id = "edit-submit-1")
	private WebElement searchSubmit;

	public CatalogPage navigateTo(String catecory) {
		Reporter.log("Navigate to \"" + catecory + "\" category");
		By categoryButton = By.xpath(String.format(CATEGORY_BUTTON, catecory));
		getElement(categoryButton).click();

		return PageFactory.getPage(driver, CatalogPage.class);
	}

	public void search(String searchQuery) {
		searchField.sendKeys(searchQuery);
		searchSubmit.click();
	}
}