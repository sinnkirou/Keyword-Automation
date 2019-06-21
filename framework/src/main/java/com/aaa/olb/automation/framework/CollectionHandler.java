package com.aaa.olb.automation.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class CollectionHandler implements InvocationHandler {

	private ElementLocator locator;

	public CollectionHandler(SeleniumContext context, ElementLocator locator) {
		this.locator = locator;
	}

	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {

		try {
			Object target = this.getCollection();
			return method.invoke(target, objects);
		} catch (InvocationTargetException e) {
			// Unwrap the underlying exception
			throw e.getCause();
		}
	}

	public List<WebElement> getCollection() throws Exception {
		return this.locator.findElements();
	}

}
