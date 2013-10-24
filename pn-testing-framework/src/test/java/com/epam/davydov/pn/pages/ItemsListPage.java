package com.epam.davydov.pn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.util.How;
import com.epam.davydov.pn.util.PageFactory;

public class ItemsListPage extends Page {
	protected By webItemPrice = By.cssSelector(".item strong");
	protected By webItemName = By.cssSelector(".name");

	@FindBy(css = ".order")
	private WebElement sortingArea;
	@FindBy(css = "[href$='sort=price']")
	protected WebElement sortByPriceButton;
	@FindBy(css = "[href$='sort=name']")
	protected WebElement sortByNameButton;
	@FindBy(css = ".item")
	protected List<WebElement> webItems;

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
		int itemPrice = Integer.parseInt(webItem.findElement(webItemPrice).getText().replaceAll("\\s|грн", ""));
		String itemName = webItem.findElement(webItemName).getText();
		return new Item(itemName, itemPrice);
	}

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub

	}
}