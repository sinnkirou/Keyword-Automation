package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Control;

public class ControlBehavior implements Behavior {

	protected BehaviorFacet facet;

	public ControlBehavior(BehaviorFacet facet) {
		this.facet = facet;
	}

	/**
	 *
	 * execute the action, 
	 * 
	 * perform the default behavior defined in Control type,
	 * 
	 * otherwise invoke it by reflect, 
	 * 
	 * see more on BehaviorReflect.action(facet)
	 *
	 */
	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		Control target = (Control) this.facet.getTarget();
		String behaviorName = this.facet.getBehaviorName();
		String parameter = (String) this.facet.getParameters()[0];
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}
		if (behaviorName.contains("[css]") || behaviorName.contains("[attr]")) {
			parameter = behaviorName.substring(behaviorName.indexOf("(") + 1, behaviorName.length() - 1);
			behaviorName = behaviorName.substring(0, behaviorName.indexOf("("));
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_CLICK: {
			target.waitForClickable();
			target.click();
			return null;
		}
		case SystemConstants.BEHAVIOR_DOUBLECLICK: {
			target.waitForClickable();
			target.doubleClick();
			return null;
		}
		case SystemConstants.BEHAVIOR_FOCUS: {
			target.focus();
			return null;
		}
		case SystemConstants.BEHAVIOR_HOVER: {
			target.hover();
			return null;
		}
		case SystemConstants.BEHAVIOR_WAIT_FOR_VISIBLE: {
			target.waitForVisible();
			return null;
		}
		case SystemConstants.BEHAVIOR_WATI_TO_CLICK: {
			target.waitForClickable();
			return null;
		}
		case SystemConstants.BEHAVIOR_WATI_TO_HIDE: {
			target.waitForHidden();
			return null;
		}
		case SystemConstants.BEHAVIOR_ATTRIBUTE: {
			target.threadSleep();
			return target.getAttribute(parameter);
		}
		case SystemConstants.BEHAVIOR_STYLE: {
			target.threadSleep();
			return target.getCssValue(parameter);
		}
		case SystemConstants.BEHAVIOR_CLASS: {
			target.threadSleep();
			return target.getClassName();
		}
		case SystemConstants.BEHAVIOR_CLASS_CONTAINS: {
			target.threadSleep();
			return target.getClassName();
		}
		case SystemConstants.BEHAVIOR_VISIBLE: {
			return target.visible();
		}
		case SystemConstants.BEHAVIOR_CLICK_IF_VISIBLE: {
			if (target.visible())
				target.click();
			return null;
		}
		case SystemConstants.BEHAVIOR_CLICK_IF_ENABLED: {
			if (target.enabled())
				target.click();
			return null;
		}
		case SystemConstants.BEHAVIOR_DRAG_AND_DROP_BY_OFFSET: {
			String newParameter = parameter;
			target.dragAndDropByOffset(newParameter);
			return null;
		}
		case SystemConstants.BEHAVIOR_CLICK_AND_HOLD: {
			target.clickAndHold();
			return null;
		}
		case SystemConstants.BEHAVIOR_RIGHT_CLICK: {
			target.rightClick();
			return null;
		}
		case SystemConstants.BEHAVIOR_SEND_KEY_BY_ROBOT: {
			target.sendKeyByRobot(parameter);
			return null;
		}
		case SystemConstants.BEHAVIOR_SEND_KEY: {
			target.sendKey(parameter);
			return null;
		}
		case SystemConstants.BEHAVIOR_TAKE_SCREENSHOT: {
			return null;
		}
		case SystemConstants.BEHAVIOR_CLICK_BY_JS: {
			target.clickByJS();
			return null;
		}
		case SystemConstants.BEHAVIOR_THREAD_SLEEP_BY_MINUTES: {
			target.threadSleepByMinutes(parameter);
			return null;
		}
		default:
			return BehaviorReflect.action(facet);
		}
	}
}
