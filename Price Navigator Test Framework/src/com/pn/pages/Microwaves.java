package com.pn.pages;

import com.pn.utils.PageFactory;

public class Microwaves extends ItemsListPage {
	public Microwaves sortByPrice(){
		sortByPriceButton.click();
		return PageFactory.getPage(driver, Microwaves.class);
	}
}
