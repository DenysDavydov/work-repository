package com.epam.davydov.pn.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Catalog extends Component {
	@FindBy(css = ".item")
	private List<WebElement> webItems;	
}
