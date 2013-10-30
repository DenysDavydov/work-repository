package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogFilter;

public class Test_WashersPriceFilter extends Test_Base {
	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void test_PriceFilter(String category, String minFilterName, int minFilterValue, 
			String maxFilterName, int maxFilterValue, int filterLimit) {
		
		openHomePage().navigateTo(category);
		CatalogFilter washersFilter = PageFactory.getPage(driver, CatalogFilter.class);
		
		washersFilter.toggleFilter(minFilterName, String.valueOf(minFilterValue));
		if (minFilterValue != filterLimit) {
			washersFilter.toggleFilter(maxFilterName, String.valueOf(maxFilterValue));
		}		
		
		assertTrue(washersFilter.allCatalogItemsMatches(minFilterValue, maxFilterValue));
	}
}
