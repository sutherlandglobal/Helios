/**
 * 
 */
package com.sutherland.helios.report.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.sutherland.helios.api.report.frontend.ReportFrontEndGroups;
import com.sutherland.helios.data.Aggregation;
import com.sutherland.helios.data.formatting.NumberFormatter;
import com.sutherland.helios.data.granularity.user.UserGrains;
import com.sutherland.helios.date.formatting.DateFormatter;
import com.sutherland.helios.date.parsing.DateParser;
import com.sutherland.helios.exceptions.ReportSetupException;
import com.sutherland.helios.report.Report;
import com.sutherland.helios.report.parameters.groups.ReportParameterGroups;
import com.sutherland.helios.roster.test.MockRoster;
import com.sutherland.helios.statistics.Statistics;

/**
 * @author Jason Diamond
 *
 */
public class MockReport extends Report 
{
	private MockRoster roster;
	private final static String AMTS_ATTR = "amounts";
	
	public static String uiGetReportName()
	{
		return "Mock Report";
	}
	
	public static String uiGetReportDesc()
	{
		return "Mockup of a simulated report.";
	}
	
	public final static LinkedHashMap<String, String> uiSupportedReportFrontEnds = ReportFrontEndGroups.BASIC_METRIC_FRONTENDS;
	
	public final static LinkedHashMap<String, ArrayList<String>> uiReportParameters = ReportParameterGroups.BASIC_METRIC_REPORT_PARAMETERS;
	
