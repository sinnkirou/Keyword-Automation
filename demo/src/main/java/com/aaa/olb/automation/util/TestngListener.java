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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timestamp = df.format(new Date());// new Date()为获取当前系统时间
		baseTestcase.takescreen(getTestCaseName(tr) + "_" + timestamp, "FailedTestCasesScreenshoots");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		takeScreenShot(tr);
	}
}
