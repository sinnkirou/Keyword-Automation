package com.aaa.olb.automation.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Div;
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

	@ByXPath(".//div[@id='problem']//button")
	private Button carePlanButton;

	@ColumnName("CarePlanButton")
	public Button getCarePlanButton() {
		return carePlanButton;
	}

	@ByXPath(".//div[@id='problem']//tbody/tr")
	private List<Div> problemList;

	@ColumnName("ProblemList")
	public List<Div> getProblemList() {
		return problemList;
	}
}
