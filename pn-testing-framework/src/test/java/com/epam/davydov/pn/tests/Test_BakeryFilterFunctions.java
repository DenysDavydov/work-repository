package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.davydov.pn.pages.CatalogFilter;

public class Test_BakeryFilterFunctions extends TestsCommon {
	private static final String BAKERY = "Хлебопечи";
	private static final String WEIGHT_ADJUSTMENT = "Регулировка веса";
	private CatalogFilter bakeryFilter;

	@Test
	public void test_WeightAdjustmentFilter_ResultMatchesCondition() {
		bakeryFilter = (CatalogFilter) openHomePage().navigateTo(BAKERY);
		bakeryFilter.toggleFilter(WEIGHT_ADJUSTMENT);
		
		assertTrue(bakeryFilter.allItemsMatches(WEIGHT_ADJUSTMENT));
	}
}
