package com.aaa.olb.automation.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public abstract class BaseTestngListener extends TestListenerAdapter {
	protected Logger logger = Logger.getLogger(BaseTestngListener.class);

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info("【" + getTestCaseName(tr) + " Start】");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info("【" + getTestCaseName(tr) + " Failure】");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info("【" + getTestCaseName(tr) + " Skipped】");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info("【" + getTestCaseName(tr) + " Success】");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
	}

	protected String getTestCaseName(ITestResult tr) {
		if (tr.getTestContext() != null && tr.getTestContext().getName() != null) {
			return tr.getTestContext().getName();
		} else {
			return tr.getName();
		}
	}
}
