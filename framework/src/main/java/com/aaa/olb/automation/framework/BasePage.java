package com.aaa.olb.automation.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.log.ActionRepository;
import com.aaa.olb.automation.log.BaseAction;
import com.google.common.base.Function;

public abstract class BasePage extends ActionRepository {

	protected WebDriver driver;

	protected SeleniumContext context;

	/**
	 * using PageFactory to create page instance
	 * 
	 * @param driver
	 */
	public BasePage(WebDriver driver) {
		super();
		this.driver = driver;
		this.context = new SeleniumContext();
		this.context.setDriver(driver);
		this.context.setRepository(this);
		try {
			PageFactory.create(context, this);
		} catch (Exception ex) {
			this.error(this, generateAction("Page Initialization"), ex.getLocalizedMessage(), ex);
		}
	}
	
	protected BaseAction generateAction(String actionName) {
		String targetName = this.getClass().getSimpleName();
		if (this.context != null && this.context.getRoute() != null) {
			targetName = this.context.getRoute().getFieldName();
		}
		BaseAction action = new BaseAction();
		action.setTarget(this);
		action.setTargetName(targetName);
		action.setAction(actionName);
		action.setCompleted(true);
		return action;
	}

	public void waitForAvailable() {
		try {
			long startTime = System.currentTimeMillis();
			long totolSeconds = 0;
			WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getRedirectTimeout());
			wait.until(isPageLoaded());
			totolSeconds = System.currentTimeMillis() - startTime;
			String message = "wait page ready for " + totolSeconds + " seconds";
			this.info(this, generateAction(message));
		} catch (Exception e) {
			this.error(this, generateAction("waitForAvailable"), e.getLocalizedMessage(), e);
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
