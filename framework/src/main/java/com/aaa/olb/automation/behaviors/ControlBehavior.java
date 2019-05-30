package com.aaa.olb.automation.behaviors;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.controls.Control;

public class ControlBehavior implements Behavior {

	protected BehaviorFacet facet;

	public ControlBehavior(BehaviorFacet facet) {
		this.facet = facet;
	}

	/*
	 * execute the action,
	 * perform the default behavior defined in Control type,
	 * otherwise invoke it by reflect, see more on BehaviorReflect.action(facet)
	 * */
	@Override
	public Object Execute() throws Exception {
		// TODO Auto-generated method stub
		Control target = (Control) this.facet.getTarget();
		String behaviorName = this.facet.getBehaviorName();
		if (behaviorName == null) {
			behaviorName = SystemConstants.BEHAVIOR_CLICK;
		}

		switch (behaviorName.toLowerCase()) {
		case SystemConstants.BEHAVIOR_CLICK: {
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.waitForClickable();
					target.click();
				}
			});
			return null;
		}
		case SystemConstants.BEHAVIOR_DOUBLECLICK: {
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.waitForClickable();
					target.doubleClick();
				}
			});
			return null;
		}
		case SystemConstants.BEHAVIOR_FOCUS: {
			behaves(new ControlAction() {

				@Override
				public void act() {
					target.focus();
				}
			});

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
			String attribute = (String) this.facet.getParameters()[0];
			return target.getAttribute(attribute);
		}
		case SystemConstants.BEHAVIOR_STYLE: {
			String styleName = (String) this.facet.getParameters()[0];
			return target.getCssValue(styleName);
		}
		case SystemConstants.BEHAVIOR_CLASS: {
			return target.getClassName();
		}
		case SystemConstants.BEHAVIOR_CLICK_IF_VISIBLE: {
			behaves(new ControlAction() {

				@Override
				public void act() {
					if (target.visible())
						target.click();
				}
			});

			return null;
		}
		case SystemConstants.BEHAVIOR_DRAG_AND_DROP_BY_OFFSET: {
			String text = (String) this.facet.getParameters()[0];
			target.dragAndDropByOffset(text);;
			return null;
		}
		case SystemConstants.BEHAVIOR_CLICK_AND_HOLD: {
			target.clickAndHold();
			return null;
		}
		case SystemConstants.BEHAVIOR_SEND_KEY: {
			String keyname = (String) this.facet.getParameters()[0];
			target.sendKey(keyname);
			return null;
		}
		default:
			return BehaviorReflect.action(facet);
		}
	}

	public void behaves(ControlAction func) throws InterruptedException {
		if (this.facet.getAsync()) {
			Thread.sleep(RuntimeSettings.getInstance().getAsyncTimeout() * 1000);
		}
		func.act();
	}

}
