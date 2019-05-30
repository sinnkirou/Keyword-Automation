package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByLinkText;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.BasePage;

public class AnnotationHomePage extends BasePage {

	public AnnotationHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByXPath(".//span[text()='标注工作台']")
	private Span annotainMenu;
	
	@ColumnName("AnnotainMenu")
	public Span getAnnotainMenu() {
		return annotainMenu;
	}
	
	@ByLinkText("我的任务")
	private A myTaskSubMenu;
	
	@ColumnName("MyTaskSubMenu")
	public A getMyTaskSubMenu() {
		return myTaskSubMenu;
	}
	
	@ByXPath(".//div[@title='测试']")
	private Div testTask;
	
	@ColumnName(value = "TestTask", async = true)
	public Div getTestTask() {
		return testTask;
	}
	
	@ByXPath(".//tbody//td[2]/span[2]")
	private Span firstTask;
	
	@ColumnName(value = "FirstTask", async = true)
	public Span getFirstTask() {
		return firstTask;
	}
}
