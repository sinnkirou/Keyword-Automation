package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.LocationKind;
import com.aaa.olb.automation.framework.Route;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.Log;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class CCMMemberList extends Component {

	public CCMMemberList(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * select member with MID
	 * 
	 *	@param text
	 */
	public void selectMemberByMID(String text) {
		Route route = new Route();
		route.setFieldType(A.class);
		route.setLocationKind(LocationKind.XPATH);
		route.setLocation(".//td[text()='"+ text +"']/following-sibling::td[1]//a");
		route.setFieldName("member"+text);
		A target = null;
		try {
			target = getChildren(route);
			target.clickByJS();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

}