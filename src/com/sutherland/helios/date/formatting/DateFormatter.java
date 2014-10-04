package com.sutherland.helios.date.formatting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import com.sutherland.helios.data.granularity.time.TimeGrains;
import com.sutherland.helios.date.parsing.DateParser;

public abstract class DateFormatter 
{
	public final static int SQL_FORMAT = 0;
	public final static int EXCEL_FORMAT = 1;	
	
	public static final LinkedHashMap<String, String> avaliableDateFormats = new LinkedHashMap<String, String>()
	{
		private static final long serialVersionUID = 3206537418971004168L;
		{
			put("YYYY-MM-DD HH:MM:SS", "" + SQL_FORMAT);
			put("MM/DD/YYYY hh:MM:SS AM/PM", "" + EXCEL_FORMAT);
		}
	};
	
	
	private static String getDayString(GregorianCalendar date)
	{
		int day = date.get(Calendar.DAY_OF_MONTH);
		String retval =  null;
		if(day < 10)
		{
			retval = "0" + day;
		}
		else
		{
			retval = ""+ day;
		}
		
		return retval;
	}
	
	private static String getMonthString(GregorianCalendar date)
	{
		//stupid zero-based months
		int month = date.get(Calendar.MONTH)+1;
		String retval =  null;
		if(month < 10)
		{
			retval = "0" + month;
		}
		else
		{
			retval = "" + month;
		}
		
		return retval;
	}
	
	private static String getWeekString(GregorianCalendar date)
	{
		int week = date.get(Calendar.WEEK_OF_YEAR);
		String retval =  null;
		if(week < 10)
		{
			retval = "0" + week;
		}
		else
		{
			retval = ""+ week;
		}
		
		return retval;
	}
	
	private static String getFiscalYearString(GregorianCalendar date)
	{
		//7-1 -> 6/30  
		GregorianCalendar newDate = date;
		
		String retval = null;
		
		if(newDate.get(Calendar.MONTH) >= 1 && newDate.get(Calendar.MONTH) <= 6 )
		{
			//previous fiscal year, sub 1 for fiscal
			newDate.add(Calendar.YEAR, -1);	
		}
		
		retval = "" + newDate.get(Calendar.YEAR) ;
		
		return retval;
	}
	
	private static String getQuarterString(GregorianCalendar date)
	{
		String retval = "0";
		if(date.get(Calendar.MONTH) >= 0 && (date.get(Calendar.MONTH) <= 2 ))
		{
			retval += 1;
		}
		else if(date.get(Calendar.MONTH) >= 3 && (date.get(Calendar.MONTH) <= 5 ))
		{
			retval += 2;
		}
		else if(date.get(Calendar.MONTH) >= 6 && (date.get(Calendar.MONTH) <= 8 ))
		{
			retval += 3;
		}
		else if(date.get(Calendar.MONTH) >= 9 && (date.get(Calendar.MONTH) <= 11 ))
		{
			retval += 4;
		}
		
		return retval;
	}
	
	private static String getFiscalQuarterString(GregorianCalendar date)
	{
		String retval = "0";
		//fiscal year starts 7/1/thisYear every year and a quarter is 3months later's first day
		
		//7-1 -> 9-30
		//10-1 -> 12-31
		//1-1 -> 3-31
		//4-1 -> 6/30  
		if(date.get(Calendar.MONTH) >= 6 && (date.get(Calendar.MONTH) <= 8 ))
		{
			retval += "1";
		}
		else if(date.get(Calendar.MONTH) >= 9 && (date.get(Calendar.MONTH) <= 11 ))
		{
			retval += "2";
		}
		else if(date.get(Calendar.MONTH) >= 0 && (date.get(Calendar.MONTH) <= 2 ))
		{
			retval += "3";
		}
		else if(date.get(Calendar.MONTH) >= 3 && (date.get(Calendar.MONTH) <= 5 ))
		{
			retval += "4";
		}
		
		return retval;
	}
	
	private static String get24HourString(GregorianCalendar date)
	{
		String retval = null; 
		int hourOfDay = date.get(Calendar.HOUR_OF_DAY);
		
		if(hourOfDay < 10) 
		{
			retval =  "0";
		}
		else
		{
			retval = "";
		}
		retval += hourOfDay;
		
		return retval;
	}
	
	private static String get12HourString(GregorianCalendar date)
	{
		int hourOfDay = date.get(Calendar.HOUR_OF_DAY);
		
		String retval = null;
		
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
			retval =  "0";
		}
		else
		{
			retval = "";
		}
		
		retval += hourOfDay;
		
