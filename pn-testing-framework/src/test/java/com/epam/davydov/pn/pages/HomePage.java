package com.epam.davydov.pn.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.util.PageFactory;

public class HomePage extends Page {
	private static final String CURRENT_URL="http://pn.com.ua/";
	
	@FindBy(css="[href$='2163/']")
	private WebElement fridgesCategory;

	@Override
	public void tryToOpen() {
		driver.get(CURRENT_URL);
	}
	
	@Override
	public boolean isOnThisPage(){
		return driver.getCurrentUrl().equals(CURRENT_URL);
	}

	public FridgesCategory navigateToFridges() {
		fridgesCategory.click();
		return PageFactory.getPage(driver, FridgesCategory.class);
	}
}