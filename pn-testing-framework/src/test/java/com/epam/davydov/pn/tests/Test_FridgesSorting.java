package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.entities.Item;
import com.epam.davydov.pn.entities.Property;
import com.epam.davydov.pn.pages.FridgesCategory;
import com.epam.davydov.pn.pages.ItemsListPage;
import com.epam.davydov.pn.util.How;
import com.epam.davydov.pn.util.Helper;
import com.epam.davydov.pn.util.Logger;

public class Test_FridgesSorting extends _Test {

	@Test()
	public void test_AscendantSorting_ByPrice() {
		List<Item> items = getSortedItems(FridgesCategory.class, How.BY_PRICE);
		assertTrue(Helper.isItemsSortedBy(items, Property.PRICE), Logger.errorMessage);
	}

	@Test(priority = 1)
	public void test_AscendantSorting_ByName() {
		List<Item> items = getSortedItems(FridgesCategory.class, How.BY_NAME);
		assertTrue(Helper.isItemsSortedBy(items, Property.NAME), Logger.errorMessage);
	}

	private <P extends ItemsListPage> List<Item> getSortedItems(Class<P> pageClass, How how) {
		List<Item> sortedItems = 
				openHomePage()
				.navigateToFridges()
				.sort(pageClass, how)
				.getItems();
		return sortedItems;
	}
}