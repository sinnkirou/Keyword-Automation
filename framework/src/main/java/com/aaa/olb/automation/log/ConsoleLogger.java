package com.aaa.olb.automation.log;

public class ConsoleLogger implements ActionLogger{

	@Override
	public void info(Object sender,BaseAction action) {
		// TODO Auto-generated method stub
		String message = String.format("%s", action.toString());
		System.out.println(String.format("%s%s", LoggerHelper.formatConsoleLog("INFO"), message));
		Log.info(message);
	}

	@Override
	public void warn(Object sender,BaseAction action) {
		// TODO Auto-generated method stub
		String message = String.format("%s", action.toString());
		System.out.println(String.format("%s%s", LoggerHelper.formatConsoleLog("WARN"), message));
		Log.warn(message);
	}

	@Override
	public void warn(Object sender,BaseAction action, String warning) {
		// TODO Auto-generated method stub
		String message = String.format("%s | %s",  action.toString(), warning);
		System.out.println(String.format("%s%s| %s", LoggerHelper.formatConsoleLog("WARN"), message, warning));
		Log.warn(message);
	}

	@Override
	public void error(Object sender,BaseAction action) {
		// TODO Auto-generated method stub
		String message =String.format("%s",  action.toString());
		System.out.println(String.format("%s%s", LoggerHelper.formatConsoleLog("ERROR"), message));
		Log.error(message);
	}

	@Override
	public void error(Object sender,BaseAction action, String message) {
		// TODO Auto-generated method stub
		String msg = String.format("%s | %s",  action.toString(), message);
		System.out.println(String.format("%s%s| %s", LoggerHelper.formatConsoleLog("ERROR"), action.toString(), message));
		Log.error(msg);
	}

	@Override
	public void error(Object sender,BaseAction action, String message, Exception ex) {
		// TODO Auto-generated method stub
		String msg = String.format("%s | %s | %s",  action.toString(), message, ex.getLocalizedMessage());
		System.out.println(String.format("%s%s| %s | %s", LoggerHelper.formatConsoleLog("ERROR"), action.toString(), message, ex.getLocalizedMessage()));
		Log.error(msg);
		ex.printStackTrace(System.out);
	}

}
