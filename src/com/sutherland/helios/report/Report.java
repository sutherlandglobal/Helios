/**
 * 
 */
package com.sutherland.helios.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.sutherland.helios.date.parsing.DateParser;
import com.sutherland.helios.exceptions.ExceptionFormatter;
import com.sutherland.helios.exceptions.ReportSetupException;
import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.ReportParameters;
import com.sutherland.helios.report.parameters.groups.ReportParameterGroups;
import com.sutherland.helios.report.parameters.parameter.Parameter;
import com.sutherland.helios.report.parameters.validation.ParameterGroupValidator;
import com.sutherland.helios.report.parameters.validation.StackReportValidator;
import com.sutherland.helios.report.parameters.validation.TimeTrendReportValidator;

/**
 * Architecture for a report.
 * 
 * @author Jason Diamond
 * 
 */
public abstract class Report implements ReportTypes, ReportParameterGroups
{
	protected String reportName;
	protected String reportDesc;

	protected ReportParameters parameters;

	private String errorMsg;
	protected boolean isChildReport;

	protected String logID;
	protected ArrayList<String[]> data;
	
	protected final static String LOG_ID_PREFIX = "id=";

	/**
	 * Build the stuff that every report should want. Specifically a logger, and
	 * each report's prerequisite resources like database connections and
	 * rosters. The actual data store interaction is best left to implementing
	 * subclasses.
	 * 
	 * @throws ReportSetupException		If any prerequisites fail 
	 * @throws LoggerCreationException 
	 * 
	 */
	protected Report() throws ReportSetupException
	{
		data = new ArrayList<String[]>();
		
		isChildReport = false;

		setErrorMessage("");

		parameters = new ReportParameters();

		getParameters().addSupportedParameter(ParameterInfo.REPORT_TYPE_PARAM);
		getParameters().addSupportedParameter(ParameterInfo.SOURCE_PARAM);

		if( !setupLogger())
		{
			throw new ReportSetupException("Logger setup failed");
		}

		if( !setupReport() )
		{
			throw new ReportSetupException("Report setup failed.");
		}
		
		logInfoMessage( "Building report " + getReportName() );

		if( !setupDataSourceConnections())
		{
			throw new ReportSetupException("Connecting to database failed.");
		}		
	}

	/**
	 * Attempt to establish connections to all required datasources. A report by
	 * definition has at least one, and possibly many.
	 * 
	 * @return True if the connection was established, false otherwise.
	 */
	protected abstract boolean setupDataSourceConnections();

	/**
	 * Attempt to fulfill the prerequisites of the report.
	 * 
	 * @return True if the setup was run successfully, false otherwise.
	 */
	protected abstract boolean setupReport();

	protected abstract boolean setupLogger();
	
	/**
	 * Process the report.
	 * 
	 * @return The result set, null if the report fails.
	 */
	public void startReport() 
	{
		try 
		{

			if (validateParameters()) 
			{
				long startTime = System.currentTimeMillis();
				data.clear();
				data.addAll(loadData());
				
				//sort the data
				//standardize how information leaves the data layer. report types imply the structures assumed
				sortData();
				
				long endTime = System.currentTimeMillis();
				
				logInfoMessage("Execution completed in: " + (endTime - startTime) + " ms, returning rows: " + data.size());
			} 
			else 
			{
				setErrorMessage("Parameter validation failed: " + getErrorMessage());
				logErrorMessage(getErrorMessage());
			}
		}
		catch (Exception e) 
		{
			setErrorMessage(ExceptionFormatter.asString(e));
			logErrorMessage(getErrorMessage());
		}
	}
	
	public ArrayList<String[]> getData()
	{
		return data;
	}
	
	protected void sortData()
	{
		if(isTimeTrendReport())
		{
			//sort data by time grain, which will never be a full date
			Collections.sort(data, new Comparator<String[]>()
			{
					public int compare(String[] arg0, String[] arg1) 
					{
						//time trend schema is always date first
						return DateParser.compareDateGrains(arg0[0], arg1[0]);
					}
			}
			);
		}
		else if(isStackReport())
		{
			Collections.sort(data, new Comparator<String[]>()
			{
					public int compare(String[] arg0, String[] arg1) 
					{
						int retval;
						if(arg0[1] == null)
						{
							retval = -1;
						}
						else if(arg1[1] == null)
						{
							retval = 1;
						}
						else
						{
							//for n elements, compare row[1]
							//compare value, descending
							retval =-1 *( new Double(arg0[1]).compareTo(new Double(arg1[1])));
						}
						return retval;
					}
			}
			);
		}
	}

