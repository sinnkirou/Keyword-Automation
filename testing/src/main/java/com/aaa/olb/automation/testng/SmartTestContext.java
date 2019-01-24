package com.aaa.olb.automation.testng;

import java.util.HashMap;
import java.util.Map;

public class SmartTestContext {

	private static SmartTestContext _instance;

	static {
		_instance = new SmartTestContext();
	}

	public static SmartTestContext getInstance() {
		return _instance;
	}

	private Map<String, TestSuiteWrapper> suites;


	private SmartTestContext() {
		suites = new HashMap<>();
	}

	public TestSuiteWrapper getTestSuite(String suiteName) {
		return this.suites.get(suiteName);
	}

	public void addTestSuite(String suiteName, TestSuiteWrapper suite) {
		this.suites.put(suiteName, suite);
	}
}
