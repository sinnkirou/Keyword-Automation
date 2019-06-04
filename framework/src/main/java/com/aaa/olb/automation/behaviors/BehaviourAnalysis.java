package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.SearchContext;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.framework.BasePage;
import com.aaa.olb.automation.framework.PageRepository;
import com.aaa.olb.automation.log.Log;

public class BehaviourAnalysis {
	private static BasePage page = null;

	public static Object action(SearchContext driver, TestStepEntity testStep, Boolean initializePage)
			throws Throwable {
		Class<?> pageClazz = PageRepository.getInstance().getPage(testStep.getPageName());

		if (pageClazz != null) {
			/*
			 * should initialize page if the page is not initialized or page redirected is
			 * performed, using PageRepository.create to initialize page instance
			 */
			if (page == null || initializePage) {
				page = PageRepository.create(driver, pageClazz);
				page.waitForAvailable();
			}
			Method[] methods = page.getClass().getMethods();

			for (Method method : methods) {
				/*
				 * find the specific method and invoke it to get the web element instance, then
				 * execute the action
				 */
				if (method.getAnnotation(ColumnName.class) != null && methodMathced(testStep.getTargetName(), method)) {
					Object target = method.invoke(page);

					BehaviorProvider provider = getBehaviorProvider(method);
					BehaviorFacet facet = getBehaviorFacet(target, testStep, method);
					Behavior behavior = getBehavior(provider, facet);

					try {
						if (facet.getShouldDelay()) {
							threadSleep();
						}
						Object result = behavior.getClass().getMethod("Execute").invoke(behavior);
						if (facet.getShouldWait()) {
							threadSleep();
						}
						return result;

					} catch (InvocationTargetException e) {
						Log.info(e.getLocalizedMessage());
						throw e.getCause();
					}

				}
			}
		}
		return null;
	}

	public static Boolean methodMathced(String targetName, Method method) {
		String columnName = method.getAnnotation(ColumnName.class).value();
		if (targetName.contains("[")) {
			return targetName.substring(0, targetName.indexOf('[')).equals(columnName);
		} else {
			return targetName.equals(columnName);
		}
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
	public static BehaviorProvider getBehaviorProvider(Method method) {
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
	 * get behavior name from method's BehaviorIndication annotation with medium priority,
	 * 
	 * get behavior name from method return type's BehaviorIndication annotation with lowest priority,
	 * 
	 * @param method
	 * @param testStep
	 * @return
	 */
	public static String getBehaviorName(Method method, TestStepEntity testStep) {
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
	public static BehaviorFacet getBehaviorFacet(Object target, TestStepEntity testStep, Method method) {
		BehaviorFacet facet = new BehaviorFacet();
		facet.setBehaviorName(getBehaviorName(method, testStep));
		facet.setParameters(new Object[] { testStep.getValue() });
		facet.setTarget(target);
		facet.setShouldDelay(method.getAnnotation(ColumnName.class).shouldDelay());
		facet.setShouldWait(method.getAnnotation(ColumnName.class).shouldWait());
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
	public static Behavior getBehavior(BehaviorProvider provider, BehaviorFacet facet) {
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

	private static void threadSleep() {
		try {
			Thread.sleep(RuntimeSettings.getInstance().getWaitOrDelayTimeout() * 1000);
			Log.info("waited: " + RuntimeSettings.getInstance().getWaitOrDelayTimeout());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

}