	/**
	 * Retrieve any error messages.
	 * 
	 * @return The error message string.
	 */
	public String getErrorMessage() 
	{
		return errorMsg;
	}
	
	public String getReportName() 
	{
		return reportName;
	}

	public String getReportDesc() 
	{
		return reportDesc;
	}

	/**
	 * Set an error message.
	 * 
	 * @param errorMsg The error message string.
	 */
	public void setErrorMessage(String errorMsg) 
	{
		this.errorMsg = errorMsg;
	}

	public boolean isTimeTrendReport()
	{
		return parameters.isTimeReport();
	}
	
	public boolean isStackReport()
	{
		return parameters.isStackReport();
	}
	
	public boolean isDriversReport()
	{
		return parameters.isDriversReport();
	}
	
	public int getDataSize()
	{
		return data.size();
	}
	
	/**
	 * Process the child report. Only report.startReport should call this.
	 * 
	 * 
	 * @return The result set.
	 * 
	 * @throws Any
	 *             exception generated by children, up to its caller,
	 *             startReport() or another proper handling mechanism
	 * 
	 */
	protected abstract ArrayList<String[]> loadData() throws Exception;

	public abstract ArrayList<String> getReportSchema();
	
	public abstract String getUnits();
	
	/**
	 * Validate the report's parameters. 
	 * 
	 * @return True if the report's parameters are valid and viable to run the
	 *         report. False otherwise.
	 */
	protected boolean validateParameters()
	{
		//for each parameter designated, validate it
		boolean retval = false;

		//any report has a type, even if it's hardcoded twice
		
		//determine if this is a stack report or time trend
		//validate accordingly

		Parameter reportTypeParam = getParameters().getParameter(ParameterInfo.REPORT_TYPE_PARAM);

		if(reportTypeParam != null)
		{
			if(reportTypeParam.validate())
			{
				String reportType = getParameters().getReportType();
								
				ParameterGroupValidator validator = null;
	
				if(reportType.equals("" + ReportTypes.TIME_TREND_REPORT))
				{
					validator = new TimeTrendReportValidator();
				}
				else if(reportType.equals("" + ReportTypes.STACK_REPORT))
				{
					validator = new StackReportValidator();
				}
				else
				{
					setErrorMessage( "Unknown report type, defaulting to time trend: " + reportType);
					//shouldn't get here due to report type validation
					validator = new TimeTrendReportValidator();
				}
	
				retval = validator.validate(this);
	
				if(!retval)
				{
					setErrorMessage(validator.getErrorMessage());
					logErrorMessage(getErrorMessage());
				}
			}
			else
			{
				setErrorMessage(reportTypeParam.getErrorMessage());
				logErrorMessage(getErrorMessage());
			}
		}
		else
		{
			setErrorMessage("Error: could not determine report type.");
			logErrorMessage(getErrorMessage());
		}
		
		return retval;
	}
	
	/**
	 * Close the report. Subclasses (reports) need to run super.close() to hit
	 * this, any resources owned by Report bleed out.
	 */
	public void close()
	{
		logInfoMessage( "Closing report " + getReportName());
	}


	/**
	 * Mark the report as a child or parent report.
	 * 
	 * @param isChild
	 *            True if the report is a child of another report, false
	 *            otherwise.
	 */
	public void setChildReport(boolean isChild) 
	{
		isChildReport = isChild;
	}

	/**
	 * Generate a string that summarizes this report object. Information
	 * includes report name, parameters defined and their respective values, if
	 * any. Useful in logging.
	 * 
	 * @return The summary string.
	 */

	public String toString() 
	{
		return getReportName()  + " with parameters: " + parameters.toString();
	}
	
	public ReportParameters getParameters()
	{
		return parameters;
	}
	
	public void setParameters(ReportParameters newParameters)
	{
		parameters = newParameters;
	}
	
	protected abstract void logInfoMessage(String message);
	protected abstract void logWarnMessage(String message);
	protected abstract void logErrorMessage(String message);
}
