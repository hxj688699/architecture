package com.feinno.architecture.util.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	private DateFormatUtil(){};
	private final static String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	public static String long2string(long time){
		Date date = new Date(time);
		DateFormat format = new SimpleDateFormat(FORMAT_STR);
		return format.format(date);
	}
}
