package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aaa.olb.automation.log.Log;

public class IE extends Browser {

	public IE(String remoteHub) {
		super("./driverLib/IEDriverServer", remoteHub);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub) {
		if (remoteHub.toUpperCase().equals("N/A")) {
			File ieDriver = new File(driverPath);
			System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
			return new InternetExplorerDriver();
		} else {
			try {
				return new RemoteWebDriver(new URL(remoteHub), DesiredCapabilities.internetExplorer());
			} catch (MalformedURLException e) {
				Log.error(e.getCause().getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

}
