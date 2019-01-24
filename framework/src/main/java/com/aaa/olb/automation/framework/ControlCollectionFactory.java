package com.aaa.olb.automation.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.List;

import com.aaa.olb.automation.controls.Control;

public class ControlCollectionFactory {

	@SuppressWarnings("unchecked")
	public <T extends Control> List<T> create(SeleniumContext context) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		InvocationHandler handler = new ElementCollectionInvocationHandler(context, new DefaultElementLocator(context));
		return (List<T>) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { List.class }, handler);
	}
}
