package com.epam.davydov.pn.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.epam.davydov.pn.config.PageFactory;
import com.epam.davydov.pn.helpers.dataproviders.Product;

public class CatalogPage extends Page {
	private static final int CATALOG_DIV_OFFSET = 2;
	private static final String PAGER = "%s?page=%s";
	private static final String ITEM_POSITION = ".item:nth-child(%s)";
	private static final String SORT_BUTTON = "//div[@class='order']//a[contains(.,'%s')]";

	private By addToComparisonButton = By.cssSelector(".compare_add_link");
	private By productImage = By.cssSelector(".image");
	private By webItemMinPrice = By.xpath(".//span/b[1]");
	private By webItemDescription = By.cssSelector(".description");
	protected By webItemPrice = By.cssSelector("strong");
	protected By webItemName = By.cssSelector(".name");	

	@FindBy(css = ".show_compare_head_block")
	private WebElement compareButton;
	@FindBy(css = ".item")
	protected List<WebElement> webItems;
	@FindBy(css = ".pager-last.last>a")
	private WebElement lastPageLink;	

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

		product.setProperty(Product.PRICE, itemPrice);
		product.setProperty(Product.NAME, itemName);
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

	public Set<String> getAllCatalogManufacturers() {
		Reporter.log("Get set of all catalog manufacturers");
		Set<String> allManufacturers = new TreeSet<>();
		allManufacturers.addAll(getCatalogManufacturers());
		String currentURL = getCurrentURL();
		int pagesCount = getPagesCount();
		for (int i = 1; i < pagesCount; i++) {
			String pageURL = String.format(PAGER, currentURL, i);
			driver.get(pageURL);
			CatalogPage nextPage = PageFactory.getPage(driver, CatalogPage.class);
			allManufacturers.addAll(nextPage.getCatalogManufacturers());
		}
		return allManufacturers;
	}

	private Set<String> getCatalogManufacturers() {
		Set<String> manufacturers = new TreeSet<>();
		for (WebElement item : webItems) {
			String itemName = item.findElement(webItemName).getText().toLowerCase();
			String manufacturer = itemName.substring(0, itemName.indexOf(" "));
			manufacturers.add(manufacturer);
		}
		return manufacturers;
	}
	
	
	private int getPagesCount() {
		return Integer.parseInt(lastPageLink.getText());
	}

	public List<String> getProductDescribtion(int i) {
		WebElement item = getItemByNumber(i);
		List<String> shortDescription = new ArrayList<>();
//		StringBuilder sb = new StringBuilder();
//		sb.append(item.findElement(webItemName).getText().toLowerCase() + " ");
//		sb.append(item.findElement(webItemMinPrice).getText());
		String description = item.findElement(webItemDescription).getText();		 
//		String shortDescription = description
//				.substring(description.indexOf(";") + 1)
//				.replaceAll("[,;]", " ")
//				.replaceAll("\\s+", " ");
		
		shortDescription.add(item.findElement(webItemName).getText().toLowerCase());
		shortDescription.add(item.findElement(webItemMinPrice).getText());
		String[] temp = description.substring(description.indexOf(";") + 1).split(";");
		for (String string : temp) {
			shortDescription.add(string.trim());
		}
//		sb.append(shortDescription);
		return shortDescription;
	}
}