package com.aaa.olb.automation.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aaa.olb.automation.log.LoggerHelper;
import com.aaa.olb.automation.testng.BaseTestClass;

public abstract class BaseTestngListener extends TestListenerAdapter {
	protected Logger logger = Logger.getLogger("TestngListener");

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(LoggerHelper.getPrefix() + "【" + getTestCaseName(tr) + " Start】");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(LoggerHelper.getPrefix() + "【" + getTestCaseName(tr) + " Failure】");
		String message = tr.getThrowable().toString();
		logger.error(LoggerHelper.getPrefix() + message);
		System.out.println(LoggerHelper.formatConsoleLog("ERROR") + message);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(LoggerHelper.getPrefix() + "【" + getTestCaseName(tr) + " Skipped】");
		String message = tr.getThrowable().toString();
		logger.error(LoggerHelper.getPrefix() + message);
		System.out.println(LoggerHelper.formatConsoleLog("ERROR") + message);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(LoggerHelper.getPrefix() + "【" + getTestCaseName(tr) + " Success】");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
	}

	protected String getTestCaseName(ITestResult tr) {
		/*
		if (tr.getTestContext() != null && tr.getTestContext().getName() != null) {
			return tr.getTestContext().getName();
		} else {
			return tr.getName();
		}
		*/
		
		BaseTestClass baseTestcase = (BaseTestClass) tr.getInstance();
		return baseTestcase.tc.getTestCaseID();
	}
}
