package com.pn.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pn.utils.PageFactory;

public class HomePage extends Page {
	@FindBy(css="[href$='2163/']")
	private WebElement fridgesCategory;

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub

	}

	public FridgesCategory goToFridges() {
		fridgesCategory.click();
		return PageFactory.getPage(driver, FridgesCategory.class);
	}

}
