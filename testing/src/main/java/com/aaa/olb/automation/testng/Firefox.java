package com.aaa.olb.automation.testng;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aaa.olb.automation.log.Log;

public class Firefox extends Browser {

	public Firefox(String remoteHub, Boolean headless) {
		super("./driverLib/geckodriver", remoteHub, headless);
	}

	@Override
	protected WebDriver initialize(String driverPath, String remoteHub, Boolean headless) {
		if (remoteHub.toUpperCase().equals("N/A")) {
			File firefoxDriver = new File(driverPath);
			System.setProperty("webdriver.gecko.driver", firefoxDriver.getAbsolutePath());
			FirefoxOptions option = new FirefoxOptions();
			option.setHeadless(headless);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("intl.accept_languages", "zh-cn");
			option.setProfile(profile);

			return new FirefoxDriver(option);
		} else {
			try {
				return new RemoteWebDriver(new URL(remoteHub), DesiredCapabilities.firefox());
			} catch (MalformedURLException e) {
				Log.error(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

}
