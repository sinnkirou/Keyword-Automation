package com.aaa.olb.automation.framework;

import java.lang.reflect.Type;

import org.openqa.selenium.By;

public class Route {

	protected LocationKind locationKind;

	protected String location;

	/**
	 * The control class type.
	 * if the field is a list, it should be the special parameter type
	 * 
	 * */
	protected Type fieldType;
	
	protected String fieldName;
	
	/**
	 * is a generic collection or not.
	 * */
	protected boolean generic = false;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	protected boolean isGeneric() {
		return generic;
	}

	public void setGeneric(boolean generic) {
		this.generic = generic;
	}

	public LocationKind getLocationKind() {
		return locationKind;
	}

	public void setLocationKind(LocationKind locationKind) {
		this.locationKind = locationKind;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Type getFieldType() {
		return fieldType;
	}

	public void setFieldType(Type fieldType) {
		this.fieldType = fieldType;
	}

	public By getFinder() {
		switch (this.getLocationKind()) {
			case ID: {
				return By.id(this.getLocation());
			}
			case CSS: {
				return By.cssSelector(this.getLocation());
			}
			case CLASSNAME: {
				return By.className(this.getLocation());
			}
			case TAG: {
				return By.tagName(this.getLocation());
			}
			default: {
				return By.xpath(this.getLocation());
			}
		}
	}

}
