package com.epam.davydov.pn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.helpers.dataproviders.Product;

public class ProductPage extends Page {
	private By webProperty = By.cssSelector(".pr");
	private By webPropertyValue = By.cssSelector(".val");

	@FindBy(css = ".row")
	private List<WebElement> propertyRows;
	@FindBy(xpath = "//div[@class='other-prices']/b[1]")
	private WebElement minPrice;
	@FindBy(xpath = "//*[@id='page-breadcrumbs']")
	private WebElement productName;

	public Product getProduct() {
		Product product = new Product();
		for (WebElement row : propertyRows) {
			String property = row.findElement(webProperty).getText();
			String propertyValue = row.findElement(webPropertyValue).getText();

			product.setProperty(property, propertyValue);
		}
		return product;
	}

	public List<String> getProductDescription() {
		List<String> shortDescription = new ArrayList<>();
		shortDescription.add(productName.getText().toLowerCase());
		shortDescription.add(minPrice.getText().toLowerCase());
		for (WebElement row : propertyRows) {
			shortDescription.add(row.findElement(webProperty).getText().toLowerCase() + " " + 
		row.findElement(webPropertyValue).getText().toLowerCase());
		}
		return shortDescription;
	}
}