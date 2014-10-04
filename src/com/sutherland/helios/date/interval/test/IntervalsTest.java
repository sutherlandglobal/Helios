package com.sutherland.helios.date.interval.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.date.interval.Intervals;
import com.sutherland.helios.date.parsing.DateParser;

public class IntervalsTest extends TestCase 
{
	private final static String START_DATE = "NOW";		//test the next year's worth of seconds
	private final int testPeriodMonths = 1;

	/**
	 * 
	 * 
	 *  (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()
	{
		
	}

	@Test
	public void testNextYearsIntervalGeneration()
	{
		//test every second over the next year
		//60 *60 * 24 * 365
		GregorianCalendar thisDate = DateParser.convertSQLDateToGregorian(START_DATE);
		GregorianCalendar endDate = DateParser.convertSQLDateToGregorian(START_DATE);
		
		//end one day after the next leap year
		endDate.add(Calendar.MONTH, testPeriodMonths);
		
		
		//ThreadPool pool = null;
		try
		{
			//pool = new ThreadPool(30);
			
			while(thisDate.before(endDate))
			{

				final GregorianCalendar testDate = thisDate;
				thisDate.add(Calendar.SECOND, 1);

				testValidIntervalGeneration(testDate);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
//			if(pool != null)
//			{
//				pool.close();
//			}
		}
	}
	
	@Test
	private static void testValidIntervalGeneration(GregorianCalendar date)
	{
		//System.out.println(DateParser.toSQLDateFormat(date));
		
		Intervals intervals = new Intervals(START_DATE );
		
		String startDate, endDate;
		
		//only applicable between years 0 and 3000
		String regex = "[01234][0-9][0-9][0-9]\\-[01][0-9]\\-[0123][0-9]\\ [012][0-9]\\:[012345][0-9]\\:[012345][0-9]";
		
		for(String intervalName : intervals.getAvaliableIntervalNames())
		{
			startDate = intervals.getTimeInterval(intervalName).getStartDate();
			endDate = intervals.getTimeInterval(intervalName).getEndDate();
			
			assertTrue("Interval " + intervalName + " test for date: " + DateParser.toSQLDateFormat(date) + ", with interval " + intervalName, 
					startDate.matches(regex) &&
					endDate.matches(regex)
				);
		}
	}
}
