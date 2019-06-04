package com.aaa.olb.automation.pages;

public class PageClazzProvider {
	/**
	 * get the page class with corresponding page name
	 * 
	 * @param pageName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getPageClazz(String pageName) throws ClassNotFoundException {
		if (pageName.equals(HomePage.class.getSimpleName()))
			return HomePage.class;
		if (pageName.equals(SearchResultPage.class.getSimpleName()))
			return SearchResultPage.class;
		if(pageName.equals(LoginPage.class.getSimpleName()))
			return LoginPage.class;

		return Class.forName("com.aaa.olb.automation.pages." + pageName);
	}
}
