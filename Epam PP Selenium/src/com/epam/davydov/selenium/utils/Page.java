package com.epam.davydov.selenium.utils;

import org.openqa.selenium.WebDriver;

public abstract class Page {
	public WebDriver driver;
	
	public boolean isOnThisPage(){
		return true;
	}
	
	public abstract void tryToOpen();
}