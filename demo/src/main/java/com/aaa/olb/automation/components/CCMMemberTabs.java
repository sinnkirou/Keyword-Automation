package com.aaa.olb.automation.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
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

	@ByXPath(".//li[contains(@class,'ant-menu-item')]")
	private List<Li> tabs;
	
	@ColumnName("EnrollmentTab")
	public Li getEnrollmentTab() {
		return tabs.get(0);
	}
	
	@ColumnName("CarePlanTab")
	public Li getCarePlanTab() {
		return tabs.get(1);
	}
	
	@ColumnName("ChronicLogTab")
	public Li getChronicLogTab() {
		return tabs.get(5);
	}
}
