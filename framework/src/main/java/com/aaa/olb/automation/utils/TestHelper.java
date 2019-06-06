package com.aaa.olb.automation.utils;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.log.Log;

public class TestHelper {

	public static void threadSleep() {
		try {
			Thread.sleep(RuntimeSettings.getInstance().getWaitOrDelayTimeout() * 1000);
			Log.info("waited: " + RuntimeSettings.getInstance().getWaitOrDelayTimeout());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}
	
	public static void threadSleep(long time) {
		try {
			Thread.sleep(time);
			Log.info("waited: " + time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}
}
