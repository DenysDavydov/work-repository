package com.epam.davydov.pn.pages;

import static com.epam.davydov.pn.helpers.core.BaseHelper.*;
import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.helpers.core.CustomSoftAssert;

public class PricePage extends HomePage {
	private boolean isFailed;
	private By descriptionLink = By.cssSelector(".description-link");

	@FindBy(css = ".n")
	private List<WebElement> searchResultRows;

	public void verifyDescriptionLinks(String productPageURL, CustomSoftAssert sa) {
		String message = format("Verify search result description links matches \"%s\"", productPageURL);
		log(BLUE_FONT, message);
		if (searchResultRows.isEmpty()) {
			sa.fail();
			return;
		}
		for (WebElement row : searchResultRows) {
			try {
				String descriptionURL = row.findElement(descriptionLink).getAttribute("href");
				if (!descriptionURL.equals(productPageURL)) {
					String errorMessage = format("\"%s\" doesn't lead to \"%s\"", getPriceItemName(row), productPageURL);
					generateResult(errorMessage);
				}
			} catch (NoSuchElementException e) {
				String errorMessage = format("\"%s\" doesn't contains description link", getPriceItemName(row));
				generateResult(errorMessage);
			}
		}
		sa.assertFalse(isFailed, "Links assert");
	}

	private String getPriceItemName(WebElement row) {
		String rowText = row.getText();
		return rowText.substring(0, rowText.indexOf("описание")).trim();
	}

	private void generateResult(String message) {
		log(RED_FONT, message);
		isFailed = true;
	}
}
