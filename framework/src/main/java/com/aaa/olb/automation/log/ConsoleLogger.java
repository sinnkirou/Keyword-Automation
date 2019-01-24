package com.aaa.olb.automation.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements ActionLogger{
	
	private String getPrefix(String level){
		return String.format("[%s][%s][%d][%s]=>", level, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), Thread.currentThread().getId(),"console");
	}

	@Override
	public void info(Object sender,BaseAction action) {
		// TODO Auto-generated method stub
		String message = String.format("%s%s", this.getPrefix("INFO"), action.toString());
		System.out.println(message);
		Log.info(message);
	}

	@Override
	public void warn(Object sender,BaseAction action) {
		// TODO Auto-generated method stub
		String message = String.format("%s%s", this.getPrefix("WARN"), action.toString());
		System.out.println(message);
		Log.warn(message);
	}

	@Override
	public void warn(Object sender,BaseAction action, String warning) {
		// TODO Auto-generated method stub
		String message = String.format("%s%s | %s", this.getPrefix("WARN"), action.toString(), warning);
		System.out.println(message);
		Log.warn(message);
	}

	@Override
	public void error(Object sender,BaseAction action) {
		// TODO Auto-generated method stub
		String message =String.format("%s%s", this.getPrefix("ERROR"), action.toString());
		System.out.println(message);
		Log.error(message);
	}

	@Override
	public void error(Object sender,BaseAction action, String message) {
		// TODO Auto-generated method stub
		String msg = String.format("%s%s | %s", this.getPrefix("ERROR"), action.toString(), message);
		System.out.println(msg);
		Log.error(msg);
	}

	@Override
	public void error(Object sender,BaseAction action, String message, Exception ex) {
		// TODO Auto-generated method stub
		String msg = String.format("%s%s | %s", this.getPrefix("ERROR"), action.toString(), message);
		System.out.println(msg);
		Log.error(msg);
		ex.printStackTrace(System.out);
	}

}
