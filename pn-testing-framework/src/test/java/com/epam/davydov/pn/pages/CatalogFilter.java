package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class CatalogFilter extends CatalogPage {
	private static final String FILTER = "//div[@class='panel corner criteria']//a[contains(.,'%s')]";

	public void toggleFilter(String filterName) {
		Reporter.log("Toggle \"" + filterName + "\" filter");
		String selector = String.format(FILTER, filterName);
		WebElement filterButton = getElement(By.xpath(selector));
		String filterBgColor = filterButton.getCssValue("background-color");
		if (!filterBgColor.equals("transparent")) {
			return;
		}
		filterButton.click();
	}

	public boolean allItemsMatches(String property) {
		for (WebElement item : webItems) {
			if (!item.getText().contains(property)) {
				String itemName = item.findElement(webItemName).getText();
				Reporter.log(String.format("Product \"%s\" doesn't contains \"%s\" property", itemName, property));
				return false;
			}
		}
		return true;
	}
}
