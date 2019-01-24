package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

public class ControlFactory {
	
	public Object create(SeleniumContext context) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		WebElement element= this.find(context);
		Class<?>  targetType=(Class<?>)context.getRoute().getFieldType();
		Constructor<?> constructor = targetType.getConstructor(SeleniumContext.class, WebElement.class);
		return constructor.newInstance(context, element);
	}
	
	private WebElement find(SeleniumContext context){
		InvocationHandler handler = new LocatingElementHandler(new DefaultElementLocator(context));
		return (WebElement) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[] { WebElement.class, WrapsElement.class, Locatable.class }, handler);
	}

}
