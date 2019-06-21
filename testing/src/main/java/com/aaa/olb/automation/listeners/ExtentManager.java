package com.aaa.olb.automation.listeners;

import com.aaa.olb.automation.utils.TestHelper;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			extent = new ExtentReports(TestHelper.getExtendReportFilePath(), true, DisplayOrder.NEWEST_FIRST);
		}
		return extent;
	}
}
