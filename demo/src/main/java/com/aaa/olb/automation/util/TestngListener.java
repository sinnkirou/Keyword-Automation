package com.aaa.olb.automation.util;

import org.testng.ITestResult;

import com.aaa.olb.automation.listeners.BaseTestngListener;
import com.aaa.olb.automation.utils.TestHelper;

public class TestngListener extends BaseTestngListener {
	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		takeScreenShot(tr);
	}

	private void takeScreenShot(ITestResult tr) {
		TestClass baseTestcase = (TestClass) tr.getInstance();
		String filename = TestHelper.getScreentshotFileName(getTestCaseName(tr));
		baseTestcase.takescreen(filename, TestHelper.Failed_Testcases_Screentshots_Dir);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		takeScreenShot(tr);
	}
}
