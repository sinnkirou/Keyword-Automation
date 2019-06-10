package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByTag;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.CCMDemographicsForm;
import com.aaa.olb.automation.components.CCMEnrollmentForm;
import com.aaa.olb.automation.components.CCMMemberTabs;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.BasePage;

public class CCMEnrollmentPage extends BasePage {

	public CCMEnrollmentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@ByXPath(".//div[contains(@class,'ccmMember')]//ul[@role='menu']")
	private CCMMemberTabs tabs;
	
	@ColumnName("CCMMemberTabs")
	public CCMMemberTabs getCCMMemberTabs() {
		return tabs;
	}
	
	@ByXPath(".//div[@id='demographics']//button")
	private Button demographicsButton;

	@ColumnName("DemographicsButton")
	public Button gerDemographicsButton() {
		return demographicsButton;
	}
	
	@ByXPath(".//div[@id='enrollment']//button")
	private Button enrollmentButton;

	@ColumnName("EnrollmentButton")
	public Button gerEnrollmentButton() {
		return enrollmentButton;
	}
	
	@ByXPath(".//div[text()='Consent:']/parent::div//span")
	private Span consentValue;
	
	@ColumnName("ConsentValue")
	public Span getConsentValue() {
		return consentValue;
	}
	
	@ByTag("form")
	private CCMDemographicsForm demographicsForm;
	
	@ColumnName(nested = true)
	public CCMDemographicsForm getCCMDemographicsForm() {
		return demographicsForm;
	}
	
	@ByTag("form")
	private CCMEnrollmentForm enrollmentForm;
	
	@ColumnName(nested = true)
	public CCMEnrollmentForm getCCMEnrollmentForm() {
		return enrollmentForm;
	}
}
