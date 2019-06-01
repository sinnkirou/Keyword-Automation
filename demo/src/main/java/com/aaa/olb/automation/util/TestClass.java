package com.aaa.olb.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aaa.olb.automation.behaviors.BehaviourAnalysis;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.listeners.ScreenScr;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.testng.BaseTestClass;
import com.aaa.olb.automation.utils.DeleteFile;
import com.aaa.olb.automation.utils.SystemProperty;

public class TestClass extends BaseTestClass {

	protected String previousPage = "";

	protected String currentPage = "";
	
	@BeforeSuite
	public void beforeSuite() {
		String cyrPatn = SystemProperty.getWorkingDir();
		if(SystemProperty.isWindows()){  
			DeleteFile.delAllFile(cyrPatn + "\\" + Constant.Failed_Testcases_Screentshots_Dir);
			//DeleteFile.delAllFile(cyrPatn + "\\" + Constant.Toverify_Testcases_Screenshots_Dir);
		}else {
			DeleteFile.delAllFile(cyrPatn + "/" + Constant.Failed_Testcases_Screentshots_Dir);
			//DeleteFile.delAllFile(cyrPatn + "/" + Constant.Toverify_Testcases_Screenshots_Dir);
		}
	}

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
			if(ts.getActionKeyWord().equals("takescreen")) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String timestamp = df.format(new Date());
				String takescreen = ts.getTestCaseID() + "_" + timestamp;
				takescreen(takescreen, Constant.Toverify_Testcases_Screenshots_Dir);
			}
		}
	}

	public Boolean pageNavigated(String currentPageName) {
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
