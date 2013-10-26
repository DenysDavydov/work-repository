package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.helpers.core.How;
import com.epam.davydov.pn.helpers.entities.Product;
import com.epam.davydov.pn.helpers.entities.Property;
import com.epam.davydov.pn.helpers.factory.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends Page {
	private static final int CATALOG_DIV_OFFSET = 2;
	private static final String ITEM_POSITION_PATTERN = ".item:nth-child(%s)";

	private By webItemPrice = By.cssSelector("strong");
	private By webItemName = By.cssSelector(".name");
	
	private By addToComparisonButton = By.cssSelector(".compare_add_link");
	
	private By productImage = By.cssSelector(".image");	
	
	@FindBy(css = "[href$='sort=price']")
	private WebElement sortByPriceButton;
	@FindBy(css = "[href$='sort=name']")
	private WebElement sortByNameButton;
	
	@FindBy(css = ".item")
	protected List<WebElement> webItems;
	
	@FindBy(css = ".show_compare_head_block")	
	private WebElement compareButton;


	/**
	 * Sorts items on the current page of this catalog
	 * 
	 * @param by describes how to sort items
	 * */
	public CatalogPage sort(How by) {
		switch (by) {
		case BY_PRICE:
            Reporter.log("Clicking on the sort-by-price button<br>");
            sortByPriceButton.click();
            break;
		case BY_NAME:
            Reporter.log("Clicking on the sort-by-name button<br>");
			sortByNameButton.click();
			break;
		}
		return PageFactory.getPage(driver, this.getClass());
	}
	
	
	/** 
	 * Gets a list of all items from the current page of this catalog
	 * 
	 * @return ArrayList contained all items from the current page
	 * */	
	public List<Product> getProducts() {
        Reporter.log("Getting catalog items<br>");
		List<Product> products = new ArrayList<>();
		for (WebElement webItem : webItems) {
			products.add(parseWebItem(webItem));
		}
		return products;
	}

	private Product parseWebItem(WebElement webItem) {
		Product product = new Product();
		
		String itemPrice = webItem.findElement(webItemPrice).getText();		
		String itemName = webItem.findElement(webItemName).getText();
		
		product.setProperty(Property.PRICE, itemPrice);
		product.setProperty(Property.NAME, itemName);		
		return product;
	}
	
	
	/**
	 * Navigates to selected product from the catalog
	 * 
	 * @param itemNumber it's an item's number in the catalog starting from top of the page
	 * 
	 * @return Page of selected product
	 */
    public ProductPage selectProduct(int itemNumber){
        WebElement selectedItem = getItemByNumber(itemNumber);
        selectedItem.findElement(productImage).click();
        return PageFactory.getPage(driver, ProductPage.class);
    }
    
	
	/**
	 * Adds an item specified by it's number to comparison
	 * 
	 * @param itemNumber it's an item's number in the catalog starting from top of the page 
	 */	
	public void addItemToComparison(int itemNumber) {
        Reporter.log("Adding item to comparison");
		getItemByNumber(itemNumber).findElement(addToComparisonButton).click();
	}

	private WebElement getItemByNumber(int itemNumber) {
        Reporter.log("Getting item by number");
		By item = By.cssSelector(String.format(ITEM_POSITION_PATTERN, itemNumber + CATALOG_DIV_OFFSET));
		return getElement(item);
	}
	
	/** 
	 * Compares added items
	 * 
	 * @return comparison page wich contains item added to comparison
	 */	
	public ComparisonPage compareItems() {
		compareButton.click();
		return PageFactory.getPage(driver, ComparisonPage.class);
	}	
}