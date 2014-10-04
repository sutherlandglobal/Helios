package com.sutherland.helios.report.parameters.test;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.data.granularity.time.TimeGrains;
import com.sutherland.helios.data.granularity.user.UserGrains;
import com.sutherland.helios.date.formatting.DateFormatter;
import com.sutherland.helios.report.ReportTypes;
import com.sutherland.helios.report.parameters.ReportParameters;

public class ReportParametersTest extends TestCase 
{
	private ReportParameters params;
	
	private String testReportType;
	private String testTimeGrain;
	
	private String testStartDate;
	private String testEndDate;
	
	private String testTeamName1;
	private String testTeamName2;
	
	private String testAgentName1;
	private String testAgentName2;
	private String testAgentName3;
	private String testAgentName4;

	private String testUserGrain;

	private String testGoodReportTypeName;

	private int testGoodReportTypeVal;

	private String testBadReportTypeName;

	private int testBadReportTypeValue;

	private String testDateFormat;

	public void setUp()
	{
		params = new ReportParameters();
		
		testTeamName1 = "ROCJFS Sales Team";
		testTeamName2 = "CHNDLF Sales Team";
		
		testAgentName1 = "Zim, Invader";
		testAgentName2 = "Whateveryourlastnameis, Dib";
		testAgentName3 = "Tallest, Almighty";
		testAgentName4 = "Hogulus, Meatthirsty";
		
		testStartDate = "2011-10-01 00:00:00";
		testEndDate = "2011-10-31 23:59:59";
		
		testReportType = "" + ReportTypes.TIME_TREND_REPORT;
		testTimeGrain = "" + TimeGrains.DAILY_GRANULARITY;
		testUserGrain = "" + UserGrains.TEAM_GRANULARITY;
		testDateFormat = "" + DateFormatter.SQL_FORMAT;
		
		testGoodReportTypeName = ReportTypes.STACK_REPORT_NAME;
		testGoodReportTypeVal = ReportTypes.STACK_REPORT;
		
		testBadReportTypeName = "Derp Trend";
		testBadReportTypeValue = -999;
	}
	
	@Test
	public void testAddSupportedReportType()
	{
		assertTrue("Add report type and report value", params.addSupportedReportType(testGoodReportTypeName, testGoodReportTypeVal));
		assertTrue("Update report type and report value", params.addSupportedReportType(testGoodReportTypeName, ReportTypes.STACK_REPORT));
	}
	
	@Test
	public void testAddBadSupportedReportType()
	{
		assertFalse("Add bad report type ", params.addSupportedReportType(testBadReportTypeName, testGoodReportTypeVal));
		assertFalse("Add bad report value", params.addSupportedReportType(testGoodReportTypeName, testBadReportTypeValue));
		assertFalse("Add bad report type and bad report value", params.addSupportedReportType(testBadReportTypeName, testBadReportTypeValue));
		assertFalse("Add null report type and bad report value", params.addSupportedReportType(null, testBadReportTypeValue));
		assertFalse("Add empty report type and bad report value", params.addSupportedReportType("", testBadReportTypeValue));
		assertFalse("Add good report type and null report value", params.addSupportedReportType(testGoodReportTypeName, null));
	}
	
	@Test
	public void testGetSupportedReportTypes()
	{
		assertTrue("Add time trend report type and report value", params.addSupportedReportType(ReportTypes.TIME_TREND_REPORT_NAME, ReportTypes.TIME_TREND_REPORT));
		assertTrue("Add stack report type and report value", params.addSupportedReportType(ReportTypes.STACK_REPORT_NAME, ReportTypes.STACK_REPORT));
		
		ArrayList<String> expectedReportTypes = new ArrayList<String>();
		expectedReportTypes.add(ReportTypes.TIME_TREND_REPORT_NAME);
		expectedReportTypes.add(ReportTypes.STACK_REPORT_NAME);
		
		ArrayList<Integer> expectedReportTypeValues = new ArrayList<Integer>();
		expectedReportTypeValues.add(ReportTypes.TIME_TREND_REPORT);
		expectedReportTypeValues.add(ReportTypes.STACK_REPORT);
		
		Collections.sort(expectedReportTypes);
		Collections.sort(expectedReportTypeValues);
		
		ArrayList<String> actualReportTypes = new ArrayList<String>(params.getAllowedReportTypes().keySet());
		ArrayList<Integer> actualReportTypeValues = new ArrayList<Integer>( params.getAllowedReportTypes().values());
		
		Collections.sort(actualReportTypes);
		Collections.sort(actualReportTypeValues);
		
		assertEquals("Check report types", expectedReportTypes, actualReportTypes );
		assertEquals("Check report type values", actualReportTypeValues, actualReportTypeValues );
	}
	
