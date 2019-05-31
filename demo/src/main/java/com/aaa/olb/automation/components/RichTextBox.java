package com.aaa.olb.automation.components;

import java.time.LocalDateTime;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.LocationKind;
import com.aaa.olb.automation.framework.Route;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.PatameterExacter;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class RichTextBox extends Component {

	public RichTextBox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * @param
	 * startxOffset, startyOffset, endxOffset, endyOffset
	 * */
	public void drogAndDropRichBox(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = PatameterExacter.getIntParameters(text, 4);
		moveByOffsetFromElement(parameters[0]+","+parameters[1]);
		dragAndDropByOffsetFromCurrent(parameters[2]+","+parameters[3]);
		this.info(this, generateAction("drogAndDropRichBox with offset: "+ text, startTime, LocalDateTime.now()));
	}
	
	private void selectPartialContextByContext(Route route, String text) {
		Span target = null;
		try {
			target = getChildren(route);
			target.selectPartialContextByContext(text);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}
	
	public void selectPartialContext(String text) {
		Route route = new Route();
		route.setFieldType(Span.class);
		route.setLocationKind(LocationKind.XPATH);
		route.setLocation(".//span[contains(text(), '"+ text +"')]");
		route.setFieldName("span");
		selectPartialContextByContext(route, text);
	}
}
