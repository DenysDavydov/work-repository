package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.core.CoreHelper;
import com.epam.davydov.pn.helpers.dp.TestDataProvider;
import com.epam.davydov.pn.helpers.entities.Product;

public class Test_FridgesSorting extends TestsCommon {
	@Test(dataProvider = "parametersProvider", dataProviderClass = TestDataProvider.class)
	public void test_ProductsSorting(String category, String howToSort) {
		List<Product> products = 
				openHomePage()
				.navigateTo(category)
				.sortBy(howToSort)
				.getProducts();
		String howToCompare = howToSort;

		assertTrue(CoreHelper.isItemsSortedBy(products, howToCompare));
	}
}