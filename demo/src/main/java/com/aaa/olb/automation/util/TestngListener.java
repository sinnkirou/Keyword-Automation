package com.aaa.olb.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timestamp = df.format(new Date());
		baseTestcase.takescreen(getTestCaseName(tr) + "_" + timestamp, Constant.Failed_Testcases_Screentshots_Dir);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		takeScreenShot(tr);
	}
}
