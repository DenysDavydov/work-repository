package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.core.CoreHelper;
import com.epam.davydov.pn.helpers.dp.TestDataProvider;
import com.epam.davydov.pn.helpers.entities.Product;
import com.epam.davydov.pn.helpers.entities.Property;

public class Test_FridgesSorting extends TestsCommon {

	@Test(dataProvider = "parametersProvider", dataProviderClass = TestDataProvider.class)
	public void test_Sorting_ByPrice(String category, String howToSort) {
		List<Product> products = 
				openHomePage()
				.navigateTo(category)
				.sort(howToSort)
				.getProducts();

		assertTrue(CoreHelper.isItemsSortedBy(products, Property.PRICE));
	}
}