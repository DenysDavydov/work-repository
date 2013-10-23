package com.epam.davydov.selenium.gmail_pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.selenium.data.Mail;
import com.epam.davydov.selenium.utils.PageFactory;

public class MailInboxPage extends InternalPage {
	private By destEmail = By.xpath(".//span[@email]");
	private By subject = By.xpath(".//td[@role='link']//span[1]");
	private By bodyPart = By.xpath(".//td[@role='link']//span[2]");

	@FindBy(css = ".z0>div")
	private WebElement createNewMailButton;
	@FindBy(name = "to")
	private WebElement destinationEmailInputField;
	@FindBy(name = "subjectbox")
	private WebElement subjectInputField;
	@FindBy(css = "[class$='editable']>iframe")
	private WebElement bodyInputField;
	@FindBy(css = "[data-tooltip*='Enter']")
	private WebElement sendMailButton;
	@FindBy(css = "[href^='h'][href$=\"#inbox\"]")
	private WebElement inboxButton;
	@FindBy(css = "#\\:38 tr")
	private List<WebElement> mailRows;

	public MailInboxPage createNewMail(Mail mail) {
		createNewMailButton.click();
		destinationEmailInputField.sendKeys(mail.destEmail);
		subjectInputField.sendKeys(mail.subject);
		bodyInputField.sendKeys(mail.body);
		sendMailButton.click();
		inboxButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return PageFactory.getPage(driver, MailInboxPage.class);
	}

	public List<Mail> getMailList() {
		List<Mail> mailList = new ArrayList<>();
		for (WebElement row : mailRows) {
			mailList.add(parseRow(row));
		}
		return mailList;
	}

	private Mail parseRow(WebElement row) {
		String destEmail, subject, bodyPart;
		destEmail = row.findElement(this.destEmail).getAttribute("email");
		subject = row.findElement(this.subject).getText();
		bodyPart = row.findElement(this.bodyPart).getText().replaceFirst(" - ", "");
		return new Mail(destEmail, subject, bodyPart);
	}

	@Override
	public boolean isOnThisPage() {
		return createNewMailButton.isDisplayed();
	}
}