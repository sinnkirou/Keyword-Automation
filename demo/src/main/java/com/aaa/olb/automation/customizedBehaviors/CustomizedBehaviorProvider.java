package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.Behavior;
import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.BehaviorProvider;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.SearchboxForm;

/*
 * With the given provider in behaviorIndication, so as to get the needed customized Behavior
 * */
public class CustomizedBehaviorProvider implements BehaviorProvider {
	@Override
	public Behavior get(BehaviorFacet facet) {
		// TODO Auto-generated method stub

		if (facet.getTarget() instanceof SearchboxForm) {
			return new SearchboxFormBehavior(facet);
		} 
		
		return new ControlBehavior(facet);
	}
}
