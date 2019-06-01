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
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_CLICK;
		}
		switch (this.facet.getBehaviorName()) {
		case SystemConstants.BEHAVIOR_TEXT:{
			return target.getText();
		}
		case SystemConstants.BEHAVIOR_WATI_TO_PRESENT:{
			String text = (String)this.facet.getParameters()[0];
			target.waitTextToBePresented(text);
			return null;
		}
		case SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT_BY_INDEX:{
			String text = (String)this.facet.getParameters()[0];
			target.selectPartialContextByIndex(text);
			return null;
		}
		default:
			return super.Execute();
		}
	}

}
