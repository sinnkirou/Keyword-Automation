package com.aaa.olb.automation.customizedBehaviors;

import java.lang.reflect.InvocationTargetException;

import com.aaa.olb.automation.behaviors.Behavior;
import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.BehaviorProvider;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.CCMDatePicker;
import com.aaa.olb.automation.components.CCMLogDiff;
import com.aaa.olb.automation.components.CCMMemberTabs;
import com.aaa.olb.automation.components.CCMOrRPMPanel;
import com.aaa.olb.automation.components.RichTextBox;
import com.aaa.olb.automation.components.SearchboxForm;
import com.aaa.olb.automation.util.PackageConstants;

/**
 * With the given provider in behaviorIndication
 * 
 * so as to get the needed customized Behavior
 */
public class CustomizedBehaviorProvider implements BehaviorProvider {
	@Override
	public Behavior get(BehaviorFacet facet) {
		// TODO Auto-generated method stub

		Class<?> clazzType = facet.getTarget().getClass();
		
		if (facet.getTarget() instanceof SearchboxForm) {
			return new SearchboxFormBehavior(facet);
		} else if (facet.getTarget() instanceof RichTextBox) {
			return new RichTextBoxBehavior(facet);
		} else if (facet.getTarget() instanceof CCMOrRPMPanel) {
			return new CCMOrRPMPanelBehavior(facet);
		} else if (facet.getTarget() instanceof CCMMemberTabs) {
			return new CCMMemberTabsBehavior(facet);
		} else if (facet.getTarget() instanceof CCMDatePicker) {
			return new CCMDatePickerBehavior(facet);
		} else if (facet.getTarget() instanceof CCMLogDiff) {
			return new CCMLogDiffBehavior(facet);
		}

		try {
			return (Behavior) Class
					.forName(PackageConstants.CUSTOMIZED_BEHAVIORS_PACKAGE_NAME + "." + clazzType.getSimpleName() + "Behavior")
					.getConstructor(BehaviorFacet.class).newInstance(facet);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			//e.printStackTrace();
			//Log.info(e.getLocalizedMessage());
			return new ControlBehavior(facet);
		}
	}
}
