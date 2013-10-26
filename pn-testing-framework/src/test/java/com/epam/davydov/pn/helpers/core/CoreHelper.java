package com.epam.davydov.pn.helpers.core;

import java.util.Iterator;

import com.epam.davydov.pn.helpers.entities.Product;

public class CoreHelper {
	public static String navDestination = "Холодильники";

	public static boolean isItemsSortedBy(Iterable<Product> iterable, String key) {
		Iterator<Product> iterator = iterable.iterator();
		Product p = iterator.next();
		while (iterator.hasNext()) {
			Product p2 = iterator.next();
			if (p.compareItemByProperty(p2, key) > 0) {
				return false;
			}
			p = p2;
		}
		return true;
	}
}