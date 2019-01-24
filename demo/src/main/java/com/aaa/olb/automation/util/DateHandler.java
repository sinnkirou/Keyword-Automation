package com.aaa.olb.automation.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
	public String getFormattedDateByDay(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, i);
		return sdf.format(c.getTime());
	}
	
	public String getFormattedDateByMonth(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, i);
		return sdf.format(c.getTime());
	}
	
	public String getFormattedDateByYear(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, i);
		return sdf.format(c.getTime());
	}
}
