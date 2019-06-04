package com.aaa.olb.automation.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.log.ActionRepository;
import com.aaa.olb.automation.log.Log;
import com.google.common.base.Function;

public abstract class BasePage extends ActionRepository {

	protected WebDriver driver;

	protected SeleniumContext context;

	/*
	 * using PageFactory to create page instance
	 * */
	public BasePage(WebDriver driver) {
		super();
		this.driver = driver;
		this.context = new SeleniumContext();
		this.context.setDriver(driver);
		this.context.setRepository(this);
		try {
			PageFactory.create(context, this);
		} catch (Exception ex) {
			Log.error(ex.getLocalizedMessage());
			ex.printStackTrace();
		}
	}

	public void waitForAvailable() {
		try {
			long startTime = System.currentTimeMillis();
			long endTime = 0;
			WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getRedirectTimeout());
			wait.until(isPageLoaded());
			endTime = System.currentTimeMillis() - startTime;
			System.out.println("page should be ready, waited for " + endTime);
		} catch (Exception e) {
			Log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public Function<WebDriver, Boolean> isPageLoaded() {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
	}

}
