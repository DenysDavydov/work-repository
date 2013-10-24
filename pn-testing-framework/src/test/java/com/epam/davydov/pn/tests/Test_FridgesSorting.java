package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.pages.Item;
import com.epam.davydov.pn.util.ListUtils;


public class Test_FridgesSorting extends _Test {	
	
	@Test()
	public void test_AscendantSorting_ByPrice() {
		openHomePage().goToFridges().sortByPrice().getItemList();
		List<Item> list = new ArrayList<>();
		assertTrue(ListUtils.isSorted(list));		
	}	

	@Test()
	public void test_AscendantSorting_ByName() {
	}	
}