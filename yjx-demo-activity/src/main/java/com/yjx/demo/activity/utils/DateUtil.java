package com.yjx.demo.activity.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateToStr(Date date, String partaner) {
		SimpleDateFormat sdf = new SimpleDateFormat(partaner);
		return sdf.format(date);
	}


	public static Date strToDate(String dateStr, String partaner) {
		SimpleDateFormat sdf = new SimpleDateFormat(partaner);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
