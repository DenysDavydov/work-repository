package com.epam.davydov.pn.pages;

import static java.lang.String.format;

import java.util.ArrayList;
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
	private static final String PAGER = "%s?page=%s";
	private static final String ITEM_POSITION = "//div[@class='item'][%s]";
	private static final String SORT = "//div[@class='order']//a[contains(.,'%s')]";
	private static final String ADD_ITEM_TO_COMPARISON = "//div[@class='item'][%s]//span[@class='compare_add_link comparep cs']";
	private static final String SELECT_PRODUCT = "//div[@class='item'][%s]//div[@class='name']/a";

	private By catalogItemDescription = By.cssSelector(".description");
	protected By catalogItemPrice = By.cssSelector("strong");
	protected By catalogItemName = By.cssSelector(".name");
	
	@FindBy(xpath="//a[@class='green' and @rel='nofollow']")
	private WebElement pricesLink;
	@FindBy(css = ".pager-last.last>a")
	private WebElement lastPageLink;
	@FindBy(css = ".show_compare_head_block")
	private WebElement compareButton;
	@FindBy(css = ".item")
	protected List<WebElement> catalogItems;

	/**
	 * Sorts items on the current page of this catalog
	 * 
	 * @param how - describes how to sort items
	 * 
	 * */
	public CatalogPage sortBy(String how) {
		Reporter.log("Click on the \"" + how + "\" sort button");
		By sortButton = By.xpath(String.format(SORT, how));
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
		for (WebElement catalogItem : catalogItems) {
			products.add(parseCatalogItem(catalogItem));
		}
		return products;
	}

	private Product parseCatalogItem(WebElement catalogItem) {
		Product product = new Product();

		String productPrice = catalogItem.findElement(catalogItemPrice).getText();
		String productName = catalogItem.findElement(catalogItemName).getText();

		product.setProperty(Product.PRICE, productPrice);
		product.setProperty(Product.NAME, productName);
		return product;
	}

	/**
	 * Navigates to selected product from the catalog
	 * 
	 * @param catalogItemNumber it's an item's number in the catalog starting from top of the page
	 * 
	 * @return Page of selected product
	 */
	public ProductPage navigateToProductPage(int catalogItemNumber) {
		Reporter.log(format("Open product page at number %s<br>", catalogItemNumber));
		By productLink = By.xpath(String.format(SELECT_PRODUCT, catalogItemNumber));
		getElement(productLink).click();
		return PageFactory.getPage(driver, ProductPage.class);
	}

	public PricePage navigateToPricePage() {
		Reporter.log("Open price-list page<br>");
		pricesLink.click();
		return PageFactory.getPage(driver, PricePage.class);
	}

	/**
	 * Adds an item specified by it's number to comparison
	 * 
	 * @param itemNumber it's an item's number in the catalog starting from top of the page
	 */
	public void addItemToComparison(int itemNumber) {
		Reporter.log("Add to comparison product at number " + itemNumber);
		By addToComparisonButton = By.xpath(String.format(ADD_ITEM_TO_COMPARISON, itemNumber));
		getElement(addToComparisonButton).click();
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
	
	/**
	 * Gets description of the specified protuct by catalog item number
	 * 
	 * */
	public List<String> getProductDescription(int itemNumber) {
		Reporter.log(format("Get description of product at number %s<br>", itemNumber));
		WebElement item = getItemByNumber(itemNumber);
		List<String> shortDescription = new ArrayList<>();

		String description = item.findElement(catalogItemDescription).getText();

		shortDescription.add(item.findElement(catalogItemName).getText().toLowerCase().replaceAll(" / ", " "));
		shortDescription.add(item.findElement(catalogItemPrice).getText());

		String[] temp = description.substring(description.indexOf(";") + 1).split(";");

		for (String string : temp) {
			shortDescription.add(string.trim().toLowerCase());
		}
		return shortDescription;
	}

	private WebElement getItemByNumber(int itemNumber) {
		By item = By.xpath(String.format(ITEM_POSITION, itemNumber));
		return getElement(item);
	}
	
	/**
	 * 
	 * */
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
		for (WebElement catalogItem : catalogItems) {
			String productName = catalogItem.findElement(catalogItemName).getText().toLowerCase();
			String manufacturer = productName.substring(0, productName.indexOf(" "));
			manufacturers.add(manufacturer);
		}
		return manufacturers;
	}

	private int getPagesCount() {
		return Integer.parseInt(lastPageLink.getText());
	}
}