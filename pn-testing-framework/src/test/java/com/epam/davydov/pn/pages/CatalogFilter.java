package com.epam.davydov.pn.pages;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.epam.davydov.pn.helpers.core.BaseHelper;

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

	public void verifyProductsMatchesFilter(String property) {
		boolean isFailed = false;
		for (WebElement catalogItem : catalogItems) {
			if (!catalogItem.getText().contains(property)) {
				String productName = catalogItem.findElement(catalogItemName).getText();
				Reporter.log(String.format("Product \"%s\" doesn't contains \"%s\" property", productName, property));
				isFailed = true;
			}
		}
		BaseHelper.generateResult(isFailed);
	}

	public void verifyProductsInPriceRange(int minPrice, int maxPrice) {
		boolean isFailed = false;
		for (WebElement catalogItem : catalogItems) {
			String productPriceText = catalogItem.findElement(catalogItemPrice).getText();
			int productPrice = Integer.parseInt(productPriceText.replaceAll("[\\D\\s]", ""));

			if (productPrice < minPrice || productPrice > maxPrice) {
				String productName = catalogItem.findElement(catalogItemName).getText();
				Reporter.log(String.format("Product \"%s\" price (\"%s\") not in range \"%s\" - \"%s\"", 
						productName, productPrice, minPrice, maxPrice));
				isFailed = true;
			}
		}
		BaseHelper.generateResult(isFailed);
	}

	public Set<String> getFilterNames(String filterCategory) {
		Reporter.log("Get set of filter names from \"" + filterCategory + "\" category");
		Set<String> filterNames = new TreeSet<>();
		
		showAllButton.click();
		
		By filterLinks = By.xpath(String.format(FILTER_LINKS, filterCategory));
		List<WebElement> filterLinksList = getElements(filterLinks);
		
		for (WebElement filterLink : filterLinksList) {
			if (filterLink.getAttribute("href").startsWith("javascript"))
				continue;
			filterNames.add(filterLink.getText().toLowerCase());
		}
		return filterNames;
	}
}
