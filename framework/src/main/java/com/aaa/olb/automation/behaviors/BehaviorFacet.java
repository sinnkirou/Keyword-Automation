package com.aaa.olb.automation.behaviors;

public class BehaviorFacet {

	private Object target;

	/**
	 * Enter, click, double click
	 */
	private String behaviorName;

	private Object[] parameters;
	
	private Boolean blur = false;
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getBehaviorName() {
		return behaviorName;
	}

	public void setBehaviorName(String behaviorName) {
		this.behaviorName = behaviorName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public Boolean getBlur() {
		return blur;
	}

	public void setBlur(Boolean blur) {
		this.blur = blur;
	}
}
