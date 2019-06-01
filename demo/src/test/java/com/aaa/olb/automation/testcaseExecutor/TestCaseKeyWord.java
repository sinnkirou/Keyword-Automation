package com.aaa.olb.automation.testcaseExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;

import com.aaa.olb.automation.configuration.TestCaseEntity;
import com.aaa.olb.automation.listeners.AnnotationTransformer;
import com.aaa.olb.automation.listeners.ExtentReporterNGListener;
import com.aaa.olb.automation.util.Constant;
import com.aaa.olb.automation.testng.SmartTestContext;
import com.aaa.olb.automation.testng.TestCaseWrapper;
import com.aaa.olb.automation.testng.TestClassWrapper;
import com.aaa.olb.automation.testng.TestSuiteWrapper;
import com.aaa.olb.automation.util.TestCaseGenerator;
import com.aaa.olb.automation.util.TestClass;
import com.aaa.olb.automation.util.TestngListener;

public class TestCaseKeyWord {

	/*
	 * entry point
	 * */
	public static void main(String[] args) {
		new TestCaseKeyWord().run();
	}

	@SuppressWarnings("deprecation")
	public void run() {
		DOMConfigurator.configure("log4j.xml");
		
		/*
		 * generate testcases from excel file
		 * */
		String filePath = Constant.Path_TestData + Constant.TS_File_Name;
		Map<String, TestCaseEntity> testCaseEntities = new TestCaseGenerator().createTestCases(filePath);

		/*
		 * XmlSuite > XmlTest > XmlClass
		 * */
		TestSuiteWrapper testSuite = new TestSuiteWrapper(Constant.TS_File_Name);
		List<TestCaseWrapper> testCases = new ArrayList<>();
		int index = 0;
		for (String key : testCaseEntities.keySet()) {
			TestCaseEntity tc = testCaseEntities.get(key);
			TestCaseWrapper testCase = new TestCaseWrapper(testSuite, tc, index++);
			/*
			 * notice we put TestClass into the TestClassWrapper,
			 * each action process is defined inside TestClass,
			 * by running the testng suite will perform the corresponding actions performed on website
			 * */
			TestClassWrapper testClass = new TestClassWrapper(TestClass.class);
			testCase.setTestCase(Arrays.asList(testClass));
			testCases.add(testCase);
		}

		testSuite.setSuite(testCases);
		SmartTestContext.getInstance().addTestSuite(testSuite.getName(), testSuite);

		TestNG testng = new TestNG();
		XmlSuite suite = testSuite.getSuite();
		testng.setXmlSuites(Arrays.asList(suite));
		testng.setParallel(ParallelMode.NONE);
		testng.addListener(new TestngListener());
		testng.addListener(new AnnotationTransformer());
		testng.addListener(new ExtentReporterNGListener());
		testng.run();
	}
}
