/**
 * 
 */
package test.validation;

import org.junit.Test;

import report.Report;

import util.date.DateParser;
import util.parameter.Validation;

import junit.framework.TestCase;

/**
 * @author Jason Diamond
 *
 */
public class GenericParameterValidation extends TestCase
{
	public void setUp()
	{
		
	}
	
	public void tearDown()
	{
		
	}
	
	@Test
	public void testTimeGrainValidation()
	{
		assertNotNull("Yearly granularity validation", Validation.hasValidTimeGrain(DateParser.YEARLY_GRANULARITY));
		assertNotNull("Monthly granularity validation", Validation.hasValidTimeGrain(DateParser.MONTHLY_GRANULARITY));
		assertNotNull("Daily granularity validation", Validation.hasValidTimeGrain(DateParser.WEEKLY_GRANULARITY));
		assertNotNull("Weekly granularity validation", Validation.hasValidTimeGrain(DateParser.DAILY_GRANULARITY));
		assertNotNull("Hourly granularity validation", Validation.hasValidTimeGrain(DateParser.HOURLY_GRANULARITY));
		
		assertNull("Negative invalid time grain", Validation.hasValidTimeGrain(-12124));
		assertNull("Positive invalid time grain", Validation.hasValidTimeGrain(9999999));
	}
	
	@Test
	public void testDateValidation()
	{
		//nothing? handled in tests of dateparser?
		
		
	}
	
	@Test
	public void testReportTypeValidation()
	{
		assertNotNull("Agent Stack type validation", Validation.hasValidReportType(Report.AGENT_STACK_REPORT, new int[]{1}));
		assertNotNull("Agent Time type validation", Validation.hasValidReportType(Report.AGENT_TIME_REPORT, new int[]{2}));
		assertNotNull("Team Stack type validation", Validation.hasValidReportType(Report.TEAM_STACK_REPORT, new int[]{3}));
		assertNotNull("Team Time type validation", Validation.hasValidReportType(Report.TEAM_TIME_REPORT, new int[]{4}));
		
		assertNull("Negative invalid report type", Validation.hasValidTimeGrain(-12124));
		assertNull("Positive invalid report type", Validation.hasValidTimeGrain(9999999));
	}
}
