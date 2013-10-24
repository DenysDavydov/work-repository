package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.util.PageFactory;


public class Washers extends ItemsListPage {

	@Override
	public Washers sortByPrice() {
		sortByPriceButton.click();
		return PageFactory.getPage(driver, Washers.class);
	}
	

}
