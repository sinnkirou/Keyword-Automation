package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aaa.olb.automation.log.Log;

public class Chrome extends Browser {

	public Chrome(String remoteHub) {
		super("./driverLib/chromedriver", remoteHub);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub) {
		if (remoteHub.toUpperCase().equals("N/A")) {
			File chromeDriver = new File(driverPath);
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			return new ChromeDriver();
		} else {
			try {
				return new RemoteWebDriver(new URL(remoteHub), DesiredCapabilities.chrome());
			} catch (MalformedURLException e) {
				Log.error(e.getCause().getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

}
