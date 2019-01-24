package com.aaa.olb.automation.framework;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.controls.Control;

public abstract class Component extends Control {

	private WebElement element;
	
	public Component(SeleniumContext context, WebElement element ){
		super(context, element);
		this.element=element;
		try{
			ComponentFactory.create(context, this.element, this);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
