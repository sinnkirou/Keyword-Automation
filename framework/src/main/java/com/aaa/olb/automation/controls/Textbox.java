package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.utils.PatameterExacter;

public class Textbox extends Control {
	protected Textbox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
		// TODO Auto-generated constructor stub
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
	
	/*
	 * select partial context from a span or div,
	 * eg: <div>test context</div>
	 * */
	public void selectPartialContextByIndex(String text) {
		String[] parameters = PatameterExacter.getParamters(text, 2);
		String start = !parameters[0].isEmpty() ? parameters[0] : "0";
		String end = !parameters[1].isEmpty() ? parameters[1] : String.valueOf(we.getText().length());
		StringBuilder script = new StringBuilder();
		script.append("var leafNode = arguments[0].firstChild;");
		script.append("var range = document.createRange();");
		script.append("range.setStart(leafNode, "+ start +");");
		script.append("range.setEnd(leafNode, "+ end +");");
		script.append("var sel = window.getSelection();");
		script.append("sel.removeAllRanges();");
		script.append("sel.addRange(range);");
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContext", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * select partial context from a span or div,
	 * eg: <div>test context</div>
	 * */
	public void selectPartialContextByContext(String text) {
		StringBuilder script = new StringBuilder();
		script.append("var start = arguments[0].textContent.indexOf('"+text+"');");
		script.append("var end = start + "+text.length() + ";");
		script.append("var leafNode = arguments[0].firstChild;");
		script.append("var range = document.createRange();");
		script.append("range.setStart(leafNode, start);");
		script.append("range.setEnd(leafNode, end);");
		script.append("var sel = window.getSelection();");
		script.append("sel.removeAllRanges();");
		script.append("sel.addRange(range);");
		System.out.println(script);
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContext", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
	}
}
