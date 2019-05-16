package com.aaa.olb.automation.customizedFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.datasource.DataProvider;
import com.aaa.olb.automation.flow.FlowTemplate;
import com.aaa.olb.automation.util.MapKeyComparator;

/*
 * the default flow template, which transforms test steps sheet data into test step entities
 * the output test steps are sorted and grouped here
 * the hidden actions will be appended into each flow,
 * 
 * eg: GoNext button is clicked by default in SimpleFlowTemplate at the end of each flow
 * the default hidden action will be inherited by child template and able to be override.
 * */
public abstract class SimpleFlowTemplate implements FlowTemplate {
	protected String pageName;
	protected String testcaseID;
	protected String groupID;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Simple";
	}

	@Override
	public List<TestStepEntity> translate(DataProvider provider, String pageName) {
		// TODO Auto-generated method stub
		List<Map<String, CellEntity>> sources = provider.getData();
		List<TestStepEntity> results = new ArrayList<>();

		for (Map<String, CellEntity> source : sources) {
			Map<Integer, TestStepEntity> originalSteps = new HashMap<>();
			this.pageName = pageName;

			for (String key : source.keySet()) {
				switch (key.toLowerCase()) {
				case ConfigurationOptions.TEMPLATE_OPTION_ID:
					this.testcaseID = source.get(key).getValue();
					break;
				case ConfigurationOptions.TEMPLATE_GROUP_ID:
					this.groupID = source.get(key).getValue();
					break;
				default:
					TestStepEntity testStep = new TestStepEntity();
					testStep.setTargetName(key);
					testStep.setValue(source.get(key).getValue());
					testStep.setPageName(this.pageName);

					int index = source.get(key).getIndex();
					if (!testStep.getValue().equals("N/A"))
						originalSteps.put(index, testStep);
					break;
				}
			}

			originalSteps = sortMapByKey(originalSteps);
			results = appendInfoByRow(originalSteps, results);
		}

		return results;
	}

	/*
	 * GoNext button is clicked by default in SimpleFlowTemplate
	 * */
	public List<TestStepEntity> appendInfoByRow(Map<Integer, TestStepEntity> originalSteps,
			List<TestStepEntity> results) {
		List<TestStepEntity> teststeps = results;

		if (originalSteps != null && !originalSteps.isEmpty()) {
			for (Integer key : originalSteps.keySet()) {
				TestStepEntity originalStep = originalSteps.get(key);

				originalStep.setTestCaseID(testcaseID);
				originalStep.setTestGroupID(groupID);
				teststeps.add(originalStep);
			}
		}

		teststeps = appendButton("GoNext", teststeps);
		return teststeps;
	}

	protected List<TestStepEntity> appendButton(String targetName, List<TestStepEntity> teststeps) {
		TestStepEntity testStep = new TestStepEntity();
		testStep.setTargetName(targetName);
		testStep.setPageName(this.pageName);
		testStep.setTestCaseID(testcaseID);
		testStep.setTestGroupID(groupID);
		teststeps.add(testStep);

		return teststeps;
	}

	public static Map<Integer, TestStepEntity> sortMapByKey(Map<Integer, TestStepEntity> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<Integer, TestStepEntity> sortMap = new TreeMap<Integer, TestStepEntity>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}

}
