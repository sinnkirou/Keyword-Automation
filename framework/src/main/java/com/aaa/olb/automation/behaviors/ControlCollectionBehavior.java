package com.aaa.olb.automation.behaviors;

import java.util.List;

import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.TestHelper;

public class ControlCollectionBehavior implements Behavior {

	protected BehaviorFacet facet;

	public ControlCollectionBehavior(BehaviorFacet facet) {
		this.facet = facet;
	}

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub
		List<?> target = (List<?>) this.facet.getTarget();
		String behaviorName = this.facet.getBehaviorName();
		String parameter = (String) this.facet.getParameters()[0];
		if (behaviorName == null) {
			behaviorName = SystemConstants.LIST_BEHAVIOR_GET;
		}
		switch (behaviorName.toLowerCase()) {
		case SystemConstants.LIST_BEHAVIOR_GET: {
			int index = Integer.parseInt(parameter);
			return target.get(index);
		}
		case SystemConstants.LIST_BEHAVIOR_SIZE: {
			TestHelper.threadSleep();
			return target.size();
		}
		default: {
			Log.error(this.facet.getTarget() + " with action: " + this.facet.getBehaviorName());
			throw new UnsupportedOperationException("unable to finish this operation.");
		}
		}
	}
}
