package com.aaa.olb.automation.pages;

public class PageClazzProvider {
	/*
	 * get the page clazz with corresponding page name
	 * */
	public static Class<?> getPageClazz(String pageName) {
		if (pageName.equals(HomePage.class.getSimpleName()))
			return HomePage.class;


		return null;
	}
}
