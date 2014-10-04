package com.sutherland.helios.date.interval;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.sutherland.helios.date.parsing.DateParser;



/**
 * @author Jason Diamond
 *
 */
public class Intervals 
{
	//fiscal year starts 7/1/thisYear every year
	private final int FISCAL_YEAR_START_MON =6;
	private final int FISCAL_YEAR_START_DAY = 1;
	
	//THIS*END is always NOW. we can't have data for the future, as much as I'd like to.
	
	public final static int TODAY_START = 1;
	public final static int YESTERDAY_START = 2;
	public final static int YESTERDAY_END = 3;
	public final static int THIS_WEEK_START = 4;
	public final static int LAST_WEEK_START = 5;
	public final static int LAST_WEEK_END = 6;
	public final static int THIS_MONTH_START = 7;
	public final static int LAST_MONTH_START = 8;
	public final static int LAST_MONTH_END = 9;
	public final static int THIS_YEAR_START = 10;
	public final static int LAST_YEAR_START = 11;
	public final static int LAST_YEAR_END = 12;
	public final static int THIS_FISCAL_QUARTER_START = 13;
	public final static int LAST_FISCAL_QUARTER_START = 14;
	public final static int LAST_FISCAL_QUARTER_END = 15;
	public final static int THIS_FISCAL_YEAR_START = 16;
	public final static int LAST_FISCAL_YEAR_START = 17;
	public final static int LAST_FISCAL_YEAR_END = 18;
	
	public final static String TODAY_INTERVAL_NAME = "Today";
	public final static String YESTERDAY_INTERVAL_NAME = "Yesterday";
	public final static String THIS_WEEK_INTERVAL_NAME = "This Week";
	public final static String LAST_WEEK_INTERVAL_NAME = "Last Week";
	public final static String THIS_MONTH_INTERVAL_NAME = "This Month";
	public final static String LAST_MONTH_INTERVAL_NAME = "Last Month";
	public final static String THIS_YEAR_INTERVAL_NAME = "This Year";
	public final static String LAST_YEAR_INTERVAL_NAME = "Last Year";
	public final static String THIS_QUARTER_INTERVAL_NAME = "This Quarter";
	public final static String LAST_QUARTER_INTERVAL_NAME = "Last Quarter";
	public final static String THIS_FQ_INTERVAL_NAME = "This Fiscal Quarter";
	public final static String LAST_FQ_INTERVAL_NAME = "Last Fiscal Quarter";
	public final static String THIS_FY_INTERVAL_NAME = "This Fiscal Year";
	public final static String LAST_FY_INTERVAL_NAME = "Last Fiscal Year";
	
	private LinkedHashMap<String, Interval> avaliableTimeIntervals;
	
	GregorianCalendar nowDate;
	private String nowDateString;

	public Intervals()
	{
		this(DateParser.toSQLDateFormat(new GregorianCalendar()));
	}
	
	public Intervals(GregorianCalendar nowDate)
	{
		this(DateParser.toSQLDateFormat(nowDate));
	}
	
