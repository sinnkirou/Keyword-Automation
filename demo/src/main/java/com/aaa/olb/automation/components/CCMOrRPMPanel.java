package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.util.Constants;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = Constants.CUSTOMIZED_BEHAVIOR_PROVIDER_CLASS)
public class CCMOrRPMPanel extends Component {

	public CCMOrRPMPanel(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//span[text()='RPM']/parent::button")
	private Button rPMButton;

	@ColumnName("RPMButton")
	public Button getRPMButton() {
		return rPMButton;
	}

	@ByXPath(".//span[text()='CCM']/parent::button")
	private Button cCMButton;

	@ColumnName("CCMButton")
	public Button getCCMButton() {
		return cCMButton;
	}
}
