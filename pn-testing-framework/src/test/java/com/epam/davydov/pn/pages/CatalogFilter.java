package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class CatalogFilter extends CatalogPage {
	private static final String FILTER = "//div[@class='group' and contains(.,'%s')]//a[contains(.,'%s')]";

	public void toggleFilter(String filterCategory, String filterName) {
		Reporter.log(String.format("Toggle \"%s\" filter from \"%s\" category", filterName, filterCategory));
		
		String selector = String.format(FILTER, filterCategory, filterName);
		WebElement filterButton = getElement(By.xpath(selector));
		
		String filterButtonCSSClassName = filterButton.getAttribute("class");
		
		if (filterButtonCSSClassName.equals("selected active")) {
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
	
	public boolean allItemsMatchesPriceFilter(int minPrice, int maxPrice){
		for (WebElement item : webItems) {
			String price= item.findElement(webItemPrice).getText();
			int itemPrice = Integer.parseInt(price.replaceAll("[\\D\\s]", ""));
			
			if (itemPrice < minPrice || itemPrice > maxPrice) {
				String itemName = item.findElement(webItemName).getText();
				Reporter.log(String.format(
						"Product \"%s\" price (\"%s\") not in range \"%s\" - \"%s\"", 
						itemName, itemPrice, minPrice, maxPrice));
				return false;
			}
		}
		return true;
	}
}
