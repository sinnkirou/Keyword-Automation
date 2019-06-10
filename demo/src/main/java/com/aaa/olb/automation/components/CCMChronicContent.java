package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.P;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

public class CCMChronicContent extends Component {

	public CCMChronicContent(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//div[@id='problem']//p[@class='ant-empty-description']")
	private P carePlanEmptyProblem;
	
	@ColumnName("CarePlanProblem")
	public P getCarePlanEmptyProblem() {
		return carePlanEmptyProblem;
	}
}
