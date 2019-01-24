package com.aaa.olb.automation.behaviors;

public interface Behavior {

	Object Execute() throws Exception;
	
	void behaves(ControlAction func) throws InterruptedException;
}
