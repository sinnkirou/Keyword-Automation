package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class Option extends Textbox {

	public Option(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public String getValue() {
		LocalDateTime startTime = LocalDateTime.now();
		String value = this.getAttribute("value");
		this.info(this, generateAction(String.format("getValue"), startTime, LocalDateTime.now()));
		return value;
	}

	/**
	 * @param wait
	 */
	public void waitForSelected(WebDriverWait wait) {
		LocalDateTime startTime = LocalDateTime.now();
		wait.until(ExpectedConditions.elementToBeSelected(this.we));
		this.info(this, generateAction("waitForSelected", startTime, LocalDateTime.now()));
	}
	
	public void waitForSelected() {
		LocalDateTime startTime = LocalDateTime.now();
		WebDriverWait wait=new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		wait.until(ExpectedConditions.elementToBeSelected(this.we));
		this.info(this, generateAction("waitForSelected", startTime, LocalDateTime.now()));
	}

}
