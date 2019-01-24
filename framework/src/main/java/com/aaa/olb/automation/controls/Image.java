package com.aaa.olb.automation.controls;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class Image extends Control {

	public Image(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}


	public Dimension getSize() {
		return we.getSize();
	}

	public int getHeight() {
		return getSize().getHeight();
	}

	public int getWidth() {
		return getSize().getWidth();
	}

	public String getSource() {
		return this.getAttribute("src");
	}
}
