package com.epam.davydov.pn.tests;

import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogPage;
import com.epam.davydov.pn.pages.PricePage;
import com.epam.davydov.pn.pages.ProductPage;

public class Test_AirConditionerPrice extends Test_Base {
	private SoftAssert softAssert;

	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void test_AirConditionerPrice(String category, int productNumber) {
		CatalogPage catalog = openHomePage().navigateTo(category);
		List<String> catalogItemDescription = catalog.getProductDescription(productNumber);

		ProductPage productPage = catalog.navigateToProductPage(productNumber);
		List<String> productDescription = productPage.getProductDescription();

		String productPageURL = productPage.getCurrentURL();
		String productName = productPage.getCurrentProductName();

		navigateBack(CatalogPage.class).navigateToPricePage().search(productName);
		PricePage priceSearchResult = PageFactory.getPage(driver, PricePage.class);

		softAssert.assertTrue(isParentContainsChildItems(productDescription, catalogItemDescription),
				"Description assert");
		softAssert.assertTrue(priceSearchResult.allDescriptionLinksLeadsToProductPage(productPageURL), 
				"Links assert");
		softAssert.assertAll();
		softAssert = null;
	}

	@BeforeMethod
	public void beforeTest() {
		softAssert = new SoftAssert();
	}

	private boolean isParentContainsChildItems(List<String> parent, List<String> child) {
		boolean result = true;
		String parentText = parent.toString();
		for (String row : child) {
			if (!parentText.contains(row)) {
				Reporter.log(parent.get(0) + " product page doesn't contains " + row + " property");
				result = false;
			}
		}
		return result;
	}
}
