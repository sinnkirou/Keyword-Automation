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
	public Object Execute(){
		// TODO Auto-generated method stub
		CCMOrRPMPanel target = (CCMOrRPMPanel) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();
		
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_CLICK;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_CLICK:
			if (parameter != "") {
				behaves(new ControlAction() {

					@Override
					public void act() {
						if(parameter.equals("CCM")) {
							target.getCCMButton().waitForVisible();
							target.getCCMButton().click();
						}else if(parameter.equals("RPM")) {
							target.getRPMButton().waitForVisible();
							target.getRPMButton().click();
						}
					}
				});
				
			}
			return null;
		default:
			return super.Execute();
		}
	}
}
