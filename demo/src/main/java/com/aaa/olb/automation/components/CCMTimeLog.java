package com.aaa.olb.automation.components;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.utils.ParameterExacter;

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
	 * @param text
	 * e.g.: text => "4.5,5"
	 * => 4.5 to 5 minutes
	 * 
	 * @return boolean
	 */
	public boolean isDurationCorrect(String text){
		int[] parameters =  ParameterExacter.getIntParameters(text, 2);
		String[] timestamp = duration.getText().split(" ");
		int seconds = 0;
		int minutes = 0;
		for(String arr: timestamp) {
			if(arr.toLowerCase().contains("m")) {
				minutes = Integer.valueOf(arr.substring(0, arr.length()-1));
			}else if(arr.toLowerCase().contains("s")) {
				seconds = Integer.valueOf(arr.substring(0, arr.length()-1));
			}
		}
		int totalSeconds = seconds + minutes * 60;
		if(parameters[0]*60 <totalSeconds && totalSeconds < parameters[1]*60) {
			return true;
		}
		
		return false;
	}
}
