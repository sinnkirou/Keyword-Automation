package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class PageRepository {

	private static PageRepository _instance;

	static {
		_instance = new PageRepository();
	}

	private Map<String, Class<?>> pages;

	private PageRepository() {
		pages = new HashMap<>();
	}

	public static PageRepository getInstance() {
		return _instance;
	}

	public void addPage(String name, Class<?> page) {
		this.pages.put(name, page);
	}

	public Class<?> getPage(String name) {
		return this.pages.get(name);
	}

	public static BasePage create(SearchContext driver, Class<?> page) throws Exception {
		Constructor<?> contractor = page.getConstructor(WebDriver.class);
		return (BasePage) contractor.newInstance(driver);
	}

}
