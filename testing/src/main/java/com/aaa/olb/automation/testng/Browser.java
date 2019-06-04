package com.aaa.olb.automation.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.SystemProperty;

public abstract class Browser {

	private WebDriver driver;

	public Browser(String driverPath, String remoteHub) {
		if(SystemProperty.isWindows()){  
			driverPath+=".exe";
		}  
		this.driver = this.initialize(driverPath, remoteHub);
	}

	protected abstract WebDriver initialize(String driverPath, String remoteHub);

	public SearchContext getDriver() {
		return driver;
	}

	public void open() {
		try {
			driver.manage().timeouts().implicitlyWait(RuntimeSettings.getInstance().getImplicitTimeout(), TimeUnit.SECONDS);

			driver.manage().timeouts().pageLoadTimeout(RuntimeSettings.getInstance().getRedirectTimeout(),TimeUnit.SECONDS);

			driver.manage().timeouts().setScriptTimeout(RuntimeSettings.getInstance().getTimeout(),
					TimeUnit.SECONDS);
			
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

	public void navigate(String site) {
		try {
			driver.get(site);
		} catch (TimeoutException ex) {
			ex.printStackTrace();
			Log.error(ex.getLocalizedMessage());
		}
	}

	public void close() {
		driver.close();
	}

}
