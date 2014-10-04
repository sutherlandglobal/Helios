package com.sutherland.helios.date.parsing.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.date.parsing.DateParser;

public class DateParserTest extends TestCase
{
	private DateParser dp;

	protected void setUp()
	{
		dp = new DateParser();
	}

	@Test
	public void testMonthLookup()
	{
		assertEquals("1", dp.monthLookup("Jan"));
		assertEquals("2", dp.monthLookup("Feb"));
		assertEquals("3", dp.monthLookup("Mar"));
		assertEquals("4", dp.monthLookup("Apr"));
		assertEquals("5", dp.monthLookup("May"));
		assertEquals("6", dp.monthLookup("Jun"));
		assertEquals("7", dp.monthLookup("Jul"));
		assertEquals("8", dp.monthLookup("Aug"));
		assertEquals("9", dp.monthLookup("Sep"));
		assertEquals("10", dp.monthLookup("Oct"));
		assertEquals("11", dp.monthLookup("Nov"));
		assertEquals("12", dp.monthLookup("Dec"));

		assertEquals("Jan", dp.monthLookup("1"));
		assertEquals("Feb", dp.monthLookup("2"));
		assertEquals("Mar", dp.monthLookup("3"));
		assertEquals("Apr", dp.monthLookup("4"));
		assertEquals("May", dp.monthLookup("5"));
		assertEquals("Jun", dp.monthLookup("6"));
		assertEquals("Jul", dp.monthLookup("7"));
		assertEquals("Aug", dp.monthLookup("8"));
		assertEquals("Sep", dp.monthLookup("9"));
		assertEquals("Oct", dp.monthLookup("10"));
		assertEquals("Nov", dp.monthLookup("11"));
		assertEquals("Dec", dp.monthLookup("12"));
	}

	@Test
	public void testGetPeriod()
	{
		String testDateString = "10/31/2010 12:00:00";
		String period = "AM";
		GregorianCalendar testDate = DateParser.convertExcelDateToGregorian(testDateString + " " + period );
		
		assertEquals("Test " + period, period, DateParser.getPeriod(testDate));
		
		period = "PM";
		testDate = DateParser.convertExcelDateToGregorian(testDateString + " " + period );
		assertEquals("Test " + period, period, DateParser.getPeriod(testDate));
		
		testDate = new GregorianCalendar();
		testDate.set(Calendar.HOUR_OF_DAY, 0);
		testDate.set(Calendar.MINUTE, 0);
		testDate.set(Calendar.SECOND, 0);
		
		int startDay = testDate.get(Calendar.DAY_OF_WEEK);
		
		
		String testPeriod;
		while(startDay == testDate.get(Calendar.DAY_OF_WEEK))
		{
			if(testDate.get(Calendar.HOUR_OF_DAY) <= 11)
			{
				testPeriod = "AM";
			}
			else
			{
				testPeriod = "PM";
			}
			
			assertEquals( "Time Period test: " + DateParser.toSQLDateFormat(testDate) + " => " + testPeriod, testPeriod, DateParser.getPeriod(testDate));
			
			testDate.add(Calendar.SECOND, 1);
		}
	}
	
