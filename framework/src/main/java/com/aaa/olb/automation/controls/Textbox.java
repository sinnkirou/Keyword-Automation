package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.framework.SeleniumContext;

public class Textbox extends Control {
	protected Textbox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public String getText() {
		return we.getText();
	}

	public void waitTextToBePresented(String text) {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();
		wait.until(ExpectedConditions.textToBePresentInElement(we, text));
		this.info(this, generateAction(String.format("wait for text - %s", text), startTime, LocalDateTime.now()));
	}

	public void waitTextToBePresented(String text, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();
		wait.until(ExpectedConditions.textToBePresentInElement(we, text));
		this.info(this, generateAction(String.format("wait for text - %s", text), startTime, LocalDateTime.now()));
	}
}
