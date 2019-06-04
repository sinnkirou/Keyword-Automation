package com.aaa.olb.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aaa.olb.automation.configuration.RuntimeSettings;

public class RetryAnalyzer implements IRetryAnalyzer {
	/**
	 *
	 *  * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will call
	 * this method every time a test fails. So we can put some code in here to
	 * decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and false
	 * it not.
	 *
	 */
	@Override
	public boolean retry(ITestResult result) {
		int retryLimit = RuntimeSettings.getInstance().getRetry();
		int counter = RetryCounter.getInstance().getCounter();

		if (counter < retryLimit) {
			RetryCounter.getInstance().setCounter(++counter);
			return true;
		}
		return false;
	}
}