package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.CCMLogDiff;
import com.aaa.olb.automation.configuration.SystemConstants;

public class CCMLogDiffBehavior extends ControlBehavior {

	public CCMLogDiffBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		CCMLogDiff target = (CCMLogDiff) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();

		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}
		switch (behaviorName.toLowerCase()) {
		case CustomizedBehaviorConstants.LOG_IS_CHANGED:
			return target.isChanged();
		default:
			return super.Execute();
		}
	}
}
