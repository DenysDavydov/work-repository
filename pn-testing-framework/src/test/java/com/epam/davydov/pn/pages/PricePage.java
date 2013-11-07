package com.epam.davydov.pn.pages;

import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PricePage extends HomePage {
	private By descriptionLink = By.cssSelector(".description-link");
	
	@FindBy(css = ".n")
	private List<WebElement> searchResultRows;

	public boolean allDescriptionLinksLeadsToProductPage(String productPageURL) {
		boolean result = true;
		for (WebElement row : searchResultRows) {
			if (searchResultRows.isEmpty()){
				return false;
			}
			try {
				String descriptionURL = row.findElement(descriptionLink).getAttribute("href");
				if (!descriptionURL.equals(productPageURL)) {
					String rowText = row.getText();
					String priceItemName = rowText.substring(0, rowText.indexOf("описание")).trim();
					Reporter.log(format("\"%s\" doesn't lead to \"%s\"<br>", priceItemName, productPageURL));
					result = false;
				}
			} catch (NoSuchElementException e) {
				String rowText = row.getText();
				String priceItemName = rowText.substring(0, rowText.indexOf("описание")).trim();
				Reporter.log(format("\"%s\" doesn't contains description link<br>", priceItemName));
				result = false;
			}
		}
		return result;
	}
}
