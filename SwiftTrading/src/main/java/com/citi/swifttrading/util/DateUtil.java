package com.citi.swifttrading.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date addMin(Date date,int min) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MINUTE, min);
			return c.getTime();
	}
	public static Date addDay(Date date,int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
}
}
