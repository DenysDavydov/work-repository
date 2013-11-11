package com.epam.davydov.pn.helpers.core;

import static java.lang.String.format;
import static org.testng.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;

import org.testng.Reporter;

import com.epam.davydov.pn.helpers.dataproviders.Product;

public class BaseHelper {
	public static final String RED_FONT = "<font color=\"red\">%s</font><br>";
	public static final String BLUE_FONT = "<font color=\"blue\">%s</font><br>";

	public static void verifyProductsSorting(Iterable<Product> iterable, String key) {
		String message = format("Verify products is correctly sorted by \"%s\"", key);
		log(BLUE_FONT, message);
		boolean isFailed = false;
		Iterator<Product> iterator = iterable.iterator();
		Product p = iterator.next();
		while (iterator.hasNext()) {
			Product p2 = iterator.next();
			if (p.compareItemByProperty(p2, key) > 0) {
				String errorMessage = format("%s > %s", p, p2);
				log(RED_FONT, errorMessage);
				isFailed = true;
			}
			p = p2;
		}
		assertFalse(isFailed);
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

	public static void verifyParentContainsChildItems(List<String> parent, List<String> child, CustomSoftAssert sa) {
		log(BLUE_FONT, "Verify product page contains all properties from the catalog item");
		boolean isFailed = false;
		String parentText = parent.toString();
		for (String childItem : child) {
			if (!parentText.contains(childItem)) {
				String message = format("\"%s\" product page doesn't contains \"%s\" property", parent.get(0),
						childItem);
				log(format(RED_FONT, message));
				isFailed = true;
			}
		}
		sa.assertFalse(isFailed, "Description assert");
	}

	public static void log(String format, Object... args) {
		Reporter.log(format(format, args));
	}

	public static void log(String message) {
		Reporter.log(message);
	}
}