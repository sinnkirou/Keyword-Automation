package com.aaa.olb.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
		String[][] tabArray = null;

		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startRow = 1;
			int startCol = 1;
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			// System.out.println("totalRows: " + totalRows);
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum() - 1;
			// System.out.println("totalCols: " + totalCols);
			tabArray = new String[totalRows][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
					// System.out.println(tabArray[ci][cj]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			@SuppressWarnings("deprecation")
			int dataType = Cell != null ? Cell.getCellType() : 3;
			if (dataType == 1) {
				return Cell.getStringCellValue();
			} else if (dataType == 4) {
				return String.valueOf(Cell.getBooleanCellValue());
			} else if (dataType == 0) {
				String value = String.valueOf(Cell.getNumericCellValue());
				if (value != null && value.substring(value.indexOf(".")).equals(".0")) {
					value = value.substring(0, value.indexOf("."));
				}
				return value;
			} else {
				return "";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	@SuppressWarnings("deprecation")
	public static String getCellData(XSSFRow row, int ColNum) throws Exception {
		try {
			Cell = row.getCell(ColNum);

			if (Cell != null)
				switch (Cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					return Cell.getStringCellValue();
				case HSSFCell.CELL_TYPE_NUMERIC:
					String value = String.valueOf(Cell.getNumericCellValue());
					if (value != null && value.substring(value.indexOf(".")).equals(".0")) {
						value = value.substring(0, value.indexOf("."));
					}
					return value;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					return String.valueOf(Cell.getBooleanCellValue());
				case HSSFCell.CELL_TYPE_BLANK:
				default:
					return "";
				}
			return "";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	public static List<String> getSheetName(String Path) throws FileNotFoundException, IOException {
		ExcelWBook = new XSSFWorkbook(new FileInputStream(Path));
		int number = ExcelWBook.getNumberOfSheets();
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < number; i++) {
			sheetNames.add(ExcelWBook.getSheetAt(i).getSheetName());
		}
		return sheetNames;
	}

	public static XSSFSheet getSheet(String Path, String SheetName) {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			return ExcelWBook.getSheet(SheetName);
		} catch (Exception ex) {
			return null;
		}

	}
}
