package com.aaa.olb.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.log.LoggerHelper;

/**
 * @author ziv
 *
 */
public class TestHelper {

	public static final String TestData_Dir_Name = "testData";

	public static final String TS_File_Name = "testCase.xlsx";

	public static final String Failed_Testcases_Screentshots_Dir = "FailedTestCasesScreenshots";

	public static final String To_Verify_Testcases_Screenshots_Dir = "ToVerifyTestCasesScreenshots";

	public static final String Extent_Report_Dir = "ExtentReports";

	public static final String Extent_Report_File_Name = "Extent.html";
	
	/**
	 * thread sleep for default wait or delay timeout
	 */
	public static void waitOrDealy() {
		try {
			long time = RuntimeSettings.getInstance().getWaitOrDelayTimeout() * 1000;
			String message = "waited for: " + time + " milliseconds";
			Thread.sleep(time);
			Log.info(message);
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * thread sleep for default timeout
	 * 
	 */
	public static void threadSleep() {
		try {
			long time = RuntimeSettings.getInstance().getTimeout() * 1000;
			String message = "sleeped for: " + time + " milliseconds";
			Thread.sleep(time);
			Log.info(message);
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * thread sleep for specific millseconds
	 * @param time
	 */
	public static void threadSleep(long time) {
		try {
			Thread.sleep(time);
			String message = "sleeped for " + time + " milliseconds";
			Log.info(message);
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}
	
	/**
	 * sleep for specific minutes
	 * 
	 */
	public static void threadSleepByMinutes(String parameter) {
		try {
			double minutes = Double.parseDouble(parameter);
			long time = new Double(minutes * 60 * 1000).longValue();
			
			String message1 = "start sleeping for " + parameter + " minutes";
			Log.info(message1);
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + message1);
			
			Thread.sleep(time);
			
			String message2 = "sleeped for " + parameter + " minutes";
			Log.info(message2);
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + message2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * @param testcaseID
	 * @return
	 */
	public static String getScreentshotFileName(String testcaseID) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String timestamp = df.format(new Date());
		String filename = testcaseID + "_" + timestamp;
		return filename;
	}

	/**
	 * @param testcaseID
	 * @param time
	 * @return
	 */
	public static String getScreentshotFileName(String testcaseID, Date time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String timestamp = df.format(time);
		String filename = testcaseID + "_" + timestamp;
		return filename;
	}

	/**
	 * @param dir
	 * @param filename
	 * @return
	 */
	public static String getScreentshotFilePath(String dir, String filename) {
		return SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator() + dir
				+ SystemProperty.getFileSeparator() + filename + ".png";
	}

	public static String getExtendReportFilePath() {
		return SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator() + Extent_Report_Dir
				+ SystemProperty.getFileSeparator() + Extent_Report_File_Name;
	}
	
	public static String getFailedScreenshotsPath() {
		return SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator() + Failed_Testcases_Screentshots_Dir;
	}

	public static String getToVerifyScreenshotsPath() {
		return SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator() + To_Verify_Testcases_Screenshots_Dir;
	}
}
