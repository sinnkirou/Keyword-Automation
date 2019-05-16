package com.aaa.olb.automation.framework;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class PageFactory {

	private static List<Route> scan(Class<?> pageClazz) {
		List<Route> metadatas = new ArrayList<Route>();
		Field[] fields = pageClazz.getDeclaredFields();
		for (Field field : fields) {
			Route route = ReflectionRoute.create(field);
			if (route != null) {
				metadatas.add(route);
			}
		}

		return metadatas;
	}

	/*
	 * initialize page element or elements list instance 
	 * */
	private static Object initControl(SeleniumContext context) throws Exception {
		if(context.getRoute().isGeneric()){
			ControlCollectionFactory factory = new ControlCollectionFactory();
			return factory.create(context);
		}
		else{
			ControlFactory factory = new ControlFactory();
			return factory.create(context);
		}
	}

	private static void initPage(Object pageClazz, SeleniumContext context) throws Exception {
		Object value = initControl(context);
		ReflectionRoute route = (ReflectionRoute)context.getRoute();
		
		/*
		 * 当isAccessible()的结果是false时不允许通过反射访问该字段
		 * 当该字段时private修饰时isAccessible()得到的值是false，
		 * 所以必须要setAccessible改成true才可以访问
		 * 
		 * set将指定对象变量上此 Field 对象表示的字段设置为指定的新值。 
		 * */
		route.getField().setAccessible(true);
		route.getField().set(pageClazz, value);
	}

	/*
	 * initialize page instance along with page elements instances
	 * */
	public static void create(SeleniumContext context, Object pageClazz) throws Exception {
		List<Route> routes = scan(pageClazz.getClass());
		for (Route route : routes) {
			SeleniumContext elementContext = new SeleniumContext();
			elementContext.setDriver(context.getDriver());
			elementContext.setRoute(route);
			elementContext.setParent(context.getDriver());
			initPage(pageClazz, elementContext);
		}
	}

}
