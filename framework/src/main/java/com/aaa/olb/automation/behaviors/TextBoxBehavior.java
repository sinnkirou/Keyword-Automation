package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Textbox;

public class TextBoxBehavior extends ControlBehavior {


	public TextBoxBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		Textbox target = (Textbox)this.facet.getTarget();
		String behaviorName=this.facet.getBehaviorName();
		String parameter = (String) this.facet.getParameters()[0];
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_CLICK;
		}
		switch (this.facet.getBehaviorName().toLowerCase()) {
		case SystemConstants.BEHAVIOR_TEXT:{
			return target.getText();
		}
		case SystemConstants.BEHAVIOR_WATI_TO_PRESENT:{
			target.waitTextToBePresented(parameter);
			return null;
		}
		case SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTENT_BY_INDEX:{
			target.selectPartialContentByIndex(parameter);
			return null;
		}
		case SystemConstants.BEHAVIOR_TEXT_CONTAINS:{
			return target.getText().contains(parameter);
		}
		default:
			return super.Execute();
		}
	}

}
