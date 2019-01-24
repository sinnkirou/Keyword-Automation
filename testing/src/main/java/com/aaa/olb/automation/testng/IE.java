package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IE extends Browser {

	public IE(String remoteHub) throws MalformedURLException {
		super("./driverLib/IEDriverServer.exe", remoteHub);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub) throws MalformedURLException {
		if (remoteHub.toUpperCase().equals("N/A")) {
			File ieDriver = new File(driverPath);
			System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
			return new InternetExplorerDriver();
		} else {
			return new RemoteWebDriver(new URL(remoteHub), DesiredCapabilities.internetExplorer());
		}
	}

}
