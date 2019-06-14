package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.RichTextBox;
import com.aaa.olb.automation.configuration.SystemConstants;

public class RichTextBoxBehavior extends ControlBehavior {

	public RichTextBoxBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		RichTextBox target = (RichTextBox) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();

		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT_BY_CONTEXT;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTEXT_BY_CONTEXT:
			if (parameter != "") {
				target.waitForVisible();
				target.selectPartialContextByContext(parameter);
			}
			return null;
		case SystemConstants.BEHAVIOR_SELECT_PARTIAL_CONTENT_BY_INDEX:
			if (parameter != "") {
				target.waitForVisible();
				target.selectPartialContentByIndex(parameter);
			}
			return null;
		case CustomizedBehaviorConstants.DRAG_AND_DROP_RICH_TEXTBOX:
			if (parameter != "") {
				target.waitForVisible();
				target.drogAndDropRichBox(parameter);
			}
			return null;
		default:
			return super.Execute();
		}
	}
}
