package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CHECK, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class CheckBox extends Control {

	public CheckBox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}


	public void check() {
		LocalDateTime startTime = LocalDateTime.now();
		click();
		this.info(this, generateAction("check", startTime, LocalDateTime.now()));
	}

	public boolean isChecked() {
		return we.isSelected();
	}

	public String getValue() {
		return we.getAttribute("value");
	}
	
	public void waitForCheck() {
		LocalDateTime startTime = LocalDateTime.now();
		WebDriverWait wait=new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		wait.until(ExpectedConditions.elementToBeSelected(we));
		this.info(this, generateAction("waitForCheck", startTime, LocalDateTime.now()));
	}
	
	public void waitForCheck(Long seconds) {
		LocalDateTime startTime = LocalDateTime.now();
		WebDriverWait wait=new WebDriverWait(this.driver, seconds);
		wait.until(ExpectedConditions.elementToBeSelected(we));
		this.info(this, generateAction("waitForCheck", startTime, LocalDateTime.now()));
	}
}
