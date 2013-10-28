package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.helpers.core.CoreHelper;
import com.epam.davydov.pn.helpers.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class ComparisonPage extends Page {
	private static final String PRODUCT_PROPERTY_VALUE = ".//td[%s]";

	private By webPropertyValues = By.xpath(".//td[position() > 1]");
	private By webProperty = By.xpath(".//td[1]");

	@FindBy(xpath = "//tr[*[@colspan]]/following-sibling::tr[not(*[@colspan])]")
	private List<WebElement> propertyRows;

	public Product getComparingProduct(int productNumber) {
		Product item = new Product();

		for (WebElement row : propertyRows) {
			String property = row.findElement(webProperty).getText();

			By webPropertyValue = By
					.xpath(String.format(PRODUCT_PROPERTY_VALUE, productNumber + 1));
			String propertyValue = row.findElement(webPropertyValue).getText();

			if (propertyValue.equals("-"))
				continue;
			item.setProperty(property, propertyValue);
		}
		return item;
	}

	public boolean isDifferentContentHighlighted() {
		for (WebElement row : propertyRows) {
			List<String> rowContent = getRowContent(row);
			// checks if content of the row is not same
			if (!CoreHelper.isContentSame(rowContent)) { 
				String rowCSSClassName = row.getAttribute("class");
				// checks if different content of the row is highlighted
				if (!rowCSSClassName.equals("different")) { 
					Reporter.log("Row " + rowContent + " is not highlighted");
					return false;
				}
			}
		}
		return true;
	}

	private List<String> getRowContent(WebElement row) {
		List<WebElement> cells = row.findElements(webPropertyValues);
		List<String> propertyValues = new ArrayList<>();
		for (WebElement cell : cells) {
			propertyValues.add(cell.getText());
		}
		return propertyValues;
	}
}