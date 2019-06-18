package com.aaa.olb.automation.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

public class CCMTimeReminderModal extends Component {

	public CCMTimeReminderModal(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//div/div[1]")
	private Div reminderContent;
	
	@ByXPath(".//button")
	private List<Button> buttons;
	
	public String getReminderContent() {
		return reminderContent.getText();
	}
}
