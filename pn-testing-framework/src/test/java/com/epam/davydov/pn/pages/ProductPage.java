package com.epam.davydov.pn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.epam.davydov.pn.helpers.dataproviders.Product;

public class ProductPage extends Page {
	private By webProperty = By.cssSelector(".pr");
	private By webPropertyValue = By.cssSelector(".val");

	@FindBy(css = ".row")
	private List<WebElement> propertyRows;
	@FindBy(css = ".summary-price")
	private WebElement price;
	@FindBy(xpath = "//div[@id='page-breadcrumbs']")
	private WebElement breadCrumbs;

	public Product getProduct() {
		Product product = new Product();
		for (WebElement row : propertyRows) {
			String property = row.findElement(webProperty).getText();
			String propertyValue = row.findElement(webPropertyValue).getText();

			product.setProperty(property, propertyValue);
		}
		return product;
	}

	public String getCurrentProductName() {
		String breadCrumbsText = breadCrumbs.getText();
		return breadCrumbsText.substring(breadCrumbsText.lastIndexOf("»") + 3).replaceAll(" / ", " ");
	}

	public List<String> getProductDescription() {
		Reporter.log("Get product's description from the product page<br>");
		List<String> shortDescription = new ArrayList<>();

		shortDescription.add(getCurrentProductName().toLowerCase());
		shortDescription.add(price.getText().toLowerCase());

		for (WebElement row : propertyRows) {
			String productProperty = row.findElement(webProperty).getText().toLowerCase();
			String productPropertyValue = row.findElement(webPropertyValue).getText().toLowerCase();
			shortDescription.add(productProperty + " " + productPropertyValue);
		}
		return shortDescription;
	}
}