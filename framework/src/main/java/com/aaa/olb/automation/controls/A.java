package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;
import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class A extends Textbox {

	public A(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	/**
	 * @return control's href value
	 */
	public String getLink() {
		LocalDateTime startTime = LocalDateTime.now();
		String href =  this.getAttribute("href");
		this.info(this, generateAction(String.format("getLink"), startTime, LocalDateTime.now()));
		return href;
	}

	/**
	 * @return control's target value
	 */
	public String getTarget() {
		LocalDateTime startTime = LocalDateTime.now();
		String value  = this.getAttribute("target");
		this.info(this, generateAction(String.format("getTarget"), startTime, LocalDateTime.now()));
		return value;
	}
}
