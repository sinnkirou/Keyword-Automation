package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.CheckBox;

public class CheckBoxBehavior extends ControlBehavior {

	public CheckBoxBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		
		CheckBox target = (CheckBox) this.facet.getTarget();
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_CHECK;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_CHECK:{
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.check();
				}
			});
			
			return null;
		}
		case SystemConstants.BEHAVIOR_WATI_TO_CHECK:{
			target.waitForCheck();
			return null;
		}
		default:
			return super.Execute();
		}
	}

}
