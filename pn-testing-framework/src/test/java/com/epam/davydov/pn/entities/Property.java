package com.epam.davydov.pn.entities;

public enum Property {
	NAME("Название"), PRICE("Цена"), TYPE("Тип"), BLANK("");
	public String name;

	private Property(String name) {
		this.name = name;
	}
}