package com.aaa.olb.automation.components;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ByTag;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.BasePage;

public class CCMDatePicker extends BasePage {

	public CCMDatePicker(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ByTag("Input")
	private Input input;
	
	@ByClassName("ant-calendar-prev-year-btn")
	private A prevYearBtn;
	
	@ByClassName("ant-calendar-prev-month-btn")
	private A prevMonthBtn;
	
	@ByClassName("ant-calendar-next-year-btn")
	private A nextYearBtn;
	
	@ByClassName("ant-calendar-next-month-btn")
	private A nextMonthBtn;
	
	@ByClassName("ant-calendar-month-select")
	private A selectMonthBtn;
	
	@ByClassName("ant-calendar-year-select")
	private A selectYearBtn;
	
	@ByClassName("ant-calendar-year-panel-decade-select-content")
	private Span selectDecadeYearBtn;
	
	@ByClassName("ant-calendar-today-btn")
	private A selectTodayBtn;
	
	@ByClassName("ant-calendar-cell")
	private List<Div> calendarDate;
	
	@ByXPath(".//td[@title='Jan']/a")
	private A jan;
	
	@ByXPath(".//td[@title='Feb']/a")
	private A feb;
	
	@ByXPath(".//td[@title='Mar']/a")
	private A mar;
	
	@ByXPath(".//td[@title='Apr']/a")
	private A apr;
	
	@ByXPath(".//td[@title='May']/a")
	private A may;
	
	@ByXPath(".//td[@title='Jun']/a")
	private A jun;
	
	@ByXPath(".//td[@title='Jul']/a")
	private A jul;
	
	@ByXPath(".//td[@title='Aug']/a")
	private A aug;
	
	@ByXPath(".//td[@title='Sep']/a")
	private A sep;
	
	@ByXPath(".//td[@title='Oct']/a")
	private A oct;
	
	@ByXPath(".//td[@title='Nov']/a")
	private A nov;
	
	@ByXPath(".//td[@title='Dec']/a")
	private A dec;
	
	@ByClassName("ant-calendar-year-panel-year")
	private A calendarYear;
}
