package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ByTag;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.CCMMemberList;
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
	
	@ByXPath(".//li[contains(@class,'ant-pagination-item')][last()]")
	private Li lastPageNumberButton;
	
	@ColumnName("LastPageNumberButton")
	public Li getLastPageNumberButton() {
		return lastPageNumberButton;
	}
	
	@ByXPath(".//td[text()='40']/following-sibling::td[1]//a")
	private A member40;
	
	@ColumnName("Member40")
	public A getMember40() {
		return member40;
	}
	
	@ByTag("tbody")
	private CCMMemberList members;
	
	@ColumnName("MembersList")
	public CCMMemberList getMembers() {
		return members;
	}
	
	@ByXPath(".//tbody//tr[last()]/td/a")
	private A lastMemberOfPage;
	
	@ColumnName("LastMemberOfPage")
	public A getLastMemberOfPage() {
		return lastMemberOfPage;
	}
}
