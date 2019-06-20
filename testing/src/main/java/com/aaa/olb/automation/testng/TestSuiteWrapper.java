package com.aaa.olb.automation.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

import com.aaa.olb.automation.configuration.RuntimeSettings;

public class TestSuiteWrapper {

	private String name;

	private XmlSuite suite;
	
	private List<TestCaseWrapper> testCases;

	public TestSuiteWrapper(String name) {
		this.suite = new XmlSuite();
		this.name = name;
	}
	
	public TestCaseWrapper getTestCaseWrapper(int index) {
		return this.testCases.get(index);
	}

	public String getName() {
		return name;
	}

	public XmlSuite getSuite() {
		return this.suite;
	}

	public void setSuite(List<TestCaseWrapper> testCases) {
		this.suite.setName(this.name);
		if (RuntimeSettings.getInstance().isParallel())
			this.suite.setParallel(ParallelMode.TESTS);
		this.suite.setThreadCount(RuntimeSettings.getInstance().getThreadCount());
		List<XmlTest> tests = new ArrayList<>();
		this.testCases = testCases;
		if (!testCases.isEmpty()) {
			for (int i = 0; i < testCases.size(); i++) {
				tests.add(testCases.get(i).getTestCase());
			}
		}
		this.suite.setTests(tests);
	}

}
