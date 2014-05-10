package helios.data.granularity.time;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

public class TimeGrains 
{
	public static final LinkedHashMap<String, String> avaliableTimeGrains = new LinkedHashMap<String, String>()
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4157353852074203745L;
		{
			put("Yearly", "" + YEARLY_GRANULARITY);
			put("Fiscal Yearly", "" + FISCAL_YEARLY_GRANULARITY );
			put("Quarterly", "" + QUARTERLY_GRANULARITY);
			put("Fiscal Quarterly", "" + FISCAL_QUARTERLY_GRANULARITY);
			put("Monthly", "" + MONTHLY_GRANULARITY);
			put("Weekly", "" + WEEKLY_GRANULARITY);
			put("Daily", "" + DAILY_GRANULARITY);
			put("Hourly", "" + HOURLY_GRANULARITY);
		}
	};

	public final static int FISCAL_YEARLY_GRANULARITY = 7;
	public final static int YEARLY_GRANULARITY = 0;
	public final static int MONTHLY_GRANULARITY = 1;
	public final static int QUARTERLY_GRANULARITY = 5;
	public final static int FISCAL_QUARTERLY_GRANULARITY = 6;
	public final static int WEEKLY_GRANULARITY = 2;
	public final static int DAILY_GRANULARITY = 3;
	public final static int HOURLY_GRANULARITY = 4;
	
	public TimeGrains()
	{}

	/**
	 * Determine the date grain for the provided date's day, in the form of YYYY-MM-DD. 
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The day granularity.
	 */
	private static String getDayGrain(GregorianCalendar date)
	{
		//YYYY-MM
		String dayGrain = getMonthGrain(date) + "-";

		if(date.get(Calendar.DAY_OF_MONTH) < 10)
		{
			dayGrain += "0";
		}

		dayGrain += date.get(Calendar.DAY_OF_MONTH);

		return dayGrain;
	}

	/**
	 * Determine the date grain for the provided date's month, in the form of YYYY-MM. 
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The month granularity.
	 */
	private static String getMonthGrain(GregorianCalendar date)
	{
		//YYYY-MM
		String monthGrain = ""+ date.get(Calendar.YEAR) + "-";

		if(date.get(Calendar.MONTH)+1 < 10)
		{
			monthGrain += "0";
		}
		monthGrain += (date.get(Calendar.MONTH)+1);

		return monthGrain;
	}

	/**
	 * Determine the date grain for the provided date's week, in the form of YYYY-MM-WW. 
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The week granularity.
	 */
	private static String getWeekGrain(GregorianCalendar date)
	{
		//YYYY-MM-Week of year

		String weekGrain = getMonthGrain(date) + "-";
		
		if(date.get(Calendar.WEEK_OF_YEAR) < 10)
		{
			weekGrain += "0";
		}
		weekGrain += date.get(Calendar.WEEK_OF_YEAR); 

		return weekGrain;
	}
	
	/**
	 * Determine the date grain for the provided date's quarter, in the form of YYYY-0Q.
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The quarter granularity.
	 */
	private static String getQuarterGrain(GregorianCalendar date)
	{
		//YYYY-0Q
		
		String quarterGrain = getYearGrain(date) + "-0";

		if(date.get(Calendar.MONTH) >= 0 && (date.get(Calendar.MONTH) <= 2 ))
		{
			quarterGrain += 1;
		}
		else if(date.get(Calendar.MONTH) >= 3 && (date.get(Calendar.MONTH) <= 5 ))
		{
			quarterGrain += 2;
		}
		else if(date.get(Calendar.MONTH) >= 6 && (date.get(Calendar.MONTH) <= 8 ))
		{
			quarterGrain += 3;
		}
		else if(date.get(Calendar.MONTH) >= 9 && (date.get(Calendar.MONTH) <= 11 ))
		{
			quarterGrain += 4;
		}

		return quarterGrain;
	}
	
	/**
	 * Determine the date grain for the provided date's fiscal quarter, in the form of YYYY-0Q.
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The quarter granularity.
	 */
	private static String getFiscalQuarterGrain(GregorianCalendar date)
	{
		//YYYY-0Q
		
		String quarterGrain = getFiscalYearGrain(date) + "-0";

		//fiscal year starts 7/1/thisYear every year and a quarter is 3months later's first day
		
		//7-1 -> 9-30
		//10-1 -> 12-31
		//1-1 -> 3-31
		//4-1 -> 6/30  
		if(date.get(Calendar.MONTH) >= 6 && (date.get(Calendar.MONTH) <= 8 ))
		{
			quarterGrain += 1;
		}
		else if(date.get(Calendar.MONTH) >= 9 && (date.get(Calendar.MONTH) <= 11 ))
		{
			quarterGrain += 2;
		}
		else if(date.get(Calendar.MONTH) >= 0 && (date.get(Calendar.MONTH) <= 2 ))
		{
			quarterGrain += 3;
		}
		else if(date.get(Calendar.MONTH) >= 3 && (date.get(Calendar.MONTH) <= 5 ))
		{
			quarterGrain += 4;
		}

		return quarterGrain;
	}

	private static String getFiscalYearGrain(GregorianCalendar date) 
	{
		//7-1 -> 6/30  
		
		GregorianCalendar newDate = date;
		
		String fiscalYearGrain = null;
		
		if(newDate.get(Calendar.MONTH) >= 1 && newDate.get(Calendar.MONTH) <= 6 )
		{
			//previous fiscal year, sub 1 for fiscal
			newDate.add(Calendar.YEAR, -1);	
		}
		
		fiscalYearGrain = "" + newDate.get(Calendar.YEAR) ;
		
		return fiscalYearGrain;
	}

	/**
	 * Determine the date grain for the provided date's year, in the form of YYYY. 
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The year granularity.
	 */
	private static String getYearGrain(GregorianCalendar date)
	{
		return "" + date.get(Calendar.YEAR);
	}
	
	/**
	 * Determine the date grain for the provided date's Hour, in the form of YYYY-MM-DD_HH:00:00. 
	 * 
	 * @param date	The date to determine the grain for.
	 * 
	 * @return	The Hour granularity.
	 */
	private static String getHourGrain(GregorianCalendar date)
	{
		//YYYY-MM-W?W

		String hourGrain = getDayGrain(date) + " ";
		if( date.get(Calendar.HOUR_OF_DAY) < 10)
		{
			hourGrain += "0";

		}
		hourGrain += date.get(Calendar.HOUR_OF_DAY) + ":00:00"; 

		return hourGrain;
	}

	public static String getDateGrain(int dateGrain, GregorianCalendar date)
	{
		String retval = null;

		switch(dateGrain)
		{
		case FISCAL_YEARLY_GRANULARITY:
			retval = getFiscalYearGrain(date);
			break;
		case YEARLY_GRANULARITY:
			retval = getYearGrain(date);
			break;
		 case MONTHLY_GRANULARITY:
			retval = getMonthGrain(date);
			break;
		 case QUARTERLY_GRANULARITY:
			retval = getQuarterGrain(date);
			break;
		 case FISCAL_QUARTERLY_GRANULARITY:
			retval = getFiscalQuarterGrain(date);
			break;
		 case WEEKLY_GRANULARITY:
			retval = getWeekGrain(date);
			break;
		 case DAILY_GRANULARITY:
			retval = getDayGrain(date);
			break;
		 case HOURLY_GRANULARITY:
			retval = getHourGrain(date);
			break;
		default:
			retval = getMonthGrain(date);
			break;
		}

		return retval;
	}
}
