package com.pn.pages;

import com.pn.utils.PageFactory;

public class Washers extends ItemsListPage {

	@Override
	public Washers sortByPrice() {
		sortByPriceButton.click();
		return PageFactory.getPage(driver, Washers.class);
	}
	

}
