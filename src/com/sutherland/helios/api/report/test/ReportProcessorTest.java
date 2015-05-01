package com.sutherland.helios.api.report.test;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sutherland.helios.api.report.ReportProcessor;
import com.sutherland.helios.api.report.frontend.ReportFrontEnds;
import com.sutherland.helios.data.granularity.user.UserGrains;
import com.sutherland.helios.report.Report;
import com.sutherland.helios.report.ReportTypes;
import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.ReportParameters;
import com.sutherland.helios.report.test.MockReport;

public class ReportProcessorTest extends  TestCase  
{
	private ReportProcessor proc;
	private String testReportClassName;
	
	@Before
	public void setUp() throws Exception
	{
		testReportClassName = "com.sutherland.helios.report.test.MockReport";
		proc = new ReportProcessor();
		
		//proc.addSite("Helios");
		//proc.loadReportEntities();
	}
	
	@After
	public void tearDown()
	{

	}
	
	@Test
	public void testIsValidReport() 
	{
		try 
		{
			assertTrue("Check if valid report resolves from its classname", proc.isValidReport(testReportClassName));
		} 
		catch (ClassNotFoundException e1) 
		{
			assertFalse("Check ClassNotFoundException thrown on valid report",true);
		}
		
		try
		{
			assertFalse("Check if invalid report name resolves from its class", proc.isValidReport("derpyderpderp"));
		}
		catch(Exception e)
		{
			assertTrue("Check ClassNotFoundException thrown on invalid report class",true);
		}
		
	}

	@Test
	public void testIsValidReportClassName() 
	{
		try 
		{
			assertTrue(proc.isValidReportClassName(testReportClassName));
		} 
		catch (ClassNotFoundException e1) 
		{
			assertFalse("Check ClassNotFoundException thrown on valid report class",true);
		}
		
		try
		{
			assertFalse("Check if invalid report class is deemed valid", proc.isValidReportClassName("derpyderpderp"));
			assertFalse("Check if invalid report class is deemed valid", true);
		}
		catch(Exception e)
		{
			assertTrue("Check ClassNotFoundException thrown on invalid report class",true);
		}
	}

	@Test
	public void testGetClassNames() 
	{
		assertNotNull(proc.getClassNames());
		assertTrue(proc.getClassNames().size() > 0);
	}

