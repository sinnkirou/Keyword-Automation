package com.aaa.olb.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.CCMDatePicker;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.Li;
import com.aaa.olb.automation.controls.RadioButton;
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
	
	@ById("gender")
	private Div genderDropdown;
	
	@ColumnName("Gender")
	public Div getGenderDropdown() {
		return genderDropdown;
	}
	
	@ByXPath(".//div[contains(@class, 'ant-select-dropdown') and not(contains(@class,'ant-select-dropdown-hidden'))]//li")
	private List<Li> options;
	
	@ColumnName("ActiveOptions")
	public List<Li> getOptions(){
		return options;
	}
	
	@ById("date_of_birth")
	private CCMDatePicker birthdate; 
	
	@ColumnName("BirthDate")
	public CCMDatePicker getBirthDate() {
		return birthdate;
	}
	
	@ById("organization_id")
	private Div organization;
	
	@ColumnName("Organization")
	public Div getOrganization() {
		return organization;
	}
	
	@ById("facility_id")
	private Div facility;
	
	@ColumnName("Facility")
	public Div getFacility() {
		return facility;
	}
	
	@ById("city")
	private Div city;
	
	@ColumnName("City")
	public Div getCity() {
		return city;
	}
	
	@ByXPath(".//input[@id='city']")
	private Input cityInput;
	
	@ColumnName("CityInput")
	public Input getCityInput() {
		return cityInput;
	}
	
	@ById("state")
	private Div state;
	
	@ColumnName("State")
	public Div getState() {
		return state;
	}
	
	@ByXPath(".//label[@title='Advanced Directives']/parent::div/parent::div//input")
	private List<RadioButton> advancedDirectivesOptions;
	
	@ColumnName("AdvancedDirectivesOptions")
	public List<RadioButton> getAdvancedDirectivesOptions(){
		return advancedDirectivesOptions;
	}
	
	@ById("home_phone_number")
	private Input homePhoneNumber;
	
	@ColumnName(value = "HomePhoneNumber", blur = true)
	public Input getHomePhoneNumber() {
		return homePhoneNumber;
	}
	
	@ById("home_phone_number_ext")
	private Input homePhoneNumberExt;
	
	@ColumnName(value = "HomePhoneNumberExt", blur = true)
	public Input getHomePhoneNumberExt() {
		return homePhoneNumberExt;
	}
	
	@ById("cell_number")
	private Input cellNumber;
	
	@ColumnName(value = "CellNumber", blur = true)
	public Input getCellNumber() {
		return cellNumber;
	}
	
	@ById("cell_number_ext")
	private Input cellNumberExt;
	
	@ColumnName(value = "CellNumberExt", blur = true)
	public Input getCellNumberExt() {
		return cellNumberExt;
	}
	
	@ByXPath(".//input[@value='home_phone_number']")
	private RadioButton homePhoneNumberPerferred;
	
	@ColumnName("HomePhoneNumberPerferred")
	public RadioButton getHomePhoneNumberPerferred() {
		return homePhoneNumberPerferred;
	}
	
	@ByXPath(".//input[@value='cell_number']")
	private RadioButton cellNumberPerferred;
	
	@ColumnName("CellNumberPerferred")
	public RadioButton getCellNumberPerferred() {
		return cellNumberPerferred;
	}
	
	@ByClassName("ant-btn-primary")
	private Button submitButton;
	
	@ColumnName("SubmitButton")
	public Button getSubmitButton() {
		return submitButton;
	}
}
