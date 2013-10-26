package com.epam.davydov.pn.pages;

import com.epam.davydov.pn.helpers.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ComparisonPage extends Page {
    private static final String WEB_PROPERTY_VALUE_PATTERN = ".//td[%s]";
    private By webProperty = By.xpath(".//td[1]");

    @FindBy(xpath = "//tr[*[@colspan]]/following-sibling::tr[not(*[@colspan])]")
    private List<WebElement> propertyRows;    

    public Product getComparingProduct(int productNumber) {
        Product item = new Product();
        
        for (WebElement row : propertyRows) {
            String property = row.findElement(webProperty).getText();

            By webPropertyValue = By.xpath(String.format(WEB_PROPERTY_VALUE_PATTERN, productNumber + 1));
            String propertyValue = row.findElement(webPropertyValue).getText();

            if (propertyValue.equals("-"))
                continue;
            item.setProperty(property, propertyValue);
        }
        return item;
    }
}
