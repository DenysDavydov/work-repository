package com.epam.davydov.pn.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.davydov.pn.pages.CatalogPage;

public class TestDemo extends Test_Base {
	@Test
	public void test_Demo() {
		CatalogPage catalog = openHomePage().navigateTo("Кондиционеры");
		List<String> description = catalog.getProductDescribtion(3);
		for (String string : description) {
			System.out.println(string);
		}
		System.out.println("-----------------");
		description = catalog.selectProduct(3).getProductDescription();
		for (String string : description) {
			System.out.println(string);
		}
	}
}
