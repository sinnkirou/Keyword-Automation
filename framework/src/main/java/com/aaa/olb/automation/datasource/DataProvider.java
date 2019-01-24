package com.aaa.olb.automation.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;

public abstract class DataProvider {
	
	protected List<Map<String, CellEntity>> data = new ArrayList<>();

	public List<Map<String, CellEntity>> getData() {
		return data;
	}

}
