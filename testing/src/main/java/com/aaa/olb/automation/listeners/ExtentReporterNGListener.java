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

import com.aaa.olb.automation.log.Log;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ReporterType;

public class ExtentReporterNGListener implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// true, to replace existing report.
		String workingDir = System.getProperty("user.dir");
		ExtentManager.getReporter().startReporter(ReporterType.DB, workingDir + "\\ExtentReports\\Extent.html");

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
					test.log(status, test
							.addScreenCapture("../screenshots/" + testName + "_" + result.getStartMillis() + ".png"));
					test.log(status, result.getThrowable());
					Log.error(testName + ">>>>>>>>>>> \r" + result.getThrowable().toString());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
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