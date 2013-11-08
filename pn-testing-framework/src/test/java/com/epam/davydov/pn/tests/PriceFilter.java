package com.epam.davydov.pn.tests;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogFilter;

public class PriceFilter extends TestBase {
	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void testPriceFilter(String category, String minFilterName, int minFilterValue, 
			String maxFilterName, int maxFilterValue, int filterLimit) {
		
		openHomePage().navigateTo(category);
		CatalogFilter washersFilter = PageFactory.getPage(CatalogFilter.class);
		
		washersFilter.toggleFilter(minFilterName, String.valueOf(minFilterValue));
		
		if (minFilterValue != filterLimit) {
			washersFilter.toggleFilter(maxFilterName, String.valueOf(maxFilterValue));
		}		
		
		washersFilter.verifyProductsIsInPriceRange(minFilterValue, maxFilterValue);
	}
}
