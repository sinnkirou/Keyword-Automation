package com.aaa.olb.automation.flow;

import java.util.List;

import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.datasource.DataProvider;

public interface FlowTemplate {
	
	String getName();
	
	List<TestStepEntity> translate(DataProvider provider, String pageClazz);
}
