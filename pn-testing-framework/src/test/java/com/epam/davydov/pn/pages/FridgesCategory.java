package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.util.PageFactory;


public class FridgesCategory extends ItemsListPage {
	public FridgesCategory sortByPrice(){
		sortByPriceButton.click();
		return PageFactory.getPage(driver, FridgesCategory.class);
	}
}
