/**
 * 
 */
package com.sutherland.helios.date.parsing;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import com.sutherland.helios.date.formatting.DateFormatter;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

/**
 * Library for any date manipulation, conversion, and calculation. Any database work with a lot of reporting on date granularity will have plenty of this.
 * 
 * @author Jason Diamond
 */
public class DateParser 
{

	private LinkedHashMap<String,Integer> monthNameToValue;
	private LinkedHashMap<Integer,String> valueToMonthName;

	private LinkedHashMap<Integer,String> valueToDOW;
	
	public static final String NOW_DATE_KEYWORD = "NOW";

	/**
	 * 	Build the date parser, and load up hashes for the easy Month <-> Month number associations.
	 */
	public DateParser() 
	{
		monthNameToValue = new LinkedHashMap<String,Integer>();
		valueToMonthName = new LinkedHashMap<Integer,String>();
		valueToDOW = new LinkedHashMap<Integer,String>();

		monthNameToValue.put("Jan", 1);
		monthNameToValue.put("Feb", 2);
		monthNameToValue.put("Mar", 3);
		monthNameToValue.put("Apr", 4);
		monthNameToValue.put("May", 5);
		monthNameToValue.put("Jun", 6);
		monthNameToValue.put("Jul", 7);
		monthNameToValue.put("Aug", 8);
		monthNameToValue.put("Sep", 9);
		monthNameToValue.put("Oct", 10);
		monthNameToValue.put("Nov", 11);
		monthNameToValue.put("Dec", 12);

		valueToDOW.put(1, "Sun");
		valueToDOW.put(2, "Mon");
		valueToDOW.put(3, "Tue");
		valueToDOW.put(4, "Wed");
		valueToDOW.put(5, "Thu");
		valueToDOW.put(6, "Fri");
		valueToDOW.put(7, "Sat");

		for(String monthName : monthNameToValue.keySet())
		{
			valueToMonthName.put(monthNameToValue.get(monthName), monthName);
		}
	}

	/**
	 * Retrieve the Month name <-> Month index bindings.
	 * 
	 * @param month	Either an integer to lookup the month with, or a string to lookup the index with.
	 * 
	 * @return	The month index corresponding to the month name, or vice versa depending on the actual type of the month util.parameter. 
	 */
	public String monthLookup(String month)
	{
		month = StringSanitizer.sanitize(month, 4);
		
		String retval = "";
		try
		{
			retval = valueToMonthName.get(Integer.parseInt(month));
		}
		catch (NumberFormatException e)
		{
			retval = monthNameToValue.get(month).toString();
		}
		finally
		{
			if (retval.equals(""))
			{
				System.err.println("Error looking up " + month + " in month to index mappings");
			}
		}

		return retval;
	}

	/**
	 * Determine the distance in minutes between two dates. Start and end are interchangable since this is essentially a distance measurement.	
	 * 
	 * @param startDate	Demarcation 1 of the interval.
	 * @param endDate		Demarcation 2 of the interval.
	 * 
	 * @return 	The distance in minutes between two dates.	
	 */
	public static double getMinutesBetween(GregorianCalendar startDate, GregorianCalendar endDate)
	{

		double retval = -1L;

		if(startDate != null && endDate != null)
		{
			if(startDate.equals(endDate))
			{
				retval = 0L;
			}
			else if(startDate.before(endDate))
			{
				retval = (endDate.getTime().getTime() - startDate.getTime().getTime());
			}
			else
			{
				retval = ( startDate.getTime().getTime() - endDate.getTime().getTime() );

			}
			retval /= 60000; //ms -> s, s-> m
		}

		return retval;
	}

