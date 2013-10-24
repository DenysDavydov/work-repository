package com.epam.davydov.pn.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
	@FindBy(css="[href$='2163/']")
	private WebElement fridgesCategory;

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub

	}

	public FridgesCategory goToFridges() {
		fridgesCategory.click();
		return com.epam.davydov.pn.util.PageFactory.getPage(driver, FridgesCategory.class);
	}

}
