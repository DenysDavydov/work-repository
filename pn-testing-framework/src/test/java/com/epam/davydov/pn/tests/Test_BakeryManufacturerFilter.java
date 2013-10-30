package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogFilter;
import com.epam.davydov.pn.pages.CatalogPage;

public class Test_BakeryManufacturerFilter extends Test_Base {
	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void test_BakeryManufacturerFilter(String category, String filterCategory) {
		Set<String> filterManufacturers;
		Set<String> catalogManufacturers;
		
		openHomePage().navigateTo(category);
		
		CatalogFilter bakeryFilter = PageFactory.getPage(driver, CatalogFilter.class);
		filterManufacturers  = bakeryFilter.getFilterNames(filterCategory);
		
		CatalogPage bakeryCatalog = PageFactory.getPage(driver, CatalogPage.class);
		catalogManufacturers = bakeryCatalog.getAllCatalogManufacturers();
		
		assertEquals(filterManufacturers, catalogManufacturers);
	}
}
