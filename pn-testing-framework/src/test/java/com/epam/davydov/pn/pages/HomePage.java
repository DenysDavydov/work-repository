package com.epam.davydov.pn.pages;

import static java.lang.String.format;

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

	public CatalogPage navigateTo(String category) {
		Reporter.log(format("Navigate to \"%s\" category<br>", category));
		By categoryButton = By.xpath(format(CATEGORY_BUTTON, category));
		getElement(categoryButton).click();

		return PageFactory.getPage(CatalogPage.class);
	}

	public void search(String searchQuery) {
		Reporter.log(format("Search for \"%s\"<br>", searchQuery));
		searchField.sendKeys(searchQuery);
		searchSubmit.click();
	}
}