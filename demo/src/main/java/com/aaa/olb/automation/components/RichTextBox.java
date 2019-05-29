package com.aaa.olb.automation.components;

import java.time.LocalDateTime;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CHOOSE, provider="com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class RichTextBox extends Input {

	public RichTextBox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
		// TODO Auto-generated constructor stub
	}

	public void selectPartialContent (String text) {
		String start = text.split(",")[0] != null ? text.split(",")[0] : "0";
		String end = text.split(",").length > 1 ? text.split(",")[1] : String.valueOf(we.getText().length());
		String script = "if( arguments[0].createTextRange ) {\n" + 
				"      var selRange = arguments[0].createTextRange();\n" + 
				"      selRange.collapse(true);\n" + 
				"      selRange.moveStart('character', "+start+");\n" + 
				"      selRange.moveEnd('character', "+end+");\n" + 
				"      selRange.select();\n" + 
				"      arguments[0].focus();\n" + 
				"    } else if( arguments[0].setSelectionRange ) {\n" + 
				"      arguments[0].focus();\n" + 
				"      arguments[0].setSelectionRange("+start+", "+end+");\n" + 
				"    } else if( typeof arguments[0].selectionStart != 'undefined' ) {\n" + 
				"      arguments[0].selectionStart = "+start+";\n" + 
				"      arguments[0].selectionEnd = "+end+";\n" + 
				"      arguments[0].focus();\n" + 
				"    }";
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script, we);
			this.info(this, generateAction("selectPartialContent", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendEnter() {
		LocalDateTime startTime = LocalDateTime.now();
		we.sendKeys(Keys.ENTER);
		this.info(this, generateAction("sendEnter", startTime, LocalDateTime.now()));
	}
	
	/*
	 * @param
	 * startxOffset, startyOffset, endxOffset, endyOffset
	 * */
	public void drogAndDropRichBox(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int startxOffset = Integer.parseInt(text.split(",")[0]);
		int startyOffset = text.split(",").length > 1 ? Integer.parseInt(text.split(",")[1]) : 0;
		int endxOffset = text.split(",").length > 2 ? Integer.parseInt(text.split(",")[2]) : 0;
		int endyOffset = text.split(",").length > 3 ? Integer.parseInt(text.split(",")[3]) : 0;
		moveByOffsetFromElement(startxOffset+","+startyOffset);
		dragAndDropByOffsetFromCurrent(endxOffset+","+endyOffset);
		rightClickAtCurrentPosition();
		this.info(this, generateAction("drogAndDropRichBox", startTime, LocalDateTime.now()));
	}
}
