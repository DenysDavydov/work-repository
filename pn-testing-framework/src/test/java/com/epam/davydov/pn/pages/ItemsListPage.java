package com.epam.davydov.pn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.entities.Item;
import com.epam.davydov.pn.entities.Property;
import com.epam.davydov.pn.util.How;
import com.epam.davydov.pn.util.PageFactory;

public class ItemsListPage extends Page {
	private static int CATALOG_DIV_OFFSET = 2;

	protected By webItemPrice = By.cssSelector(".item strong");
	protected By webItemName = By.cssSelector(".name");
	protected By addToComparisonButton = By.cssSelector(".compare_add_link");
	
	@FindBy(css = ".order")
	private WebElement sortingArea;
	@FindBy(css = "[href$='sort=price']")
	protected WebElement sortByPriceButton;
	@FindBy(css = "[href$='sort=name']")
	protected WebElement sortByNameButton;
	@FindBy(css = ".item")
	protected List<WebElement> webItems;
	@FindBy(css = ".show_compare_head_block")
	protected WebElement compareButton;

	public <P extends ItemsListPage> P sort(Class<P> pageClass, How by) {
		switch (by) {
		case BY_PRICE:
			sortByPriceButton.click();
			break;
		case BY_NAME:
			sortByNameButton.click();
			break;
		}
		return PageFactory.getPage(driver, pageClass);
	}

	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		for (WebElement webItem : webItems) {
			items.add(parseWebItem(webItem));
		}
		return items;
	}

	private Item parseWebItem(WebElement webItem) {
		Item item = new Item();
		
		String itemPrice = webItem.findElement(webItemPrice).getText();
		String itemName = webItem.findElement(webItemName).getText();
		
		item.setProperty(Property.PRICE, itemPrice);
		item.setProperty(Property.NAME, itemName);		
		return item;
	}

	public void addItemToComparison(int itemNumber) {
		getItemByNumber(itemNumber).findElement(addToComparisonButton).click();
	}

	private WebElement getItemByNumber(int itemNumber) {
		By item = By.cssSelector(String.format(".item::nth-child(%s)", itemNumber
				+ CATALOG_DIV_OFFSET));
		return driver.findElement(item);
	}

	public ComparisonPage compareItems() {
		compareButton.click();
		return PageFactory.getPage(driver, ComparisonPage.class);
	}

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub

	}
}