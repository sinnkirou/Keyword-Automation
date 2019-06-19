package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_SELECT, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class RadioButton extends Control {

	public RadioButton(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public void select() {
		LocalDateTime startTime = LocalDateTime.now();
		click();
		this.info(this, generateAction("select", startTime, LocalDateTime.now()));
	}

	public boolean isSelected() {
		LocalDateTime startTime = LocalDateTime.now();
		boolean value = we.isSelected();
		this.info(this, generateAction(String.format("isSelected"), startTime, LocalDateTime.now()));
		return value;
	}

	public String getValue() {
		LocalDateTime startTime = LocalDateTime.now();
		String value = this.getAttribute("value");
		this.info(this, generateAction(String.format("getValue"), startTime, LocalDateTime.now()));
		return value;
	}
	
	public void waitUtilSelected() {
		WebDriverWait wait=new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime= LocalDateTime.now();
		wait.until(ExpectedConditions.elementToBeSelected(we));
		this.info(this, generateAction("wait for selectable", startTime, LocalDateTime.now()));
	}
	
	/**
	 * @param seconds
	 */
	public void waitUtilSelected(long seconds) {
		WebDriverWait wait=new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime= LocalDateTime.now();
		wait.until(ExpectedConditions.elementToBeSelected(we));
		this.info(this, generateAction("wait for selectable", startTime, LocalDateTime.now()));
	}
}
