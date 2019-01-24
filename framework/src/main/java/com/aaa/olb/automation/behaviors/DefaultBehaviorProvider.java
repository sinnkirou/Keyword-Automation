package com.aaa.olb.automation.behaviors;

public class DefaultBehaviorProvider implements BehaviorProvider {

	@Override
	public Behavior get(BehaviorFacet facet) {
		// TODO Auto-generated method stub
		return BehaviorRepository.getInstance().getBuiltIn(facet);
	}

}
