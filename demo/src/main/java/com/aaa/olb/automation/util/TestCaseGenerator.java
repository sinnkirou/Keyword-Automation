package com.aaa.olb.automation.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.configuration.PageModelEntity;
import com.aaa.olb.automation.configuration.TestCaseEntity;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.customizedFlow.TemplateProvider;
import com.aaa.olb.automation.datasource.DataProvider;
import com.aaa.olb.automation.datasource.ExcelProvider;
import com.aaa.olb.automation.datasource.PageModelProvider;
import com.aaa.olb.automation.datasource.RuntimeSettingProvider;
import com.aaa.olb.automation.datasource.TestCaseProvider;
import com.aaa.olb.automation.flow.FlowDeclaration;
import com.aaa.olb.automation.flow.FlowProvider;
import com.aaa.olb.automation.flow.FlowTemplate;
import com.aaa.olb.automation.framework.PageRepository;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.BaseTestCaseGenerator;
import com.aaa.olb.automation.utils.ExcelUtils;
import com.aaa.olb.automation.utils.PageClazzProvider;

public class TestCaseGenerator implements BaseTestCaseGenerator {

	private String filePath;

	/**
	 * @param: excel file path
	 * @throws Exception
	 *
	 */
	public Map<String, TestCaseEntity> getTestCases(String filePath) throws Exception {
		this.filePath = filePath;

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
		testCases = createTestcases(testSteps, testCases, flows);

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

	private Map<String, TestCaseEntity> createTestcases(List<TestStepEntity> testSteps,
			Map<String, TestCaseEntity> testCases, List<FlowDeclaration> flows) throws Exception {
		for (TestStepEntity testStep : testSteps) {
			TestCaseEntity tc = testCases.get(testStep.getTestCaseID());
			if (tc != null) {
				tc.getTestSteps().add(testStep);
				testStep = handleParameters(testStep);
				testCases.put(tc.getTestCaseID(), tc);

				for (FlowDeclaration flow : flows) {

					/*
					 * create page instance that the pageClass will retrieved by name afterwards
					 */
					try {
						if (flow.getPage().equals(testStep.getPageName())) {
							PageRepository pageRepository = tc.getPageRepository();
							if (!flow.isExcelModel()) {
								pageRepository.addPage(flow.getPage(),
										PageClazzProvider.getPageClazz(flow.getPage(), Constants.PAGES_PACKAGE_NAME));
							} else {
								String pageName = flow.getPage();
								XSSFSheet pageModel = ExcelUtils.getSheet(this.filePath, pageName + "Model");
								DataProvider provider = new ExcelProvider(pageModel);
								List<PageModelEntity> targets = PageModelProvider.read(provider);
								Class<?> pageClazz = PageClazzProvider.createClazz(Constants.PAGES_PACKAGE_NAME,
										pageName, targets, Constants.COMPONENTS_PACKAGE_NAME);
								pageRepository.addPage(pageName, pageClazz);
							}
							tc.setPageRepository(pageRepository);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Log.error(e.getLocalizedMessage());
						throw e;
					}
				}
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

	/**
	 * get specific test steps by inputting flow entities
	 * 
	 * @throws Exception
	 *
	 */
	public List<TestStepEntity> getTestStepEntities(List<FlowDeclaration> flows, String filePath) throws Exception {
		List<TestStepEntity> allTestSteps = new ArrayList<>();
		Map<String, List<TestStepEntity>> groups = new HashMap<>();

		for (FlowDeclaration flow : flows) {
			List<TestStepEntity> teststeps = new ArrayList<>();
			String templateName = flow.getName();
			XSSFSheet sheet = ExcelUtils.getSheet(filePath, templateName);

			if (flow.isTemplate() && sheet != null) {
				/*
				 * when the template is enabled, get the specific flow sheet, the use the
				 * customized flowTemplate to get teststeps
				 */
				templateName = flow.getName();
				TemplateProvider.addTemplate(flow);
			} else {
				/*
				 * if the template is not enabled, get the basic page sheet, then use the basic
				 * flowTemplate to get teststeps
				 */
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
