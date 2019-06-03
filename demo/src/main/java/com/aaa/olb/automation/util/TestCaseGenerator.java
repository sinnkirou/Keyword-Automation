package com.aaa.olb.automation.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.configuration.TestCaseEntity;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.customizedFlow.TemplateProvider;
import com.aaa.olb.automation.datasource.DataProvider;
import com.aaa.olb.automation.datasource.ExcelProvider;
import com.aaa.olb.automation.datasource.RuntimeSettingProvider;
import com.aaa.olb.automation.datasource.TestCaseProvider;
import com.aaa.olb.automation.flow.FlowDeclaration;
import com.aaa.olb.automation.flow.FlowProvider;
import com.aaa.olb.automation.flow.FlowTemplate;
import com.aaa.olb.automation.framework.PageRepository;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.pages.PageClazzProvider;
import com.aaa.olb.automation.utils.BaseTestCaseGenerator;
import com.aaa.olb.automation.utils.ExcelUtils;

public class TestCaseGenerator implements BaseTestCaseGenerator {

	/*
	 * param: excel file path
	 * */
	public Map<String, TestCaseEntity> createTestCases(String filePath) {
		DataProvider timeoutProvider = new ExcelProvider(
				ExcelUtils.getSheet(filePath, ConfigurationOptions.CONFIG_SHEET_NAME));
		DataProvider testcaseProvider = new ExcelProvider(
				ExcelUtils.getSheet(filePath, ConfigurationOptions.TEST_CASE_SHEET_NAME));
		DataProvider flowProvider = new ExcelProvider(
				ExcelUtils.getSheet(filePath, ConfigurationOptions.FLOW_SHEET_NAME));

		List<FlowDeclaration> flows = FlowProvider.read(flowProvider);
		Map<String, TestCaseEntity> testCases = TestCaseProvider.read(testcaseProvider);
		RuntimeSettingProvider.read(timeoutProvider);
		List<TestStepEntity> testSteps = getTestStepEntities(flows, filePath);

		testSteps = sortTestSteps(testSteps);
		testCases = appendTestStep(testSteps, testCases);

		return testCases;
	}

	private List<TestStepEntity> sortTestSteps(List<TestStepEntity> testSteps) {
		testSteps.sort(new Comparator<TestStepEntity>() {
			@Override
			public int compare(TestStepEntity ts1, TestStepEntity ts2) {
				return ts1.getTestGroupID().compareTo(ts2.getTestGroupID());
			}

		});
		return testSteps;
	}

	private Map<String, TestCaseEntity> appendTestStep(List<TestStepEntity> testSteps,
			Map<String, TestCaseEntity> testCases) {
		for (TestStepEntity testStep : testSteps) {
			TestCaseEntity tc = testCases.get(testStep.getTestCaseID());
			if (tc != null) {
				tc.getTestSteps().add(testStep);
				testStep = handleParameters(testStep);
				testCases.put(tc.getTestCaseID(), tc);
			}
		}
		return testCases;
	}

	private TestStepEntity handleParameters(TestStepEntity ts) {
		String value = ts.getValue() != null && ts.getValue() != "" ? ts.getValue() : "";

		if (value.indexOf("[Now") >= 0 && value.indexOf("d]") > 0) {
			int i = Integer.valueOf(value.substring(value.indexOf("[Now") + 4, value.indexOf("d]")));
			ts.setValue(new DateHandler().getFormattedDateByDay(i));
		} else if (value.indexOf("[Now") >= 0 && value.indexOf("m]") > 0) {
			int i = Integer.valueOf(value.substring(value.indexOf("[Now") + 4, value.indexOf("m]")));
			ts.setValue(new DateHandler().getFormattedDateByMonth(i));
		} else if (value.indexOf("[Now") >= 0 && value.indexOf("y]") > 0) {
			int i = Integer.valueOf(value.substring(value.indexOf("[Now") + 4, value.indexOf("y]")));
			ts.setValue(new DateHandler().getFormattedDateByYear(i));
		}

		return ts;
	}

	
	/*
	 * get specific test steps by inputting flow entities 
	 * */
	public List<TestStepEntity> getTestStepEntities(List<FlowDeclaration> flows, String filePath){
		List<TestStepEntity> allTestSteps = new ArrayList<>();
		Map<String, List<TestStepEntity>> groups = new HashMap<>();

		for (FlowDeclaration flow : flows) {
			List<TestStepEntity> teststeps = new ArrayList<>();
			String templateName = flow.getName();
			XSSFSheet sheet = ExcelUtils.getSheet(filePath, templateName);

			/*
			 * create page instance that the pageClass will retrieved by name afterwards
			 * */
			try {
				PageRepository.getInstance().addPage(flow.getPage(), PageClazzProvider.getPageClazz(flow.getPage()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getCause().getMessage());
			}

			/*
			 * when the template is enabled, 
			 * */
			if (flow.isTemplate() && sheet != null) {
				templateName = flow.getName();
				TemplateProvider.addTemplate(flow);
			} else {
				templateName = flow.getPage();
				sheet = ExcelUtils.getSheet(filePath, templateName);
			}

			if (groups.get(templateName) == null || groups.get(templateName).isEmpty()) {
				DataProvider provider = null;
				try {
					provider = new ExcelProvider(sheet);
				} catch (Exception ex) {
					System.out.println("please check whether the sheet " + templateName + " is filled suitable");
					Log.error(ex.getMessage());
					throw ex;
				}

				FlowTemplate template = TemplateProvider.getFlowTemplate(templateName);
				teststeps = template.translate(provider, flow.getPage());
				groups.put(templateName, teststeps);
				allTestSteps.addAll(teststeps);
			}
		}

		return allTestSteps;
	}
}
