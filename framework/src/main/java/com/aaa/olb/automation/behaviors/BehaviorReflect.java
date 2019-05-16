package com.aaa.olb.automation.behaviors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.NotFoundException;

/*
 * invoke a customized action defined in control type clazz by reflect
 * */
public class BehaviorReflect {
	public static Object action(BehaviorFacet facet)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
		Object target = facet.getTarget();
		Method fieldMethods[] = target.getClass().getMethods();
		int parameterAmounts = facet.getParameters() != null ? facet.getParameters().length : 0;
		for (Method method : fieldMethods) {

			/*
			 * get the right method with same method name and consistent parameters, 
			 * then invoke it by the instance stored in BehaviorFacet
			 * */
			if (method.getName().equals(facet.getBehaviorName())) {
				if (facet.getParameters() != null && method.getParameterTypes().length == parameterAmounts
						&& checkIsParameterMatched(method, facet)) {
					return method.invoke(target, facet.getParameters());
				}

				if (facet.getParameters() != null && facet.getParameters()[0].equals("")
						&& method.getParameterTypes().length == 0) {
					return method.invoke(target);
				}
			}
		}

		throw new NotFoundException("unable to finish this operation");
	}

	private static Boolean checkIsParameterMatched(Method method, BehaviorFacet facet) {
		Boolean result = true;
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			result = result && (method.getParameterTypes()[i].equals(facet.getParameters()[i].getClass()));
		}
		return result;
	}
}