	@Test
	public void testGetMinutesBetween()
	{
		GregorianCalendar startDate = new GregorianCalendar();
		GregorianCalendar endDate = new GregorianCalendar();

		//1301227200
		//Sun 27 Mar 2011 08:00:00 AM EST
		long epochTestDate = 1301227200;

		startDate.setTimeInMillis(epochTestDate * 1000);
		endDate.setTimeInMillis(epochTestDate * 1000);

		//same date
		assertEquals((double)0, DateParser.getMinutesBetween(startDate, endDate));

		//hourish spanning
		endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY)+1);
		assertEquals((double)60, DateParser.getMinutesBetween(startDate, endDate));

		//before <-> after
		assertEquals((double)60, DateParser.getMinutesBetween(endDate, startDate));

		//day spanning
		endDate.setTimeInMillis(epochTestDate * 1000);
		endDate.set(Calendar.DAY_OF_MONTH,  endDate.get(Calendar.DAY_OF_MONTH)+1);
		assertEquals((double)(24*60), DateParser.getMinutesBetween(startDate, endDate));

		//month spanning, 31 days in starting month
		//march 27 -> april 27 (31 days)
		endDate.setTimeInMillis(epochTestDate * 1000);
		endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH)+1);
		assertEquals((double)(24*60*startDate.getActualMaximum(Calendar.DAY_OF_MONTH)), DateParser.getMinutesBetween(startDate, endDate));

		//month spanning, 30 days in starting month
		//april 27 - may 27 (30 days)
		startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH)+ 2);
		assertEquals((double)(24*60*endDate.getActualMaximum(Calendar.DAY_OF_MONTH)), DateParser.getMinutesBetween(startDate, endDate));

		//reset both
		startDate.setTimeInMillis(epochTestDate * 1000);
		endDate.setTimeInMillis(epochTestDate * 1000);

		//year spanning

		//1298894400
		//Mon 28 Feb 2011 07:00:00 AM EST
		long leapYearTestDate = 1298894400;
		startDate.setTimeInMillis(leapYearTestDate * 1000);
		endDate.setTimeInMillis(leapYearTestDate * 1000);

		//non-leap year spanning. 2 days -> 48 hours
		endDate.set(Calendar.DAY_OF_YEAR, endDate.get(Calendar.DAY_OF_YEAR) + 2);
		assertEquals((double)(2*24*60), DateParser.getMinutesBetween(startDate, endDate));

		//leap year spanning. 3 days -> 72 hours
		endDate.setTimeInMillis(leapYearTestDate * 1000);
		endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR) + 1);
		startDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR));
		endDate.set(Calendar.DAY_OF_YEAR, endDate.get(Calendar.DAY_OF_YEAR) + 3);
		assertEquals((double)(3*24*60), DateParser.getMinutesBetween(startDate, endDate));

	}

	@Test
	public void testToSQLDateFormat()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01 17:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-10 17:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-28 17:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-01 17:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-29 17:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-10-29 18:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-12-29 17:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:    1325139138
		//DATE: 12 / 29 / 2011 @ 01:12:18 EST
		epochTestDate = 1325139138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-12-29 01:12:18", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1325221201
		//DATE: 12 / 30 / 11 @ 00:00:01pm EST
		epochTestDate = 1325221201;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-12-30 00:00:01", DateParser.toSQLDateFormat(testDate));

		//TIME STAMP:  1325221200
		//DATE: 12 / 30 / 11 @ 00:00:00pm EST
		epochTestDate = 1325221200;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-12-30 00:00:00", DateParser.toSQLDateFormat(testDate));

		//			//TIME STAMP:     1293861599   
		//			//DATE: 12 / 31 / 10 @ 12:59:59pm EST
		//			epochTestDate =  1293861599 ;
		//			testDate.setTimeInMillis(epochTestDate*1000);
		//			assertEquals("2010-12-31 23:59:59", Parser.readableGregorian(testDate));
	}
	
	@Test
	public void testToExcelDateFormat()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("01/01/2011 05:12:18 PM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("01/10/2011 05:12:18 PM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("02/28/2011 05:12:18 PM", DateParser.toExcelDateFormat(testDate));
		
		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("03/01/2011 05:12:18 PM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("02/29/2012 05:12:18 PM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("10/29/2011 06:12:18 PM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("12/29/2011 05:12:18 PM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:    1325139138
		//DATE: 12 / 29 / 2011 @ 01:12:18 EST
		epochTestDate = 1325139138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("12/29/2011 01:12:18 AM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1325221201
		//DATE: 12 / 30 / 11 @ 00:00:01pm EST
		epochTestDate = 1325221201;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("12/30/2011 12:00:01 AM", DateParser.toExcelDateFormat(testDate));

		//TIME STAMP:  1325221200
		//DATE: 12 / 30 / 11 @ 00:00:00pm EST
		epochTestDate = 1325221200;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("12/30/2011 12:00:00 AM", DateParser.toExcelDateFormat(testDate));
	}

	@Test
	public void testConvertSQLDateToGregorian()
	{
		//mysql -> gregorian -> readablegregorian

		String testDate = "2010-10-31 00:00:00";
		assertEquals(testDate, DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(testDate)));

		testDate = "2010-09-01 02:03:03";
		assertEquals(testDate, DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(testDate)));

		testDate = "2011-01-01 00:00:00";
		assertEquals(testDate, DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(testDate)));

		testDate = "2012-02-29 23:59:59";
		assertEquals(testDate, DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(testDate)));

		testDate = "2011-02-28 23:59:59";
		assertEquals(testDate, DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(testDate)));

		testDate = "2011-02-29 23:59:59";
		assertEquals("Impossible date: 2011-03-01 23:59:59", "2011-03-01 23:59:59", DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(testDate)));

		testDate = "a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a ";
		assertNull("Long array of text", DateParser.convertSQLDateToGregorian(testDate));

		testDate = ""; 
		assertNull("Empty String", DateParser.convertSQLDateToGregorian(testDate));

		testDate = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"; 
		assertNull("Semicolon String", DateParser.convertSQLDateToGregorian(testDate));

		testDate = "3333-20-60 34:99:99";
		assertNull("Really impossible date", DateParser.convertSQLDateToGregorian(testDate));

		testDate = "abcd-10-ef gh:34:ji";
		assertNull("Proper format - bad values", DateParser.convertSQLDateToGregorian(testDate));
	}
	
	@Test
	public void testConvertExcelDateToGregorian()
	{
		//excel -> gregorian -> excel date

		String testDate = "10/31/2010 12:00:00 AM";
		assertEquals(testDate, DateParser.toExcelDateFormat(DateParser.convertExcelDateToGregorian(testDate)));

		testDate = "09/01/2010 02:03:03 AM";
		assertEquals(testDate, DateParser.toExcelDateFormat(DateParser.convertExcelDateToGregorian(testDate)));

		testDate = "01/01/2010 12:00:00 AM";
		assertEquals(testDate, DateParser.toExcelDateFormat(DateParser.convertExcelDateToGregorian(testDate)));

		testDate = "02/29/2012 11:59:59 PM";
		assertEquals(testDate, DateParser.toExcelDateFormat(DateParser.convertExcelDateToGregorian(testDate)));

		testDate = "02/28/2011 11:59:59 PM";
		assertEquals(testDate, DateParser.toExcelDateFormat(DateParser.convertExcelDateToGregorian(testDate)));

		testDate = "02/29/2011 11:59:59 PM";
		assertEquals("Impossible date: 03/01/2011 11:59:59 PM", "03/01/2011 11:59:59 PM", DateParser.toExcelDateFormat(DateParser.convertExcelDateToGregorian(testDate)));

		testDate = "a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a ";
		assertNull("Long array of text", DateParser.convertExcelDateToGregorian(testDate));

		testDate = ""; 
		assertNull("Empty String", DateParser.convertExcelDateToGregorian(testDate));

		testDate = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"; 
		assertNull("Semicolon String", DateParser.convertExcelDateToGregorian(testDate));

		testDate = "20/60/3333 34:99:99 ZF";
		assertNull("Really impossible date", DateParser.convertExcelDateToGregorian(testDate));

		testDate = "10/ef/abcd gh:34:ji %%";
		assertNull("Proper format - bad values", DateParser.convertExcelDateToGregorian(testDate));
	}
	
	@Test
    public void testConvertMSAccessDateToGregorian()
    {
            // format Mon Oct 25 14:36:00 EDT 2010

            //ms access date string -> gregorian -> sql

            String testDate = "Mon Oct 25 14:36:00 EDT 2010";

            assertEquals("2010-10-25 14:36:00", DateParser.toSQLDateFormat(dp.convertMSAccessDateToGregorian(testDate)) );

            //bad day name, but it should be irrelevant in the conversion
            testDate = "Tue Oct 25 14:36:00 EDT 2010";
            assertEquals("2010-10-25 14:36:00", DateParser.toSQLDateFormat(dp.convertMSAccessDateToGregorian(testDate)) );

            //bad day name, but it should be irrelevant in the conversion
            testDate = "Tue Feb 02 04:06:00 EDT 2010";
            assertEquals("2010-02-02 04:06:00", DateParser.toSQLDateFormat(dp.convertMSAccessDateToGregorian(testDate)) );
    }
}
