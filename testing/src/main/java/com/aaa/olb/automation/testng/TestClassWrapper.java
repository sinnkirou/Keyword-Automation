package com.aaa.olb.automation.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;

public class TestClassWrapper {

	private XmlClass testClass;

	public TestClassWrapper(Class<?> clazz) {
		this.testClass = new XmlClass(clazz);
	}

	public XmlClass getTestClass() {
		return this.testClass;
	}

	public void setIncludeMethod(String method) {
		XmlInclude include = new XmlInclude(method);
		List<XmlInclude> includes = new ArrayList<>();
		includes.add(include);
		this.testClass.setIncludedMethods(includes);
	}

}
