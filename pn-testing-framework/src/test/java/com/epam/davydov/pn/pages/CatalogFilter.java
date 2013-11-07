package com.epam.davydov.pn.pages;

import static com.epam.davydov.pn.helpers.core.BaseHelper.*;
import static java.lang.String.format;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.helpers.core.BaseHelper;

public class CatalogFilter extends CatalogPage {
	private static final String FILTER = "//div[@class='group' and contains(.,'%s')]//a[contains(.,'%s')]";
	private static final String FILTER_LINKS = "//div[@class='group' and contains(.,'%s')]//a";

	@FindBy(css = ".show_common_producer")
	private WebElement showAllButton;

	public void toggleFilter(String filterCategory, String filterName) {
		log("Toggle \"%s\" filter from \"%s\" category<br>", filterName, filterCategory);

		String selector = format(FILTER, filterCategory, filterName);
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
				String message = format("Product \"%s\" doesn't contains \"%s\" property", productName, property);
				log(RED_FONT, message);
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
				String message = format("Product \"%s\" price (\"%s\") not in range \"%s\" - \"%s\"<br>",
						productName, productPrice, minPrice, maxPrice);
				log(RED_FONT, message);
				isFailed = true;
			}
		}
		BaseHelper.generateResult(isFailed);
	}

	public Set<String> getFilterNames(String filterCategory) {
		log("Get set of filter names from \"%s\" category<br>", filterCategory);
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
