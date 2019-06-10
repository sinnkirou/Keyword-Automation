package com.aaa.olb.automation.customizedBehaviors;

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

/**
 * With the given provider in behaviorIndication
 * 
 * so as to get the needed customized Behavior
 */
public class CustomizedBehaviorProvider implements BehaviorProvider {
	@Override
	public Behavior get(BehaviorFacet facet) {
		// TODO Auto-generated method stub

		if (facet.getTarget() instanceof SearchboxForm) {
			return new SearchboxFormBehavior(facet);
		}else if (facet.getTarget() instanceof RichTextBox) {
			return new RichTextBoxBehavior(facet);
		}else if (facet.getTarget() instanceof CCMOrRPMPanel) {
			return new CCMOrRPMPanelBehavior(facet);
		}else if (facet.getTarget() instanceof CCMMemberTabs) {
			return new CCMMemberTabsBehavior(facet);
		}else if (facet.getTarget() instanceof CCMDatePicker) {
			return new CCMDatePickerBehavior(facet);
		}else if (facet.getTarget() instanceof CCMLogDiff) {
			return new CCMLogDiffBehavior(facet);
		}
		
		return new ControlBehavior(facet);
		
	}
}
