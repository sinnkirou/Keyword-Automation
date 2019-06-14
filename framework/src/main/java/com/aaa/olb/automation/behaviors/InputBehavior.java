package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Input;

public class InputBehavior extends ControlBehavior {

	public InputBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	public Object Execute() {
		Input target = (Input) this.facet.getTarget();
		String behaviorName = this.facet.getBehaviorName();
		String parameter = (String) this.facet.getParameters()[0];
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_ENTER;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_ENTER: {
			Boolean blur = this.facet.getBlur();
			target.enter(parameter, blur);
			if (parameter != null && parameter != "")
				target.waitForNotEmptyAttribute("value");
			return null;
		}
		case SystemConstants.BEHAVIOR_VALUE: {
			target.threadSleep();
			return target.getValue();
		}
		case SystemConstants.BEHAVIOR_CLEAR: {
			target.clear();
			return null;
		}
		case SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTENT_FOR_INPUT: {
			target.selectPartialContentForInput(parameter);
			return null;
		}
		default:
			return super.Execute();
		}
	}

}
