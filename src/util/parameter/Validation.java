package util.parameter;

import java.util.GregorianCalendar;

import util.date.DateParser;

/*
 * @Jason Diamond
 * 
 * Extremely generic util.parameter validation in the form of utility functions.
 * An implementor would have their own Parameter validation class, and reference methods here to assist. 
 * 
 */
public abstract class Validation 
{
	private static String NOW_DATE = "now";
	
	/**
	 * Validate the report's time grain util.parameter.
	 *  
	 * @return	True if the time grain util.parameter are valid and viable to run the report. False otherwise.
	 */
	public static boolean hasValidTimeGrain(int timeGrain)
	{
		return
		(
			timeGrain == DateParser.YEARLY_GRANULARITY || 
			timeGrain == DateParser.QUARTERLY_GRANULARITY || 
			timeGrain == DateParser.MONTHLY_GRANULARITY || 
			timeGrain == DateParser.WEEKLY_GRANULARITY || 
			timeGrain == DateParser.DAILY_GRANULARITY || 
			timeGrain == DateParser.HOURLY_GRANULARITY
		);
	}
	
	/**
	 * Validate the report's type util.parameter.
	 *  
	 * @return	True if the report type util.parameter are valid and viable to run the report. False otherwise.
	 */
	public static boolean hasValidReportType(int reportType, int[] acceptableTypes)
	{
		boolean retval = false;
		
		for(int type : acceptableTypes)
		{
			if(type == reportType)
			{
				retval = true;
				break;
			}
		}
		
		return retval;
	}
	
	public static String validateDate(String date)
	{
		DateParser dateParser= null;
		String retval = null; 
		try
		{
			dateParser = new DateParser();
			GregorianCalendar now = new GregorianCalendar();
			
			if(date.equalsIgnoreCase(NOW_DATE))
			{
				retval = dateParser.readableGregorian(now);
			}
			
			retval = dateParser.readableGregorian(dateParser.convertSQLDateToGregorian(date));
			
		}
		catch (Throwable t) 
		{
			retval = null;
		}
		
		return retval;
	}
}
