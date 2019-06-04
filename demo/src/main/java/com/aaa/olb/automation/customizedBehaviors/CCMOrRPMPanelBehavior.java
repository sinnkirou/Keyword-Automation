package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlAction;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.CCMOrRPMPanel;
import com.aaa.olb.automation.configuration.SystemConstants;

public class CCMOrRPMPanelBehavior extends ControlBehavior {

	public CCMOrRPMPanelBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		CCMOrRPMPanel target = (CCMOrRPMPanel) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();

		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}
		String a = CustomizedBehaviorConstants.SELECT_CCM.toLowerCase();
		switch (behaviorName.toLowerCase()) {
		case CustomizedBehaviorConstants.SELECT_CCM :
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.getCCMButton().waitForVisible();
					target.getCCMButton().click();
				}
			});

			return null;
		case CustomizedBehaviorConstants.SELECT_RPM:
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.getRPMButton().waitForVisible();
					target.getRPMButton().click();
				}
			});

			return null;
		default:
			return super.Execute();
		}
	}
}
