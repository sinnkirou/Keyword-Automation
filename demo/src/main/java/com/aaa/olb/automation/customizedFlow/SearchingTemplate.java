package com.aaa.olb.automation.customizedFlow;

/*
 * if any other action should be performed by default at the end of each flow
 * override SimpleFlowTemplate (next button is clicked by default in SimpleFlowTemplate) 
 * (such as click next button or redirect button), 
 * */
public class SearchingTemplate extends SimpleFlowTemplate {
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return FlowNames.SEARCHING;
	}
}
