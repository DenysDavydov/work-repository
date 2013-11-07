package com.epam.davydov.pn.helpers.core;

import static java.lang.String.format;
import static org.testng.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.testng.Reporter;

import com.epam.davydov.pn.helpers.dataproviders.Product;

public class BaseHelper {
	public static void verifyProductsSorting(Iterable<Product> iterable, String key) {
		boolean isFailed = false;
		Iterator<Product> iterator = iterable.iterator();
		Product p = iterator.next();
		while (iterator.hasNext()) {
			Product p2 = iterator.next();
			if (p.compareItemByProperty(p2, key) > 0) {
				Reporter.log(p + " > " + p2);
				isFailed = true;
			}
			p = p2;
		}
		generateResult(isFailed);
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

	public static boolean isParentContainsChildItems(List<String> parent, List<String> child) {
		boolean result = true;
		String parentText = parent.toString();
		for (String row : child) {
			if (!parentText.contains(row)) {
				String message = format(
						"<font color=\"red\">\"%s\" product page doesn't contains \"%s\" property</font><br>",
						parent.get(0), row);
				Reporter.log(message);
				result = false;
			}
		}
		return result;
	}

	public static void generateResult(boolean isFailed) {
		if (isFailed)
			fail();
	}
}