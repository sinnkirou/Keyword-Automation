package com.aaa.olb.automation.utils;

public class SystemProperty {

	public static Boolean isWindows() {
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			return true;
		}
		return false;
	}
	
	public static String getWorkingDir() {
		return System.getProperty("user.dir");
	}
}
