package com.aaa.olb.automation.customizedBehaviors;

import com.aaa.olb.automation.behaviors.BehaviorFacet;
import com.aaa.olb.automation.behaviors.ControlBehavior;
import com.aaa.olb.automation.components.CCMDatePicker;
import com.aaa.olb.automation.configuration.SystemConstants;

public class CCMDatePickerBehavior extends ControlBehavior {

	public CCMDatePickerBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		CCMDatePicker target = (CCMDatePicker) this.facet.getTarget();
		String parameter = this.facet.getParameters()[0].toString();

		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}
		switch (behaviorName.toLowerCase()) {
		case CustomizedBehaviorConstants.SELECT_FIRST_DATE_OF_NEXT_MONTH:
			target.input.focus();
			target.nextMonthBtn.click();
			target.firstDate.click();
			return null;
		default:
			return super.Execute();
		}
	}
}
