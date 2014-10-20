package com.sutherland.helios.date.interval.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.date.interval.Interval;
import com.sutherland.helios.date.interval.Intervals;
import com.sutherland.helios.date.parsing.DateParser;

public class IntervalsTest extends TestCase 
{
	@Test
	public void testNewYearsIntervals()
	{
		String nowDate = "2010-01-01 00:00:00";
		Intervals intervals = new Intervals( nowDate  );
		
		String dateDesc = "New Years 2010";
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today start on " + dateDesc, nowDate, testInterval.getStartDate());
		assertEquals("Today end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.YESTERDAY_INTERVAL_NAME);
		assertEquals("Yesterday start on " + dateDesc, "2009-12-31 00:00:00", testInterval.getStartDate());
		assertEquals("Yesterday end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week start on " + dateDesc, "2009-12-28 00:00:00", testInterval.getStartDate());
		assertEquals("This week end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_WEEK_INTERVAL_NAME);
		assertEquals("Last week start on " + dateDesc, "2009-12-21 00:00:00", testInterval.getStartDate());
		assertEquals("Last week end on " + dateDesc,  "2009-12-28 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month start on " + dateDesc, nowDate, testInterval.getStartDate());
		assertEquals("This month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_MONTH_INTERVAL_NAME);
		assertEquals("Last month start on " + dateDesc,  "2009-12-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year start on " + dateDesc,  nowDate, testInterval.getStartDate());
		assertEquals("This year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_YEAR_INTERVAL_NAME);
		assertEquals("Last year start on " + dateDesc,  "2009-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter start on " + dateDesc,  nowDate, testInterval.getStartDate());
		assertEquals("This quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_QUARTER_INTERVAL_NAME);
		assertEquals("Last quarter start on " + dateDesc,  "2009-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter start on " + dateDesc, "2009-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FQ_INTERVAL_NAME);
		assertEquals("Last fiscal quarter start on " + dateDesc, "2009-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal quarter end on " + dateDesc, "2009-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year start on " + dateDesc, "2009-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FY_INTERVAL_NAME);
		assertEquals("Last fiscal year start on " + dateDesc, "2008-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal year end on " + dateDesc, "2009-07-01 00:00:00", testInterval.getEndDate());
	}
	
	@Test
	public void testArbitrary2014Date()
	{
		String nowDate = "2014-03-27 00:00:00";
		Intervals intervals = new Intervals( nowDate  );
		
		String dateDesc = "March 27 2014";
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today start on " + dateDesc, nowDate, testInterval.getStartDate());
		assertEquals("Today end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.YESTERDAY_INTERVAL_NAME);
		assertEquals("Yesterday start on " + dateDesc, "2014-03-26 00:00:00", testInterval.getStartDate());
		assertEquals("Yesterday end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week start on " + dateDesc, "2014-03-24 00:00:00", testInterval.getStartDate());
		assertEquals("This week end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_WEEK_INTERVAL_NAME);
		assertEquals("Last week start on " + dateDesc, "2014-03-17 00:00:00", testInterval.getStartDate());
		assertEquals("Last week end on " + dateDesc,  "2014-03-24 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month start on " + dateDesc, "2014-03-01 00:00:00", testInterval.getStartDate());
		assertEquals("This month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_MONTH_INTERVAL_NAME);
		assertEquals("Last month start on " + dateDesc,  "2014-02-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last month end on " + dateDesc,  "2014-03-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year start on " + dateDesc,  "2014-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_YEAR_INTERVAL_NAME);
		assertEquals("Last year start on " + dateDesc,  "2013-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last year end on " + dateDesc, "2014-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter start on " + dateDesc,  "2014-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_QUARTER_INTERVAL_NAME);
		assertEquals("Last quarter start on " + dateDesc,  "2013-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last quarter end on " + dateDesc, "2014-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter start on " + dateDesc, "2014-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FQ_INTERVAL_NAME);
		assertEquals("Last fiscal quarter start on " + dateDesc, "2013-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal quarter end on " + dateDesc, "2014-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year start on " + dateDesc, "2013-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FY_INTERVAL_NAME);
		assertEquals("Last fiscal year start on " + dateDesc, "2012-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal year end on " + dateDesc, "2013-07-01 00:00:00", testInterval.getEndDate());
	}
	
	@Test
	public void testArbitrarySunday()
	{
		String nowDate = "2014-10-05 00:00:00";
		Intervals intervals = new Intervals( nowDate  );
		
		String dateDesc = "Arbitrary Sunday Oct 05 2014";
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today start on " + dateDesc, nowDate, testInterval.getStartDate());
		assertEquals("Today end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.YESTERDAY_INTERVAL_NAME);
		assertEquals("Yesterday start on " + dateDesc, "2014-10-04 00:00:00", testInterval.getStartDate());
		assertEquals("Yesterday end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week start on " + dateDesc, "2014-09-29 00:00:00", testInterval.getStartDate());
		assertEquals("This week end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_WEEK_INTERVAL_NAME);
		assertEquals("Last week start on " + dateDesc, "2014-09-22 00:00:00", testInterval.getStartDate());
		assertEquals("Last week end on " + dateDesc,  "2014-09-29 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month start on " + dateDesc, "2014-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_MONTH_INTERVAL_NAME);
		assertEquals("Last month start on " + dateDesc,  "2014-09-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last month end on " + dateDesc,  "2014-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year start on " + dateDesc,  "2014-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_YEAR_INTERVAL_NAME);
		assertEquals("Last year start on " + dateDesc,  "2013-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last year end on " + dateDesc, "2014-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter start on " + dateDesc,  "2014-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_QUARTER_INTERVAL_NAME);
		assertEquals("Last quarter start on " + dateDesc,  "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last quarter end on " + dateDesc, "2014-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter start on " + dateDesc, "2014-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FQ_INTERVAL_NAME);
		assertEquals("Last fiscal quarter start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal quarter end on " + dateDesc, "2014-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FY_INTERVAL_NAME);
		assertEquals("Last fiscal year start on " + dateDesc, "2013-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal year end on " + dateDesc, "2014-07-01 00:00:00", testInterval.getEndDate());
	}
	
	@Test
	public void testArbitraryMonday()
	{
		String nowDate = "2014-10-06 00:00:00";
		Intervals intervals = new Intervals( nowDate  );
		
		String dateDesc = "Arbitrary Monday Oct 06 2014";
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today start on " + dateDesc, nowDate, testInterval.getStartDate());
		assertEquals("Today end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.YESTERDAY_INTERVAL_NAME);
		assertEquals("Yesterday start on " + dateDesc, "2014-10-05 00:00:00", testInterval.getStartDate());
		assertEquals("Yesterday end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week start on " + dateDesc, nowDate, testInterval.getStartDate());
		assertEquals("This week end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_WEEK_INTERVAL_NAME);
		assertEquals("Last week start on " + dateDesc, "2014-09-29 00:00:00", testInterval.getStartDate());
		assertEquals("Last week end on " + dateDesc,  nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month start on " + dateDesc, "2014-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_MONTH_INTERVAL_NAME);
		assertEquals("Last month start on " + dateDesc,  "2014-09-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last month end on " + dateDesc,  "2014-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year start on " + dateDesc,  "2014-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_YEAR_INTERVAL_NAME);
		assertEquals("Last year start on " + dateDesc,  "2013-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last year end on " + dateDesc, "2014-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter start on " + dateDesc,  "2014-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_QUARTER_INTERVAL_NAME);
		assertEquals("Last quarter start on " + dateDesc,  "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last quarter end on " + dateDesc, "2014-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter start on " + dateDesc, "2014-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FQ_INTERVAL_NAME);
		assertEquals("Last fiscal quarter start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal quarter end on " + dateDesc, "2014-10-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FY_INTERVAL_NAME);
		assertEquals("Last fiscal year start on " + dateDesc, "2013-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal year end on " + dateDesc, "2014-07-01 00:00:00", testInterval.getEndDate());
	}
	
	@Test
	public void testLeapYear2012()
	{
		String nowDate = "2012-02-29 12:30:59";
		
		String nowDateStart = "2012-02-29 00:00:00";
		
		Intervals intervals = new Intervals( nowDate  );
		
		String dateDesc = "Leap year 2012";
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today start on " + dateDesc, nowDateStart, testInterval.getStartDate());
		assertEquals("Today end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.YESTERDAY_INTERVAL_NAME);
		assertEquals("Yesterday start on " + dateDesc, "2012-02-28 00:00:00", testInterval.getStartDate());
		assertEquals("Yesterday end on " + dateDesc, nowDateStart, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week start on " + dateDesc, "2012-02-27 00:00:00", testInterval.getStartDate());
		assertEquals("This week end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_WEEK_INTERVAL_NAME);
		assertEquals("Last week start on " + dateDesc, "2012-02-20 00:00:00", testInterval.getStartDate());
		assertEquals("Last week end on " + dateDesc,  "2012-02-27 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month start on " + dateDesc, "2012-02-01 00:00:00", testInterval.getStartDate());
		assertEquals("This month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_MONTH_INTERVAL_NAME);
		assertEquals("Last month start on " + dateDesc,  "2012-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last month end on " + dateDesc,  "2012-02-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year start on " + dateDesc,  "2012-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_YEAR_INTERVAL_NAME);
		assertEquals("Last year start on " + dateDesc,  "2011-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last year end on " + dateDesc, "2012-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter start on " + dateDesc,  "2012-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_QUARTER_INTERVAL_NAME);
		assertEquals("Last quarter start on " + dateDesc,  "2011-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last quarter end on " + dateDesc, "2012-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter start on " + dateDesc, "2012-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FQ_INTERVAL_NAME);
		assertEquals("Last fiscal quarter start on " + dateDesc, "2011-10-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal quarter end on " + dateDesc, "2012-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year start on " + dateDesc, "2011-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FY_INTERVAL_NAME);
		assertEquals("Last fiscal year start on " + dateDesc, "2010-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal year end on " + dateDesc, "2011-07-01 00:00:00", testInterval.getEndDate());
	}
	
	@Test
	public void testFiscalYearStart2014()
	{
		String nowDate = "2014-07-01 23:59:12";
		Intervals intervals = new Intervals( nowDate  );
		
		String dateDesc = "FY 2014 start: July 01 2014";
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today start on " + dateDesc,  "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Today end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.YESTERDAY_INTERVAL_NAME);
		assertEquals("Yesterday start on " + dateDesc, "2014-06-30 00:00:00", testInterval.getStartDate());
		assertEquals("Yesterday end on " + dateDesc, "2014-07-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week start on " + dateDesc, "2014-06-30 00:00:00", testInterval.getStartDate());
		assertEquals("This week end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_WEEK_INTERVAL_NAME);
		assertEquals("Last week start on " + dateDesc,"2014-06-23 00:00:00", testInterval.getStartDate());
		assertEquals("Last week end on " + dateDesc, "2014-06-30 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This month end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_MONTH_INTERVAL_NAME);
		assertEquals("Last month start on " + dateDesc,  "2014-06-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last month end on " + dateDesc,  "2014-07-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year start on " + dateDesc,  "2014-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("This year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_YEAR_INTERVAL_NAME);
		assertEquals("Last year start on " + dateDesc,  "2013-01-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last year end on " + dateDesc, "2014-01-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter start on " + dateDesc,  "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_QUARTER_INTERVAL_NAME);
		assertEquals("Last quarter start on " + dateDesc,  "2014-04-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last quarter end on " + dateDesc, "2014-07-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal quarter end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FQ_INTERVAL_NAME);
		assertEquals("Last fiscal quarter start on " + dateDesc, "2014-04-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal quarter end on " + dateDesc, "2014-07-01 00:00:00", testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year start on " + dateDesc, "2014-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("This fiscal year end on " + dateDesc, nowDate, testInterval.getEndDate());
		
		testInterval = intervals.getTimeInterval(Intervals.LAST_FY_INTERVAL_NAME);
		assertEquals("Last fiscal year start on " + dateDesc, "2013-07-01 00:00:00", testInterval.getStartDate());
		assertEquals("Last fiscal year end on " + dateDesc, "2014-07-01 00:00:00", testInterval.getEndDate());
	}
	
	@Test
	public void testNowHandling()
	{
		Intervals intervals = new Intervals( DateParser.NOW_DATE_KEYWORD  );
				
		String dateDesc = DateParser.NOW_DATE_KEYWORD;
		
		Interval testInterval;
		
		testInterval = intervals.getTimeInterval(Intervals.TODAY_INTERVAL_NAME);
		assertEquals("Today end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_WEEK_INTERVAL_NAME);
		assertEquals("This week end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_MONTH_INTERVAL_NAME);
		assertEquals("This month end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_YEAR_INTERVAL_NAME);
		assertEquals("This year end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_QUARTER_INTERVAL_NAME);
		assertEquals("This quarter end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FQ_INTERVAL_NAME);
		assertEquals("This fiscal quarter end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
		
		testInterval = intervals.getTimeInterval(Intervals.THIS_FY_INTERVAL_NAME);
		assertEquals("This fiscal year end on " + dateDesc, DateParser.NOW_DATE_KEYWORD.toLowerCase(), testInterval.getEndDate().toLowerCase());
	}
}
