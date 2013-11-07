package com.epam.davydov.pn.tests;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogFilter;

public class FilterFunctioning extends TestBase {
	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void resultMatchesFilter(String category, String filterCategory, String filterName) {
		openHomePage().navigateTo(category);
		CatalogFilter bakeryFilter = PageFactory.getPage(CatalogFilter.class);
		bakeryFilter.toggleFilter(filterCategory, filterName);

		bakeryFilter.verifyProductsMatchesFilter(filterName);
	}
}