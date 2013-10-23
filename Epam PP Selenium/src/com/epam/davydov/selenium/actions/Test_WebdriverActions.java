package com.epam.davydov.selenium.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.davydov.selenium.utils.MyWebDriverFactory;

public class Test_WebdriverActions {
	private static final String BASE_URL = "http://jqueryui.com/";
	private WebDriver driver;
	private Actions action;
	
	@Test()
	public void draggable() {
		driver.get("http://jqueryui.com/draggable/");
		WebElement demoFrame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(demoFrame);
		WebElement draggable = driver.findElement(By.id("draggable"));
		action.dragAndDropBy(draggable, 50, 50).build().perform();
		driver.switchTo().defaultContent();
	}
	
	@Test()
	public void droppable(){
		driver.get("http://jqueryui.com/droppable/");
		WebElement demoFrame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(demoFrame);
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		action.dragAndDrop(draggable, droppable).build().perform();
	}

	@BeforeClass
	public void beforeClass() {
		driver = MyWebDriverFactory.getInstance(BrowserType.FIREFOX, 5);
		driver.get(BASE_URL);
		action = new Actions(driver);
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(15000);
		MyWebDriverFactory.dispose();
	}
}