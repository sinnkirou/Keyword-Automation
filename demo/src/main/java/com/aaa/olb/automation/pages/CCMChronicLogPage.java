package com.aaa.olb.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.CCMLogDiff;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.framework.BasePage;

public class CCMChronicLogPage extends BasePage {

	public CCMChronicLogPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@ByXPath(".//div[contains(@class,'chronicLog')]//table/tbody/tr")
	private List<Div> logList;
	
	@ColumnName("LogList")
	public List<Div> getLogList() {
		return logList;
	}
	
	@ByXPath(".//div[text()='Date of Birth']")
	private CCMLogDiff birthdate;
	
	@ColumnName("Birthdate")
	public CCMLogDiff getBirthdate() {
		return birthdate;
	};
	
	@ByXPath(".//div[text()='Email']")
	private CCMLogDiff email;
	
	@ColumnName("Email")
	public CCMLogDiff getBirthdateDelete() {
		return email;
	}
	
	@ByXPath(".//div[text()='Home Phone Number']")
	private CCMLogDiff homePhoneNumber;
	
	@ColumnName("HomePhoneNumber")
	public CCMLogDiff getHomePhoneNumber() {
		return homePhoneNumber;
	}

}
