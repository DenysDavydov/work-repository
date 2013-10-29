package com.epam.davydov.pn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.epam.davydov.pn.helpers.entities.Product;
import com.epam.davydov.pn.helpers.entities.Property;
import com.epam.davydov.pn.helpers.factory.PageFactory;

public class CatalogPage extends Page {
	private static final int CATALOG_DIV_OFFSET = 2;
	private static final String ITEM_POSITION = ".item:nth-child(%s)";
	private static final String SORT_BUTTON = "//div[@class='order']//a[contains(.,'%s')]";

	private By addToComparisonButton = By.cssSelector(".compare_add_link");
	private By productImage = By.cssSelector(".image");
	protected By webItemPrice = By.cssSelector("strong");
	protected By webItemName = By.cssSelector(".name");
	
	@FindBy(css = ".show_compare_head_block")
	private WebElement compareButton;

	@FindBy(css = ".item")
	protected List<WebElement> webItems;

	/**
	 * Sorts items on the current page of this catalog
	 * 
	 * @param how
	 *            - describes how to sort items
	 * 
	 * */
	public CatalogPage sortBy(String how) {
		Reporter.log("Click on the \"" + how + "\" sort button");
		By sortButton = By.xpath(String.format(SORT_BUTTON, how));
		getElement(sortButton).click();
		return PageFactory.getPage(driver, this.getClass());
	}

	/**
	 * Gets a list of all items from the current page of this catalog
	 * 
	 * @return ArrayList contained all items from the current page
	 * */
	public List<Product> getProducts() {
		Reporter.log("Get short products from the catalog");
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
	 * @param itemNumber
	 *            it's an item's number in the catalog starting from top of the
	 *            page
	 * 
	 * @return Page of selected product
	 */
	public ProductPage selectProduct(int itemNumber) {
		Reporter.log("Open product page at number " + itemNumber);
		WebElement selectedItem = getItemByNumber(itemNumber);
		selectedItem.findElement(productImage).click();
		return PageFactory.getPage(driver, ProductPage.class);
	}

	/**
	 * Adds an item specified by it's number to comparison
	 * 
	 * @param itemNumber
	 *            it's an item's number in the catalog starting from top of the
	 *            page
	 */
	public void addItemToComparison(int itemNumber) {
		Reporter.log("Add to comparison product at number " + itemNumber);
		getItemByNumber(itemNumber).findElement(addToComparisonButton).click();
	}

	private WebElement getItemByNumber(int itemNumber) {
		By item = By.cssSelector(String.format(ITEM_POSITION, itemNumber + CATALOG_DIV_OFFSET));
		return getElement(item);
	}

	/**
	 * Compares added items
	 * 
	 * @return comparison page wich contains item added to comparison
	 */
	public ComparisonPage compareItems() {
		Reporter.log("Click at the compare button, go to comparison page");
		compareButton.click();
		return PageFactory.getPage(driver, ComparisonPage.class);
	}
}