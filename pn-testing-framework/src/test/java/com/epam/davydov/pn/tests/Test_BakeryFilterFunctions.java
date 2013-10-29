package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.dp.TestDataProvider;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.pages.CatalogFilter;

public class Test_BakeryFilterFunctions extends TestsCommon {

	@Test(dataProvider = "parametersProvider", dataProviderClass = TestDataProvider.class)
	public void filterResultMatchesCondition(String category, String filterCategory, String filterName) {
		openHomePage().navigateTo(category);
		CatalogFilter bakeryFilter = PageFactory.getPage(driver, CatalogFilter.class);
		bakeryFilter.toggleFilter(filterCategory, filterName);

		assertTrue(bakeryFilter.allItemsMatches(filterName));
	}
}