	/**
	 * @throws ReportSetupException
	 */
	public MockReport() throws ReportSetupException 
	{
		super();
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.report.Report#setupDataSourceConnections()
	 */
	@Override
	protected boolean setupDataSourceConnections() 
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.report.Report#setupReport()
	 */
	@Override
	protected boolean setupReport() 
	{
		reportName = MockReport.uiGetReportName();
		reportDesc = MockReport.uiGetReportDesc();
		
		try 
		{
			roster = new MockRoster();
			
			for(Entry<String, ArrayList<String>> reportType : uiReportParameters.entrySet())
			{
				for(String paramName :  reportType.getValue())
				{
					getParameters().addSupportedParameter(paramName);
				}
			}
		} 
		catch (ReportSetupException e) 
		{
			e.printStackTrace();
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.report.Report#setupLogger()
	 */
	@Override
	protected boolean setupLogger() 
	{
		boolean retval = false;
		
		try
		{
			//setLoggerHandle(Constants.JUNIT_LOGGING_HANDLE);
			retval = true;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return retval;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.report.Report#runReport()
	 */
	@Override
	protected ArrayList<String[]> runReport() throws Exception 
	{
		roster.setParameters(getParameters());
		roster.setChildReport(true);
		
		roster.load();
		
		ArrayList<String[]> retval = new ArrayList<String[]>();
		ArrayList<String[]> rawData = new ArrayList<String[]>();
		
		rawData.add(new String[]{"2014-01-01 07:10:02","004","12.34"});
		rawData.add(new String[]{"2014-01-01 07:10:02","003","12.34"});
		rawData.add(new String[]{"2014-01-01 07:10:02","002","12.34"});
		rawData.add(new String[]{"2014-01-02 07:10:02","001","56.78"});
		rawData.add(new String[]{"2014-01-02 07:10:02","004","56.78"});
		rawData.add(new String[]{"2014-01-02 07:10:02","003","56.78"});
		rawData.add(new String[]{"2014-01-02 07:10:02","002","56.78"});
		rawData.add(new String[]{"2014-01-02 07:16:02","001","56.78"});
		rawData.add(new String[]{"2014-01-03 07:16:02","004","56.78"});
		rawData.add(new String[]{"2014-01-03 07:16:02","003","56.78"});
		rawData.add(new String[]{"2014-01-03 07:16:02","002","12.34"});
		rawData.add(new String[]{"2014-01-04 07:16:02","001","12.34"});
		rawData.add(new String[]{"2014-01-04 07:16:02","004","12.34"});
		rawData.add(new String[]{"2014-01-05 07:16:02","003","12.34"});
		rawData.add(new String[]{"2014-01-05 07:16:02","002","12.34"});
		rawData.add(new String[]{"2014-01-05 07:16:02","001","12.34"});
		rawData.add(new String[]{"2014-01-07 07:16:02","004","12.34"});
		rawData.add(new String[]{"2014-01-07 07:16:02","003","12.34"});
		rawData.add(new String[]{"2014-01-08 07:16:23","002","12.34"});
		rawData.add(new String[]{"2014-01-09 07:16:23","001","56.78"});
		rawData.add(new String[]{"2014-01-10 07:16:23","004","56.78"});
		rawData.add(new String[]{"2014-01-11 23:16:23","003","56.78"});
		rawData.add(new String[]{"2014-01-11 23:16:23","002","56.78"});
		rawData.add(new String[]{"2014-01-11 23:16:23","001","56.78"});
		rawData.add(new String[]{"2014-01-12 23:16:23","004","56.78"});
		rawData.add(new String[]{"2014-01-12 23:16:23","003","56.78"});
		rawData.add(new String[]{"2014-01-12 23:16:23","002","56.78"});
		rawData.add(new String[]{"2014-01-14 23:16:23","001","56.78"});
		rawData.add(new String[]{"2014-01-14 23:16:23","004","56.78"});
		rawData.add(new String[]{"2014-01-14 23:16:23","003","56.78"});
		rawData.add(new String[]{"2014-01-15 23:16:23","002","56.78"});
		rawData.add(new String[]{"2014-01-16 23:16:23","001","56.78"});
		rawData.add(new String[]{"2014-01-16 23:16:23","004","56.78"});
		rawData.add(new String[]{"2014-01-16 23:16:23","003","56.78"});
		rawData.add(new String[]{"2014-01-17 23:16:23","002","56.78"});
		rawData.add(new String[]{"2014-01-19 23:16:23","001","56.78"});
		rawData.add(new String[]{"2014-01-19:07 16:23","004","56.78"});
		rawData.add(new String[]{"2014-01-20 23:16:23","003","56.78"});
		rawData.add(new String[]{"2014-01-20 23:16:23","001","56.78"});
		rawData.add(new String[]{"2014-01-20 23:16:23","004","56.78"});
		rawData.add(new String[]{"2014-02-01 11:16:23","003","56.78"});
		rawData.add(new String[]{"2014-02-01 11:16:23","002","56.78"});
		rawData.add(new String[]{"2014-02-01 11:16:23","001","56.78"});
		rawData.add(new String[]{"2014-02-02 11:16:23","004","56.78"});
		rawData.add(new String[]{"2014-02-02 11:16:23","003","56.78"});
		rawData.add(new String[]{"2014-02-02 11:10:23","002","56.78"});
		rawData.add(new String[]{"2014-02-02 11:10:23","001","12.34"});
		rawData.add(new String[]{"2014-02-02 11:10:23","004","12.34"});
		rawData.add(new String[]{"2014-02-03 11:10:23","003","12.34"});
		rawData.add(new String[]{"2014-02-03 11:10:23","002","12.34"});
		rawData.add(new String[]{"2014-02-03 11:10:23","001","12.34"});
		rawData.add(new String[]{"2014-02-04 11:10:23","004","12.34"});
		rawData.add(new String[]{"2014-02-04 11:10:23","003","12.34"});
		rawData.add(new String[]{"2014-02-05 11:10:23","002","12.34"});
		rawData.add(new String[]{"2014-02-05 11:10:23","001","12.34"});
		rawData.add(new String[]{"2014-02-05 11:10:23","004","12.34"});
		rawData.add(new String[]{"2014-02-07 11:10:23","003","12.34"});
		rawData.add(new String[]{"2014-02-07 11:10:23","002","12.34"});
		rawData.add(new String[]{"2014-02-08 11:10:23","001","12.34"});
		rawData.add(new String[]{"2014-02-09 11:10:23","004","12.34"});
		rawData.add(new String[]{"2014-02-10 11:10:23","003","12.34"});
		rawData.add(new String[]{"2014-02-11 11:10:23","002","12.34"});
		rawData.add(new String[]{"2014-02-11 11:10:23","001","12.34"});
		rawData.add(new String[]{"2014-02-11 11:10:23","004","12.34"});
		rawData.add(new String[]{"2014-02-12 11:10:23","003","56.78"});
		rawData.add(new String[]{"2014-02-12 11:10:23","002","56.78"});
		rawData.add(new String[]{"2014-02-12 11:10:23","001","56.78"});
		rawData.add(new String[]{"2014-02-14 11:10:23","004","56.78"});
		rawData.add(new String[]{"2014-02-14 11:10:23","003","56.78"});
		rawData.add(new String[]{"2014-02-14 11:10:23","002","56.78"});
		rawData.add(new String[]{"2014-02-15 11:10:23","004","56.78"});
		rawData.add(new String[]{"2014-02-16 11:10:23","003","56.78"});
		rawData.add(new String[]{"2014-02-16 11:10:23","002","56.78"});
		rawData.add(new String[]{"2014-02-16 11:10:23","001","56.78"});
		rawData.add(new String[]{"2014-02-17 11:10:23","004","56.78"});
		rawData.add(new String[]{"2014-02-19 11:10:23","003","56.78"});
		rawData.add(new String[]{"2014-02-19:07 10:23","002","12.34"});
		rawData.add(new String[]{"2014-02-20 07:10:23","001","12.34"});
		rawData.add(new String[]{"2014-02-20 07:10:23","004","12.34"});
		rawData.add(new String[]{"2014-02-20 07:10:23","003","12.34"});
		
		Aggregation reportGrainData = new Aggregation();
		
		String userID, timeStamp, amount, reportGrain;
		int timeGrain, userGrain;
		for(String[] row : rawData)
		{
			userID = row[1];

			
			if(roster.getUser(userID) != null)
			{
				timeStamp = row[0];
				amount = row[2];

				//time grain for time reports
				if(isTimeTrendReport())
				{
					timeGrain = Integer.parseInt(getParameters().getTimeGrain());
					reportGrain = DateFormatter.getFormattedDate(DateParser.convertSQLDateToGregorian(timeStamp), timeGrain);
				}
				else //if(isStackReport())
				{
					//is stack report
					userGrain = Integer.parseInt(getParameters().getUserGrain());
					reportGrain = UserGrains.getUserGrain(userGrain, roster.getUser(userID));
				}
				
				reportGrainData.addDatum(reportGrain);
				reportGrainData.getDatum(reportGrain).addAttribute(AMTS_ATTR);
				reportGrainData.getDatum(reportGrain).addData(AMTS_ATTR, amount);
			}
		}
		
		double finalAmt;
		
		retval = new ArrayList<String[]>(reportGrainData.getSize());
		
		for(String grain : reportGrainData.getDatumIDList())
		{
			finalAmt = Statistics.getAverage(reportGrainData.getDatum(grain).getAttributeData(AMTS_ATTR));

			retval.add(new String[]{grain, NumberFormatter.convertToCurrency(finalAmt) });
		}
		
		return retval;
	}
	
	public void close()
	{
		//super.close();
	}

	@Override
	public ArrayList<String> getReportSchema() 
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		if(isTimeTrendReport())
		{
			retval.add("Date");
			retval.add("Amount");
		}
		else if(isStackReport())
		{
			retval.add("Entity");
			retval.add("Amount");
		}
		
		return retval;
	}

	@Override
	protected void logInfoMessage(String message) 
	{

		
	}

	@Override
	protected void logWarnMessage(String message) 
	{

		
	}

	@Override
	protected void logErrorMessage(String message) 
	{
		
	}
}
