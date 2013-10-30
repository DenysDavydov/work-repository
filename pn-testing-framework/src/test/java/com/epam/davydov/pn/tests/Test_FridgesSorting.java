package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.core.BaseHelper;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.helpers.dataproviders.Product;

public class Test_FridgesSorting extends Test_Base {
	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void test_ProductsSorting(String category, String howToSort) {		
		List<Product> products = 
				openHomePage()
				.navigateTo(category)
				.sortBy(howToSort)
				.getProducts();
		String howToCompare = howToSort;

		assertTrue(BaseHelper.isProductsSortedBy(products, howToCompare));
	}
}