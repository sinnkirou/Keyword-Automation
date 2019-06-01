package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aaa.olb.automation.log.Log;

public class Firefox extends Browser {

	public Firefox(String remoteHub) {
		super("./driverLib/geckodriver", remoteHub);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub) {
		if (remoteHub.toUpperCase().equals("N/A")) {
			File firefoxDriver = new File(driverPath);
			System.setProperty("webdriver.gecko.driver", firefoxDriver.getAbsolutePath());
			return new FirefoxDriver();
		} else {
			try {
				return new RemoteWebDriver(new URL(remoteHub), DesiredCapabilities.firefox());
			} catch (MalformedURLException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

}
