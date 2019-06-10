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
		// TODO Auto-generated method stub
		ListItemBehaviorFacet itemFacet= (ListItemBehaviorFacet)this.facet;
		List<?> target = (List<?>) this.facet.getTarget();
		Object item=  target.get(itemFacet.getIndex());
		BehaviorFacet targetFacet=new BehaviorFacet();
		targetFacet.setBehaviorName(itemFacet.getBehaviorName());
		targetFacet.setParameters(itemFacet.getParameters());
		targetFacet.setTarget(item);
		targetFacet.setBlur(itemFacet.getBlur());
		BehaviorProvider provider = null;
		try {
			provider = BehaviorRepository.getInstance().getBehaviorProvider(item.getClass());
			Behavior behavior=provider.get(targetFacet);
			return behavior.Execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
		throw new NotFoundException("unable to finish this operation");
	}

}
