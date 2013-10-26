package com.epam.davydov.pn.tests;

import com.epam.davydov.pn.helpers.core.CoreHelper;
import com.epam.davydov.pn.helpers.core.How;
import com.epam.davydov.pn.helpers.entities.Product;
import com.epam.davydov.pn.helpers.entities.Property;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class Test_FridgesSorting extends TestsCommon {
	
	@Test()
	public void test_AscendantSorting_ByPrice() {
		List<Product> products = getSortedItems(How.BY_PRICE);
		
		assertTrue(CoreHelper.isItemsSortedBy(products, Property.PRICE));
	}

	@Test(priority = 1)
	public void test_AscendantSorting_ByName() {
		List<Product> products = getSortedItems(How.BY_NAME);
		
		assertTrue(CoreHelper.isItemsSortedBy(products, Property.NAME));
	}

	private List<Product> getSortedItems(How how) {
        return openHomePage()
                .navigateTo(CoreHelper.navDestination)
                .sort(how)
                .getProducts();
    }
}