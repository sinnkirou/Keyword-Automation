package com.aaa.olb.automation.behaviors;

import java.util.List;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.log.Log;

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
				int index= Integer.parseInt(parameter);
				return target.get(index);
			}
			case SystemConstants.LIST_BEHAVIOR_SIZE: {
				return target.size();
			}
			default: {
				throw new UnsupportedOperationException("unable to action this operation.");
			}
		}
	}

	private void threadSleep() {
		try {
			Thread.sleep(RuntimeSettings.getInstance().getWaitOrDelayTimeout() * 1000);
			Log.info("waited: " + RuntimeSettings.getInstance().getWaitOrDelayTimeout());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getCause().getMessage());
		}
	}

	public void behaves(ControlAction func) {
		if (this.facet.getShouldDelay()) {
			threadSleep();
		}
		func.act();
		if (this.facet.getShouldWait()) {
			threadSleep();
		}
	}
}
