package com.aaa.olb.automation.datasource;

import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;

public interface DataGroupBy {
	
	Map<String, List<Map<String, CellEntity>>> groupBy(String columnName) throws Exception;

}
