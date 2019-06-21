package com.aaa.olb.automation.components;

import java.time.LocalDateTime;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.LocationKind;
import com.aaa.olb.automation.framework.Route;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.util.Constants;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = Constants.CUSTOMIZED_BEHAVIOR_PROVIDER_CLASS)
public class CCMMemberList extends Component {

	public CCMMemberList(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	/**
	 * select member with MID
	 * 
	 * @param text
	 */
	public void selectMemberByMID(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		Route route = new Route();
		route.setFieldType(A.class);
		route.setLocationKind(LocationKind.XPATH);
		route.setLocation(".//td[text()='" + text + "']/following-sibling::td[1]//a");
		route.setFieldName("member" + text);
		A target = null;
		try {
			target = getChildren(route);
			target.clickByJS();
		} catch (Exception e) {
			this.error(this, generateAction("selectMemberByMID", startTime, LocalDateTime.now()),
					e.getLocalizedMessage(), e);
			throw new NotFoundException(e.getMessage());
		}
	}

}
