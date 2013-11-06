package com.epam.davydov.pn.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.helpers.core.BaseHelper;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.helpers.dataproviders.Product;

public class ProductsSorting extends TestBase {
	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void testCatalogProductsSorting(String category, String howToSort) {		
		List<Product> products = 
				openHomePage()
				.navigateTo(category)
				.sortBy(howToSort)
				.getProducts();
		
		String howToCompare = howToSort;		
		BaseHelper.verifyProductsSorting(products, howToCompare);
	}
}