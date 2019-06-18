package com.aaa.olb.automation.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.log.LoggerHelper;
import com.aaa.olb.automation.utils.SystemProperty;

public class ScreenScr {
	public static void getScreen(TakesScreenshot driver, String filename, String dir) {

		File scrfile = driver.getScreenshotAs(OutputType.FILE);
		String filepath = SystemProperty.getWorkingDir() + SystemProperty.getFileSeparator() + dir
				+ SystemProperty.getFileSeparator() + filename + ".png";

		try {
			FileUtils.copyFile(scrfile, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(LoggerHelper.formatConsoleLog("ERROR") + "GetScreenshot Fail");
			Log.error("GetScreenshot Fail" + e.getLocalizedMessage());
		} finally {
			String message = "GetScreenshot Successful" + filepath;
			System.out.println(LoggerHelper.formatConsoleLog("INFO") + message);
			Log.info(message);
		}

	}
}
