package com.epam.davydov.pn.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	public WebDriver driver;

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public WebElement getElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public List<WebElement> getElements(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public WebElement getElementChild(WebElement parent, By childLocator) {
		return parent.findElement(childLocator);
	}

	public List<WebElement> getElementChildren(WebElement parent, By childrenLocator) {
		return parent.findElements(childrenLocator);
	}
}