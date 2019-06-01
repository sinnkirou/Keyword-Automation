package com.aaa.olb.automation.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.utils.ExcelUtils;

public class ExcelProvider extends DataProvider implements DataReader, DataGroupBy {

	private XSSFSheet sheet;

	public ExcelProvider(XSSFSheet sheet)  {
		// TODO Auto-generated constructor stub
		this.sheet = sheet;
		read();
	}

	private List<String> readColumn(XSSFSheet sheet)  {
		List<String> columns = new ArrayList<>();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		for (int i = 0; i < columnCount; i++) {
			columns.add(ExcelUtils.getCellData(row, i));
		}
		return columns;
	}

	private Map<String, CellEntity> readRow(List<String> columns, XSSFRow row) {
		Map<String, CellEntity> rowData = new HashMap<>();
		for (int i = 0; i < columns.size(); i++) {
			CellEntity entity = new CellEntity();
			entity.setIndex(i);
			entity.setValue(ExcelUtils.getCellData(row, i));
			rowData.put(columns.get(i), entity);
		}
		return rowData;
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		int rowCount = this.sheet.getLastRowNum();
		List<String> columns = null;
		columns = this.readColumn(this.sheet);
		
		if (!this.data.isEmpty())
			this.data.clear();
		for (int i = 1; i <= rowCount; i++) {
			this.data.add(this.readRow(columns, this.sheet.getRow(i)));
		}

		// return this.data;
	}

	@Override
	public Map<String, List<Map<String, CellEntity>>> groupBy(String columnName)  {
		// TODO Auto-generated method stub
		int rowCount = this.sheet.getLastRowNum();
		List<String> columns = null;
		columns = this.readColumn(this.sheet);

		Map<String, List<Map<String, CellEntity>>> groups = new HashMap<>();
		int groupIndex = columns.indexOf(columnName);

		if (rowCount > 2) {
			List<Map<String, CellEntity>> rows = new ArrayList<>();
			XSSFRow row = this.sheet.getRow(1);
			String groupKey = row.getCell(groupIndex).getRawValue();
			rows.add(this.readRow(columns, row));

			for (int i = 2; i < rowCount; i++) {
				row = this.sheet.getRow(i);
				if (row.getCell(groupIndex).getRawValue() != groupKey) {
					groups.put(groupKey, rows);
					groupKey = row.getCell(groupIndex).getRawValue();
					rows = new ArrayList<>();
				}
				rows.add(this.readRow(columns, row));
			}

			if (groupKey != null) {
				groups.put(groupKey, rows);
			}
		}

		return groups;
	}

}
