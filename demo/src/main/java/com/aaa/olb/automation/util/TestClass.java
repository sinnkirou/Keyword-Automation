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
import com.aaa.olb.automation.configuration.BrowserType;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.listeners.ScreenScr;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.testng.BaseTestClass;
import com.aaa.olb.automation.utils.DeleteFile;
import com.aaa.olb.automation.utils.SystemProperty;
import com.aaa.olb.automation.utils.TestHelper;

public class TestClass extends BaseTestClass {

	protected String previousPage = "";

	protected String currentPage = "";
	
	@BeforeSuite
	public void beforeSuite() {
		String cyrPatn = SystemProperty.getWorkingDir();
		DeleteFile.delAllFile(cyrPatn + SystemProperty.getFileSeparator() + Constant.Failed_Testcases_Screentshots_Dir);
		//DeleteFile.delAllFile(cyrPatn + SystemProperty.getFileSeparator() + Constant.Toverify_Testcases_Screenshots_Dir);
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
		System.out.println(this.tc.getTestCaseID() + " with thread id: "+id);
		
		for (TestStepEntity ts : this.tc.getTestSteps()) {
			Boolean initializePage = pageNavigated(ts.getPageName().toString());
			Object result = null;
			try {
				result = BehaviourAnalysis.action(browser.getDriver(), ts, initializePage, this.tc.getPageRepository().getPage(ts.getPageName()));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getLocalizedMessage());
				break;
			}
			
			/*
			 * with [value] or [text] etc defined in test step, it means we should compare the value on real value with expected value
			 * */
			if(ts.getActionKeyWord() != null) {
				if (result != null && ts.getActionKeyWord().contains("[")) {
					result = result.toString().replaceAll("\\u00a0|\\s*", "").toUpperCase();
					String expect = ts.getValue().replaceAll("\\u00a0|\\s*", "").toUpperCase();
					if(tc.getEnvironmentVariable().getBrowserType().equals(BrowserType.FIREFOX) && ts.getActionKeyWord().contains("color")) {
						expect = expect.substring(expect.indexOf('(')+1, expect.lastIndexOf(','));
						expect = "RGB(" + expect + ")";
					}
					
					if(!ts.getActionKeyWord().toLowerCase().contains("contains")) {
						Assert.assertEquals(result, expect);
					}else {
						Assert.assertTrue(result.toString().contains(expect.toString()));
					}
					Log.info(ts.getTargetName() + " is displayed as expected: " + ts.getValue());
					System.out.println(ts.getTargetName() + " is displayed as expected: " + ts.getValue());
				}
				if(ts.getActionKeyWord().toLowerCase().trim().equals(SystemConstants.BEHAVIOR_TAKE_SCREENSHOT)) {
					//TestHelper.threadSleep(1000);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
					String timestamp = df.format(new Date());
					String filename = ts.getTestCaseID() + "_" + timestamp;
					takescreen(filename, Constant.Toverify_Testcases_Screenshots_Dir);
				}
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
