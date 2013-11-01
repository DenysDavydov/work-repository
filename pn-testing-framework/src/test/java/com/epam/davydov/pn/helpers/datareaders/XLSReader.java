package com.epam.davydov.pn.helpers.datareaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSReader {
	public static Object[][] readFile(String fileName, String sheetName) {
		try (InputStream fileInputStream = new FileInputStream(fileName)) {

			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

			Object[][] result = new Object[rowCount - 1][columnCount];

			for (int i = 1; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					Cell cell = sheet.getRow(i).getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
						case Cell.CELL_TYPE_STRING:
							result[i - 1][j] = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							result[i - 1][j] = (int) cell.getNumericCellValue();
							break;						
						}
					} else {
						result[i - 1][j] = "";
					}
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