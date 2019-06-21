package com.aaa.olb.automation.util;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aaa.olb.automation.behaviors.BehaviourAnalysis;
import com.aaa.olb.automation.configuration.BrowserType;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.listeners.ScreenScr;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.log.LoggerHelper;
import com.aaa.olb.automation.testng.BaseTestClass;
import com.aaa.olb.automation.utils.FileUtils;
import com.aaa.olb.automation.utils.TestHelper;

public class TestClass extends BaseTestClass {

	protected String previousPage = "";

	protected String currentPage = "";

	@BeforeSuite
	public void beforeSuite() {
		FileUtils.delAllFile(TestHelper.getFailedScreenshotsPath());
		FileUtils.delAllFile(TestHelper.getToVerifyScreenshotsPath());
	}

	@BeforeMethod
	public void beforeMethod() {
		this.previousPage = "";
		this.currentPage = "";
	}

	/**
	 * BehaviourAnalysis.action performs each action by test step
	 * 
	 * @throws Throwable
	 */
	@Test()
	@Parameters()
	public void testMethod() {
		long id = Thread.currentThread().getId();
		String msg = String.format("Testcase %s is running on thread %d", this.tc.getTestCaseID(), id);
		Log.info(msg);
		System.out.println(this.tc.getTestCaseID() + " with thread id: " + id);

		BehaviourAnalysis analyser = new BehaviourAnalysis();
		for (TestStepEntity ts : this.tc.getTestSteps()) {
			Boolean initializePage = pageNavigated(ts.getPageName().toString());
			Object result = null;
			try {
				result = analyser.action(browser.getDriver(), ts, initializePage,
						this.tc.getPageRepository().getPage(ts.getPageName()));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getLocalizedMessage());
				Assert.fail(e.getLocalizedMessage());
			}

			if (ts.getActionKeyWord() != null) {
				handleAssert(result, ts);
				handleGlobalAction(ts);
			}
		}
	}

	private void handleAssert(Object result, TestStepEntity ts) {
		/*
		 * with [value] or [text] etc defined in test step, it means we should compare
		 * the value on real value with expected value
		 */
		if (result != null && ts.getActionKeyWord().contains("[")) {
			result = result.toString().replaceAll("\\u00a0|\\s*", "").toUpperCase();
			String expect = ts.getValue().replaceAll("\\u00a0|\\s*", "").toUpperCase();
			if (tc.getEnvironmentVariable().getBrowserType().equals(BrowserType.FIREFOX)
					&& ts.getActionKeyWord().contains("color")) {
				expect = expect.substring(expect.indexOf('(') + 1, expect.lastIndexOf(','));
				expect = "RGB(" + expect + ")";
			}

			if (ts.getActionKeyWord().contains("[is")) {
				Assert.assertTrue(result.equals(expect.equals("FALSE") ? "FALSE" : "TRUE"));
			} else {
				Assert.assertEquals(result, expect);
			}
			Log.info(ts.getTargetName() + " is displayed as expected: " + ts.getValue());
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + ts.getTargetName()
					+ " is displayed as expected: " + ts.getValue());
		}
	}

	private void handleGlobalAction(TestStepEntity ts) {
		if (ts.getTargetName().trim().length() == 0)
			if (ts.getActionKeyWord().toLowerCase().trim().equals(SystemConstants.BEHAVIOR_TAKE_SCREENSHOT)) {
				takescreen(TestHelper.getScreentshotFileName(ts.getTestCaseID()),
						TestHelper.To_Verify_Testcases_Screenshots_Dir);
			} else if (ts.getActionKeyWord().toLowerCase().trim().equals(SystemConstants.BEHAVIOR_REFRESH)) {
				this.browser.refresh();
				TestHelper.threadSleep();
			} else if (ts.getActionKeyWord().toLowerCase().trim()
					.equals(SystemConstants.BEHAVIOR_THREAD_SLEEP_BY_MINUTES)) {
				TestHelper.threadSleepByMinutes(ts.getValue());
			}
	}

	private Boolean pageNavigated(String currentPageName) {
		previousPage = currentPage;
		currentPage = currentPageName;
		if (!previousPage.equals(currentPage)) {
			return true;
		}
		return false;
	}

	public void takescreen(String filename, String dir) {
		if (this.browser != null) {
			SearchContext driver = this.browser.getDriver();
			ScreenScr.getScreen((TakesScreenshot) driver, filename, dir);
		}
	}
}
