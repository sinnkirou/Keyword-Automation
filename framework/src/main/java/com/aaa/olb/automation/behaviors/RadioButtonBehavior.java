package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.RadioButton;

public class RadioButtonBehavior extends ControlBehavior {

	public RadioButtonBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		RadioButton target = (RadioButton) this.facet.getTarget();
		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_SELECT;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_SELECT: {
			target.select();
			return null;
		}
		case SystemConstants.BEHAVIOR_WATI_TO_SELECT: {
			target.waitUtilSelected();
			return null;
		}
		default:
			return super.Execute();
		}
	}

}
