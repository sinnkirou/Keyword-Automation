package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.framework.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ById("i0116")
	private Input email;

	@ById("idSIButton9")
	private Button nextButton;

	@ById("i0118")
	private Input password;

	@ById("idSIButton9")
	private Button loginButton;

	@ById("passwordError")
	private Div passwordError;

	@ColumnName("Email")
	public Input getEmail() {
		return email;
	}

	@ColumnName("NextButton")
	public Button getNextButton() {
		return nextButton;
	}

	@ColumnName("Password")
	public Input getPassword() {
		return password;
	}

	@ColumnName("LoginButton")
	public Button getLoginButton() {
		return loginButton;
	}

	@ColumnName("PasswordError")
	public Div getPasswordError() {
		return passwordError;
	}
}
