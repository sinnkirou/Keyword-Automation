package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
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
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}
		switch (behaviorName.toLowerCase()) {
		case CustomizedBehaviorConstants.SELECT_ENROLLMENT:
			target.getEnrollmentTab().waitForVisible();
			target.getEnrollmentTab().click();
			return null;
		case CustomizedBehaviorConstants.SELECT_CARE_PLAN:
			target.getCarePlanTab().waitForVisible();
			target.getCarePlanTab().click();
			return null;
		case CustomizedBehaviorConstants.SELECT_NOTE:
			target.getNoteTab().waitForVisible();
			target.getNoteTab().click();
			return null;
		case CustomizedBehaviorConstants.SELECT_RPM:
			target.getRPMTab().waitForVisible();
			target.getRPMTab().click();
			return null;
		case CustomizedBehaviorConstants.SELECT_TIME:
			target.getTimeTab().waitForVisible();
			target.getTimeTab().click();
			return null;
		case CustomizedBehaviorConstants.SELECT_CARE_PLAN_LOG:
			target.getCarePlanLogTab().waitForVisible();
			target.getCarePlanLogTab().click();
			return null;
		case CustomizedBehaviorConstants.SELECT_DOCUMENT:
			target.getDocumentTab().waitForVisible();
			target.getDocumentTab().click();
			return null;
		default:
			return super.Execute();
		}
	}
}
