package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class Image extends Control {

	public Image(SeleniumContext context, WebElement webElement) {
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

	/**
	 * @return element's src value
	 */
	public String getSource() {
		LocalDateTime startTime = LocalDateTime.now();
		String value = this.getAttribute("src");
		this.info(this, generateAction(String.format("getSource"), startTime, LocalDateTime.now()));
		return value;
	}
}
