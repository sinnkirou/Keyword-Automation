package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_SELECT, provider="com.aaa.olb.automation.behaviors.DefaultBehaviorProvider")
public class DropDown extends Control {
	private Select oSelect;

	public DropDown(SeleniumContext context, WebElement webElement) {
		super(context, webElement);
	}

	private Select getSelect() {
		if (oSelect == null) {
			oSelect = new Select(we);
		}
		return oSelect;
	}

	/**
	 * @param text
	 */
	public void selectByText(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		this.focus();
		getSelect().selectByVisibleText(text);
		this.info(this, generateAction(String.format("select '%s'", text), startTime, LocalDateTime.now()));
	}

	/**
	 * @param index
	 */
	public void selectByIndex(int index) {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		this.focus();
		getSelect().selectByIndex(index);
		this.info(this, generateAction(String.format("select by index: '%s'", index), startTime, LocalDateTime.now()));
	}

	/**
	 * @return
	 */
	public Option getSelectedOption() {
		return new Option(this.context, getSelect().getFirstSelectedOption());
	}
	
	/**
	 * @return text: string
	 */
	public String getSelectedOptionText() {
		return getSelect().getFirstSelectedOption().getText();
	}

	public List<Option> getOptions() {
		List<WebElement> sources = getSelect().getOptions();
		List<Option> options = new ArrayList<Option>();
		for (WebElement source : sources) {
			options.add(new Option(this.context, source));
		}
		return options;
	}

	/**
	 * @return count: int
	 */
	public int getOptionsCount() {
		return getSelect().getOptions().size();
	}

	/**
	 * @return options' text: List<String>
	 */
	public List<String> getOptionsTexts() {
		List<String> options = new ArrayList<String>();
		for (int i = 0; i < getOptionsCount(); i++) {
			options.add(getOption(i).getText());
		}
		return options;
	}

	/**
	 * @param index
	 * @return Option
	 */
	public Option getOption(int index) {
		return getOptions().get(index);
	}

	/**
	 * @param text
	 * @return Opition
	 */
	public Option getOption(String text) {
		for (int i = 0; i < getOptionsCount(); i++) {
			if (getOption(i).getText().trim() == text.trim()) {
				return getOption(i);
			}
		}
		return null;
	}
	
	/**
	 * @param text
	 */
	public void waitForSelected(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		WebDriverWait wait=new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		getOption(text).waitForSelected(wait);
		this.info(this, generateAction(String.format("wait '%s' for selected ", text), startTime, LocalDateTime.now()));
	}
	
	/**
	 * @param text
	 * @param seconds
	 */
	public void waitForSelected(String text, long seconds) {
		LocalDateTime startTime = LocalDateTime.now();
		WebDriverWait wait=new WebDriverWait(this.driver, seconds);
		getOption(text).waitForSelected(wait);
		this.info(this, generateAction(String.format("wait '%s' for selected ", text), startTime, LocalDateTime.now()));
	}
}
