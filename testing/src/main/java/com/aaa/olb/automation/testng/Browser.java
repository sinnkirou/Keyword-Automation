package com.aaa.olb.automation.testng;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.configuration.RuntimeSettings;

public abstract class Browser {

	private WebDriver driver;

	public Browser(String driverPath, String remoteHub) throws MalformedURLException{
		this.driver = this.initialize(driverPath, remoteHub);
	}

	protected abstract WebDriver initialize(String driverPath, String remoteHub) throws MalformedURLException;

	public SearchContext getDriver() {
		return driver;
	}

	public void open() {
		try {
			driver.manage().timeouts().implicitlyWait(RuntimeSettings.getInstance().getImplicitTimeout(), TimeUnit.SECONDS);

			driver.manage().timeouts().pageLoadTimeout(RuntimeSettings.getInstance().getRedirectTimeout(),TimeUnit.SECONDS);

			driver.manage().timeouts().setScriptTimeout(RuntimeSettings.getInstance().getAsyncTimeout(),
					TimeUnit.SECONDS);
			
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigate(String site) {
		try {
			driver.get(site);
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
	}

	public void close() {
		driver.close();
	}

}
