package com.epam.davydov.pn.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.entities.Item;
import com.epam.davydov.pn.entities.Property;
import com.epam.davydov.pn.util.Helper;

public class ProductPage extends Page {
	private By webProperty = By.cssSelector(".pr");
	private By webPropertyValue = By.cssSelector(".val");
		
	@FindBy(css = ".row")
	private List<WebElement> propertyRows;

	public Item getProduct() {
		Item item = new Item();
		for (WebElement row : propertyRows) {
			String propertyName = row.findElement(webProperty).getText();
			String propertyValue = row.findElement(webPropertyValue).getText();
			Property property = Helper.getPropertyByName(propertyName);
			item.setProperty(property, propertyValue);
		}
		return item;
	}

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub

	}

}
