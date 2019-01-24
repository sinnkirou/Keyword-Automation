package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlAction;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.SearchboxForm;
import com.aaa.olb.automation.configuration.SystemConstants;

public class SearchboxFormBehavior extends ControlBehavior {

	public SearchboxFormBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object Execute() throws Exception {
		// TODO Auto-generated method stub
		SearchboxForm target = (SearchboxForm) this.facet.getTarget();
		String text = this.facet.getParameters()[0].toString();
		
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_ENTER;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_ENTER:
			if (text != "") {
				behaves(new ControlAction() {

					@Override
					public void act() {
						target.getKeyword().enter(text);
						target.getSearchButton().click();
					}
				});
				
			}
			return null;
		default:
			return null;
		}
	}

}
