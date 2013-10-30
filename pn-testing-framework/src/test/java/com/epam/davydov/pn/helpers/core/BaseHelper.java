package com.epam.davydov.pn.helpers.core;

import java.util.Iterator;

import org.testng.Reporter;

import com.epam.davydov.pn.helpers.dataproviders.Product;

public class BaseHelper {

	public static boolean isProductsSortedBy(Iterable<Product> iterable, String key) {
		Iterator<Product> iterator = iterable.iterator();
		Product p = iterator.next();
		while (iterator.hasNext()) {
			Product p2 = iterator.next();
			if (p.compareItemByProperty(p2, key) > 0) {
				Reporter.log(p + " > " + p2);
				return false;
			}
			p = p2;
		}
		return true;
	}

	public static <T> boolean isContentSame(Iterable<T> iterable) {
		Iterator<T> iterator = iterable.iterator();
		Object o = iterator.next();
		while (iterator.hasNext()) {
			Object o2 = iterator.next();
			if (!o.equals(o2)) {
				return false;
			}
			o = o2;
		}
		return true;
	}
}