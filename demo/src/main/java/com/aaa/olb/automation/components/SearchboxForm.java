package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.util.Constants;

/**
 *	Basically it's used like a page class
 *
 * a component which contains various of basic fields, mainly used for datepicker, radiobuttons, etc
 *
 */
@BehaviorIndication(name = SystemConstants.BEHAVIOR_ENTER, provider = Constants.CUSTOMIZED_BEHAVIOR_PROVIDER_CLASS)
public class SearchboxForm extends Component {

	public SearchboxForm(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
		// TODO Auto-generated constructor stub
	}
	
	@ById("sb_form_q")
	private Input keyword;

	@ById("sb_form_go")
	private Button searchButton;
	
	@ColumnName("Keyword")
	public Input getKeyword() {
		return keyword;
	}
	
	@ColumnName("SearchButton")
	public Button getSearchButton() {
		return searchButton;
	}
}
