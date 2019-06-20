package com.aaa.olb.automation.testcaseExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.TestNG;
import com.aaa.olb.automation.configuration.TestCaseEntity;
import com.aaa.olb.automation.listeners.AnnotationTransformer;
import com.aaa.olb.automation.listeners.ExtentReporterNGListener;
import com.aaa.olb.automation.testng.SmartTestContext;
import com.aaa.olb.automation.testng.TestCaseWrapper;
import com.aaa.olb.automation.testng.TestClassWrapper;
import com.aaa.olb.automation.testng.TestSuiteWrapper;
import com.aaa.olb.automation.util.TestCaseGenerator;
import com.aaa.olb.automation.util.TestClass;
import com.aaa.olb.automation.util.TestngListener;
import com.aaa.olb.automation.utils.SystemProperty;
import com.aaa.olb.automation.utils.TestHelper;

public class TestCaseKeyWord {

	/**
	 * entry point
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new TestCaseKeyWord().run();
	}

	@SuppressWarnings("deprecation")
	public void run() throws Exception {
		DOMConfigurator.configure(SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator() + "log4j.xml");
		/*
		 * generate testcases from excel file
		 */
		String filePath = SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator()
				+ TestHelper.TestData_Dir_Name + SystemProperty.getFileSeparator() + TestHelper.TS_File_Name;
		Map<String, TestCaseEntity> testCaseEntities = new TestCaseGenerator().getTestCases(filePath);

		/*
		 * XmlSuite > XmlTest > XmlClass
		 * 
		 * XmlTest对应一个test case
		 */
		TestSuiteWrapper testSuite = new TestSuiteWrapper(TestHelper.TS_File_Name);
		List<TestCaseWrapper> testCases = new ArrayList<>();
		int index = 0;
		for (String key : testCaseEntities.keySet()) {
			TestCaseEntity tc = testCaseEntities.get(key);
			TestCaseWrapper testCase = new TestCaseWrapper(testSuite, tc, index++);
			/*
			 * notice we put TestClass into the TestClassWrapper, each action process is
			 * defined inside TestClass, by running the testng suite will perform the
			 * corresponding actions performed on website
			 */
			TestClassWrapper testClass = new TestClassWrapper(TestClass.class);
			testCase.setTestCase(Arrays.asList(testClass));
			testCases.add(testCase);
		}

		Collections.sort(testCases);
		testSuite.setSuite(testCases);
		SmartTestContext.getInstance().addTestSuite(testSuite.getName(), testSuite);

		TestNG testng = new TestNG();
		testng.setXmlSuites(Arrays.asList(testSuite.getSuite()));
		testng.addListener(new TestngListener());
		testng.addListener(new AnnotationTransformer());
		testng.addListener(new ExtentReporterNGListener());
		testng.run();
	}
}
