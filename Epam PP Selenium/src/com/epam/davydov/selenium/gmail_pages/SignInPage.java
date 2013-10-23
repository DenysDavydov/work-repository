package com.epam.davydov.selenium.gmail_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.selenium.data.User;
import com.epam.davydov.selenium.utils.Page;
import com.epam.davydov.selenium.utils.PageFactory;

public class SignInPage extends Page {
	@FindBy(id = "Email")
	private WebElement emailInputField;
	@FindBy(id = "Passwd")
	private WebElement passwdInputField;
	@FindBy(id = "signIn")
	private WebElement signInButton;

	public MailInboxPage signInAs(User user) {
		emailInputField.sendKeys(user.email);
		passwdInputField.sendKeys(user.passwd);
		signInButton.click();
		return PageFactory.getPage(driver, MailInboxPage.class);
	}

	@Override
	public boolean isOnThisPage() {
		return signInButton.isDisplayed();
	}

	@Override
	public void tryToOpen() {
		PageFactory.getPage(driver, MailHomePage.class).goToSignInPage();
	}
}
