package helios.report.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import helios.data.granularity.time.TimeGrains;
import helios.data.granularity.user.UserGrains;
import helios.report.ReportTypes;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MockReportTest extends TestCase 
{
	private Comparator<String[]> sortByGrain;
	private MockReport testReport;
	
	@Before
	protected void setUp() throws Exception 
	{
		sortByGrain = new Comparator<String[]>()
		{

			@Override
			public int compare(String[] o1, String[] o2) 
			{
				return o1[0].compareTo(o2[0]);
			};
		};
		
		testReport = new MockReport();
		
		testReport.getParameters().setStartDate("2014-01-01 00:00:00");
		testReport.getParameters().setEndDate("2014-03-01 23:59:59");
				
		testReport.getParameters().addTeamName("USA");
	}
	
	@After
	protected void tearDown()
	{
		if(testReport != null)
		{
			testReport.close();
		}
	}

	@Test
	public void testGetReportName() 
	{
		assertEquals("String check report name", "Mock Report", testReport.getReportName());
	}

	@Test
	public void testGetReportDesc() 
	{
		assertEquals("String check report description", "Mockup of a simulated report.", testReport.getReportDesc());
	}

	@Test
	public void testErrorMessage() 
	{
		assertEquals("getMessage() called before calling setMessage(String)", "", testReport.getErrorMessage());
		
		String testString = "derpyderpderp";
		testReport.setErrorMessage(testString);
		assertEquals("getMessage() called after calling setMessage(String)", testString, testReport.getErrorMessage());
	}
	
	@Test
	public void testIsDriversReport() 
	{
		assertFalse("Check report doesn't default to a drivers report", testReport.isDriversReport());
		
		testReport.getParameters().setIsDriversReport(true);

		assertTrue("Check report can be set to a drivers report", testReport.isDriversReport());
		
		testReport.getParameters().setIsDriversReport(false);

		assertFalse("Check report can be unset from a drivers report", testReport.isDriversReport());
	}

	@Test
	public void testIsTimeTrendReport() 
	{
		assertFalse("Calling isTimeTrendReport() without setting report type", testReport.isTimeTrendReport());
		
		assertTrue("Set report type to stack", testReport.getParameters().setReportType(ReportTypes.STACK_REPORT));
		assertFalse("Calling isTimeTrendReport() after setting report type to stack", testReport.isTimeTrendReport());
		
		assertTrue("Set report type to time trend", testReport.getParameters().setReportType(ReportTypes.TIME_TREND_REPORT));
		assertTrue("Calling isTimeTrendReport() after setting report type to time trend", testReport.isTimeTrendReport());
		assertFalse("Check that the report doesn't also register as a stack report", testReport.isStackReport());
		
		testReport.getParameters().setIsDriversReport(true);
		assertTrue("Set num drivers", testReport.getParameters().setNumDrivers(10));
		assertTrue("Calling isTimeTrendReport() after configuring report as a drivers report", testReport.isTimeTrendReport());
	}

	@Test
	public void testIsStackReport() 
	{
		assertFalse("Calling isStackReport() without setting report type", testReport.isStackReport());
		
		assertTrue("Set report type to time trend", testReport.getParameters().setReportType(ReportTypes.TIME_TREND_REPORT));
		assertTrue("Calling isTimeTrendReport() after setting report type to time trend", testReport.isTimeTrendReport());
		
		assertTrue("Set report type to stack", testReport.getParameters().setReportType(ReportTypes.STACK_REPORT));
		assertFalse("Calling isTimeTrendReport() after setting report type to stack", testReport.isTimeTrendReport());
	}

	@Test
	public void testTimeTrendReport() 
	{
		testReport.getParameters().setReportType(ReportTypes.TIME_TREND_REPORT);
		testReport.getParameters().setTimeGrain(TimeGrains.DAILY_GRANULARITY);
		
		ArrayList<String[]> actualResults = testReport.startReport();
		assertTrue("Result set is non-zero size", actualResults.size() > 0);
		
		ArrayList<String[]> expectedResults = new ArrayList<String[]>();
		
		expectedResults.add(new String[]{"2014-02-08", "12.34"});
		expectedResults.add(new String[]{"2014-02-16", "56.78"});
		expectedResults.add(new String[]{"2014-01-02", "56.78"});
		expectedResults.add(new String[]{"2014-02-05", "12.34"});
		expectedResults.add(new String[]{"2014-01-04", "12.34"});
		expectedResults.add(new String[]{"2014-02-12", "56.78"});
		expectedResults.add(new String[]{"2014-01-05", "12.34"});
		expectedResults.add(new String[]{"2014-02-03", "12.34"});
		expectedResults.add(new String[]{"2014-02-02", "12.34"});
		expectedResults.add(new String[]{"2014-01-19", "56.78"});
		expectedResults.add(new String[]{"2014-02-01", "56.78"});
		expectedResults.add(new String[]{"2014-01-09", "56.78"});
		expectedResults.add(new String[]{"2014-01-16", "56.78"});
		expectedResults.add(new String[]{"2014-01-14", "56.78"});
		expectedResults.add(new String[]{"2014-01-11", "56.78"});
		expectedResults.add(new String[]{"2014-01-20", "56.78"});
		expectedResults.add(new String[]{"2014-02-20", "12.34"});
		expectedResults.add(new String[]{"2014-02-11", "12.34"});
		
		assertTrue("Match expected and actual result set sizes", actualResults.size() == expectedResults.size());
		
		Collections.sort(actualResults, sortByGrain);
		Collections.sort(expectedResults, sortByGrain);

		for(int i =0; i<expectedResults.size(); i++)
		{
			assertTrue("Compare resultsets row: " + i, Arrays.equals(expectedResults.get(i), actualResults.get(i)));
		}
	}
	
	@Test
	public void testStackReport() 
	{
		testReport.getParameters().setReportType(ReportTypes.STACK_REPORT);
		testReport.getParameters().setUserGrain(UserGrains.AGENT_GRANULARITY);
		
		ArrayList<String[]> actualResults = testReport.startReport();
		assertTrue("Result set is non-zero size", actualResults.size() > 0);
		
		ArrayList<String[]> expectedResults = new ArrayList<String[]>();
		
		expectedResults.add(new String[]{"Kane:Patrick", "38.07"});

		
		assertTrue("Match expected and actual result set sizes", actualResults.size() == expectedResults.size());
		
		Collections.sort(actualResults, sortByGrain);
		Collections.sort(expectedResults, sortByGrain);

		for(int i =0; i<expectedResults.size(); i++)
		{
			assertTrue("Compare resultsets row: " + i, Arrays.equals(expectedResults.get(i), actualResults.get(i)));
		}
	}
}
