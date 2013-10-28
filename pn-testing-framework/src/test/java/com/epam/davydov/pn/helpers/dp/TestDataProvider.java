package com.epam.davydov.pn.helpers.dp;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.epam.davydov.pn.helpers.core.Settings;
import com.epam.davydov.pn.helpers.datareaders.XLSReader;

public class TestDataProvider {
	@DataProvider()
	public static Object[][] parametersProvider(Method method) {
		String fileName = Settings.getInputDataDir() + method.getDeclaringClass().getSimpleName() + ".xlsx";
		String sheetName = method.getName();

		return XLSReader.readFile(fileName, sheetName);
	}

	@DataProvider()
	public static Object[][] comparingProductsProvider(Method method) {
		String fileName = Settings.getInputDataDir() + method.getDeclaringClass().getSimpleName() + ".xlsx";
		String sheetName = method.getName();

		String[][] inputData = XLSReader.readFile(fileName, sheetName);

		int rowCount = inputData.length;

		Object[][] result = new Object[rowCount][2];

		for (int i = 0; i < inputData.length; i++) {
			String[] strings = inputData[i];
			result[i][0] = strings[0];
			int[] numbers = new int[strings.length];
			for (int j = 1; i < strings.length; i++) {
				numbers[j - 1] = Integer.parseInt(strings[j]);
			}
			result[i][1] = numbers;
		}
		return result;
	}
}
