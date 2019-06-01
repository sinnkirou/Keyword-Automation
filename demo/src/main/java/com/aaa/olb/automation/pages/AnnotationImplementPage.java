package com.aaa.olb.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.RichTextBox;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Icon;
import com.aaa.olb.automation.controls.Li;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.BasePage;

public class AnnotationImplementPage extends BasePage {

	public AnnotationImplementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//div[@name='content']")
	private RichTextBox textbox;
	
	@ColumnName(value = "RichTextBox", async = true)
	public RichTextBox getRichTextBox() {
		return textbox;
	}
	
	@ByXPath(".//div[@name='content']//span[@id!='']")
	private List<Span> selections;
	
	@ColumnName("Selections")
	public List<Span> getSelections() {
		return selections;
	}
	
	@ByXPath(".//i[@title='快捷键[+]']")
	private Icon addIcon;
	
	@ColumnName("AddIcon")
	public Icon getAddIcon() {
		return addIcon;
	}
	
	@ByXPath(".//div[contains(@class,'ant-form-item') and @label != '']")
	private List<Div> dropdowns;
	
	@ColumnName("Dropdowns")
	public List<Div> getDropdowns(){
		return dropdowns;
	}
	
	@ByXPath(".//div[contains(@class, 'ant-select-dropdown') and not(contains(@class,'ant-select-dropdown-hidden'))]//li")
	private List<Li> options;
	
	@ColumnName("ActiveOptions")
	public List<Li> getOPtions(){
		return options;
	}
	
	@ByXPath(".//div[contains(@class,'ant-form-item') and @label != '']//div[@class='ant-select-selection-selected-value']")
	private List<Div> selectedOptions;
	
	@ColumnName("SelectedOptions")
	public List<Div> getSelectedOptions(){
		return selectedOptions;
	}
}
