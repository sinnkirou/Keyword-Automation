package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.NotFoundException;

import com.aaa.olb.automation.log.Log;

public class ControlCollectionItemBehavior extends ControlBehavior {

	public ControlCollectionItemBehavior(BehaviorFacet facet) {
		super(facet);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unused")
	private Object getTarget() throws Exception {
		Method method = List.class.getMethod("get", Integer.class);
		ListItemBehaviorFacet itemFacet= (ListItemBehaviorFacet)this.facet;
		return method.invoke(itemFacet.getTarget(), itemFacet.getIndex());
	}
	
	@Override
	public Object Execute() {
		ListItemBehaviorFacet itemFacet= (ListItemBehaviorFacet)this.facet;
		List<?> target = (List<?>) this.facet.getTarget();
		BehaviorFacet targetFacet=new BehaviorFacet();
		BehaviorProvider provider = null;
		try {
			Object item = target.get(itemFacet.getIndex());
			targetFacet.setBehaviorName(itemFacet.getBehaviorName());
			targetFacet.setParameters(itemFacet.getParameters());
			targetFacet.setTarget(item);
			targetFacet.setBlur(itemFacet.getBlur());
			provider = BehaviorRepository.getInstance().getBehaviorProvider(item.getClass());
			Behavior behavior=provider.get(targetFacet);
			return behavior.Execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String message = this.facet.getTarget() +" with action: " + this.facet.getBehaviorName() + "\n" + e.getLocalizedMessage();
			Log.error(message);
			throw new NotFoundException("unable to finish this operation\n" + message);
		}
	}

}
