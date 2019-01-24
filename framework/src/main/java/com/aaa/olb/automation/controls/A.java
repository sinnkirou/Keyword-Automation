package com.aaa.olb.automation.controls;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class A extends Textbox {

	public A(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public String getLink() {
		return this.getAttribute("href");
	}

	public String getTarget() {
		return this.getAttribute("target");
	}
}
