package com.epam.davydov.selenium.gmail_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.selenium.utils.Page;
import com.epam.davydov.selenium.utils.PageFactory;

public class MailHomePage extends Page {
	private static final String CURRENT_URL="http://www.google.com/intl/ru/mail/help/about.html";
	
	@FindBy(id = "gmail-sign-in")
	private WebElement goToSignInPageButton;

	public SignInPage goToSignInPage() {
		goToSignInPageButton.click();
		return PageFactory.getPage(driver, SignInPage.class);
	}
	
	@Override
	public boolean isOnThisPage() {
		return goToSignInPageButton.isDisplayed();
	}

	@Override
	public void tryToOpen() {
		driver.get(CURRENT_URL);		
	}
}