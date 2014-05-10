package helios.data.granularity.time.test;

import helios.data.granularity.time.TimeGrains;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Test;

public class TimeGrainsTest extends TestCase 
{

	@Override
	public void setUp()
	{

	}
	
	@Test
	public void testGetYearGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011", TimeGrains.getDateGrain(TimeGrains.YEARLY_GRANULARITY, testDate));
	}
	
	@Test
	public void testGetQuarterGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-01", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-04", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-04", TimeGrains.getDateGrain(TimeGrains.QUARTERLY_GRANULARITY, testDate));
	}
	
	@Test
	public void testGetFiscalQuarterGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2010-03", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2010-03", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02", TimeGrains.getDateGrain(TimeGrains.FISCAL_QUARTERLY_GRANULARITY, testDate));
	}
	
	@Test
	public void testGetMonthGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));

		//TIME STAMP:  1294697538
		//DATE: 01 / 10 / 11 @ 4:12:18pm EST
		epochTestDate = 1294697538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));

		//TIME STAMP:  1298931138
		//DATE: 02 / 28 / 11 @ 4:12:18pm EST
		epochTestDate = 1298931138;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));

		//TIME STAMP:  1319926338
		//DATE: 10 / 29 / 11 @ 5:12:18pm EST
		epochTestDate = 1319926338;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-10", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));

		//TIME STAMP:  1325196738
		//DATE: 12 / 29 / 11 @ 4:12:18pm EST
		epochTestDate = 1325196738;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-12", TimeGrains.getDateGrain(TimeGrains.MONTHLY_GRANULARITY, testDate));
	}
	
	@Test
	public void testGetWeekGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01", TimeGrains.getDateGrain(TimeGrains.WEEKLY_GRANULARITY, testDate));

		//TIME STAMP:  1298883601
		//DATE: 02 / 28 / 11 @ 3:00:01am EST
		epochTestDate = 1298883601;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-10", TimeGrains.getDateGrain(TimeGrains.WEEKLY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-10", TimeGrains.getDateGrain(TimeGrains.WEEKLY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-09", TimeGrains.getDateGrain(TimeGrains.WEEKLY_GRANULARITY, testDate));
	}
	
	@Test
	public void testGetDayGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//DATE: 01 / 01 / 11 @ 4:12:18pm EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01", TimeGrains.getDateGrain(TimeGrains.DAILY_GRANULARITY, testDate));

		//TIME STAMP:  1298883601
		//DATE: 02 / 28 / 11 @ 3:00:01am EST
		epochTestDate = 1298883601;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-28", TimeGrains.getDateGrain(TimeGrains.DAILY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//DATE: 03 / 01 / 11 @ 4:12:18pm EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-01", TimeGrains.getDateGrain(TimeGrains.DAILY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//DATE: 02 / 29 / 12 @ 4:12:18pm EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-29", TimeGrains.getDateGrain(TimeGrains.DAILY_GRANULARITY, testDate));
	}
	
	@Test
	public void testGetHourGrain()
	{
		GregorianCalendar testDate = new GregorianCalendar();

		//TIME STAMP:  1293919938
		//Sat 01 Jan 2011 05:12:18 PM EST
		long epochTestDate = 1293919938;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-01-01 17:00:00", TimeGrains.getDateGrain(TimeGrains.HOURLY_GRANULARITY, testDate));

		//TIME STAMP:  1298883601
		//Mon 28 Feb 2011 04:00:01 AM EST
		epochTestDate = 1298883601;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-02-28 04:00:00", TimeGrains.getDateGrain(TimeGrains.HOURLY_GRANULARITY, testDate));

		//TIME STAMP:  1299017538
		//Tue 01 Mar 2011 05:12:18 PM EST
		epochTestDate = 1299017538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2011-03-01 17:00:00", TimeGrains.getDateGrain(TimeGrains.HOURLY_GRANULARITY, testDate));

		//TIME STAMP:  1330553538
		//Wed 29 Feb 2012 05:12:18 PM EST
		epochTestDate = 1330553538;
		testDate.setTimeInMillis(epochTestDate*1000);
		assertEquals("2012-02-29 17:00:00", TimeGrains.getDateGrain(TimeGrains.HOURLY_GRANULARITY, testDate));
	}
}
