package com.aaa.olb.automation.util;

import org.testng.ITestResult;

import com.aaa.olb.automation.listeners.BaseTestngListener;

public class TestngListener extends BaseTestngListener {
	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		takeScreenShot(tr);
	}

	private void takeScreenShot(ITestResult tr) {
		TestClass baseTestcase = (TestClass) tr.getInstance();
		baseTestcase.takescreen(getTestCaseName(tr) + "_" + tr.getStartMillis());
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		takeScreenShot(tr);
	}
}
