package com.epam.davydov.pn.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class ItemsListPage extends Page {
	@FindBy(css=".order")
	private WebElement sortingArea;
	@FindBy(css="[href$='sort=price']")
	protected WebElement sortByPriceButton;
	
	public abstract ItemsListPage sortByPrice();
	
	public List<Item> getItemList(){
		return null;
	}

	public boolean isOnThisPage() {
		return true;
		// TODO Auto-generated method stub
	}

	@Override
	public void tryToOpen() {
		// TODO Auto-generated method stub

	}

}
