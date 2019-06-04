package com.aaa.olb.automation.behaviors;

public class ListItemBehaviorFacet extends BehaviorFacet {
	
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setDefault(BehaviorFacet facet) {
		this.setTarget(facet.getTarget());
		this.setBehaviorName(facet.getBehaviorName());
		this.setParameters(facet.getParameters());
		this.setShouldDelay(facet.getShouldDelay());
		this.setShouldWait(facet.getShouldWait());
		this.setBlur(facet.getBlur());
	}
	
}
