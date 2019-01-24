package com.aaa.olb.automation.controls;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_WATI_TO_HIDE, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class Dialog extends Textbox {

	public Dialog(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}
}