	@Test
	public void testGetReportClass() 
	{
		try 
		{
			Class<?> classObject = proc.getReportClass(testReportClassName);
			
			assertNotNull(classObject);
			
			//make sure we can't execute methods that require init
			
			assertEquals(testReportClassName, proc.getReportClass(testReportClassName).getName());
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testBuildReportClass() 
	{
		try 
		{
			assertNotNull(proc.buildReportClass(testReportClassName));
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetReportInfo()
	{
		try 
		{
			LinkedHashMap<String, String> actualReportInfo = proc.getReportInfo(testReportClassName);
			
			assertTrue("Check that two strings are returned", actualReportInfo.size() == 2);
			assertEquals("Has correct Report Name", MockReport.uiGetReportName(), actualReportInfo.get(0));
			assertEquals("Has correct Report Desc", MockReport.uiGetReportDesc(), actualReportInfo.get(1));
		} 
		catch (ClassNotFoundException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) 
		{
			e.printStackTrace();
			assertFalse("Exception retrieving/evaluating report types", true);
		} 
	}
	
	@Test
	public void testGetSupportedReportTypes()
	{
		LinkedHashMap<String, String> expectedReportTypes = new LinkedHashMap<String, String>();

		expectedReportTypes.put(ReportTypes.STACK_REPORT_NAME,"" +ReportTypes.STACK_REPORT);
		expectedReportTypes.put(ReportTypes.TIME_TREND_REPORT_NAME,"" +ReportTypes.TIME_TREND_REPORT);

		try 
		{
			LinkedHashMap<String, String> actualReportTypes = proc.getUISupportedReportTypes(testReportClassName);
			
			assertEquals("Compare frontend collection sizes", expectedReportTypes.size(), actualReportTypes.size());
			
			for(Entry<String, String> frontEnd : expectedReportTypes.entrySet())
			{
				assertTrue("Front End key check: " + frontEnd.getKey(), actualReportTypes.containsKey(frontEnd.getKey()));
				assertEquals("Front End val check: " + frontEnd.getKey(), frontEnd.getValue(), actualReportTypes.get(frontEnd.getKey()));
			}
		} 
		catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) 
		{
			e.printStackTrace();
			assertFalse("Exception retrieving/evaluating report types", true);
		} 
	}
	
	@Test
	public void testGetSupportedReportFrontEnds()
	{
		LinkedHashMap<String, String> expectedFrontEnds = new LinkedHashMap<String, String>();

		expectedFrontEnds.put(ReportFrontEnds.CSV_NAME,"" +ReportFrontEnds.CSV);
		expectedFrontEnds.put(ReportFrontEnds.HTML_NAME,"" +ReportFrontEnds.HTML);
		expectedFrontEnds.put(ReportFrontEnds.XML_NAME,"" +ReportFrontEnds.XML);
		expectedFrontEnds.put(ReportFrontEnds.JSON_NAME,"" +ReportFrontEnds.JSON);
		expectedFrontEnds.put(ReportFrontEnds.BIRT_NAME,"" +ReportFrontEnds.BIRT);


		try 
		{
			LinkedHashMap<String, String> actualFrontEnds = proc.getUISupportedReportFrontEnds(testReportClassName);
			
			assertEquals("Compare frontend collection sizes", expectedFrontEnds.size(), actualFrontEnds.size());
			
			for(Entry<String, String> frontEnd : expectedFrontEnds.entrySet())
			{
				assertTrue("Front End key check: " + frontEnd.getKey(), actualFrontEnds.containsKey(frontEnd.getKey()));
				assertEquals("Front End val check: " + frontEnd.getKey(), frontEnd.getValue(), actualFrontEnds.get(frontEnd.getKey()));
			}
		} 
		catch (ClassNotFoundException | NoSuchFieldException
				| SecurityException | IllegalArgumentException
				| IllegalAccessException e) 
		{

			assertFalse("Exception retrieving/evaluating report front ends", true);
		}
	}
	
	@Test
	public void testBasicReportExecution() 
	{
		//String testAgent = "Kane:Patrick";
		String testTeam = "USA";
		
		try 
		{
			ReportParameters rp = new ReportParameters();
			
			rp.addSupportedParameter(ParameterInfo.START_DATE_PARAM);
			rp.addSupportedParameter(ParameterInfo.END_DATE_PARAM);
			rp.addSupportedParameter(ParameterInfo.AGENT_NAMES_PARAM);
			rp.addSupportedParameter(ParameterInfo.TEAM_NAMES_PARAM);
			rp.addSupportedParameter(ParameterInfo.USER_GRAIN_PARAM);
			rp.addSupportedParameter(ParameterInfo.TIME_GRAIN_PARAM);
			rp.addSupportedParameter(ParameterInfo.NUM_DRIVERS_PARAM);
			rp.addSupportedParameter(ParameterInfo.REPORT_TYPE_PARAM);
			rp.addSupportedParameter(ParameterInfo.SOURCE_PARAM);
			
			assertTrue("Set stack report", rp.setReportType(ReportTypes.STACK_REPORT));
			//assertTrue("Add agent to report", rp.addAgentName(testAgent));
			assertTrue("Add team to report", rp.addTeamName(testTeam));
			assertTrue("Set report start date", rp.setStartDate("2014-01-01 00:00:00"));
			assertTrue("Set report end date", rp.setEndDate("2014-03-01 23:59:59"));
			assertTrue("Set user granularity for stack report", rp.setUserGrain(UserGrains.TEAM_GRANULARITY));
			assertTrue("Set report source", rp.setSource("JUnit"));
			
			//report needs roster members
			Report report = proc.startReport(testReportClassName, rp);
			ArrayList<String[]> actualResults = report.getData();
			
			assertNotNull("Non-null result set", actualResults);
			assertTrue("Check that result set is non zero size", actualResults.size() > 0);
			assertTrue("Check that result set is expected size", actualResults.size() == 1);
			
			assertEquals("Correct team membership", testTeam, actualResults.get(0)[0]);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			assertFalse("Exception executing report", true);
		}
	}
}
