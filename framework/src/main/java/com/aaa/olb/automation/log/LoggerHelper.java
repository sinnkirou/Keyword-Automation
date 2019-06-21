package com.aaa.olb.automation.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerHelper {

	public static String getPrefix() {
		return String.format("[%d]=>", Thread.currentThread().getId());
	}

	public static String formatConsoleLog(String level) {
		return String.format("[%s][%s][%s]=>%s", level,
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "console", getPrefix());
	}
}
