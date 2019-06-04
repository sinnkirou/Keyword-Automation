package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlAction;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.CCMMemberTabs;
import com.aaa.olb.automation.configuration.SystemConstants;

public class CCMMemberTabsBehavior extends ControlBehavior {

	public CCMMemberTabsBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		CCMMemberTabs target = (CCMMemberTabs) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();

		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = CustomizedBehaviorConstants.SELECT_ENROLLMENT;
		}
		switch (behaviorName.toLowerCase()) {
		case CustomizedBehaviorConstants.SELECT_ENROLLMENT:
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.getEnrollmentTab().waitForVisible();
					target.getEnrollmentTab().click();
				}
			});

			return null;
		default:
			return super.Execute();
		}
	}
}
