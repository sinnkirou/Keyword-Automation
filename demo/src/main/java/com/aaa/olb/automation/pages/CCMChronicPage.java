package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.CCMMemberTabs;
import com.aaa.olb.automation.components.CCMOrRPMPanel;
import com.aaa.olb.automation.framework.BasePage;

public class CCMChronicPage extends BasePage {

	public CCMChronicPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByClassName("ant-modal-content")
	private CCMOrRPMPanel panel;
	
	@ColumnName("CCMOrRPMPanel")
	public CCMOrRPMPanel getCCMOrRPMPanel() {
		return panel;
	}
	
	@ByXPath(".//div[contains(@class,'ccmMember')]//ul[@role='menu']")
	private CCMMemberTabs tabs;
	
	@ColumnName("CCMMemberTabs")
	public CCMMemberTabs getCCMMemberTabs() {
		return tabs;
	}
}
