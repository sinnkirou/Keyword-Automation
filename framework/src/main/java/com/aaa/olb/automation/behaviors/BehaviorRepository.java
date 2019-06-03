package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.CheckBox;
import com.aaa.olb.automation.controls.DropDown;
import com.aaa.olb.automation.controls.Input;
import com.aaa.olb.automation.controls.RadioButton;
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
		} else if (targetType.isAssignableFrom(List.class)) {
			return new ControlCollectionBehavior(facet);
		} else if (facet.getTarget() instanceof Input) {
			return new InputBehavior(facet);
		} else if (facet.getTarget() instanceof RadioButton) {
			return new RadioButtonBehavior(facet);
		} else if (facet.getTarget() instanceof DropDown) {
			return new DropDownBehavior(facet);
		} else if (facet.getTarget() instanceof CheckBox) {
			return new CheckBoxBehavior(facet);
		} else if (facet.getBehaviorName().equals(SystemConstants.BEHAVIOR_TEXT)) {
			return new TextBoxBehavior(facet);
		} else {
			return new ControlBehavior(facet);
		}
	}

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
					| InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				e.printStackTrace();
				Log.error(e.getCause().getMessage());
			}
		}

		return new DefaultBehaviorProvider();
	}
}
