package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlAction;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.RichTextBox;
import com.aaa.olb.automation.configuration.SystemConstants;

public class RichTextBoxBehavior extends ControlBehavior {

	public RichTextBoxBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() throws Exception {
		// TODO Auto-generated method stub
		RichTextBox target = (RichTextBox) this.facet.getTarget();
		String text = this.facet.getParameters()[0].toString();
		
		String behaviorName=this.facet.getBehaviorName();
		if(behaviorName == null) {
			behaviorName= SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT:
			if (text != "") {
				behaves(new ControlAction() {

					@Override
					public void act() {
						target.waitForVisible();
						target.selectPartialContext(text);
					}
				});
				
			}
			return null;
		default:
			return super.Execute();
		}
	}
}
