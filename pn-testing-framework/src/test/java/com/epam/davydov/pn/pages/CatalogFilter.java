package com.epam.davydov.pn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CatalogFilter extends CatalogPage {
	private static final String FILTER = 
			"//div[@class='panel corner criteria']//a[contains(.,'%s')]";
	private static final String ACTIVE_FILTER = 
			"//div[@class='panel corner criteria']//a[@class='selected active' and contains(.,'%s')]";

	public void toogleFilter(String filterName) {
		String selector = String.format(FILTER, filterName);
		By filterButton = By.xpath(selector);
		getElement(filterButton).click();
	}

	public boolean isContentMatches(String property) {
		for(WebElement item : webItems){
			if (!item.getText().contains(property)) {
				return false;
			}
		}
		return true;
	}
}
