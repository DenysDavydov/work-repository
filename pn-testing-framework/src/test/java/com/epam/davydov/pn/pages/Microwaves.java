package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.util.PageFactory;


public class Microwaves extends ItemsListPage {
	public Microwaves sort(){
		sortByPriceButton.click();
		return PageFactory.getPage(driver, Microwaves.class);
	}

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub
		
	}
}
