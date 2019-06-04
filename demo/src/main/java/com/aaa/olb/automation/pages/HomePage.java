package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;
import com.aaa.olb.automation.framework.*;
import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.SearchboxForm;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Input;

/*
 * 	Using @ColumnName to map the page elements with the fields from the PageObjectModel sheet.
 * 	as a result, make sure above fields consistent with each other
 * (except GoNext button, it may not exist in sheet as this is a default action).
 *  a)	Set blur to true if want this field to blur after entering.
 *  b)	Set async to true if want this field to wait specific time after entering or clicking.
 * 
 * Using @ById, @ByClassName, @ByXPath, @ByTag, @ByCss to find the web elements.
 * The web elements are wrapped into specific Input, Button, Dropdown, etc
 * 
 * behavior for each filed could be set in page teststeps sheet (Action column), 
 * if not (also cannot set behaviors by using flow template), in that case
 * @BehaviorIndication could be used to customize special behavior of one element or several elements group.
 * if not set, it may try to find the default behavior set from return type,
 * see @BehaviorIndication on SearchboxForm or Button file for specific
 * */
public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	/*	Using XPath (or id, tag, etc) to find a filed or component
	 * a) a basic filed like input, button, etc.
	 * b) a component which contains various of basic fields, mainly used for datepicker, radiobuttons, etc
	 * */
	@ByClassName("b_searchboxForm")
	private SearchboxForm searchboxForm;

	@ColumnName("SearchboxForm")
	public SearchboxForm getSearchboxForm() {
		return searchboxForm;
	}

	@ColumnName(value = "Keyword", shouldDelay = true, blur = true)
	public Input getKeyword() {
		return searchboxForm.getKeyword();
	}

	@ColumnName("SearchBtn")
	public Button getSearchButton() {
		return searchboxForm.getSearchButton();
	}

	/*
	 * if your are using flow template without override SimpleFlowTemplate, 
	 * and you also want some default actions like clicking next buttons at the end of flow
	 * make sure the  column name here is consistent with the default button name in SimpleFlowTemplate,
	 * in this case, GoNext button will be clicked by default in SimpleFlowTemplate
	 * */
	@ColumnName("GoNext")
	public Button getNextButton() {
		return searchboxForm.getSearchButton();
	}
}
