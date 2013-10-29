package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.dp.TestDataProvider;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.pages.CatalogFilter;

public class Test_WashersPriceFilter extends TestsCommon {
	@Test(dataProvider = "parametersProvider", dataProviderClass = TestDataProvider.class)
	public void test_PriceFilter(String category, String minFilterName, int minFilterValue, 
			String maxFilterName, int maxFilterValue) {
		
		openHomePage().navigateTo(category);
		CatalogFilter washersFilter = PageFactory.getPage(driver, CatalogFilter.class);
		
		washersFilter.toggleFilter(minFilterName, String.valueOf(minFilterValue));
		if (maxFilterValue != 0) {
			washersFilter.toggleFilter(maxFilterName, String.valueOf(maxFilterValue));
		}		
		
		assertTrue(washersFilter.allItemsMatchesPriceFilter(minFilterValue, maxFilterValue));
	}
}
