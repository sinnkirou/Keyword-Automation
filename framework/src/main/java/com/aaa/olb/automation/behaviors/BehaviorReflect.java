package com.aaa.olb.automation.behaviors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.NotFoundException;

import com.aaa.olb.automation.log.Log;

/**
 * invoke a customized action defined in control type class by reflect
 * 
 */
public class BehaviorReflect {
	public static Object action(BehaviorFacet facet) {
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
					try {
						return method.invoke(target, facet.getParameters());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.error(e.getLocalizedMessage());
					}
				}

				if (facet.getParameters() != null && facet.getParameters()[0].equals("")
						&& method.getParameterTypes().length == 0) {
					try {
						return method.invoke(target);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.error(facet.getTarget() +" with action: " + facet.getBehaviorName() + "\n" + e.getLocalizedMessage());
					}
				}
			}
		}

		throw new NotFoundException("unable to finish this operation: "+ facet.getTarget() + " with action: " + facet.getBehaviorName());
	}

	private static Boolean checkIsParameterMatched(Method method, BehaviorFacet facet) {
		Boolean result = true;
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			result = result && (method.getParameterTypes()[i].equals(facet.getParameters()[i].getClass()));
		}
		return result;
	}
}
