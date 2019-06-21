package com.aaa.olb.automation.framework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class ComponentFactory {

	private static List<Route> scan(Class<?> type) {
		List<Route> metadatas = new ArrayList<Route>();
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			Route route = ReflectionRoute.create(field);
			if (route != null) {
				metadatas.add(route);
			}
		}

		return metadatas;
	}

	private static Object initControl(SeleniumContext context) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (context.getRoute().isGeneric()) {
			ControlCollectionFactory factory = new ControlCollectionFactory();
			return factory.create(context);
		} else {
			ControlFactory factory = new ControlFactory();
			return factory.create(context);
		}
	}

	private static void init(Object component, SeleniumContext context) throws Exception {
		Object value = initControl(context);
		ReflectionRoute route = (ReflectionRoute) context.getRoute();
		route.getField().setAccessible(true);
		route.getField().set(component, value);
	}

	public static void create(SeleniumContext context, WebElement parent, Object component) throws Exception {
		List<Route> routes = scan(component.getClass());
		for (Route route : routes) {
			SeleniumContext elementContext = new SeleniumContext();
			elementContext.setDriver(context.getDriver());
			elementContext.setRoute(route);
			elementContext.setParent(parent);
			init(component, elementContext);
		}
	}

}
