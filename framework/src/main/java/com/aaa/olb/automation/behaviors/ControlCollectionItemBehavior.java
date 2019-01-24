package com.aaa.olb.automation.behaviors;

import java.lang.reflect.Method;
import java.util.List;

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
	public Object Execute() throws Exception {
		// TODO Auto-generated method stub
		ListItemBehaviorFacet itemFacet= (ListItemBehaviorFacet)this.facet;
		List<?> target = (List<?>) this.facet.getTarget();
		Object item=  target.get(itemFacet.getIndex());
		BehaviorFacet targetFacet=new BehaviorFacet();
		targetFacet.setBehaviorName(itemFacet.getBehaviorName());
		targetFacet.setParameters(itemFacet.getParameters());
		targetFacet.setTarget(item);
		targetFacet.setAsync(itemFacet.getAsync());
		targetFacet.setBlur(itemFacet.getBlur());
		BehaviorProvider provider=BehaviorRepository.getInstance().getBehaviorProvider(item.getClass());
		Behavior behavior=provider.get(targetFacet);
		
		return behavior.Execute();
	}

}
