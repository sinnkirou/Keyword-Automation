package com.aaa.olb.automation.utils;

public class SystemProperty {

	public static Boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			return true;
		}
		return false;
	}

	public static String getPlatform() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.startsWith("win")) {
			return "windows";
		} else if (os.startsWith("linux")) {
			return "linux";
		} else if (os.startsWith("mac")) {
			return "mac";
		}
		return "unknown";
	}

	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getWorkingDir() {
		return System.getProperty("user.dir");
	}
}
