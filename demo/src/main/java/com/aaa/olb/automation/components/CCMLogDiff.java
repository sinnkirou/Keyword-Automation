package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.util.Constants;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = Constants.CUSTOMIZED_BEHAVIOR_PROVIDER_CLASS)
public class CCMLogDiff extends Component{

	public CCMLogDiff(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//parent::div//span[contains(@class,'add')]")
	protected Span add;
	
	@ByXPath(".//parent::div//span[contains(@class,'del')]")
	protected Span delete;
	
	public Boolean isChanged() {
		if(add.visible() && delete.visible()) {
			return true;
		}
		return false;
	}
}
