package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.SearchContext;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.framework.BasePage;
import com.aaa.olb.automation.framework.PageRepository;

public class BehaviourAnalysis {
	private static BasePage page = null;

	public static Object action(SearchContext driver, TestStepEntity testStep, Boolean initializePage)
			throws Throwable {
		Class<?> pageClazz = PageRepository.getInstance().getPage(testStep.getPageName());

		if (pageClazz != null) {
			if (page == null || initializePage) {
				page = PageRepository.create(driver, pageClazz);
				page.waitForAvailable();
			}
			Method[] methods = page.getClass().getMethods();

			for (Method method : methods) {
				if (method.getAnnotation(ColumnName.class) != null && methodMathced(testStep.getTargetName(), method)) {
					Object target = method.invoke(page);

					BehaviorProvider provider = getBehaviorProvider(method);
					BehaviorFacet facet = getBehaviorFacet(target, testStep, method);
					Behavior behavior = getBehavior(provider, facet);

					try {
						return behavior.getClass().getMethod("Execute").invoke(behavior);
					} catch (InvocationTargetException e) {
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

	public static BehaviorProvider getBehaviorProvider(Method method) throws Exception {
		BehaviorIndication indication = method.getAnnotation(BehaviorIndication.class);

		if (indication == null) {
			indication = method.getReturnType().getAnnotation(BehaviorIndication.class);
		}

		if (indication != null) {
			String providerType = indication.provider();
			Class<?> providerClass = Class.forName(providerType);
			Constructor<?> constructor = providerClass.getConstructor();
			return (BehaviorProvider) constructor.newInstance();
		}

		return new DefaultBehaviorProvider();
	}

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

	public static BehaviorFacet getBehaviorFacet(Object target, TestStepEntity testStep, Method method)
			throws Exception {
		BehaviorFacet facet = new BehaviorFacet();
		facet.setBehaviorName(getBehaviorName(method, testStep));
		facet.setParameters(new Object[] { testStep.getValue() });
		facet.setTarget(target);
		facet.setAsync(method.getAnnotation(ColumnName.class).async());
		facet.setBlur(method.getAnnotation(ColumnName.class).blur());

		if (testStep.getTargetName().contains("[")) {

			ListItemBehaviorFacet listFacet = new ListItemBehaviorFacet();
			listFacet.setDefault(facet);

			try {
				String listNum = testStep.getTargetName()
						.substring((method.getAnnotation(ColumnName.class).value()).length());
				int index = Integer.valueOf(listNum.substring(1, listNum.length() - 1));
				listFacet.setIndex(index - 1);
			} catch (Exception ex) {
				listFacet.setIndex(0);
			}

			return listFacet;
		}

		return facet;
	}

	public static Behavior getBehavior(BehaviorProvider provider, BehaviorFacet facet) throws Exception {
		return (Behavior) provider.getClass().getMethod("get", BehaviorFacet.class).invoke(provider, facet);
	}

}
