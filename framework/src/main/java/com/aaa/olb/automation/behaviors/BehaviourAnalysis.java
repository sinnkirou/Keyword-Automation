package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.SearchContext;
import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.framework.BasePage;
import com.aaa.olb.automation.framework.Component;
import com.aaa.olb.automation.framework.PageRepository;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.TestHelper;

public class BehaviourAnalysis {
	private BasePage page = null;
	private Boolean methodMatched = false;

	public Object action(SearchContext driver, TestStepEntity testStep, Boolean initializePage,
			Class<?> pageClazz) throws Throwable {

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

				BehaviorProvider provider = getBehaviorProvider(method);
				BehaviorFacet facet = getBehaviorFacet(target, testStep, method);
				Behavior behavior = getBehavior(provider, facet);

				try {
					if (method.getAnnotation(ColumnName.class).shouldDelay()) {
						TestHelper.threadSleep();
					}
					result = behavior.getClass().getMethod("Execute").invoke(behavior);
					if (method.getAnnotation(ColumnName.class).shouldWait()) {
						TestHelper.threadSleep();
					}
					methodMatched = true;
					return result;

				} catch (InvocationTargetException e) {
					Log.info(e.getLocalizedMessage());
					throw e.getCause();
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

	/**
	 * get BehaviorIndication by the method's annotation if null
	 * 
	 * get BehaviorIndication from the method's return type
	 * 
	 * using the BehaviorIndication.provider() to get the specific BehaviorProvider
	 * class
	 * 
	 * @param method
	 * @return
	 */
	private BehaviorProvider getBehaviorProvider(Method method) {
		BehaviorIndication indication = method.getAnnotation(BehaviorIndication.class);

		if (indication == null) {
			indication = method.getReturnType().getAnnotation(BehaviorIndication.class);
		}

		if (indication != null) {
			String providerType = indication.provider();
			Class<?> providerClass = null;
			try {
				providerClass = Class.forName(providerType);
				Constructor<?> constructor = null;
				constructor = providerClass.getConstructor();
				return (BehaviorProvider) constructor.newInstance();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getLocalizedMessage());
			}
		}

		return new DefaultBehaviorProvider();
	}

	/**
	 * get behavior name from test step action value with highest priority,
	 * 
	 * get behavior name from method's BehaviorIndication annotation with medium
	 * priority,
	 * 
	 * get behavior name from method return type's BehaviorIndication annotation
	 * with lowest priority,
	 * 
	 * @param method
	 * @param testStep
	 * @return
	 */
	private String getBehaviorName(Method method, TestStepEntity testStep) {
		if (testStep.getActionKeyWord() != "" && testStep.getActionKeyWord() != null) {
			return testStep.getActionKeyWord();
		} else if (method.getAnnotation(BehaviorIndication.class) != null) {
			return method.getAnnotation(BehaviorIndication.class).name();
		} else if (method.getReturnType().getAnnotation(BehaviorIndication.class) != null) {
			return method.getReturnType().getAnnotation(BehaviorIndication.class).name();
		}
		return null;
	}

	/**
	 * create the BehaviorFacet by inputting target, teststep and method
	 * 
	 * @param target
	 * @param testStep
	 * @param method
	 * @return
	 */
	private BehaviorFacet getBehaviorFacet(Object target, TestStepEntity testStep, Method method) {
		BehaviorFacet facet = new BehaviorFacet();
		facet.setBehaviorName(getBehaviorName(method, testStep));
		facet.setParameters(new Object[] { testStep.getValue() });
		facet.setTarget(target);
		facet.setBlur(method.getAnnotation(ColumnName.class).blur());

		/*
		 * handle the elements list Besides the default facet values above, index should
		 * also be set from teststep target name, like SuggestItems[1]
		 */
		if (testStep.getTargetName().contains("[")) {
			ListItemBehaviorFacet listFacet = new ListItemBehaviorFacet();
			listFacet.setDefault(facet);

			try {
				String listNum = testStep.getTargetName()
						.substring((method.getAnnotation(ColumnName.class).value()).length());
				int index = Integer.valueOf(listNum.substring(1, listNum.length() - 1));
				listFacet.setIndex(index - 1);
			} catch (Exception ex) {
				Log.error(ex.getLocalizedMessage());
				listFacet.setIndex(0);
			}

			return listFacet;
		}

		return facet;
	}

	/**
	 * invoke BehaviorProvider.get(BehaviorFacet) to get specific behavior
	 * 
	 * @param provider
	 * @param facet
	 * @return
	 */
	private Behavior getBehavior(BehaviorProvider provider, BehaviorFacet facet) {
		try {
			return (Behavior) provider.getClass().getMethod("get", BehaviorFacet.class).invoke(provider, facet);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
		return null;
	}
}
