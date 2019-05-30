package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.RichTextBox;
import com.aaa.olb.automation.framework.BasePage;

public class AnnotationImplementPage extends BasePage {

	public AnnotationImplementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByClassName("content___yJIdD")
	private RichTextBox textbox;
	
	@ColumnName(value = "RichTextBox", async = true)
	public RichTextBox getRichTextBox() {
		return textbox;
	}
}
