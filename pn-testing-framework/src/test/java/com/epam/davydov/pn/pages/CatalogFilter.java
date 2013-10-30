package com.epam.davydov.pn.pages;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CatalogFilter extends CatalogPage {
	private static final String FILTER = "//div[@class='group' and contains(.,'%s')]//a[contains(.,'%s')]";
	private static final String FILTER_LINKS = "//div[@class='group' and contains(.,'%s')]//a";

	@FindBy(css = ".show_common_producer")
	private WebElement showAllButton;

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

	public boolean allItemsMatchesPriceFilter(int minPrice, int maxPrice) {
		for (WebElement item : webItems) {
			String price = item.findElement(webItemPrice).getText();
			int itemPrice = Integer.parseInt(price.replaceAll("[\\D\\s]", ""));

			if (itemPrice < minPrice || itemPrice > maxPrice) {
				String itemName = item.findElement(webItemName).getText();
				Reporter.log(String.format("Product \"%s\" price (\"%s\") not in range \"%s\" - \"%s\"", 
						itemName, itemPrice, minPrice, maxPrice));
				return false;
			}
		}
		return true;
	}

	public Set<String> getFilterNames(String filterCategory) {
		Reporter.log("Get set of filter names from \"" + filterCategory + "\" category");
		Set<String> filterNames = new TreeSet<>();
		if (showAllButton.isDisplayed())
			showAllButton.click();
		String selector = String.format(FILTER_LINKS, filterCategory);
		By filterLinks = By.xpath(selector);
		List<WebElement> filterLinksList = driver.findElements(filterLinks);
		for (WebElement filterLink : filterLinksList) {
			if (filterLink.getAttribute("href").startsWith("javascript"))
				continue;
			filterNames.add(filterLink.getText().toLowerCase());
		}
		return filterNames;
	}
}
