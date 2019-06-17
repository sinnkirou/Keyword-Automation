package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

/**
 * create a PageRepository which contains enabled pages,
 * 
 * each page is a hash map using page name as the key, class name as the value,
 * 
 * class name will be used to create page instance afterwards.
 * 
 * @author ziv
 *
 */
public class PageRepository {

	private Map<String, Class<?>> pages;

	public PageRepository() {
		pages = new HashMap<>();
	}

	public void addPage(String name, Class<?> page) {
		this.pages.put(name, page);
	}

	public Class<?> getPage(String name) {
		return this.pages.get(name);
	}

	/**
	 * get the instance of a page with provided webDriver and pageClazz, inside each
	 * page clazz constructor, PageFactory will create web page instance along with
	 * web elements instances
	 * 
	 * @param driver
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public static BasePage create(SearchContext driver, Class<?> page) throws Exception {
		Constructor<?> contructor = page.getConstructor(WebDriver.class);
		return (BasePage) contructor.newInstance(driver);
	}
}
