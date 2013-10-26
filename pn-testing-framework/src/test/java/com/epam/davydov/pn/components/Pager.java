package com.epam.davydov.pn.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Pager extends Component {
	private static final String PAGER = "ul[class=pager]";
	private static final String NEXT_PAGE_CSS = ".pager-next";
	private static final String LAST_PAGE_CSS = ".pager-last";

	@FindBy(css = PAGER)
	private WebElement pager;
	@FindBy(css = NEXT_PAGE_CSS)
	private WebElement nextPageButton;
	@FindBy(css = LAST_PAGE_CSS)
	private WebElement lastPageButton;

	public void goToNextPage() {
		nextPageButton.click();
	}
}
