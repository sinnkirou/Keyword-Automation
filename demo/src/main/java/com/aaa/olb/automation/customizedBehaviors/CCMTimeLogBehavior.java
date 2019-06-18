package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.CCMTimeLog;
import com.aaa.olb.automation.configuration.SystemConstants;

public class CCMTimeLogBehavior extends ControlBehavior {

	public CCMTimeLogBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		CCMTimeLog target = (CCMTimeLog) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();

		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}
		switch (behaviorName.toLowerCase()) {
		case CustomizedBehaviorConstants.IS_DURATION_CORRECT:
			target.waitForVisible();
			return target.isDurationCorrect(parameter);
		default:
			return super.Execute();
		}
	}
}
