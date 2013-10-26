package com.epam.davydov.pn.components;

import org.openqa.selenium.By;

public class CatalogNavigator extends Component {
	private static final String NAVIGATE_TO_LOCATOR_PATTERN = "//a[contains(.,'%s')]";

	public void navigateTo(String catecory) {
		By categoryButton = By.xpath(String.format(NAVIGATE_TO_LOCATOR_PATTERN, catecory));
		driver.findElement(categoryButton).click();
	}
}
