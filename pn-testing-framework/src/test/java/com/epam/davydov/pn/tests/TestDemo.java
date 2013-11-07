package com.epam.davydov.pn.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.pages.CatalogPage;
import com.epam.davydov.pn.pages.PricePage;

public class TestDemo extends TestBase {
	@Test
	public void test_Demo() {
		CatalogPage catalogPage = openHomePage().navigateTo("Кондиционеры");
//		List<String> description = catalogPage.getProductDescription(3);
//		for (String string : description) {
//			System.out.println(string);
//		}
//		
//		System.out.println("-----------------");
//		
//		description = catalogPage.navigateToProductPage(5).getProductDescription();
//		for (String string : description) {
//			System.out.println(string);
//		}
		catalogPage.navigateToPricePage().search("Midea MSR-09HRN1");
		PricePage priceSearchResult = PageFactory.getPage(PricePage.class);
		assertTrue(priceSearchResult.allDescriptionLinksLeadsToProductPage("http://pn.com.ua/md/40596/"));
	}
}
