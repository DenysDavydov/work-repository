package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogFilter;

public class Test_BakeryFilterFunctions extends Test_Base {

	@Test(dataProvider = "parametersProvider", dataProviderClass = BaseDataProvider.class)
	public void filterResultMatchesCondition(String category, String filterCategory, String filterName) {
		openHomePage().navigateTo(category);
		CatalogFilter bakeryFilter = PageFactory.getPage(driver, CatalogFilter.class);
		bakeryFilter.toggleFilter(filterCategory, filterName);

		assertTrue(bakeryFilter.allItemsMatches(filterName));
	}
}
