package com.epam.davydov.pn.pages;

import static com.epam.davydov.pn.helpers.core.BaseHelper.*;
import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PricePage extends HomePage {
	private By descriptionLink = By.cssSelector(".description-link");

	@FindBy(css = ".n")
	private List<WebElement> searchResultRows;

	public boolean allDescriptionLinksLeadsToProductPage(String productPageURL) {
		boolean result = true;

		if (searchResultRows.isEmpty())
			return false;

		for (WebElement row : searchResultRows) {
			try {
				String descriptionURL = row.findElement(descriptionLink).getAttribute("href");
				if (!descriptionURL.equals(productPageURL)) {
					String rowText = row.getText();
					String priceItemName = rowText.substring(0, rowText.indexOf("описание")).trim();
					String message = format("\"%s\" doesn't lead to \"%s\"", priceItemName, productPageURL);
					log(RED_FONT, message);
					result = false;
				}
			} catch (NoSuchElementException e) {
				String rowText = row.getText();
				String priceItemName = rowText.substring(0, rowText.indexOf("описание")).trim();
				String message = format("\"%s\" doesn't contains description link", priceItemName);
				log(RED_FONT, message);
				result = false;
			}
		}
		return result;
	}
}
