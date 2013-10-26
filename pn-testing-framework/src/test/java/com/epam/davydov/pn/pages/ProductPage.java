package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.helpers.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends Page {
	private By webProperty = By.cssSelector(".pr");
	private By webPropertyValue = By.cssSelector(".val");
		
	@FindBy(css = ".row")
	private List<WebElement> propertyRows;	

    public Product getProduct(){
        Product item = new Product();
        for (WebElement row : propertyRows) {
            String property = row.findElement(webProperty).getText();
            String propertyValue = row.findElement(webPropertyValue).getText();

            item.setProperty(property, propertyValue);
        }
        return item;
    }
}