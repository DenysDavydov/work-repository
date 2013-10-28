package com.epam.davydov.pn.helpers.datareaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSReader {
	public static String[][] readFile(String fileName, String sheetName) {
		try (InputStream fileInputStream = new FileInputStream(fileName)) {

			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowsCount = sheet.getLastRowNum();
			int cellsCount = sheet.getRow(0).getLastCellNum();

			String[][] result = new String[rowsCount][cellsCount];

			for (int i = 1; i <= rowsCount; i++) {
				for (int j = 0; j < cellsCount; j++) {
					result[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
			return result;
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " file not found");
		} catch (IOException e) {
			System.out.println("IO error has occurred");
		}
		return null;
	}	
}