		return retval;
	}
	
	/**
	 * Determine the date grain for the provided date's day, in the form of YYYY-MM-DD. 
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The day granularity.
	 */
	private static String getDayGrain(GregorianCalendar date, int dateFormat)
	{
		String retval = null;
		
		switch(dateFormat)
		{
		case EXCEL_FORMAT:
			//MM/DD/YYYY
			retval = getMonthString(date) + "/" + getDayString(date) + "/" + getYearGrain(date, dateFormat);
			break;
		case SQL_FORMAT:
		default:
			//YYYY-MM-DD
			retval = getMonthGrain(date, dateFormat) + "-" + getDayString(date);
			break;
		}

		return retval;
	}

	/**
	 * Determine the date grain for the provided date's month, in the form of YYYY-MM. 
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The month granularity.
	 */
	private static String getMonthGrain(GregorianCalendar date, int dateFormat)
	{
		String retval = null;
		
		switch(dateFormat)
		{
		case EXCEL_FORMAT:
			//MM/YYYY
			retval = getMonthString(date) + "/" + getYearGrain(date, dateFormat);
			break;
		case SQL_FORMAT:
		default:
			//YYYY-MM
			retval = getYearGrain(date, dateFormat) + "-" + getMonthString(date);
			break;
		}

		return retval;
	}

	/**
	 * Determine the date grain for the provided date's week, in the form of YYYY-MM-WW. 
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The week granularity.
	 */
	private static String getWeekGrain(GregorianCalendar date, int dateFormat)
	{
		//YYYY-MM-Week of year
		String retval = null;
		
		switch(dateFormat)
		{
		case EXCEL_FORMAT:
			//MM/YYYY
			retval = getMonthString(date) + "/" + getWeekString(date) + "/" + getYearGrain(date, dateFormat);
			break;
		case SQL_FORMAT:
		default:
			//YYYY-MM
			retval = getMonthGrain(date, dateFormat) + "-" +  getWeekString(date);
			break;
		}

		return retval;
	}
	
	/**
	 * Determine the date grain for the provided date's quarter, in the form of YYYY-0Q.
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The quarter granularity.
	 */
	private static String getQuarterGrain(GregorianCalendar date, int dateFormat)
	{
		//YYYY-0Q
		
		String retval = null;
		
		switch(dateFormat)
		{
		case EXCEL_FORMAT:
			//MM/YYYY
			retval = getQuarterString(date) + "/" + getYearGrain(date, dateFormat);
			break;
		case SQL_FORMAT:
		default:
			//YYYY-MM
			retval = getYearGrain(date, dateFormat) + "-" + getQuarterString(date);
			break;
		}

		return retval;
	}
	
	/**
	 * Determine the date grain for the provided date's fiscal quarter, in the form of YYYY-0Q.
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The quarter granularity.
	 */
	private static String getFiscalQuarterGrain(GregorianCalendar date, int dateFormat)
	{
		//YYYY-0Q
		String retval = null;
		
		int year = Integer.parseInt( getYearGrain(date, dateFormat) );
		
		if(date.get(Calendar.MONTH) >= 1 && date.get(Calendar.MONTH) <= 6 )
		{
			year--;
		}
		
		String fqString =  getFiscalQuarterString(date);
		
		switch(dateFormat)
		{
		case EXCEL_FORMAT:
			//MM/YYYY
			retval = fqString + "/" + year;
			break;
		case SQL_FORMAT:
		default:
			//YYYY-MM
			retval = ""+ year + "-" + fqString;
			break;
		}

		return retval;
	}

	private static String getFiscalYearGrain(GregorianCalendar date, int dateFormat) 
	{
		return getFiscalYearString(date);
	}

	/**
	 * Determine the date grain for the provided date's year, in the form of YYYY. 
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The year granularity.
	 */
	private static String getYearGrain(GregorianCalendar date, int dateFormat)
	{
		return "" + date.get(Calendar.YEAR);
	}
	
	/**
	 * Determine the date grain for the provided date's Hour, in the form of YYYY-MM-DD_HH:00:00. 
	 * 
	 * @param date	The date to determine the grain for.
	 * @param dateFormat 
	 * 
	 * @return	The Hour granularity.
	 */
	private static String getHourGrain(GregorianCalendar date, int dateFormat)
	{
		String retval = null;
		
		switch(dateFormat)
		{
		case EXCEL_FORMAT:
			//MM/DD/YYYY hh:00:00 am/pm
			retval = getMonthString(date) + "/" + getDayString(date) + "/" + getYearGrain(date, dateFormat) + " " + get12HourString(date) + ":00:00 " + DateParser.getPeriod(date);
			break;
		case SQL_FORMAT:
		default:
			//YYYY-MM-DD HH:00:00
			retval = getDayGrain(date, dateFormat) + " " + get24HourString(date) + ":00:00";  
			break;
		}

		return retval;
	}

	public static String getFormattedDate(GregorianCalendar date, int dateGrain, int dateFormat)
	{
		String retval = null;

		switch(dateGrain)
		{
		case TimeGrains.FISCAL_YEARLY_GRANULARITY:
			retval = getFiscalYearGrain(date, dateFormat);
			break;
		case TimeGrains.YEARLY_GRANULARITY:
			retval = getYearGrain(date, dateFormat);
			break;
		case TimeGrains.QUARTERLY_GRANULARITY:
			retval = getQuarterGrain(date, dateFormat);
			break;
		case TimeGrains.FISCAL_QUARTERLY_GRANULARITY:
			retval = getFiscalQuarterGrain(date, dateFormat);
			break;
		case TimeGrains.WEEKLY_GRANULARITY:
			retval = getWeekGrain(date, dateFormat);
			break;
		case TimeGrains.DAILY_GRANULARITY:
			retval = getDayGrain(date, dateFormat);
			break;
		case TimeGrains.HOURLY_GRANULARITY:
			retval = getHourGrain(date, dateFormat);
			break;
		case TimeGrains.MONTHLY_GRANULARITY:
		default:
			retval = getMonthGrain(date, dateFormat);
			break;
		}

		return retval;
	}
	
	public static String getFormattedDate(GregorianCalendar date, int dateGrain)
	{
		return getFormattedDate(date, dateGrain, SQL_FORMAT);
	}
}
