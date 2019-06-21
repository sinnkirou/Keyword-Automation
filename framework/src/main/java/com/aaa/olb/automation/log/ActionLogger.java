package com.aaa.olb.automation.log;

public interface ActionLogger {

	void info(Object sender, BaseAction action);

	void warn(Object sender, BaseAction action);

	void warn(Object sender, BaseAction action, String warning);

	void error(Object sender, BaseAction action);

	void error(Object sender, BaseAction action, String message);

	void error(Object sender, BaseAction action, String message, Exception ex);

}
