package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Method;
import org.openqa.selenium.SearchContext;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.framework.BasePage;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.PageRepository;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.log.LoggerHelper;
import com.aaa.olb.automation.utils.TestHelper;

public class BehaviourAnalysis {
	private BasePage page = null;
	private Boolean methodMatched = false;

	public Object action(SearchContext driver, TestStepEntity testStep, Boolean initializePage, Class<?> pageClazz)
			throws Throwable {

		if (pageClazz != null) {
			/*
			 * should initialize page if the page is not initialized or page redirected is
			 * performed, using PageRepository.create to initialize page instance
			 */
			if (page == null || initializePage) {
				page = PageRepository.create(driver, pageClazz);
				page.waitForAvailable();
			}

			methodMatched = false;
			Object result = behave(pageClazz, testStep, null);
			if (methodMatched == false) {
				Exception e = new Exception("Target not found: " + testStep.getTargetName());
				Log.error(e.getLocalizedMessage());
				throw e;
			}

			return result;
		}
		return null;
	}

	private Boolean methodMathced(String targetName, Method method) {
		String columnName = method.getAnnotation(ColumnName.class).value();
		if (targetName.contains("[")) {
			return targetName.substring(0, targetName.indexOf('[')).equals(columnName);
		} else {
			return targetName.equals(columnName);
		}
	}

	private Object behave(Class<?> clazz, TestStepEntity testStep, Method parentMethod) throws Throwable {
		Object result = null;
		
		/*
		 * if no target, means a global action should be performed
		 * */
		if (testStep.getTargetName().trim().length() == 0) {
			methodMatched = true;
			return result;
		}

		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			/*
			 * find the specific method and invoke it to get the web element instance, then
			 * execute the action
			 */
			if (method.getAnnotation(ColumnName.class) != null && methodMathced(testStep.getTargetName(), method)) {
				Object target = null;
				if (parentMethod == null) {
					target = method.invoke(page);
				} else {
					Object parent = parentMethod.invoke(page);
					target = method.invoke(parent);
				}

				BehaviorProvider provider = BehaviorRepository.getInstance().getBehaviorProvider(method);
				BehaviorFacet facet = BehaviorRepository.getInstance().getBehaviorFacet(target, testStep, method);
				Behavior behavior = BehaviorRepository.getInstance().getBehavior(provider, facet);

				try {
					if (method.getAnnotation(ColumnName.class).shouldDelay()) {
						TestHelper.waitOrDealy();
					}
					result = behavior.getClass().getMethod("Execute").invoke(behavior);
					if (method.getAnnotation(ColumnName.class).shouldWait()) {
						TestHelper.waitOrDealy();
					}
					methodMatched = true;
					return result;

				} catch (Exception e) {
					StringBuilder message = new StringBuilder();
					message.append(e.getMessage() + "\n");
					message.append("Target: " + testStep.getTargetName() + "\n");
					message.append("Action: " + testStep.getActionKeyWord() + "\n");
					Log.error(message.toString());
					System.out.println(LoggerHelper.formatConsoleLog("Error") + message.toString());
					throw e;
				}
			} else if (Component.class.isAssignableFrom(method.getReturnType())
					&& method.getAnnotation(ColumnName.class).nested()) {
				result = behave(method.getReturnType(), testStep, method);
			}
			if (methodMatched == true)
				break;
		}

		return result;
	}

}
