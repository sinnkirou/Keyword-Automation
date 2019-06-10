package com.aaa.olb.automation.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.aaa.olb.automation.configuration.EnvironmentVariable;
import com.aaa.olb.automation.configuration.TestCaseEntity;
import com.aaa.olb.automation.listeners.RetryCounter;
import com.aaa.olb.automation.log.Log;

/**
 * default browser operations are defined here and then inherited by child class
 *
 */
public abstract class BaseTestClass {

	protected int Index;

	protected Browser browser;

	protected TestSuiteWrapper suite;

	protected TestCaseEntity tc;

	@BeforeClass
	public void beforeClass() {
		RetryCounter.getInstance().reset();
	}

	@BeforeMethod
	@Parameters({ "index", "suiteName", "testName" })
	public void init(String index, String suiteName, String testName) {
		this.suite = SmartTestContext.getInstance().getTestSuite(suiteName);
		this.Index = Integer.valueOf(index);
		this.tc = this.suite.getTestCaseWrapper(this.Index).getTestCaseEntity();

		EnvironmentVariable env = this.tc.getEnvironmentVariable();
		this.browser = BrowserFactory.getBrowser(env);
		this.browser.open();
		this.browser.navigate(env.getSiteURL());

		Log.startTestCase(this.tc.getTestCaseID());
	}

	@AfterMethod
	public void clear() {
		//this.browser.close();
		Log.endTestCase(this.tc.getTestCaseID());
	}

}
