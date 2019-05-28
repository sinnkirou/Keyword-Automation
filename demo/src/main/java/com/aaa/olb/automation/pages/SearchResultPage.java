package com.aaa.olb.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.BasePage;

public class SearchResultPage extends BasePage{

	public SearchResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@ByXPath(".//li[@class='b_algo']//a")
	private List<A> results;
	
	@ById("id_l")
	private A loginButton;
	
	@ColumnName("Results")
	public List<A> getFirstResult() {
		return results;
	}
	
	@ColumnName("LoginButton")
	public A getLoginButton() {
		return loginButton;
	}
}
