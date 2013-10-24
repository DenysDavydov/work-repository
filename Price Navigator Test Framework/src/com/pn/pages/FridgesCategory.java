package com.pn.pages;

import com.pn.utils.PageFactory;

public class FridgesCategory extends ItemsListPage {
	public FridgesCategory sortByPrice(){
		sortByPriceButton.click();
		return PageFactory.getPage(driver, FridgesCategory.class);
	}
}
