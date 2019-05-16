package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;


/*
 * create a PageRepository which contains enabled pages, 
 * each page is a hash map using page name as the  key, clazz name as the value,
 * clazz name will be used to create page instance afterwards.
 * */
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

	/*
	 * get the instance of a page with provided webDriver and pageClazz,
	 * inside each page clazz constructor, PageFactory will create web page instance along with web elements instances
	 * */
	public static BasePage create(SearchContext driver, Class<?> page) throws Exception {
		Constructor<?> contructor = page.getConstructor(WebDriver.class);
		return (BasePage) contructor.newInstance(driver);
	}

}
