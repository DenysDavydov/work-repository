package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.core.CoreHelper;
import com.epam.davydov.pn.helpers.entities.Product;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.pages.CatalogPage;
import com.epam.davydov.pn.pages.ComparisonPage;

public class Test_MicrowaveComparison extends TestsCommon {
	int[] itemsToCompare = { 1, 2 };

	ComparisonPage comparisonPage;

	Product realProduct_1;
	Product realProduct_2;

	Product comparingProduct_1;
	Product comparingProduct_2;

	@Test
	public void test_ComparisonPage_ContainsAllChoosenProductsProperties_1() {
		assertEquals(realProduct_1, comparingProduct_1);
	}

	@Test
	public void test_ComparisonPage_ContainsAllChoosenProductsProperties_2() {
		assertEquals(realProduct_2, comparingProduct_2);
	}

	@Test
	public void test_ComparingProducts_WithDifferentProperties_IsHighlighted() {
		assertTrue(comparisonPage.isDifferentContentHighlighted());
	}

	@BeforeClass
	public void beforeClass() {
		CatalogPage catalog = openHomePage().navigateTo(CoreHelper.NAV_DESTINATION);

		catalog.addItemToComparison(itemsToCompare[0]);
		catalog.addItemToComparison(itemsToCompare[1]);

		realProduct_1 = catalog.selectProduct(itemsToCompare[0]).getProduct();
		driver.navigate().back();

		realProduct_2 = PageFactory.getPage(driver, CatalogPage.class).selectProduct(itemsToCompare[1]).getProduct();
		driver.navigate().back();

		comparisonPage = catalog.compareItems();

		comparingProduct_1 = comparisonPage.getComparingProduct(itemsToCompare[0]);
		comparingProduct_2 = comparisonPage.getComparingProduct(itemsToCompare[1]);
	}
}
