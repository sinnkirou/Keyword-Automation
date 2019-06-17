package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.PatameterExacter;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class Textbox extends Control {
	protected Textbox(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	public String getText() {
		LocalDateTime startTime = LocalDateTime.now();
		String text =  we.getText();
		this.info(this, generateAction(String.format("getText"), startTime, LocalDateTime.now()));
		return text;
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
	
	/**
	 * select partial context from a span or div
	 * 
	 * @param text
	 * 
	 *             e.g.：text-> '50，60', e.g.: <div>test context</div>
	 */
	public void selectPartialContentByIndex(String text) {
		this.focus();
		String[] parameters = PatameterExacter.getParamters(text, 2);
		String start = !parameters[0].isEmpty() ? parameters[0] : "0";
		String end = !parameters[1].isEmpty() ? parameters[1] : String.valueOf(we.getText().length());
		StringBuilder script = new StringBuilder();
		script.append("function getTextNodesIn(node) {\n");
		script.append("  if (!node) {\n");
		script.append("    return;\n");
		script.append("  }\n");
		script.append("  const textNodes = [];\n");
		script.append("  if (node.nodeType === 3) {\n");
		script.append("    textNodes.push(node);\n");
		script.append("  } else {\n");
		script.append("    const children = node.childNodes;\n");
		script.append("    for (let i = 0, len = children.length; i < len; i += 1) {\n");
		script.append("      textNodes.push(...getTextNodesIn(children[i]));\n");
		script.append("    }\n");
		script.append("  }\n");
		script.append("  return textNodes;\n");
		script.append("}\n");
		script.append("\n");
		script.append("function setSelectionRange(el, start, end) {\n");
		script.append("  if (document.createRange && window.getSelection) {\n");
		script.append("    const range = document.createRange();\n");
		script.append("    range.selectNodeContents(el);\n");
		script.append("    const textNodes = getTextNodesIn(el);\n");
		script.append("    let foundStart = false;\n");
		script.append("    let charCount = 0;\n");
		script.append("    let endCharCount;\n");
		script.append("\n");
		script.append("    // eslint-disable-next-line\n");
		script.append("    for (let i = 0, textNode; (textNode = textNodes[i]); i += 1) {\n");
		script.append("      endCharCount = charCount + textNode.length;\n");
		script.append("      if (\n");
		script.append("        !foundStart &&\n");
		script.append("        start >= charCount &&\n");
		script.append("        (start < endCharCount || (start === endCharCount && i <= textNodes.length))\n");
		script.append("      ) {\n");
		script.append("        range.setStart(textNode, start - charCount);\n");
		script.append("        foundStart = true;\n");
		script.append("      }\n");
		script.append("      if (foundStart && end <= endCharCount) {\n");
		script.append("        range.setEnd(textNode, Math.abs(end - charCount));\n");
		script.append("        break;\n");
		script.append("      }\n");
		script.append("      charCount = endCharCount;\n");
		script.append("    }\n");
		script.append("\n");
		script.append("    const sel = window.getSelection();\n");
		script.append("    sel.removeAllRanges();\n");
		script.append("    sel.addRange(range);\n");
		script.append("  } else if (document.selection && document.body.createTextRange) {\n");
		script.append("    const textRange = document.body.createTextRange();\n");
		script.append("    textRange.moveToElementText(el);\n");
		script.append("    textRange.collapse(true);\n");
		script.append("    textRange.moveEnd('character', end);\n");
		script.append("    textRange.moveStart('character', start);\n");
		script.append("    textRange.select();\n");
		script.append("  }\n");
		script.append("}");
		script.append("setSelectionRange(arguments[0], " + start + ", " + end + ");");
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContextByIndex", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
	}

	/**
	 * select partial context from a span or div
	 * 
	 * @param text
	 * 
	 *             e.g.：text-> 'some context', e.g.: <div>test context</div>
	 */
	public void selectPartialContextByContent(String text) {
		this.focus();
		StringBuilder script = new StringBuilder();
		script.append("var start = arguments[0].textContent.indexOf('" + text + "');");
		script.append("var end = start + " + text.length() + ";");
		script.append("var leafNode = arguments[0].firstChild;");
		script.append("var range = document.createRange();");
		script.append("range.setStart(leafNode, start);");
		script.append("range.setEnd(leafNode, end);");
		script.append("var sel = window.getSelection();");
		script.append("sel.removeAllRanges();");
		script.append("sel.addRange(range);");
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContextByContext", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
	}
}
