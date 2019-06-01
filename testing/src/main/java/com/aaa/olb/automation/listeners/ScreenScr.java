package com.aaa.olb.automation.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.SystemProperty;

public class ScreenScr {
	public static void getScreen(TakesScreenshot driver, String filename, String dir) {

		String cyrPatn = SystemProperty.getWorkingDir();

		File scrfile = driver.getScreenshotAs(OutputType.FILE);

		try {
			if(SystemProperty.isWindows()){  
				FileUtils.copyFile(scrfile, new File(cyrPatn + "\\"+ dir +"\\" + filename + ".png"));
			}else {
				FileUtils.copyFile(scrfile, new File(cyrPatn + "/"+ dir +"/" + filename + ".png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("GetScreenshot Fail");
			Log.error("GetScreenshot Fail");
		} finally {
			String message = "GetScreenshot Successful" + cyrPatn + "/"+ dir +"/" + filename + ".png";
			System.out.println(message);
			Log.info(message);
		}

	}
}
