package com.aaa.olb.automation.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ByTag;
import com.aaa.olb.automation.annotations.ByXPath;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.A;
import com.aaa.olb.automation.controls.Div;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.Span;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.SeleniumContext;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = "com.aaa.olb.automation.customizedBehaviors.CustomizedBehaviorProvider")
public class CCMDatePicker extends Component {

	public CCMDatePicker(SeleniumContext context, WebElement element) {
		super(context, element);
		// TODO Auto-generated constructor stub
	}

	@ByTag("Input")
	public Input input;
	
	@ByClassName("ant-calendar-prev-year-btn")
	public A prevYearBtn;
	
	@ByClassName("ant-calendar-prev-month-btn")
	public A prevMonthBtn;
	
	@ByClassName("ant-calendar-next-year-btn")
	public A nextYearBtn;
	
	@ByClassName("ant-calendar-next-month-btn")
	public A nextMonthBtn;
	
	@ByClassName("ant-calendar-month-select")
	public A selectMonthBtn;
	
	@ByClassName("ant-calendar-year-select")
	public A selectYearBtn;
	
	@ByClassName("ant-calendar-year-panel-decade-select-content")
	public Span selectDecadeYearBtn;
	
	@ByClassName("ant-calendar-today-btn")
	public A selectTodayBtn;
	
	@ByXPath(".//td[contains(@class,'ant-calendar-cell')]/div")
	public List<Div> calendarDate;
	
	@ByXPath(".//td[contains(@class,'ant-calendar-cell')]/div[text()='1']")
	public Div firstDate;
	
	@ByXPath(".//td[@title='Jan']/a")
	public A jan;
	
	@ByXPath(".//td[@title='Feb']/a")
	public A feb;
	
	@ByXPath(".//td[@title='Mar']/a")
	public A mar;
	
	@ByXPath(".//td[@title='Apr']/a")
	public A apr;
	
	@ByXPath(".//td[@title='May']/a")
	public A may;
	
	@ByXPath(".//td[@title='Jun']/a")
	public A jun;
	
	@ByXPath(".//td[@title='Jul']/a")
	public A jul;
	
	@ByXPath(".//td[@title='Aug']/a")
	public A aug;
	
	@ByXPath(".//td[@title='Sep']/a")
	public A sep;
	
	@ByXPath(".//td[@title='Oct']/a")
	public A oct;
	
	@ByXPath(".//td[@title='Nov']/a")
	public A nov;
	
	@ByXPath(".//td[@title='Dec']/a")
	public A dec;
	
	@ByClassName("ant-calendar-year-panel-year")
	public A calendarYear;
}
