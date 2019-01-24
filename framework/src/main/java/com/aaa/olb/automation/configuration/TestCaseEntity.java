package com.aaa.olb.automation.configuration;

import java.util.ArrayList;
import java.util.List;

public class TestCaseEntity {
	private String testCaseID;
	private String description;
	
	private List<TestStepEntity> testSteps = new ArrayList<TestStepEntity>();
	private EnvironmentVariable env;

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setEnvironmentVariable(EnvironmentVariable env) {
		this.env = env;
	}
	
	public EnvironmentVariable getEnvironmentVariable() {
		return this.env;
	}

	public List<TestStepEntity> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStepEntity> testSteps) {
		this.testSteps = testSteps;
	}
}
