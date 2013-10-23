package com.epam.davydov.selenium.tests.gmail_only;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.davydov.selenium.data.DataProvider;
import com.epam.davydov.selenium.data.Mail;

public class Test_SelfMailSending extends GmailTestsBase {

	@Test
	public void test_SelfMailSending() {
		List<Mail> mailList = 
				openMailHomePage()
				.goToSignInPage()
				.signInAs(validUser)
				.createNewMail(uniqueMail)
				.getMailList();

		assertTrue(mailList.contains(uniqueMail));
	}

	@Override
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		uniqueMail = DataProvider.getUniqueMail();
	}
}