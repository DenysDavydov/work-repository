package com.epam.davydov.pn.helpers.web;

import com.epam.davydov.pn.components.Component;

public class CatalogHelper {
	Component catalog;

	public CatalogHelper(Component catalog) {
		this.catalog = catalog;		
	}
	
//	public List<Item> getItems() {
//		List<Item> items = new ArrayList<>();
//		for (WebElement webItem : webItems) {
//			items.add(parseWebItem(webItem));
//		}
//		return items;
//	}
//
//	private Item parseWebItem(WebElement webItem) {
//		Item item = new Item();
//
//		String itemPrice = webItem.findElement(By.cssSelector(WEB_ITEM_PRICE)).getText();
//		String itemName = webItem.findElement(By.cssSelector(WEB_ITEM_NAME)).getText();
//
//		item.setProperty(Property.PRICE, itemPrice);
//		item.setProperty(Property.NAME, itemName);
//		return item;
//	}
}
