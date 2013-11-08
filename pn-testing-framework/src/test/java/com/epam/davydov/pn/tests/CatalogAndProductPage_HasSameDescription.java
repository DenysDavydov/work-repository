package com.epam.davydov.pn.tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.core.BaseHelper;
import com.epam.davydov.pn.helpers.core.CustomSoftAssert;
import com.epam.davydov.pn.helpers.dataproviders.BaseDataProvider;
import com.epam.davydov.pn.pages.CatalogPage;
import com.epam.davydov.pn.pages.PricePage;
import com.epam.davydov.pn.pages.ProductPage;

public class CatalogAndProductPage_HasSameDescription extends TestBase {
	private CustomSoftAssert softAssert;

	@Test(dataProvider = "baseDataProvider", dataProviderClass = BaseDataProvider.class)
	public void testProductHasSameDescription(String category, int productNumber) {
		CatalogPage catalog = openHomePage().navigateTo(category);
		List<String> catalogItemDescription = catalog.getProductDescription(productNumber);

		ProductPage productPage = catalog.navigateToProductPage(productNumber);
		List<String> productDescription = productPage.getProductDescription();

		softAssert.assertTrue(BaseHelper.isParentContainsChildItems(productDescription, catalogItemDescription),
				"Description assert");

		String productPageURL = productPage.getCurrentURL();
		String productName = productPage.getCurrentProductName();

		navigateBack(CatalogPage.class).navigateToPricePage().search(productName);
		PricePage priceSearchResult = PageFactory.getPage(PricePage.class);

		softAssert.assertTrue(priceSearchResult.allDescriptionLinksLeadsToProductPage(productPageURL), "Links assert");
		softAssert.assertAll();
	}

	@BeforeMethod
	public void beforeMethod() {
		softAssert = new CustomSoftAssert();
	}
}