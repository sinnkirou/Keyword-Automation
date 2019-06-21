package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aaa.olb.automation.log.Log;

public class Chrome extends Browser {

	public Chrome(String remoteHub, Boolean headless) {
		super("./driverLib/chromedriver", remoteHub, headless);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub, Boolean headless) {
		ChromeOptions option = new ChromeOptions();
		option.setHeadless(headless);
		option.addArguments("--lang=zh-CN");
		if (headless)
			option.addArguments("window-size=1920x1080");
		
		if (remoteHub.toUpperCase().equals("N/A")) {
			File chromeDriver = new File(driverPath);
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			return new ChromeDriver(option);
		} else {
			try {
				return new RemoteWebDriver(new URL(remoteHub), option);
			} catch (MalformedURLException e) {
				Log.error(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

}
