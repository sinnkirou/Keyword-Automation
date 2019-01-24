package com.aaa.olb.automation.pages;

import org.openqa.selenium.WebDriver;
import com.aaa.olb.automation.framework.*;
import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.components.SearchboxForm;
import com.aaa.olb.automation.controls.Button;
import com.aaa.olb.automation.controls.Input;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@ByClassName("b_searchboxForm")
	private SearchboxForm searchboxForm;

	@ColumnName("SearchboxForm")
	public SearchboxForm getSearchboxForm() {
		return searchboxForm;
	}

	@ColumnName(value = "Keyword", async = true, blur = true)
	public Input getKeyword() {
		return searchboxForm.getKeyword();
	}

	@ColumnName("SearchBtn")
	public Button getSearchButton() {
		return searchboxForm.getSearchButton();
	}

	@ColumnName("GoNext")
	public Button getNextButton() {
		return searchboxForm.getSearchButton();
	}
}
