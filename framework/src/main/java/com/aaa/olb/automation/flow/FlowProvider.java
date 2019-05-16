package com.aaa.olb.automation.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.datasource.DataProvider;

public class FlowProvider {

	/*
	 * get flows entities from flow sheet data
	 * */
	public static List<FlowDeclaration> read(DataProvider provider) {
		// TODO Auto-generated method stub
		List<Map<String, CellEntity>> sources = provider.getData();
		List<FlowDeclaration> flows =new ArrayList<>();
		
		for(Map<String, CellEntity> source: sources) {
			FlowDeclaration flow =new FlowDeclaration();
			for(String key: source.keySet()) {
				switch(key.toLowerCase()) {
				case ConfigurationOptions.FLOW_OPTION_NAME:{
					flow.setName(source.get(key).getValue());
					break;
				}
				case ConfigurationOptions.FLOW_OPTION_PAGE:{
					flow.setPage(source.get(key).getValue());
					break;
				}
				case ConfigurationOptions.FLOW_OPTION_TEMPLATE:{
					flow.setTemplate(source.get(key).getValue().toLowerCase() == "true");
					break;
				}
				default: break;
				}
			}
			
			flows.add(flow);
		}
		return flows;
	}

}
