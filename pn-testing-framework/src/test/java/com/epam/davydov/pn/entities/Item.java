package com.epam.davydov.pn.entities;

import java.util.EnumMap;

public class Item {
	private EnumMap<Property, String> properties;
	private String name;
	private int averagePrice;

	public Item(String name, int averagePrice) {
		properties = new EnumMap<>(Property.class);
		this.name = name;
		this.averagePrice = averagePrice;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return averagePrice;
	}

	public void setProperty(Property key, String value) {
		properties.put(key, value);
	}

	public String getPropertyValue(Property key) {
		return properties.get(key);
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", averagePrice=" + averagePrice + "]";
	}
}