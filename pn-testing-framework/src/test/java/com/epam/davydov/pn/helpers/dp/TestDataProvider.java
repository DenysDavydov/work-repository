package com.epam.davydov.pn.helpers.dp;

import java.lang.reflect.Method;
import java.util.Arrays;

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

		Object[][] inputData = XLSReader.readFile(fileName, sheetName);
		for (Object[] objects : inputData) {
			System.out.println(Arrays.toString(objects));
		}

		int rowCount = inputData.length;
		int numbersCount = inputData[0].length - 1;		

		Object[][] result = new Object[rowCount][2];
		int[] numbers;

		for (int i = 0; i < rowCount; i++) {
			result[i][0] = inputData[i][0];
			numbers = new int[numbersCount];
			for (int j = 1; j <= numbersCount; j++) {
				numbers[j - 1] = (int) inputData[i][j];
			}
			result[i][1] = numbers;
		}
		return result;
	}
}