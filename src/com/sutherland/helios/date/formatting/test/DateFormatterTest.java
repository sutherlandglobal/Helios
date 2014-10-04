package com.sutherland.helios.date.formatting.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.data.granularity.time.TimeGrains;
import com.sutherland.helios.date.formatting.DateFormatter;
import com.sutherland.helios.date.parsing.DateParser;

public class DateFormatterTest extends TestCase 
{
	private final String testString =  "2010-01-01 00:00:00";
	
	@Override
	public void setUp()
	{

	}
	
	@Test
	public void testYearGrain()
	{		
		GregorianCalendar testDate = DateParser.convertSQLDateToGregorian(testString );
		
		int startYear = testDate.get(Calendar.YEAR);
		String testYear = "" + startYear;
		
		while(startYear == testDate.get(Calendar.YEAR))
		{
			assertEquals( "Year Grain test: " + DateParser.toSQLDateFormat(testDate), testYear, DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
			
			testDate.add(Calendar.SECOND, 1);
		}
	}
	
//	@Test
//	public void testMonthGrain()
//	{		
//		GregorianCalendar testDate = DateParser.convertSQLDateToGregorian(testString );
//		
//		int startYear = testDate.get(Calendar.YEAR);
//		String testYear = "" + startYear;
//		
//		while(startYear == testDate.get(Calendar.YEAR))
//		{
//			assertEquals( "Year Grain test: " + DateParser.toSQLDateFormat(testDate), testYear, DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
//			
//			testDate.add(Calendar.SECOND, 1);
//		}
//	}
	
	@Test
	public void testGetYearGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2012", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2012", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("2011", DateFormatter.getFormattedDate(testDate, TimeGrains.YEARLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
	
	@Test
	public void testGetQuarterGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2012-01", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2012", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-04", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2011-04", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("04/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-04", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals("2011-04", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("04/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
	
	@Test
	public void testGetFiscalQuarterGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2010-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2010-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/2010", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2010-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2010-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/2010", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2011-02", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertEquals("2011-02", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.FISCAL_QUARTERLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
	
	@Test
	public void testGetMonthGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2011-01", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2011-02", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2011-03", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2012-02", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/2012", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-10", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2011-10", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("10/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-12", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY));
		assertEquals("2011-12", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("12/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.MONTHLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
	
	@Test
	public void testGetWeekGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY));
		assertEquals("2011-01-01", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298883601
		//DATE: 02 / 28 / 11 @ 3:00:01am EST
		epochTestDate = 1298883601;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-10", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY));
		assertEquals("2011-02-10", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/10/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-10", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY));
		assertEquals("2011-03-10", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/10/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-09", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY));
		assertEquals("2012-02-09", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/09/2012", DateFormatter.getFormattedDate(testDate, TimeGrains.WEEKLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
	
	@Test
	public void testGetDayGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY));
		assertEquals("2011-01-01", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298883601
		//DATE: 02 / 28 / 11 @ 3:00:01am EST
		epochTestDate = 1298883601;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-28", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY));
		assertEquals("2011-02-28", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/28/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-01", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY));
		assertEquals("2011-03-01", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/01/2011", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-29", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY));
		assertEquals("2012-02-29", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/29/2012", DateFormatter.getFormattedDate(testDate, TimeGrains.DAILY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
	
	@Test
	public void testGetHourGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//Sat 01 Jan 2011 05:12:18 PM EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01 17:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY));
		assertEquals("2011-01-01 17:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("01/01/2011 05:00:00 PM", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1298883601
		//Mon 28 Feb 2011 04:00:01 AM EST
		epochTestDate = 1298883601;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-28 04:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY));
		assertEquals("2011-02-28 04:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/28/2011 04:00:00 AM", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1299017538
		//Tue 01 Mar 2011 05:12:18 PM EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-01 17:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY));
		assertEquals("2011-03-01 17:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("03/01/2011 05:00:00 PM", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));

		//TIME STAMP:  1330553538
		//Wed 29 Feb 2012 05:12:18 PM EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-29 17:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY));
		assertEquals("2012-02-29 17:00:00", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.SQL_FORMAT));
		assertEquals("02/29/2012 05:00:00 PM", DateFormatter.getFormattedDate(testDate, TimeGrains.HOURLY_GRANULARITY, DateFormatter.EXCEL_FORMAT));
	}
}
