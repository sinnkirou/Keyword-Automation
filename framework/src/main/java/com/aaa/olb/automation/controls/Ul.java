package com.aaa.olb.automation.controls;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class Ul extends Control {

	public Ul(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}
}

