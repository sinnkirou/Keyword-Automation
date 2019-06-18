package com.aaa.olb.automation.components;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.controls.Textbox;
import com.aaa.olb.automation.framework.LocationKind;
import com.aaa.olb.automation.framework.Route;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.utils.ParameterExacter;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT_BY_CONTEXT, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class RichTextBox extends Textbox {

	public RichTextBox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param text
	 * 
	 * e.g.: text -> "startxOffset, startyOffset, endxOffset, endyOffset"
	 */
	public void drogAndDropRichBox(String text) {
		int[] parameters = ParameterExacter.getIntParameters(text, 4);
		moveByOffsetFromElement(parameters[0]+","+parameters[1]);
		dragAndDropByOffsetFromCurrent(parameters[2]+","+parameters[3]);
	}
	
	/**
	 * select context with specific context
	 * 
	 *	@param text
	 */
	public void selectPartialContextByContext(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		Route route = new Route();
		route.setFieldType(Span.class);
		route.setLocationKind(LocationKind.XPATH);
		route.setLocation(".//span[contains(text(), '"+ text +"')]");
		route.setFieldName("span"+text);
		Span target = null;
		try {
			target = getChildren(route);
			target.selectPartialContextByContent(text);
		} catch (Exception e) {
			this.error(this, generateAction("selectPartialContextByContext", startTime, LocalDateTime.now()), e.getLocalizedMessage(), e);
		}
	}
}
