package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Input;

public class InputBehavior extends ControlBehavior {

	public InputBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	public Object Execute() throws Exception {
		Input target = (Input) this.facet.getTarget();
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_ENTER;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_ENTER:{
			String text = this.facet.getParameters()[0].toString();
			Boolean blur = this.facet.getBlur();
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.enter(text, blur);
				}
			});
			if(text != null && text != "")
				target.waitForNotEmptyAttribute("value");
			return null;
		}
		case SystemConstants.BEHAVIOR_VALUE:{
			return target.getValue();
		}
		case SystemConstants.BEHAVIOR_CLEAR:{
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.clear();
				}
			});
			
			return null;
		}
		default:
			return super.Execute();
		}
	}

}
