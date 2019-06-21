package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.RichTextBox;
import com.aaa.olb.automation.framework.BasePage;

public class RichTextTestPage extends BasePage {

	public RichTextTestPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//textarea")
	private RichTextBox results;

	@ColumnName("RichTextBox")
	public RichTextBox getRichTextbox() {
		return results;
	}
}
