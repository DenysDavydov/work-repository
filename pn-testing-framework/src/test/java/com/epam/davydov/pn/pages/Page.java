package com.epam.davydov.pn.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
	public WebDriver driver;
	
	public boolean isOnThisPage(){
		return true;
	}
	
	public abstract void tryToOpen();
}