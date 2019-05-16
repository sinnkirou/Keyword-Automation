package com.aaa.olb.automation.datasource;

import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.configuration.RuntimeSettings;

public class RuntimeSettingProvider {
	
	/*
	 * set runtime setting by timeout config sheet data
	 * */
	public static void read(DataProvider provider) {
		List<Map<String, CellEntity>> sources = provider.getData();
		
		for(Map<String, CellEntity> source : sources) {
			for(String key: source.keySet()) {
				RuntimeSettings.getInstance().set(key, Integer.valueOf(source.get(key).getValue()));
			}
		}
	}

}