	public Intervals(String nowDate)
	{
		this.nowDate = DateParser.convertSQLDateToGregorian(nowDate);
		nowDateString = DateParser.toSQLDateFormat(this.nowDate);
				
		
		//there appears to be no easy way to copy construct gc's

		//current day's interval
		// current y-m-d 00:00:00 --> now
		GregorianCalendar todayStart = new GregorianCalendar();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		
		//yesterday's interval
		//today minus 1 day, at 00:00:00 --> today minus 1 day at 23:59:59
		GregorianCalendar yesterdayStart = new GregorianCalendar();
		yesterdayStart.set(Calendar.HOUR_OF_DAY, 0);
		yesterdayStart.set(Calendar.MINUTE, 0);
		yesterdayStart.set(Calendar.SECOND, 0);
		yesterdayStart.add(Calendar.DAY_OF_MONTH, -1);
		
		GregorianCalendar yesterdayEnd = new GregorianCalendar();
		yesterdayEnd.set(Calendar.HOUR_OF_DAY, 23);
		yesterdayEnd.set(Calendar.MINUTE, 59);
		yesterdayEnd.set(Calendar.SECOND, 59);
		yesterdayEnd.add(Calendar.DAY_OF_MONTH, -1);
		
		//weeks decreed to run mon-sun
		//this week's interval
		GregorianCalendar thisWeekStart = new GregorianCalendar();
		thisWeekStart.set(Calendar.HOUR_OF_DAY, 0);
		thisWeekStart.set(Calendar.MINUTE, 0);
		thisWeekStart.set(Calendar.SECOND, 0);
		thisWeekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		//last week's interval
		GregorianCalendar lastWeekStart = new GregorianCalendar();
		lastWeekStart.set(Calendar.HOUR_OF_DAY, 0);
		lastWeekStart.set(Calendar.MINUTE, 0);
		lastWeekStart.set(Calendar.SECOND, 0);
		lastWeekStart.add(Calendar.WEEK_OF_MONTH, -1);
		lastWeekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		GregorianCalendar lastWeekEnd = new GregorianCalendar();
		lastWeekEnd.set(Calendar.HOUR_OF_DAY, 23);
		lastWeekEnd.set(Calendar.MINUTE, 59);
		lastWeekEnd.set(Calendar.SECOND, 59);
		lastWeekEnd.add(Calendar.WEEK_OF_MONTH, -1);
		lastWeekEnd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		lastWeekEnd.add(Calendar.DAY_OF_WEEK, 6);
		
		//current month's interval
		GregorianCalendar thisMonthStart = new GregorianCalendar();
		thisMonthStart.set(Calendar.DAY_OF_MONTH, 1);
		thisMonthStart.set(Calendar.HOUR_OF_DAY, 0);
		thisMonthStart.set(Calendar.MINUTE, 0);
		thisMonthStart.set(Calendar.SECOND, 0);
		
		//last month's interval
		GregorianCalendar lastMonthStart = new GregorianCalendar();
		lastMonthStart.add(Calendar.MONTH, -1);
		lastMonthStart.set(Calendar.DAY_OF_MONTH, 1);
		lastMonthStart.set(Calendar.HOUR_OF_DAY, 0);
		lastMonthStart.set(Calendar.MINUTE, 0);
		lastMonthStart.set(Calendar.SECOND, 0);
		
		GregorianCalendar lastMonthEnd = new GregorianCalendar();
		lastMonthEnd.set(Calendar.DAY_OF_MONTH, 1);
		lastMonthEnd.add(Calendar.DAY_OF_MONTH, -1);
		lastMonthEnd.set(Calendar.HOUR_OF_DAY, 23);
		lastMonthEnd.set(Calendar.MINUTE, 59);
		lastMonthEnd.set(Calendar.SECOND, 59);
		
		
		//current quarter's interval
		GregorianCalendar thisQuarterStart = new GregorianCalendar();
		GregorianCalendar lastQuarterStart = new GregorianCalendar();

		if(this.nowDate.get(Calendar.MONTH) >= 0 && (this.nowDate.get(Calendar.MONTH) <= 2 ))
		{
			thisQuarterStart.set(Calendar.MONTH, 0);
			lastQuarterStart.set(Calendar.MONTH, 9);
			lastQuarterStart.add(Calendar.YEAR, -1);
		}
		else if(this.nowDate.get(Calendar.MONTH) >= 3 && (this.nowDate.get(Calendar.MONTH) <= 5 ))
		{
			thisQuarterStart.set(Calendar.MONTH, 3);
			lastQuarterStart.set(Calendar.MONTH, 0);
		}
		else if(this.nowDate.get(Calendar.MONTH) >= 6 && (this.nowDate.get(Calendar.MONTH) <= 8 ))
		{
			thisQuarterStart.set(Calendar.MONTH, 6);
			lastQuarterStart.set(Calendar.MONTH, 3);
		}
		else if(this.nowDate.get(Calendar.MONTH) >= 9 && (this.nowDate.get(Calendar.MONTH) <= 11 ))
		{
			thisQuarterStart.set(Calendar.MONTH, 9);
			lastQuarterStart.set(Calendar.MONTH, 6);
		}
		
		thisQuarterStart.set(Calendar.DAY_OF_MONTH, 1);
		thisQuarterStart.set(Calendar.HOUR_OF_DAY, 0);
		thisQuarterStart.set(Calendar.MINUTE, 0);
		thisQuarterStart.set(Calendar.SECOND, 0);
		
		//last quarter's interval
		lastQuarterStart.set(Calendar.DAY_OF_MONTH, 1);
		lastQuarterStart.set(Calendar.HOUR_OF_DAY, 0);
		lastQuarterStart.set(Calendar.MINUTE, 0);
		lastQuarterStart.set(Calendar.SECOND, 0);
		
		GregorianCalendar lastQuarterEnd = thisQuarterStart;
		lastQuarterEnd.set(Calendar.DAY_OF_MONTH, 1);
		lastQuarterEnd.add(Calendar.DAY_OF_MONTH, -1);
		lastQuarterEnd.set(Calendar.HOUR_OF_DAY, 23);
		lastQuarterEnd.set(Calendar.MINUTE, 59);
		lastQuarterEnd.set(Calendar.SECOND, 59);
		
		
		GregorianCalendar thisYearStart = new GregorianCalendar();
		thisYearStart.set(Calendar.MONTH, 0);
		thisYearStart.set(Calendar.DAY_OF_MONTH, 1);
		thisYearStart.set(Calendar.HOUR_OF_DAY, 0);
		thisYearStart.set(Calendar.MINUTE, 0);
		thisYearStart.set(Calendar.SECOND, 0);
		
		GregorianCalendar lastYearStart = new GregorianCalendar();
		lastYearStart.add(Calendar.YEAR, -1);
		lastYearStart.set(Calendar.MONTH, 0);
		lastYearStart.set(Calendar.DAY_OF_MONTH, 1);
		lastYearStart.set(Calendar.HOUR_OF_DAY, 0);
		lastYearStart.set(Calendar.MINUTE, 0);
		lastYearStart.set(Calendar.SECOND, 0);
		
		GregorianCalendar lastYearEnd = new GregorianCalendar();
		lastYearEnd.add(Calendar.YEAR, -1);
		lastYearEnd.set(Calendar.MONTH, 11);
		lastYearEnd.set(Calendar.DAY_OF_MONTH, 31);
		lastYearEnd.set(Calendar.HOUR_OF_DAY, 23);
		lastYearEnd.set(Calendar.MINUTE, 59);
		lastYearEnd.set(Calendar.SECOND, 59);
		
		//XXXX-07-01 -> (XXXX+1)-06-30
		
		//get the current year from the now date
		GregorianCalendar thisFiscalYearStart = new GregorianCalendar();
		thisFiscalYearStart.set(Calendar.DAY_OF_MONTH, FISCAL_YEAR_START_DAY);
		thisFiscalYearStart.set(Calendar.MONTH, FISCAL_YEAR_START_MON);
		thisFiscalYearStart.set(Calendar.HOUR_OF_DAY, 0);
		thisFiscalYearStart.set(Calendar.MINUTE, 0);
		thisFiscalYearStart.set(Calendar.SECOND, 0);
		thisFiscalYearStart.set(Calendar.YEAR, this.nowDate.get(Calendar.YEAR));

		if(this.nowDate.get(Calendar.MONTH) < FISCAL_YEAR_START_MON)
		{
			thisFiscalYearStart.add(Calendar.YEAR, -1);
		}

		GregorianCalendar lastFiscalYearStart = new GregorianCalendar();
		lastFiscalYearStart.set(Calendar.DAY_OF_MONTH, FISCAL_YEAR_START_DAY);
		lastFiscalYearStart.set(Calendar.MONTH, FISCAL_YEAR_START_MON);
		lastFiscalYearStart.set(Calendar.HOUR_OF_DAY, 0);
		lastFiscalYearStart.set(Calendar.MINUTE, 0);
		lastFiscalYearStart.set(Calendar.SECOND, 0);
		lastFiscalYearStart.set(Calendar.YEAR, thisFiscalYearStart.get(Calendar.YEAR));
		lastFiscalYearStart.add(Calendar.YEAR, -1);

		GregorianCalendar lastFiscalYearEnd = new GregorianCalendar();
		lastFiscalYearEnd.set(Calendar.DAY_OF_MONTH, thisFiscalYearStart.get(Calendar.DAY_OF_MONTH));
		lastFiscalYearEnd.set(Calendar.MONTH, thisFiscalYearStart.get(Calendar.MONTH));
		lastFiscalYearEnd.set(Calendar.YEAR, thisFiscalYearStart.get(Calendar.YEAR));
		lastFiscalYearEnd.set(Calendar.HOUR_OF_DAY, 0);
		lastFiscalYearEnd.set(Calendar.MINUTE, 0);
		lastFiscalYearEnd.set(Calendar.SECOND, 0);
		lastFiscalYearEnd.add(Calendar.SECOND, -1);

		//generate the fiscal quarter intervals, sort out this/last with gregcal.before/after
		//next fq is +3 months, -1 second
		//this fiscal quarter is within this fiscal year
		GregorianCalendar nextFiscalQEnd = new GregorianCalendar();
		nextFiscalQEnd.set(Calendar.YEAR, thisFiscalYearStart.get(Calendar.YEAR));
		nextFiscalQEnd.set(Calendar.MONTH, thisFiscalYearStart.get(Calendar.MONTH));
		nextFiscalQEnd.set(Calendar.DAY_OF_MONTH, thisFiscalYearStart.get(Calendar.DAY_OF_MONTH));
		nextFiscalQEnd.set(Calendar.HOUR_OF_DAY, 0);
		nextFiscalQEnd.set(Calendar.MINUTE, 0);
		nextFiscalQEnd.set(Calendar.SECOND, 0);
		
		do 
		{
			//12 months/ 4 quarters
			nextFiscalQEnd.add(Calendar.MONTH, 3);
		} while (this.nowDate.after(nextFiscalQEnd));


		GregorianCalendar thisFiscalQStart = new GregorianCalendar();
		thisFiscalQStart.set(Calendar.DAY_OF_MONTH, 1);
		thisFiscalQStart.set(Calendar.MONTH, nextFiscalQEnd.get(Calendar.MONTH)-3);
		thisFiscalQStart.set(Calendar.HOUR_OF_DAY, 0);
		thisFiscalQStart.set(Calendar.MINUTE, 0);
		thisFiscalQStart.set(Calendar.SECOND, 0);

		GregorianCalendar lastFiscalQStart = new GregorianCalendar();
		lastFiscalQStart.set(Calendar.DAY_OF_MONTH, nextFiscalQEnd.get(Calendar.DAY_OF_MONTH));
		lastFiscalQStart.set(Calendar.MONTH, nextFiscalQEnd.get(Calendar.MONTH));
		lastFiscalQStart.set(Calendar.HOUR_OF_DAY, 0);
		lastFiscalQStart.set(Calendar.MINUTE, 0);
		lastFiscalQStart.set(Calendar.SECOND, 0);

		GregorianCalendar lastFiscalQEnd = new GregorianCalendar();
		lastFiscalQEnd.set(Calendar.DAY_OF_MONTH, thisFiscalQStart.get(Calendar.DAY_OF_MONTH));
		lastFiscalQEnd.set(Calendar.YEAR, thisFiscalQStart.get(Calendar.YEAR));
		lastFiscalQEnd.set(Calendar.MONTH, thisFiscalQStart.get(Calendar.MONTH));
		lastFiscalQEnd.set(Calendar.HOUR_OF_DAY, thisFiscalQStart.get(Calendar.HOUR_OF_DAY));
		lastFiscalQEnd.set(Calendar.MINUTE, thisFiscalQStart.get(Calendar.MINUTE));
		lastFiscalQEnd.set(Calendar.SECOND, thisFiscalQStart.get(Calendar.SECOND));
		lastFiscalQEnd.add(Calendar.SECOND, -1);
				
		avaliableTimeIntervals = new LinkedHashMap<String,Interval>();
		avaliableTimeIntervals.put(TODAY_INTERVAL_NAME, new Interval(TODAY_INTERVAL_NAME, DateParser.toSQLDateFormat(todayStart), nowDateString));
		avaliableTimeIntervals.put(YESTERDAY_INTERVAL_NAME, new Interval(YESTERDAY_INTERVAL_NAME, DateParser.toSQLDateFormat(yesterdayStart), DateParser.toSQLDateFormat(yesterdayEnd)));
		avaliableTimeIntervals.put(THIS_WEEK_INTERVAL_NAME, new Interval(THIS_WEEK_INTERVAL_NAME, DateParser.toSQLDateFormat(thisWeekStart), nowDateString));
		avaliableTimeIntervals.put(LAST_WEEK_INTERVAL_NAME, new Interval(LAST_WEEK_INTERVAL_NAME, DateParser.toSQLDateFormat(lastWeekStart), DateParser.toSQLDateFormat(thisWeekStart)));
		avaliableTimeIntervals.put(THIS_MONTH_INTERVAL_NAME, new Interval(THIS_MONTH_INTERVAL_NAME, DateParser.toSQLDateFormat(thisMonthStart), nowDateString));
		avaliableTimeIntervals.put(LAST_MONTH_INTERVAL_NAME, new Interval(LAST_MONTH_INTERVAL_NAME, DateParser.toSQLDateFormat(lastMonthStart), DateParser.toSQLDateFormat(thisMonthStart)));
		avaliableTimeIntervals.put(THIS_FQ_INTERVAL_NAME, new Interval(THIS_FQ_INTERVAL_NAME, DateParser.toSQLDateFormat(thisFiscalQStart), nowDateString));
		avaliableTimeIntervals.put(LAST_FQ_INTERVAL_NAME, new Interval(LAST_FQ_INTERVAL_NAME, DateParser.toSQLDateFormat(lastFiscalQEnd), DateParser.toSQLDateFormat(thisFiscalQStart)));
		
		avaliableTimeIntervals.put(THIS_QUARTER_INTERVAL_NAME, new Interval(THIS_QUARTER_INTERVAL_NAME, DateParser.toSQLDateFormat(thisQuarterStart), nowDateString));
		avaliableTimeIntervals.put(LAST_QUARTER_INTERVAL_NAME, new Interval(LAST_QUARTER_INTERVAL_NAME, DateParser.toSQLDateFormat(lastQuarterStart), DateParser.toSQLDateFormat(lastQuarterEnd)));
		
		avaliableTimeIntervals.put(THIS_FY_INTERVAL_NAME, new Interval(THIS_FY_INTERVAL_NAME, DateParser.toSQLDateFormat(thisFiscalYearStart), nowDateString));
		avaliableTimeIntervals.put(LAST_FY_INTERVAL_NAME, new Interval(LAST_FY_INTERVAL_NAME, DateParser.toSQLDateFormat(lastFiscalYearStart), DateParser.toSQLDateFormat(thisFiscalYearStart)));
		avaliableTimeIntervals.put(THIS_YEAR_INTERVAL_NAME, new Interval(THIS_YEAR_INTERVAL_NAME, DateParser.toSQLDateFormat(thisYearStart), nowDateString));
		avaliableTimeIntervals.put(LAST_YEAR_INTERVAL_NAME, new Interval(LAST_YEAR_INTERVAL_NAME, DateParser.toSQLDateFormat(lastYearStart), DateParser.toSQLDateFormat(lastYearEnd)));
	}
	
	public LinkedHashMap<String, Interval> getAvailableTimeIntervals()
	{
		return avaliableTimeIntervals;
	}
	
	public Interval getTimeInterval(String intervalName)
	{
		return avaliableTimeIntervals.get(intervalName);
	}
	
	public Set<String> getAvaliableIntervalNames()
	{
		return avaliableTimeIntervals.keySet();
	}
		
	public String toString()
	{
		StringBuilder retval = new StringBuilder();
		
		for(Entry<String, Interval> interval  : avaliableTimeIntervals.entrySet())
		{
			retval.append(interval.getKey());
			retval.append(" : ");
			retval.append(interval.getValue().getStartDate());
			retval.append(" => ");
			retval.append(interval.getValue().getEndDate());
			retval.append("\n");
		}
		
		return retval.toString();
	}
}
