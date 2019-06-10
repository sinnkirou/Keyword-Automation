package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Li;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class CCMMemberTabs extends Component {

	public CCMMemberTabs(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//li[@class='ant-menu-item'][1]")
	private Li enrollmentTab;
	
	public Li getEnrollmentTab() {
		return enrollmentTab;
	}
	
	@ByXPath(".//li[@class='ant-menu-item'][5]")
	private Li chronicLogTab;
	
	public Li getChronicLogTab() {
		return chronicLogTab;
	}
}
