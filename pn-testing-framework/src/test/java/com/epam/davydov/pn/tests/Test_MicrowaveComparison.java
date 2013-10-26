package com.epam.davydov.pn.tests;

import com.epam.davydov.pn.helpers.entities.Product;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import com.epam.davydov.pn.pages.CatalogPage;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Test_MicrowaveComparison extends TestsCommon {
    int[] itemsToCompare = {1, 2};

    Product realProduct_1;
    Product realProduct_2;

    Product comparingProduct_1;
    Product comparingProduct_2;

    @Test
    public void test_ComparisonPage_ContainsAllChoosenProductsProperties_1() {
        Reporter.log(comparingProduct_1.toString());
        assertEquals(realProduct_1, comparingProduct_1);
    }

    @Test
    public void test_ComparisonPage_ContainsAllChoosenProductsProperties_2() {
        Reporter.log(comparingProduct_2.toString());
        assertEquals(realProduct_2, comparingProduct_2);
    }

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        CatalogPage catalog = openHomePage().navigateTo("Микроволновки");

        catalog.addItemToComparison(itemsToCompare[0]);
        catalog.addItemToComparison(itemsToCompare[1]);

        realProduct_1 = catalog.selectProduct(itemsToCompare[0]).getProduct();
        driver.navigate().back();
        realProduct_2 = PageFactory.getPage(driver, CatalogPage.class).selectProduct(itemsToCompare[1]).getProduct();
        driver.navigate().back();

        comparingProduct_1 = catalog.compareItems().getComparingProduct(itemsToCompare[0]);
        comparingProduct_2 = catalog.compareItems().getComparingProduct(itemsToCompare[1]);
    }
}
