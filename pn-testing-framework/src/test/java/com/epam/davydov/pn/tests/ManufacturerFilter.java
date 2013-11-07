package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogFilter;
import com.epam.davydov.pn.pages.CatalogPage;

public class ManufacturerFilter extends TestBase {
	private Set<String> filterManufacturers;
	private Set<String> catalogManufacturers;

	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void testManufacturerFilter(String category, String filterCategory) {
		openHomePage().navigateTo(category);
		
		CatalogFilter bakeryFilter = PageFactory.getPage(CatalogFilter.class);
		filterManufacturers  = bakeryFilter.getFilterNames(filterCategory);
		
		CatalogPage bakeryCatalog = PageFactory.getPage(CatalogPage.class);
		catalogManufacturers = bakeryCatalog.getAllCatalogManufacturers();
		
		assertEquals(filterManufacturers, catalogManufacturers);
	}
}