	@Test
	public void testClearSupportedReportTypes()
	{
		params.clearSupportedReportTypes();
		assertEquals("Clear supported report types" , 0, params.getSupportedReportTypes().size());
	}
	
	@Test
	public void testSettingReportType()
	{
		assertTrue("Report type set", params.setReportType(testReportType) );
	}
	
	@Test
	public void testGettingReportType()
	{
		assertTrue("Report type get", params.setReportType(testReportType) );
		assertEquals("Report type get", testReportType, params.getReportType());
	}
	
	@Test
	public void testSettingTimeGrain()
	{		
		assertTrue("Time grain set", params.setTimeGrain( testTimeGrain ));
	}
	
	@Test
	public void testGettingTimeGrain()
	{
		assertTrue("Time grain set", params.setTimeGrain( testTimeGrain ));
		assertEquals("Time grain get", testTimeGrain, params.getTimeGrain());
	}
	
	@Test
	public void testSettingDateFormat()
	{		
		assertTrue("Date Format set", params.setDateFormat( testDateFormat ));
	}
	
	@Test
	public void testGettingDateFormat()
	{
		assertTrue("Date format set", params.setDateFormat( testDateFormat ));
		assertEquals("Date format get", testDateFormat, params.getDateFormat());
	}
	
	@Test
	public void testSettingUserGrain()
	{		
		assertTrue("User grain set", params.setUserGrain( testUserGrain ));
	}
	
	@Test
	public void testGettingUserGrain()
	{
		assertTrue("User grain set", params.setUserGrain( testUserGrain ));
		assertEquals("User grain get", testUserGrain, params.getUserGrain());
	}
	
	@Test
	public void testSettingDates()
	{
		assertTrue("Start date set", params.setStartDate(testStartDate));
		assertTrue("End date set", params.setEndDate(testEndDate ));
	}
	
	@Test
	public void testGettingDates()
	{
		assertTrue("Start date set", params.setStartDate(testStartDate));
		assertEquals("Start date get",testStartDate,params.getStartDate());
		
		assertTrue("End date set", params.setEndDate(testEndDate ));
		assertEquals("End date setting and getting",testEndDate,params.getEndDate());
	}
	
	@Test
	public void testSettingTeamNames()
	{
		assertTrue("Add test team name 1", params.addTeamName(testTeamName1));
		assertTrue("Add test team name 2", params.addTeamName(testTeamName2));
	}
	
	@Test
	public void testGettingTeamNames()
	{
		assertTrue("Add test team name 1", params.addTeamName(testTeamName1));
		assertTrue("Add test team name 2", params.addTeamName(testTeamName2));
		
		ArrayList<String> expectedTeamNames = new ArrayList<String>();
		expectedTeamNames.add(testTeamName1);
		expectedTeamNames.add(testTeamName2);
		
		assertEquals("Get test team names", expectedTeamNames, params.getTeamNames());
	}
	
	@Test
	public void testSettingAgentNames()
	{
		assertTrue("Add test agent name 1", params.addTeamName(testAgentName1));
		assertTrue("Add test agent name 2", params.addTeamName(testAgentName2));
		assertTrue("Add test agent name 3", params.addTeamName(testAgentName3));
		assertTrue("Add test agent name 4", params.addTeamName(testAgentName4));
	}
	
	@Test
	public void testGettingAgentNames()
	{
		assertTrue("Add test agent name 1", params.addTeamName(testAgentName1));
		assertTrue("Add test agent name 2", params.addTeamName(testAgentName2));
		assertTrue("Add test agent name 3", params.addTeamName(testAgentName3));
		assertTrue("Add test agent name 4", params.addTeamName(testAgentName4));
		
		ArrayList<String> expectedTeamNames = new ArrayList<String>();
		expectedTeamNames.add(testAgentName1);
		expectedTeamNames.add(testAgentName2);
		expectedTeamNames.add(testAgentName3);
		expectedTeamNames.add(testAgentName4);
		
		assertEquals("Get test team names", expectedTeamNames, params.getTeamNames());
	}
	
	
	

}
