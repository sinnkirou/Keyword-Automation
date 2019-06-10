package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class Icon extends Textbox {

	public Icon(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public Dimension getSize() {
		return we.getSize();
	}

	public int getHeight() {
		LocalDateTime startTime = LocalDateTime.now();
		int value = getSize().getHeight();
		this.info(this, generateAction(String.format("getHeight"), startTime, LocalDateTime.now()));
		return value;
	}

	public int getWidth() {
		LocalDateTime startTime = LocalDateTime.now();
		int value = getSize().getWidth();
		this.info(this, generateAction(String.format("getWidth"), startTime, LocalDateTime.now()));
		return value;
	}
}
