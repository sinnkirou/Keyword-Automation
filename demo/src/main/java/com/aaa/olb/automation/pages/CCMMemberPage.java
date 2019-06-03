package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.controls.Li;
import com.aaa.olb.automation.framework.BasePage;

public class CCMMemberPage extends BasePage {

	public CCMMemberPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByClassName("ant-pagination-next")
	private Li nextPageButton;
	
	@ColumnName("NextPageButton")
	public Li getNextPageButton() {
		return nextPageButton;
	}
	
	@ByXPath(".//td[text()='40']/following-sibling::td[1]//a")
	private A member40;
	
	@ColumnName("Member40")
	public A getMember40() {
		return member40;
	}
}
