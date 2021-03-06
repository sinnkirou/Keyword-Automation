package com.aaa.olb.automation.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.configuration.PageModelEntity;

public class PageModelProvider {

	/**
	 * get PageModel Entities from DataProvider
	 * 
	 * @param provider
	 * @return
	 */
	public static List<PageModelEntity> read(DataProvider provider) {

		List<PageModelEntity> pageModels = new ArrayList<>();
		List<Map<String, CellEntity>> sources = provider.getData();

		for (Map<String, CellEntity> source : sources) {
			PageModelEntity entity = new PageModelEntity();
			for (String key : source.keySet()) {
				switch (key.toLowerCase()) {
				case ConfigurationOptions.PAGE_MODEL_FIND_BY:
					entity.setFindBy(source.get(key).getValue());
					break;
				case ConfigurationOptions.PAGE_MODEL_FIND_BY_VALUE:
					entity.setFindByValue(source.get(key).getValue());
					break;
				case ConfigurationOptions.PAGE_MODEL_SHOULD_BLUR:
					entity.setShouldBlur(source.get(key).getValue().toLowerCase() == "true");
					break;
				case ConfigurationOptions.PAGE_MODEL_SHOULD_DELAY:
					entity.setShouldDelay(source.get(key).getValue().toLowerCase() == "true");
					break;
				case ConfigurationOptions.PAGE_MODEL_SHOULD_WAIT:
					entity.setShouldWait(source.get(key).getValue().toLowerCase() == "true");
					break;
				case ConfigurationOptions.PAGE_MODEL_TARGET_NAME:
					entity.setTargetName(source.get(key).getValue());
					break;
				case ConfigurationOptions.PAGE_MODEL_FIELD_TYPE:
					entity.setFiledType(source.get(key).getValue());
					break;
				default:
					break;
				}
			}
			pageModels.add(entity);
		}
		return pageModels;
	}
}
