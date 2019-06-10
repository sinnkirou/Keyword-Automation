package com.aaa.olb.automation.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.Li;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

public class CCMEnrollmentForm extends Component {

	public CCMEnrollmentForm(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ById("Consent")
	private Div consentDropdown;
	
	@ColumnName("Consent")
	public Div getGenderDropdown() {
		return consentDropdown;
	}
	
	@ById("member_status")
	private Div memberStatus;
	
	@ColumnName("MemberStatus")
	public Div getMemberStatus() {
		return memberStatus;
	}
	
	@ById("enrollment_date")
	private CCMDatePicker enrollmentDate;
	
	@ColumnName("EnrollmentDate")
	public CCMDatePicker getEnrollmentDate() {
		return enrollmentDate;
	}
	
	@ByXPath(".//div[contains(@class, 'ant-select-dropdown') and not(contains(@class,'ant-select-dropdown-hidden'))]//li")
	private List<Li> options;
	
	@ColumnName("ActiveOptions")
	public List<Li> getOptions(){
		return options;
	}
	
	@ById("description")
	private Input note;
	
	@ColumnName("Note")
	public Input getNote() {
		return note;
	}
	
}
