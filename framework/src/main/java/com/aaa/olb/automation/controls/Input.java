package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_ENTER, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class Input extends Control {

	public Input(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}


	public void enter(String keys) {
		LocalDateTime startTime= LocalDateTime.now();
		clear();
		new Actions(driver).sendKeys(we, keys).perform();
		this.info(this, generateAction(String.format("enter '%s'", keys), startTime, LocalDateTime.now()));
	}

	public void enter(String keys, Boolean blur) {
		enter(keys);
		if (blur)
			blur();
	}

	public void clear() {
		LocalDateTime startTime= LocalDateTime.now();
		focus();
		//we.sendKeys(Keys.BACK_SPACE);
		we.clear();
		this.info(this, generateAction("clear", startTime, LocalDateTime.now()));
	}

	public String getValue() {
		return we.getAttribute("value");
	}

	public String getInputType() {
		return this.getAttribute("type");
	}

	public void blur() {
		LocalDateTime startTime= LocalDateTime.now();
		scrollToViewElement();
		we.sendKeys(Keys.TAB);
		this.info(this, generateAction("blur", startTime, LocalDateTime.now()));
	}
	
	public void waitForValueEntered(String value) {
		WebDriverWait wait=new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime= LocalDateTime.now();
		wait.until(ExpectedConditions.textToBePresentInElementValue(we, value));
		this.info(this, generateAction("Wait for entered", startTime, LocalDateTime.now()));
	}
		
	public void waitForValueEntered(String value, long seconds) {
		WebDriverWait wait=new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime= LocalDateTime.now();
		wait.until(ExpectedConditions.textToBePresentInElementValue(we, value));
		this.info(this, generateAction("Wait for entered", startTime, LocalDateTime.now()));
	}
}
