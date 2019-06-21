package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.annotations.ColumnName;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.controls.CheckBox;
import com.aaa.olb.automation.controls.DropDown;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.RadioButton;
import com.aaa.olb.automation.controls.Textbox;
import com.aaa.olb.automation.log.Log;

public class BehaviorRepository {

	private static BehaviorRepository _instance;

	static {
		_instance = new BehaviorRepository();
	}

	public static BehaviorRepository getInstance() {
		return _instance;
	}

	private BehaviorRepository() {

	}

	Behavior getBuiltIn(BehaviorFacet facet) {
		Class<?> targetType = facet.getTarget().getClass();

		if (facet instanceof ListItemBehaviorFacet) {
			return new ControlCollectionItemBehavior(facet);
		} else if (facet.getTarget() instanceof List || List.class.isAssignableFrom(targetType)) {
			return new ControlCollectionBehavior(facet);
		} else if (facet.getTarget() instanceof Input) {
			return new InputBehavior(facet);
		} else if (facet.getTarget() instanceof RadioButton) {
			return new RadioButtonBehavior(facet);
		} else if (facet.getTarget() instanceof DropDown) {
			return new DropDownBehavior(facet);
		} else if (facet.getTarget() instanceof CheckBox) {
			return new CheckBoxBehavior(facet);
		} else if (facet.getTarget() instanceof Textbox) {
			return new TextBoxBehavior(facet);
		} else {
			return new ControlBehavior(facet);
		}
	}

	/**
	 * 
	 * get BehaviorIndication from the class type
	 * 
	 * using the BehaviorIndication.provider() to get the specific BehaviorProvider
	 * class
	 * 
	 * @param method
	 * @return
	 */
	public BehaviorProvider getBehaviorProvider(Class<?> type) {
		BehaviorIndication indication = type.getAnnotation(BehaviorIndication.class);

		if (indication != null) {
			String providerType = indication.provider();
			Class<?> providerClass;
			try {
				providerClass = Class.forName(providerType);
				Constructor<?> constructor = null;
				constructor = providerClass.getConstructor();
				return (BehaviorProvider) constructor.newInstance();
			} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| InstantiationException e) {
				e.printStackTrace();
				Log.error(e.getLocalizedMessage());
			}
		}

		return new DefaultBehaviorProvider();
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
	public BehaviorProvider getBehaviorProvider(Method method) {
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
	public BehaviorFacet getBehaviorFacet(Object target, TestStepEntity testStep, Method method) {
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
				// ex.printStackTrace();
				// listFacet.setIndex(0);
				throw ex;
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
	public Behavior getBehavior(BehaviorProvider provider, BehaviorFacet facet) {
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
