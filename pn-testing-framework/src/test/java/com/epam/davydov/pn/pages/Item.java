package com.epam.davydov.pn.pages;

public class Item {
	@Override
	public String toString() {
		return "Item [name=" + name + ", averagePrice=" + averagePrice + "]";
	}

	private String name;
	private int averagePrice;
	
	public Item(String name, int averagePrice) {
		this.name = name;
		this.averagePrice = averagePrice;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return averagePrice;
	}
}
