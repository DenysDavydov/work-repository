package com.epam.davydov.pn.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.davydov.pn.helpers.core.How;

public class SortBlock extends Component {
	private static final String SORT_BLOCK = ".order";
	private static final String BY_NAME = ".order";
	private static final String BY_PRICE = ".order";

	@FindBy(css = SORT_BLOCK)
	private WebElement sortBlock;
	@FindBy(css = BY_NAME)
	private WebElement sortByNameButton;
	@FindBy(css = BY_PRICE)
	private WebElement sortByPriceButton;

	public void sort(How how) {
		switch (how) {
		case BY_NAME:
			sortByNameButton.click();
			break;
		case BY_PRICE:
			sortByPriceButton.click();
			break;
		}
	}
}
