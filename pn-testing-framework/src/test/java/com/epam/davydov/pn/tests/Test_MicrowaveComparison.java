package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.helpers.dataproviders.Product;
import com.epam.davydov.pn.pages.CatalogPage;
import com.epam.davydov.pn.pages.ComparisonPage;

public class Test_MicrowaveComparison extends Test_Base {
	ComparisonPage comparisonPage;

	List<Product> realProducts;
	List<Product> comparingProducts;

	@Test(dataProvider = "comparingProductsProvider", dataProviderClass = BaseDataProvider.class)
	public void test_ProductsComparison(String category, int[] productsNumbers) {
		CatalogPage microwaveCatalog = openHomePage().navigateTo(category);

		for (int i = 0; i < productsNumbers.length; i++) {
			microwaveCatalog.addItemToComparison(productsNumbers[i]);
			realProducts.add(microwaveCatalog.navigateToProductPage(productsNumbers[i]).getProduct());
			microwaveCatalog = navigateBack(CatalogPage.class);
		}

		comparisonPage = microwaveCatalog.compareItems();

		for (int i = 0; i < productsNumbers.length; i++) {
			comparingProducts.add(comparisonPage.getComparingProduct(i + 2));
		}

		assertEquals(realProducts, comparingProducts);
	}

	@Test(priority = 1)
	public void test_ComparingProducts_WithDifferentProperties_IsHighlighted() {
		assertTrue(comparisonPage.isDifferentContentHighlighted());
	}

	@BeforeClass
	public void beforeClass() {
		realProducts = new ArrayList<>();
		comparingProducts = new ArrayList<>();
	}
}