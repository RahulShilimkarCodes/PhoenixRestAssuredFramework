package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	private DateTimeUtil()	{}
	
	public static String getTimeWithPastDays(int days)
	{
		return Instant.now().minus(days, ChronoUnit.DAYS).toString();
	}
	
	public static String getTimeWithPastHours(int hours)
	{
		return Instant.now().minus(hours, ChronoUnit.HOURS).toString();
	}
	
	public static String getTimeWithPastMinutes(int minute)
	{
		return Instant.now().minus(minute, ChronoUnit.MINUTES).toString();
	}
	
	public static String getTimeWithPastMonths(int months)
	{
		return Instant.now().minus(months, ChronoUnit.MONTHS).toString();
	}

}
