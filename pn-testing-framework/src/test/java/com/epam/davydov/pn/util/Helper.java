package com.epam.davydov.pn.util;

import java.util.Iterator;

import com.epam.davydov.pn.entities.Item;
import com.epam.davydov.pn.entities.Property;

public class Helper {

//	public static boolean isItemsSorted(Iterable<Item> iterable, How by) {
//		Iterator<Item> iterator = iterable.iterator();
//		if (!iterator.hasNext()) {
//			return true;
//		}
//		Item i = iterator.next();
//		while (iterator.hasNext()) {
//			Item i2 = iterator.next();
//			switch (by) {
//			case BY_PRICE:
//				if (i.getPrice() > i2.getPrice()) {
//					Logger.errorMessage = String.format("%s preceeds %s", i.getPrice(),
//							i2.getPrice());
//					return false;
//				}
//				break;
//			case BY_NAME:
//				if (i.getName().compareTo(i2.getName()) > 0) {
//					Logger.errorMessage = (String.format("%s preceeds %s", i.getName(),
//							i2.getName()));
//					return false;
//				}
//				break;
//			}
//			i = i2;
//		}
//		return true;
//	}

	public static boolean isItemsSortedBy(Iterable<Item> iterable, Property key) {
		Iterator<Item> iterator = iterable.iterator();
		Item i = iterator.next();
		while (iterator.hasNext()) {
			Item i2 = iterator.next();
			if (i.getName().compareTo(i2.getName()) > 0) {
				Logger.errorMessage = String.format("%s preceeds %s", 
						i.getPropertyValue(key), i2.getPropertyValue(key));
				return false;
			}
			i = i2;
		}
		return true;
	}

	public static Property getPropertyByName(String name) {
		for (Property property : Property.values()) {
			if (property.name.equals(name)) {
				return property;
			}
		}
		return Property.BLANK;
	}
}