	/**
	 * Format a GregorianCalendar into something more readable, specifically in the form "YYYY-MM-DD HH:MM:SS".
	 * 
	 * @param date	The date to convert.
	 * 
	 * @return	A string representing a GregorianCalendar.
	 */
	public static String toSQLDateFormat(GregorianCalendar date)
	{
		StringBuilder retval = new StringBuilder();

		retval.append(date.get(Calendar.YEAR));
		retval.append("-");

		if(date.get(Calendar.MONTH)+1 < 10)
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.MONTH)+1);
		retval.append("-");

		if(date.get(Calendar.DAY_OF_MONTH) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.DAY_OF_MONTH));
		retval.append(" ");

		if(date.get(Calendar.HOUR_OF_DAY) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.HOUR_OF_DAY));
		retval.append(":");

		if(date.get(Calendar.MINUTE) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.MINUTE));
		retval.append(":");


		if(date.get(Calendar.SECOND) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.SECOND));

		return retval.toString();
	}
	
	public static String convertToString(GregorianCalendar date, int dateFormat)
	{
		String retval = null;
		
		switch(dateFormat)
		{
		case DateFormatter.EXCEL_FORMAT:
			retval = toExcelDateFormat(date);
			break;
		case DateFormatter.SQL_FORMAT:
		default:
			retval = toSQLDateFormat(date);
			break;
		}

		return retval;
		
	}
	
	public static String toExcelDateFormat(GregorianCalendar date)
	{
		//		MM/DD/YYYY hh:MM:SS AM/PM
		StringBuilder retval = new StringBuilder();

		if(date.get(Calendar.MONTH)+1 < 10)
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.MONTH)+1);
		retval.append("/");

		if(date.get(Calendar.DAY_OF_MONTH) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.DAY_OF_MONTH));
		retval.append("/");
		
		retval.append(date.get(Calendar.YEAR));
		retval.append(" ");

		int hourOfDay = date.get(Calendar.HOUR_OF_DAY);
		
		if(hourOfDay == 0 || hourOfDay == 12)
		{
			hourOfDay = 12;
		}
		else
		{
			hourOfDay %= 12;
		}
		
		if(hourOfDay < 10) 
		{
			retval.append("0");
		}
		retval.append(hourOfDay);
		retval.append(":");

		if(date.get(Calendar.MINUTE) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.MINUTE));
		retval.append(":");


		if(date.get(Calendar.SECOND) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.SECOND));
		
		retval.append(" ");
		retval.append(getPeriod(date));

		return retval.toString();
	}
	
	public static String getPeriod(GregorianCalendar date)
	{
		String retval = "AM";
		int ampm = date.get(Calendar.AM_PM);
		
		if(ampm == Calendar.PM)
		{
			retval = "PM";
		}
		
		return retval;
	}

	/**
	 * Convert an SQL date (2010-06-27 00:00:00) to a GregorianCalendar date.
	 * 
	 * @param date	The date string to convert.
	 * 
	 * @return	The converted date, or null if the conversion failed.
	 */
	public static GregorianCalendar convertSQLDateToGregorian(String date)
	{
		//dates in YYYY-MM-DD HH:MM:SS
		
		GregorianCalendar retval = null;

		if( StringSanitizer.isValidSQLDate(date))
		{
			if(date.equalsIgnoreCase("now"))
			{
				retval = new GregorianCalendar();
			}
			else
			{
				try
				{
					//cheers to dishonest javadocs
					//0-based stupid
					String[] dateComponents = date.split(" ");
					String[] dateFields = dateComponents[0].split("-");
					String[] timeFields = dateComponents[1].split(":");
	
					retval = new GregorianCalendar
					(
									Integer.parseInt(dateFields[0]),
									Integer.parseInt(dateFields[1]) -1,
									Integer.parseInt(dateFields[2]),
									Integer.parseInt(timeFields[0]),
									Integer.parseInt(timeFields[1]),
									(int) Double.parseDouble(timeFields[2])	//truncate tenths of a second in mysql timestamps
					);
				}
				catch(Exception e)
				{
					System.err.println("Error converting date string to gregorian date" + date);
					throw e;
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("Date parameter is not in expected format");
		}

		return retval;
	}
	
    /**
     * Convert an excel date to a GregorianCalendar date.
     * 
     * @param date  The date string to convert.
     * 
     * @return      The converted date, or null if the conversion failed.
     */
	public GregorianCalendar convertMSAccessDateToGregorian(String date)
	{
		GregorianCalendar retval = null;

		date = StringSanitizer.sanitize(date,30);

		if(date.equalsIgnoreCase("now"))
		{
			retval = new GregorianCalendar();
		}
		else
		{
			//dates in YYYY-MM-DD HH:MM:SS
			//Wed Nov 03 21:35:00 EDT 2010
			//
			//date is [1]-[2]-[5] [3] from splt

			String[] dateComponents= null;
			String[] dateFields = null;
			String[] timeFields= null;

			try
			{
				dateComponents = date.split(" ");
				dateFields = new String[]{dateComponents[5], monthLookup(dateComponents[1]), dateComponents[2]};
				timeFields = dateComponents[3].split(":");

				//cheers to dishonest javadocs
				//0-based stupid

				retval = new GregorianCalendar
				(
					Integer.parseInt(dateFields[0]),
					Integer.parseInt(dateFields[1]) -1,
					Integer.parseInt(dateFields[2]),
					Integer.parseInt(timeFields[0]),
					Integer.parseInt(timeFields[1]),
					(int) Double.parseDouble(timeFields[2]) //truncate tenths of a second in mysql timestamps
				);
			}
			catch(Exception e)
			{
				System.err.println("Error converting " + date);
				throw e;
			}
		}

		return retval;
	}
	
	public static GregorianCalendar convertDateToGregorian(String date)
	{
		if ( StringSanitizer.isValidSQLDate(date))
		{
			return convertSQLDateToGregorian(date);
		}
		else //if( StringSanitizer.isValidExcelDate(date)
		{
			return convertExcelDateToGregorian(date);
		}
	}

	public static GregorianCalendar convertExcelDateToGregorian(String date)
	{
		//dates in MM/DD/YYYY hh:MM:SS AM/PM
		
		GregorianCalendar retval = null;

		if( StringSanitizer.isValidExcelDate(date))
		{
			if(date.equalsIgnoreCase("now"))
			{
				retval = new GregorianCalendar();
			}
			else
			{
				try
				{
					//cheers to dishonest javadocs
					//0-based stupid
					String[] dateComponents = date.split(" ");
					String[] dateFields = dateComponents[0].split("/");
					String[] timeFields = dateComponents[1].split(":");
	
					//set the date
					retval = new GregorianCalendar
					(
									Integer.parseInt(dateFields[2]),
									Integer.parseInt(dateFields[0]) -1,
									Integer.parseInt(dateFields[1])
					);
					
					//manually set the time
					
					//seriously, fuck gregoriancalendar
					int hour = Integer.parseInt(timeFields[0]);
					
					if(hour == 12)
					{
						hour = 0;
					}
					
					retval.set(Calendar.HOUR, hour);
					retval.set(Calendar.MINUTE, Integer.parseInt(timeFields[1]));
					retval.set(Calendar.SECOND, Integer.parseInt(timeFields[2]));
					
					//set am/pm from date components
					if(dateComponents[2].equals("PM"))
					{
						retval.set(Calendar.AM_PM, Calendar.PM);
					}
					else
					{
						retval.set(Calendar.AM_PM, Calendar.AM);
					}
				}
				catch(Exception e)
				{
					System.err.println("Error converting date string to gregorian date" + date);
					throw e;
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("Date parameter is not in expected format");
		}

		return retval;
	}

	public static int compareDateGrains(String dateGrain1, String dateGrain2) 
	{
		//normalize the strings and return the compareTo
		//normalization will look like yyyy-mm-dd hh:mm:ss, with hours converted to 24 scale 
		
		//need same granularity in both grains, cannot compare days (2014-03-27) to weeks (2014-03-51) 
		
		//if we can't normalize, return zero 
		int retval = 0;
		
		
		//determine if we have nonsense or not
		//biggest grain is year; we expect at least 4 digits
		if(dateGrain1 != null && dateGrain2 != null && dateGrain1.length() >= 4 && dateGrain2.length() >= 4)
		{
			//determine format
			if(dateGrain1.contains("/") && dateGrain2.contains("/"))
			{
				//date will have either one / or two
				String[] dateFields1 = dateGrain1.split("\\/");
				String[] dateFields2 = dateGrain2.split("\\/");

				String normalizedDate1, normalizedDate2;
				
				if(dateFields1.length == 2 && dateFields2.length == 2)
				{
					//04/2010
					
					normalizedDate1 = (dateFields1[1] + "-" + dateFields1[0]);
					normalizedDate2 = (dateFields2[1] + "-" + dateFields2[0]);
					
					retval = normalizedDate1.compareTo(normalizedDate2);
				}
				else if(dateFields1.length == 3 && dateFields2.length == 3)
				{
					//03/27/1985
					//03/27/1985 11:00:00 PM

					if(dateGrain1.contains(" ") && dateGrain2.contains(" "))
					{
						//03/27/1985 11:00:00 PM
						
						//full timestamp so just convert and compare
						retval = DateParser.convertExcelDateToGregorian(dateGrain1).compareTo(DateParser.convertExcelDateToGregorian(dateGrain2));
					}
					else
					{
						normalizedDate1 = dateFields1[2] + "-" + dateFields1[1] + "-" + dateFields1[0];
						normalizedDate2 = dateFields2[2] + "-" + dateFields2[1] + "-" + dateFields2[0];
						
						retval = normalizedDate1.compareTo(normalizedDate2);
					}
				}
				else
				{
					throw new IllegalArgumentException("Invalid date grain in date grain comparison");
				}
			}
			else if(dateGrain1.contains("-") && dateGrain2.contains("-"))
			{
				retval = dateGrain1.compareTo(dateGrain2);
			}
			else
			{
				//compare just the year
				retval = dateGrain1.compareTo(dateGrain2);
			}
		}
		else
		{
			//malformed or unusable strings
			throw new IllegalArgumentException("Invalid date grain in date grain comparison");
		}
		
		return retval;
	}
}
