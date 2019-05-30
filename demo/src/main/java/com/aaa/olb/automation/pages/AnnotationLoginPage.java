package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ByTag;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.framework.BasePage;

public class AnnotationLoginPage extends BasePage {

	public AnnotationLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ById("userName")
	private Input userName;
	
	@ColumnName("UserName")
	public Input getUserName() {
		return userName;
	}
	
	@ById("password")
	private Input password;
	
	@ColumnName("Password")
	public Input getPassword() {
		return password;
	}
	
	@ByTag("button")
	private Button loginButton;
	
	@ColumnName("LoginButton")
	public Button getLoginButton() {
		return loginButton;
	}
}
