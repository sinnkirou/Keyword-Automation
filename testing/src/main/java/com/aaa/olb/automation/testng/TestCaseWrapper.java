package com.aaa.olb.automation.testng;

import java.util.ArrayList;
import java.util.List;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

import com.aaa.olb.automation.configuration.TestCaseEntity;

public class TestCaseWrapper implements Comparable<TestCaseWrapper>  {

	private String name;

	private String suiteName;

	private int Index;

	private XmlTest test;
	
	private TestCaseEntity tc;

	public TestCaseWrapper(TestSuiteWrapper testSuite, TestCaseEntity tc, int index) {
		this.test = new XmlTest(testSuite.getSuite());
		this.test.setPreserveOrder(true);
		this.name = tc.getTestCaseID();
		this.suiteName = testSuite.getName();
		this.Index = index;
		this.tc = tc;
	}

	public XmlTest getTestCase() {
		return this.test;
	}
	
	public TestCaseEntity getTestCaseEntity() {
		return this.tc;
	}

	public void setTestCase(List<TestClassWrapper> testClasses) {
		this.test.setName(this.name);
		this.test.addParameter("testName", this.name);
		this.test.addParameter("index", Integer.toString(this.Index));
		this.test.addParameter("suiteName", this.suiteName);

		List<XmlClass> targetClasses = new ArrayList<>();
		if (!testClasses.isEmpty()) {
			for (int i = 0; i < testClasses.size(); i++) {
				targetClasses.add(testClasses.get(i).getTestClass());
			}
		}

		this.test.setClasses(targetClasses);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		this.Index = index;
	}

	@Override
	public int compareTo(TestCaseWrapper o) {
		return this.getTestCaseEntity().getEnvironmentVariable().getPriority()
				- o.getTestCaseEntity().getEnvironmentVariable().getPriority();
	}

}
