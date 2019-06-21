package com.aaa.olb.automation.configuration;

import java.util.ArrayList;
import java.util.List;

import com.aaa.olb.automation.framework.PageRepository;

public class TestCaseEntity {
	private String testCaseID;
	private String description;

	private List<TestStepEntity> testSteps = new ArrayList<TestStepEntity>();
	private EnvironmentVariable env;
	private PageRepository pageRepository = null;

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

	/**
	 * @return the pageRepository
	 */
	public PageRepository getPageRepository() {
		return this.pageRepository != null ? this.pageRepository : new PageRepository();
	}

	/**
	 * @param pageRepository the pageRepository to set
	 */
	public void setPageRepository(PageRepository pageRepository) {
		this.pageRepository = pageRepository;
	}
}
