package com.aaa.olb.automation.pages;

public class PageClazzProvider {
	public static Class<?> getPageClazz(String pageName) {
		if (pageName.equals(HomePage.class.getSimpleName()))
			return HomePage.class;


		return null;
	}
}
