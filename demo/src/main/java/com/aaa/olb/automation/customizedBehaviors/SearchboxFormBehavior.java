package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlAction;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.SearchboxForm;
import com.aaa.olb.automation.configuration.SystemConstants;

/*
 * generate different actions cases depends on each given behavior name defined in BehaviorIndication
 * */
public class SearchboxFormBehavior extends ControlBehavior {

	public SearchboxFormBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		SearchboxForm target = (SearchboxForm) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();
		
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_ENTER;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_ENTER:
			if (parameter != "") {
				behaves(new ControlAction() {

					@Override
					public void act() {
						target.getKeyword().enter(parameter);
						target.getSearchButton().click();
					}
				});
				
			}
			return null;
		default:
			return super.Execute();
		}
	}

}
