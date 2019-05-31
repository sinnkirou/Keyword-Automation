package com.aaa.olb.automation.util;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aaa.olb.automation.behaviors.BehaviourAnalysis;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.listeners.ScreenScr;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.testng.BaseTestClass;

public class TestClass extends BaseTestClass {

	protected String previousPage = "";

	protected String currentPage = "";

	@BeforeMethod
	public void beforeMethod() {
		this.previousPage = "";
		this.currentPage = "";
	}

	/*
	 * BehaviourAnalysis.action performs each action by test step
	 * */
	@Test()
	@Parameters()
	public void testMethod() throws Throwable {
		for (TestStepEntity ts : this.tc.getTestSteps()) {
			Boolean initializePage = pageNavigated(ts.getPageName().toString());
			Object result = BehaviourAnalysis.action(browser.getDriver(), ts, initializePage);
			
			/*
			 * with [value] or [text] etc defined in test step, it means we should compare the value on real value with expected value
			 * */
			if (result != null && ts.getActionKeyWord().contains("[")) {
				result = result.toString().replaceAll("\\u00a0|\\s*", "");
				String expect = ts.getValue().replaceAll("\\u00a0|\\s*", "");
				Assert.assertEquals(result, expect);
				Log.info(ts.getTargetName() + " is displayed as expected: " + ts.getValue());
				System.out.println(ts.getTargetName() + " is displayed as expected: " + ts.getValue());
			}
		}
	}

	public Boolean pageNavigated(String currentPageName) throws Exception {
		previousPage = currentPage;
		currentPage = currentPageName;
		if (!previousPage.equals(currentPage)) {
			return true;
		}
		return false;
	}

	public void takescreen(String filename) {
		if (this.browser != null) {
			SearchContext driver = this.browser.getDriver();
			ScreenScr.getScreen((TakesScreenshot) driver, filename);
		}
	}
}
