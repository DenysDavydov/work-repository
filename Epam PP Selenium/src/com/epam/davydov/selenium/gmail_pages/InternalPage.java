package com.epam.davydov.selenium.gmail_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.selenium.data.DataProvider;
import com.epam.davydov.selenium.utils.Page;
import com.epam.davydov.selenium.utils.PageFactory;

public class InternalPage extends Page {
	@FindBy(css = "[title='testuser5867@gmail.com']")
	private WebElement accountManagerMenu;
	@FindBy(css = "[href*=logout][target=_top]")
	private WebElement logoutButton;

	public void doLogout() {
		accountManagerMenu.click();
		logoutButton.click();
	}

	public boolean isOnThisPage() {
		return accountManagerMenu.isDisplayed();
	}

	@Override
	public void tryToOpen() {
		PageFactory.getPage(driver, SignInPage.class).signInAs(DataProvider.getValidUser());
	}
}