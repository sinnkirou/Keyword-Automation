package com.aaa.olb.automation.framework;

import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.controls.Control;
import com.aaa.olb.automation.log.BaseAction;

public abstract class Component extends Control {

	private WebElement element;

	protected BaseAction generateAction(String actionName) {
		String targetName = this.getClass().getSimpleName();
		if (this.context != null && this.context.getRoute() != null) {
			targetName = this.context.getRoute().getFieldName();
		}
		BaseAction action = new BaseAction();
		action.setTarget(this);
		action.setTargetName(targetName);
		action.setAction(actionName);
		action.setCompleted(true);
		return action;
	}

	public Component(SeleniumContext context, WebElement element) {
		super(context, element);
		this.element = element;
		try {
			ComponentFactory.create(context, this.element, this);
			this.info(this, generateAction("Component Initialization"));
		} catch (Exception ex) {
			this.error(this, generateAction("Component Initialization"), ex.getLocalizedMessage(), ex);
			ex.printStackTrace();
		}
	}
}
