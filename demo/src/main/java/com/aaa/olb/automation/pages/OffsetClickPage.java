package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.controls.Image;
import com.aaa.olb.automation.framework.BasePage;

public class OffsetClickPage extends BasePage {

	public OffsetClickPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@ById("bgImgie")
	private Image bgImgie;

	@ColumnName("BgImgie")
	public Image getBgImgie() {
		return bgImgie;
	}
}
