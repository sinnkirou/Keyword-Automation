package com.aaa.olb.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.CCMDatePicker;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.Li;
import com.aaa.olb.automation.framework.BasePage;

public class CCMEnrollmentPage extends BasePage {

	public CCMEnrollmentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@ByXPath(".//div[@id='demographics']//button")
	private Button demographicsButton;

	@ColumnName("DemographicsButton")
	public Button gerDemographicsButton() {
		return demographicsButton;
	}
	
	@ById("primary_care_id")
	private Input primaryCareId;
	
	@ColumnName("PrimaryCareId")
	public Input getPrimaryCareId() {
		return primaryCareId;
	}
	
	@ByXPath(".//div[@id='gender']//div[contains(@class,'ant-select-selection__rendered')]")
	private Div genderDropdown;
	
	@ColumnName("GenderDropdown")
	public Div getGenderDropdown() {
		return genderDropdown;
	}
	
	@ByXPath(".//div[contains(@class, 'ant-select-dropdown') and not(contains(@class,'ant-select-dropdown-hidden'))]//li")
	private List<Li> options;
	
	@ColumnName("ActiveOptions")
	public List<Li> getOPtions(){
		return options;
	}
	
	@ById("date_of_birth")
	private CCMDatePicker birthdate; 
	
}
