package com.epam.davydov.pn.pages;

import static com.epam.davydov.pn.helpers.core.BaseHelper.*;
import static java.lang.String.format;
import static org.testng.Assert.assertFalse;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogFilter extends CatalogPage {
	private static final String FILTER = "//div[@class='group' and contains(.,'%s')]//a[contains(.,'%s')]";
	private static final String FILTER_LINKS = "//div[@class='group' and contains(.,'%s')]//a";

	@FindBy(css = ".show_common_producer")
	private WebElement showAllButton;

	public void toggleFilter(String filterCategory, String filterName) {
		log("Toggle \"%s\" filter from \"%s\" category<br>", filterName, filterCategory);

		By _filterButton = By.xpath(format(FILTER, filterCategory, filterName));
		WebElement filterButton = getElement(_filterButton);

		String filterButtonCSSClassName = filterButton.getAttribute("class");

		if (!filterButtonCSSClassName.equals("selected active")) {
			filterButton.click();
		}
	}

	public void verifyProductsMatchesFilter(String property) {
		String message = format("Verify all products matches \"%s\" filter", property);
		log(BLUE_FONT, message);
		boolean isFailed = false;
		for (WebElement catalogItem : catalogItems) {
			if (!catalogItem.getText().contains(property)) {
				String productName = catalogItem.findElement(catalogItemName).getText();
				String errorMessage = format("Product \"%s\" doesn't contains \"%s\" property", productName,
						property);
				log(RED_FONT, errorMessage);
				isFailed = true;
			}
		}
		assertFalse(isFailed);
	}

	public void verifyProductsMatchesPriceRange(int minPrice, int maxPrice) {
		String message = format("Verify products are in %s - %s price range", minPrice, maxPrice);
		log(BLUE_FONT, message);
		boolean isFailed = false;
		for (WebElement catalogItem : catalogItems) {
			String productPriceText = catalogItem.findElement(catalogItemPrice).getText();
			int productPrice = Integer.parseInt(productPriceText.replaceAll("[\\D\\s]", ""));

			if (productPrice < minPrice || productPrice > maxPrice) {
				String productName = catalogItem.findElement(catalogItemName).getText();
				String errorMessage = format("Product \"%s\" price (\"%s\") not in range \"%s\" - \"%s\"<br>",
						productName, productPrice, minPrice, maxPrice);
				log(RED_FONT, errorMessage);
				isFailed = true;
			}
		}
		assertFalse(isFailed);
	}

	public Set<String> getFilterNames(String filterCategory) {
		log("Get set of filter names from \"%s\" category<br>", filterCategory);
		Set<String> filterNames = new TreeSet<>();

		showAllButton.click();

		By filterLinks = By.xpath(format(FILTER_LINKS, filterCategory));
		List<WebElement> filterLinksList = getElements(filterLinks);

		for (WebElement filterLink : filterLinksList) {
			if (!filterLink.getAttribute("href").startsWith("javascript"))
				filterNames.add(filterLink.getText().toLowerCase());
		}
		return filterNames;
	}
}
