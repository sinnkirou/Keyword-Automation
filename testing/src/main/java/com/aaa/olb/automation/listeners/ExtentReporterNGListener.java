package com.aaa.olb.automation.listeners;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aaa.olb.automation.utils.FileUtils;
import com.aaa.olb.automation.utils.SystemProperty;
import com.aaa.olb.automation.utils.TestHelper;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ReporterType;

public class ExtentReporterNGListener implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		String workingDir = SystemProperty.getWorkingDir();
		ExtentManager.getReporter().startReporter(ReporterType.DB, workingDir + "/ExtentReports/Extent.html");

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		ExtentManager.getReporter().flush();
		ExtentManager.getReporter().close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				String testName = result.getMethod().getMethodName();
				if (result.getTestContext().getName() != null) {
					testName = result.getTestContext().getName();
				}
				ExtentTest test = ExtentTestManager.startTest(testName, "");

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					String filename = TestHelper.getScreentshotFileName(testName, test.getEndedTime());
					String filePath = TestHelper.getScreentshotFilePath(TestHelper.Failed_Testcases_Screentshots_Dir,
							filename);
					/*
					 * extentReport 加载test case失败截图
					 */
					test.log(status, test.addScreenCapture(filePath));
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
					List<String> paths = FileUtils.getFilePaths(TestHelper.getToVerifyScreenshotsPath(), testName);
					for (String path : paths) {
						/*
						 * extentReport 加载test case需要验证的截图
						 */
						test.log(status, test.addScreenCapture(path));
					}
				}

				ExtentTestManager.endTest();
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}