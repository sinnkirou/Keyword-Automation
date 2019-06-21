package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class Button extends Textbox {

	public Button(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public void submit() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		we.submit();
		this.info(this, generateAction("submit", startTime, LocalDateTime.now()));
	}
}
