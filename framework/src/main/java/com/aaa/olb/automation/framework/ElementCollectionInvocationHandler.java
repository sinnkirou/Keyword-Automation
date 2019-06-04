package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.aaa.olb.automation.controls.Control;
import com.aaa.olb.automation.log.Log;

/**
 * 可以通过修改InvocationHandler里面的处理,比如 java element = this.locator.findElement(); 
 *
 */
public class ElementCollectionInvocationHandler implements InvocationHandler {

	private final ElementLocator locator;
	private final SeleniumContext context;

	public ElementCollectionInvocationHandler(SeleniumContext context, ElementLocator locator) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.locator = locator;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		try {
			return method.invoke(this.getCollection(), args);
		} catch (InvocationTargetException e) {
			// Unwrap the underlying exception
			Log.error(e.getLocalizedMessage() + args);
			throw e.getCause();
		}
	}
	
	private <T extends Control> List<T> getCollection() throws ReflectiveOperationException, Exception{
		List<T> results = new ArrayList<T>();
		Class<?> targetClass = (Class<?>)this.context.getRoute().getFieldType(); 
		Constructor<?> constructor = targetClass.getConstructor(SeleniumContext.class, WebElement.class);
		List<WebElement> sources= this.locator.findElements();
		for (WebElement source : sources) {
			@SuppressWarnings("unchecked")
			T target = (T) constructor.newInstance(this.context, source);
			results.add(target);
		}
		return results;
	}

}
