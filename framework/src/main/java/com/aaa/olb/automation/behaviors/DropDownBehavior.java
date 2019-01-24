package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.DropDown;

public class DropDownBehavior extends ControlBehavior {

	public DropDownBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object Execute() throws Exception {
		// TODO Auto-generated method stub
		DropDown target = (DropDown) this.facet.getTarget();
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_SELECT;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_SELECT:{
			String optionText= (String)this.facet.getParameters()[0];
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.selectByText(optionText);
					target.waitForNotEmptyAttribute("value");
				}
			});
			
			return null;
		}
		case SystemConstants.BEHAVIOR_INDEX:{
			int index=(int)this.facet.getParameters()[0];
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.selectByIndex(index);
					target.waitForNotEmptyAttribute("value");
				}
			});
			
			return null;
		}
		default:
			return super.Execute();
		}
	}

}
