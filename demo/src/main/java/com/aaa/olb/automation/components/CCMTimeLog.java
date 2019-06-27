package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.log.LoggerHelper;
import com.aaa.olb.automation.util.Constants;
import com.aaa.olb.automation.utils.ParameterExacter;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = Constants.CUSTOMIZED_BEHAVIOR_PROVIDER_CLASS)
public class CCMTimeLog extends Component {

	public CCMTimeLog(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//td[2]")
	private Div duration;

	@ByXPath(".//td[6]")
	private Div loggedBy;

	/**
	 * @param text e.g.: text => "4.5,5" => 4.5 to 5 minutes
	 * 
	 * @return boolean
	 */
	public boolean isDurationCorrect(String text) {
		double[] parameters = ParameterExacter.getDoubleParameters(text, 2);
		String expect = this.duration.getText();
		String[] timestamp = expect.split(" ");
		int seconds = 0;
		int minutes = 0;
		for (String arr : timestamp) {
			if (arr.toLowerCase().contains("m")) {
				minutes = Integer.valueOf(arr.substring(0, arr.length() - 1));
			} else if (arr.toLowerCase().contains("s")) {
				seconds = Integer.valueOf(arr.substring(0, arr.length() - 1));
			}
		}
		int totalSeconds = seconds + minutes * 60;
		if (parameters[0] * 60 <= totalSeconds && totalSeconds < parameters[1] * 60) {
			return true;
		}

		String message = "Expected: " + text + " ,actual: " + expect;
		Log.error(message);
		System.out.println(LoggerHelper.formatConsoleLog("Error") + message);
		return false;
	}

	public boolean isLoggedBy(String text) {
		String expect = this.loggedBy.getText();
		boolean result = expect.toLowerCase().contains(text.toLowerCase());
		if (!result) {
			String message = "Expected: " + text + " ,actual: " + expect;
			Log.error(message);
			System.out.println(LoggerHelper.formatConsoleLog("Error") + message);
		}
		return result;
	}
}
