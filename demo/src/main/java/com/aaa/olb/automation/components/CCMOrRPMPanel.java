package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class CCMOrRPMPanel extends Component {

	public CCMOrRPMPanel(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//button[1]")
	private Button rPMButton;
	
	@ColumnName("RPMButton")
	public Button getRPMButton() {
		return rPMButton;
	}
	
	@ByXPath(".//button[2]")
	private Button cCMButton;
	
	@ColumnName("CCMButton")
	public Button getCCMButton() {
		return cCMButton;
	}
}
