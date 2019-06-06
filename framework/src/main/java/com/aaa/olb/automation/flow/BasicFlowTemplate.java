package com.aaa.olb.automation.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.datasource.DataProvider;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.GenerateValueByRegex;

public class BasicFlowTemplate implements FlowTemplate {
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Basic";
	}

	public List<TestStepEntity> translate(DataProvider provider, String pageName) {
		List<Map<String, CellEntity>> sources = provider.getData();
		List<TestStepEntity> results = new ArrayList<>();

		for (Map<String, CellEntity> source : sources) {
			TestStepEntity testStep = new TestStepEntity();
			testStep.setPageName(pageName);

			for (String key : source.keySet()) {
				switch (key.toLowerCase()) {
				case ConfigurationOptions.TEMPLATE_OPTION_ID:
					testStep.setTestCaseID(source.get(key).getValue());
					break;
				case ConfigurationOptions.TEMPLATE_OPTION_TARGET_NAME:
					testStep.setTargetName(source.get(key).getValue());
					break;
				case ConfigurationOptions.TEMPLATE_OPTION_ACTION:
					testStep.setActionKeyWord(source.get(key).getValue());
					break;
				case ConfigurationOptions.TEMPLATE_OPTION_VALUE:
					String value = source.get(key).getValue();
					if (value.indexOf("[regex]") >= 0) {
						try {
							value = GenerateValueByRegex.getRandom(value);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.error(e.getLocalizedMessage());
						}
					}
					testStep.setValue(value);
					break;
				case ConfigurationOptions.TEMPLATE_GROUP_ID:
					testStep.setTestGroupID(source.get(key).getValue());
					break;
				default:
					break;
				}

			}
			results.add(testStep);

		}

		return results;
	}

}
