package com.epam.davydov.pn.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PricePage extends HomePage {
	@FindBy(css = ".n")
	private List<WebElement> searchResultRows;

	private By descriptionLink = By.cssSelector(".description-link");

	public boolean allDescriptionLinksLeadsToProductPage(String productPageURL) {
		boolean result = true;
		for (WebElement row : searchResultRows) {
			try {
				String descriptionURL = row.findElement(descriptionLink).getAttribute("href");
				if (!descriptionURL.equals(productPageURL)) {
					String rowText = row.getText();
					String priceItemName = rowText.substring(0, rowText.indexOf("описание")).trim();
					Reporter.log(priceItemName + " doesn't lead to " + productPageURL);
					result = false;
				}
			} catch (NoSuchElementException e) {
				String rowText = row.getText();
				String priceItemName = rowText.substring(0, rowText.indexOf("описание")).trim();
				Reporter.log(priceItemName + " doesn't contains description link");
				result = false;
			}
		}
		return result;
	}
}
