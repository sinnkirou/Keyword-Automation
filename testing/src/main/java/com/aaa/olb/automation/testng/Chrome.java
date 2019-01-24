package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Chrome extends Browser {

	public Chrome(String remoteHub) throws MalformedURLException {
		super("./driverLib/chromedriver.exe", remoteHub);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub) throws MalformedURLException {
		if (remoteHub.toUpperCase().equals("N/A")) {
			File chromeDriver = new File(driverPath);
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			return new ChromeDriver();
		} else {
			return new RemoteWebDriver(new URL(remoteHub), DesiredCapabilities.chrome());
		}
	}

}
