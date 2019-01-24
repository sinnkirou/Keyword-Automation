package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_ENTER, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class SearchboxForm extends Component {

	public SearchboxForm(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
		// TODO Auto-generated constructor stub
	}
	
	@ById("sb_form_q")
	private Input keyword;

	@ById("sb_form_go")
	private Button searchButton;
	
	
	public Input getKeyword() {
		return keyword;
	}
	
	public Button getSearchButton() {
		return searchButton;
	}
}
