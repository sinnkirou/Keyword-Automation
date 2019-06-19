package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.ParameterExacter;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_ENTER, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class Input extends Control {

	public Input(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}


	/**
	 * @param keys
	 */
	public void enter(String keys) {
		LocalDateTime startTime= LocalDateTime.now();
		clear();
		new Actions(driver).sendKeys(we, keys).perform();
		this.info(this, generateAction(String.format("enter '%s'", keys), startTime, LocalDateTime.now()));
	}

	/**
	 * @param keys
	 * @param blur
	 */
	public void enter(String keys, Boolean blur) {
		enter(keys);
		if (blur)
			blur();
	}

	public void clear() {
		LocalDateTime startTime= LocalDateTime.now();
		focus();
		//we.sendKeys(Keys.BACK_SPACE);
		we.clear();
		this.info(this, generateAction("clear", startTime, LocalDateTime.now()));
	}

	public String getValue() {
		LocalDateTime startTime = LocalDateTime.now();
		String value = we.getAttribute("value");
		this.info(this, generateAction(String.format("getValue"), startTime, LocalDateTime.now()));
		return value;
	}

	/**
	 * @return input type
	 */
	public String getInputType() {
		LocalDateTime startTime = LocalDateTime.now();
		String value  = this.getAttribute("type");
		this.info(this, generateAction(String.format("getInputType"), startTime, LocalDateTime.now()));
		return value;
	}

	public void blur() {
		LocalDateTime startTime= LocalDateTime.now();
		scrollToViewElement();
		we.sendKeys(Keys.TAB);
		this.info(this, generateAction("blur", startTime, LocalDateTime.now()));
	}
	
	/**
	 * wait until text presented
	 * 
	 * @param value
	 */
	public void waitForValueEntered(String value) {
		WebDriverWait wait=new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime= LocalDateTime.now();
		wait.until(ExpectedConditions.textToBePresentInElementValue(we, value));
		this.info(this, generateAction("Wait for entered", startTime, LocalDateTime.now()));
	}
		
	/**
	 * wait until text presented
	 * 
	 * @param value
	 * @param seconds
	 */
	public void waitForValueEntered(String value, long seconds) {
		WebDriverWait wait=new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime= LocalDateTime.now();
		wait.until(ExpectedConditions.textToBePresentInElementValue(we, value));
		this.info(this, generateAction("Wait for entered", startTime, LocalDateTime.now()));
	}
	
	/**
	 * select partial context from a input with index
	 * 
	 * @param text: "0, 8"
	 * 
	 * e.g.: <input value="test context" />
	 */
	public void selectPartialContentForInput (String text) {
		String[] parameters = ParameterExacter.getParamters(text, 2);
		String start = !parameters[0].isEmpty() ? parameters[0] : "0";
		String end = !parameters[1].isEmpty() ? parameters[1] : String.valueOf(we.getText().length());
		StringBuilder script = new StringBuilder();
		script.append("if( arguments[0].createTextRange ) {");
		script.append("      var selRange = arguments[0].createTextRange();");
		script.append("      selRange.collapse(true);");
		script.append("      selRange.moveStart('character', "+start+");");
		script.append("      selRange.moveEnd('character', "+end+");");
		script.append("      selRange.select();");
		script.append("      arguments[0].focus();");
		script.append("} else if( arguments[0].setSelectionRange ) {");
		script.append("      arguments[0].focus();");
		script.append("      arguments[0].setSelectionRange("+start+", "+end+");");
		script.append("} else if( typeof arguments[0].selectionStart != 'undefined' ) {");
		script.append("      arguments[0].selectionStart = "+start+";");
		script.append("      arguments[0].selectionEnd = "+end+";");
		script.append("      arguments[0].focus();");
		script.append("}");
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContextForInput", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
	}
}
