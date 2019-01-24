package com.aaa.olb.automation.configuration;

public class TestStepEntity {
	private String testCaseID;
	private String testGroupID;
	private String targetName;
	private String actionKeyWord;
	private String value;
	private String pageName;

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getActionKeyWord() {
		return actionKeyWord;
	}

	public void setActionKeyWord(String actionKeyWord) {
		this.actionKeyWord = actionKeyWord;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageClazz) {
		this.pageName = pageClazz;
	}
	
	public String getTestGroupID() {
		return testGroupID;
	}

	public void setTestGroupID(String testGroupID) {
		this.testGroupID = testGroupID;
	}
}
