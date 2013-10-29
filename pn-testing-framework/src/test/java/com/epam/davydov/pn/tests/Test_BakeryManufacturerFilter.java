package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.dp.TestDataProvider;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.pages.CatalogFilter;
import com.epam.davydov.pn.pages.CatalogPage;

public class Test_BakeryManufacturerFilter extends TestsCommon {
	@Test(dataProvider = "parametersProvider", dataProviderClass = TestDataProvider.class)
	public void test_BakeryManufacturerFilter(String category, String filterCategory) {
		Set<String> filterManufacturers = new TreeSet<>();
		Set<String> catalogManufacturers = new TreeSet<>();
		
		openHomePage().navigateTo(category);
		
		CatalogFilter bakeryFilter = PageFactory.getPage(driver, CatalogFilter.class);
		CatalogPage bakeryCatalog = PageFactory.getPage(driver, CatalogPage.class);
		
		filterManufacturers  = bakeryFilter.getFilterNames(filterCategory);
		catalogManufacturers = bakeryCatalog.getAllCatalogManufacturers();
		
		assertEquals(filterManufacturers, catalogManufacturers);
	}
}
