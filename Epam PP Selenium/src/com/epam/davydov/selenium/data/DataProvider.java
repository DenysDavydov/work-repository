package com.epam.davydov.selenium.data;

import java.util.Random;

import com.epam.davydov.selenium.utils.Settings;

public class DataProvider {
	private static Random random = new Random();
	
	public static User getValidUser(){
		return new User().withEmail(Settings.getUsername()).withPasswd(Settings.getUserpass());
	}
	
	public static Mail getDefaultMail(){
		return new Mail(Settings.getDestinationEmail(), Settings.getMailSubject(), Settings.getMailBody());
	}
	
	public static Mail getUniqueMail(){
		return new Mail(
				Settings.getDestinationEmail(), 
				Settings.getMailSubject() + random.nextInt(10000), 
				Settings.getMailBody() + random.nextInt(10000));
	}
}
