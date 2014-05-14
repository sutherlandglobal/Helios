package helios.report.parameters;

import helios.report.ReportTypes;
import helios.report.parameters.parameter.AgentNamesParameter;
import helios.report.parameters.parameter.EndDateParameter;
import helios.report.parameters.parameter.NumDriversParameter;
import helios.report.parameters.parameter.Parameter;
import helios.report.parameters.parameter.ReportTypeParameter;
import helios.report.parameters.parameter.SourceParameter;
import helios.report.parameters.parameter.StartDateParameter;
import helios.report.parameters.parameter.TeamNamesParameter;
import helios.report.parameters.parameter.TimeGrainParameter;
import helios.report.parameters.parameter.UserGrainParameter;
import helios.report.parameters.sanitize.StringSanitizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Basic contextual parameter stuff. Overridable by sites to add additional site-only context regarding parameters
 * 
 * @author Jason Diamond
 *
 */

public class ReportParameters
{
	private boolean isDriversReport;
	
	private final static HashMap<String,Integer> allowedReportTypes = new HashMap<String, Integer>();

	private HashMap<String, Integer> supportedReportTypes;
	private ArrayList<String> supportedParameters;
	private ArrayList<String> supportedHTTPParameters;
	
	private HashMap<String, Parameter> parameters;
	
	public ReportParameters() 
	{
		//super(datumID);
		
		parameters = new HashMap<String, Parameter>();
		
		supportedReportTypes = new HashMap<String, Integer>();
		supportedParameters = new ArrayList<String>();
		supportedHTTPParameters = new ArrayList<String>();
		
		//all supported report types and names
		allowedReportTypes.put(ReportTypes.STACK_REPORT_NAME, ReportTypes.STACK_REPORT);
		allowedReportTypes.put(ReportTypes.TIME_TREND_REPORT_NAME, ReportTypes.TIME_TREND_REPORT);
		
		
		//default parameter types and names
		addSupportedReportType(ReportTypes.STACK_REPORT_NAME, ReportTypes.STACK_REPORT);
		addSupportedReportType(ReportTypes.TIME_TREND_REPORT_NAME, ReportTypes.TIME_TREND_REPORT);
		
		parameters.put(ParameterInfo.AGENT_NAMES_PARAM, new AgentNamesParameter());
		parameters.put(ParameterInfo.END_DATE_PARAM, new EndDateParameter());
		parameters.put(ParameterInfo.NUM_DRIVERS_PARAM, new NumDriversParameter());
		parameters.put(ParameterInfo.REPORT_TYPE_PARAM, new ReportTypeParameter());
		parameters.put(ParameterInfo.SOURCE_PARAM, new SourceParameter());
		parameters.put(ParameterInfo.START_DATE_PARAM, new StartDateParameter());
		parameters.put(ParameterInfo.TEAM_NAMES_PARAM, new TeamNamesParameter());
		parameters.put(ParameterInfo.TIME_GRAIN_PARAM, new TimeGrainParameter());
		parameters.put(ParameterInfo.USER_GRAIN_PARAM, new UserGrainParameter());
		
		supportedHTTPParameters.add(ParameterInfo.AGENT_NAMES_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.END_DATE_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.NUM_DRIVERS_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.REPORT_TYPE_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.SOURCE_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.START_DATE_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.TEAM_NAMES_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.TIME_GRAIN_HTTP_PARAM_NAME);
		supportedHTTPParameters.add(ParameterInfo.USER_GRAIN_HTTP_PARAM_NAME);
	}
	
	public boolean isValidParameter(String param)
	{
		param = StringSanitizer.sanitize(param, Parameter.MAX_NAME_LEN);
		return supportedParameters.contains(param) || supportedHTTPParameters.contains(param);
	}
	
	public boolean addSupportedParameter(String parameterName)
	{
		parameterName = StringSanitizer.sanitize(parameterName, Parameter.MAX_NAME_LEN);
		return parameters.containsKey(parameterName) && supportedParameters.add(parameterName);
	}
	
	public boolean removeSupportedParameter(String parameterName)
	{
		parameterName = StringSanitizer.sanitize(parameterName, Parameter.MAX_NAME_LEN);
		return parameters.containsKey(parameterName) && supportedParameters.remove(parameterName);
	}
	
	public ArrayList<String> getSupportedParameters()
	{
		return supportedParameters;
	}
	
	public boolean addSupportedReportType(String typeName, Integer typeValue)
	{
		typeName = StringSanitizer.sanitize(typeName, Parameter.MAX_NAME_LEN);
	
		boolean retval = false;
		
		if(typeName != null && typeValue != null &&  allowedReportTypes.containsKey(typeName) && isAllowedReportTypeValue(typeValue.intValue()))
		{
			supportedReportTypes.put(typeName, typeValue);
			retval = true;
		}
		
		return retval;
	}

	public void clearSupportedReportTypes()
	{
		supportedReportTypes.clear();
	}
	
	public HashMap<String, Integer> getAllowedReportTypes()
	{
		return allowedReportTypes;
	}
	
	public HashMap<String, Integer> getSupportedReportTypes()
	{
		return supportedReportTypes;
	}
	
	public boolean isAllowedReportType(String reportTypeValue)
	{
		return isAllowedReportTypeValue( Integer.parseInt(reportTypeValue));
	}
	
	public boolean isAllowedReportTypeValue(int reportTypeValue)
	{
		boolean retval = false;
		
		for(Integer allowedTypeValue : allowedReportTypes.values())
		{
			if(reportTypeValue == allowedTypeValue.intValue())
			{
				retval = true;
				break;
			}
		}
		
		return retval;
	}
	
	/**
	 * Need a way of adding a generic parameter for the api. Only add the parameter if it's a valid helios parameter.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return	true if the addition was successful, false otherwise.
	 */
	public boolean addParameter(String key, String value) 
	{
		key = StringSanitizer.sanitize(key, Parameter.MAX_NAME_LEN);
		value = StringSanitizer.sanitize(value, Parameter.MAX_VAL_LEN);
		
		boolean retval = false;
		
		if(isValidParameter(key))
		{
			if(key.equals(ParameterInfo.REPORT_TYPE_PARAM) || key.equals(ParameterInfo.REPORT_TYPE_HTTP_PARAM_NAME))
			{
				retval = setReportType(value);
			}
			else if(key.equals(ParameterInfo.AGENT_NAMES_PARAM) || key.equals(ParameterInfo.AGENT_NAMES_HTTP_PARAM_NAME))
			{
				retval = addAgentName(value);
			}
			else if(key.equals(ParameterInfo.TEAM_NAMES_PARAM) || key.equals(ParameterInfo.TEAM_NAMES_HTTP_PARAM_NAME))
			{
				retval = addTeamName(value);
			}
			else if(key.equals(ParameterInfo.TIME_GRAIN_PARAM) || key.equals(ParameterInfo.TIME_GRAIN_HTTP_PARAM_NAME))
			{
				retval = setTimeGrain(value);
			}
			else if(key.equals(ParameterInfo.USER_GRAIN_PARAM) || key.equals(ParameterInfo.USER_GRAIN_HTTP_PARAM_NAME))
			{
				retval = setUserGrain(value);
			}
			else if(key.equals(ParameterInfo.START_DATE_PARAM) || key.equals(ParameterInfo.START_DATE_HTTP_PARAM_NAME))
			{
				retval = setStartDate(value);
			}
			else if(key.equals(ParameterInfo.END_DATE_PARAM) || key.equals(ParameterInfo.END_DATE_HTTP_PARAM_NAME))
			{
				retval = setEndDate(value);
			}
			else if(key.equals(ParameterInfo.SOURCE_PARAM) || key.equals(ParameterInfo.SOURCE_HTTP_PARAM_NAME))
			{
				retval = setSource(value);
			}
			else if(key.equals(ParameterInfo.NUM_DRIVERS_PARAM) || key.equals(ParameterInfo.NUM_DRIVERS_HTTP_PARAM_NAME))
			{
				retval = setNumDrivers(Integer.parseInt(value));
			}
			else if(key.equals(ParameterInfo.REPORT_CLASSNAME_PARAM))
			{
				retval = setReportClassName(value);
			}
		}
		
		return retval;
	}
	
	/**
	 * Need a way of retrieving a generic parameter for the api. Only add the parameter if it's a valid helios parameter.
	 * 
	 * @param key
	 * 
	 * @return	The values stored for the given parameter
	 */
	public ArrayList<String> getParameterValues(String key) 
	{
		key = StringSanitizer.sanitize(key, Parameter.MAX_NAME_LEN);
		
		ArrayList<String> retval = null;
		
		if(isValidParameter(key))
		{
			if(key.equals(ParameterInfo.AGENT_NAMES_PARAM) || key.equals(ParameterInfo.AGENT_NAMES_HTTP_PARAM_NAME))
			{
				retval = getAgentNames();
			}
			else if(key.equals(ParameterInfo.TEAM_NAMES_PARAM) || key.equals(ParameterInfo.TEAM_NAMES_HTTP_PARAM_NAME))
			{
				retval = getTeamNames();
			}
			else if(key.equals(ParameterInfo.TIME_GRAIN_PARAM) || key.equals(ParameterInfo.TIME_GRAIN_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getTimeGrain());
			}
			else if(key.equals(ParameterInfo.USER_GRAIN_PARAM) || key.equals(ParameterInfo.USER_GRAIN_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getUserGrain());
			}
			else if(key.equals(ParameterInfo.START_DATE_PARAM) || key.equals(ParameterInfo.START_DATE_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getStartDate());
			}
			else if(key.equals(ParameterInfo.END_DATE_PARAM) || key.equals(ParameterInfo.END_DATE_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getEndDate());
			}
			else if(key.equals(ParameterInfo.SOURCE_PARAM) || key.equals(ParameterInfo.SOURCE_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getSource());
			}
			else if(key.equals(ParameterInfo.NUM_DRIVERS_PARAM) || key.equals(ParameterInfo.NUM_DRIVERS_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getNumDrivers());
			}
			else if(key.equals(ParameterInfo.REPORT_CLASSNAME_PARAM))
			{
				retval = new ArrayList<String>();
				retval.add(getReportClassName());
			}
			else if(key.equals(ParameterInfo.REPORT_TYPE_PARAM) || key.equals(ParameterInfo.REPORT_TYPE_HTTP_PARAM_NAME))
			{
				retval = new ArrayList<String>();
				retval.add(getReportType());
			}
		}
		
		return retval;
	}
	
	public Parameter getParameter(String parameterName)
	{
		parameterName = StringSanitizer.sanitize(parameterName, Parameter.MAX_NAME_LEN);
		
		Parameter retval = null;
		
		if(isValidParameter(parameterName) && parameters.containsKey(parameterName))
		{
			retval = parameters.get(parameterName);
		}
		
		return retval;
	}

	public String getStartDate()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.START_DATE_PARAM).getValues().get(0);
		}
		catch(Exception e)
		{}
		
		return retval;
	}
	
	public boolean setStartDate(String startDate)
	{
		parameters.get(ParameterInfo.START_DATE_PARAM).clearValues();
		return parameters.get(ParameterInfo.START_DATE_PARAM).addValue(startDate);
	}
	
	public String getEndDate()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.END_DATE_PARAM).getValues().get(0);
		}
		catch(Exception e)
		{}
		
		return retval;
	}
	
	public boolean setEndDate(String endDate)
	{
		parameters.get(ParameterInfo.END_DATE_PARAM).clearValues();
		return parameters.get(ParameterInfo.END_DATE_PARAM).addValue(endDate);
	}

	public String getTimeGrain()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.TIME_GRAIN_PARAM).getValues().get(0);
		}
		catch(Exception t)
		{}
		
		return retval;
	}
	
	public boolean setTimeGrain(int timeGrain)
	{
		return setTimeGrain("" + timeGrain);
	}
	
	public boolean setTimeGrain(String timeGrain)
	{
		parameters.get(ParameterInfo.TIME_GRAIN_PARAM).clearValues();
		return parameters.get(ParameterInfo.TIME_GRAIN_PARAM).addValue(timeGrain);
	}
	
	public String getUserGrain()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.USER_GRAIN_PARAM).getValues().get(0);
		}
		catch(Throwable t)
		{}
		
		return retval;
	}
	
	public boolean setUserGrain(int userGrain)
	{
		return setUserGrain("" + userGrain);
	}
	
	public boolean setUserGrain(String userGrain)
	{
		parameters.get(ParameterInfo.USER_GRAIN_PARAM).clearValues();
		return parameters.get(ParameterInfo.USER_GRAIN_PARAM).addValue(userGrain);
	}
	
	public String getReportType()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.REPORT_TYPE_PARAM).getValues().get(0);
		}
		catch(Exception t)
		{}
		
		return retval;
	}
	
	public boolean setReportType(int reportType)
	{
		return setReportType("" + reportType);
	}
	
	public boolean setReportType(String reportType)
	{
		boolean retval = false;
		
		if(isAllowedReportType(reportType) )
		{
			parameters.get(ParameterInfo.REPORT_TYPE_PARAM).clearValues();
			retval = parameters.get(ParameterInfo.REPORT_TYPE_PARAM).addValue(reportType);
		}
		return retval; 
	}
	
	public String getReportClassName()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.REPORT_CLASSNAME_PARAM).getValues().get(0);
		}
		catch(Exception t)
		{}
		
		return retval;
	}
	
	public boolean setReportClassName(String reportClassName)
	{
		parameters.get(ParameterInfo.REPORT_CLASSNAME_PARAM).clearValues();
		return parameters.get(ParameterInfo.REPORT_CLASSNAME_PARAM).addValue(reportClassName);
	}
	
	public ArrayList<String> getAgentNames()
	{
		ArrayList<String> retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.AGENT_NAMES_PARAM).getValues();
		}
		catch(Exception t)
		{}
		
		return retval;
	}
	
	public boolean addAgentName(String agentName)
	{
		return parameters.get(ParameterInfo.AGENT_NAMES_PARAM).addValue(agentName);
	}
	
	public boolean setAgentNames(ArrayList<String> agentNames)
	{
		boolean retval = false;
		
		if(agentNames != null)
		{
			retval = true;
			parameters.get(ParameterInfo.AGENT_NAMES_PARAM).clearValues();

			for(String agentName : agentNames)
			{
				if(!addAgentName(agentName))
				{
					retval = false;
					break;
				}
			}
		}
		return retval;
	}
	
	public boolean removeAgentName(String agentName)
	{
		return parameters.get(ParameterInfo.AGENT_NAMES_PARAM).getValues().remove(agentName);
	}
	
	public ArrayList<String> getTeamNames()
	{
		ArrayList<String> retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.TEAM_NAMES_PARAM).getValues();
		}
		catch(Exception t)
		{}
		
		return retval;
	}
	
	public boolean setTeamNames(ArrayList<String> teamNames)
	{
		boolean retval = false;
		
		if(teamNames != null)
		{
			retval = true;
			
			parameters.get(ParameterInfo.TEAM_NAMES_PARAM).clearValues();
			
			for(String teamName : teamNames)
			{
				if(!addTeamName(StringSanitizer.sanitize(teamName,Parameter.MAX_NAME_LEN)))
				{
					retval = false;
					break;
				}
			}
		}
		
		return retval;
	}
	
	public boolean addTeamName(String teamName)
	{
		return parameters.get(ParameterInfo.TEAM_NAMES_PARAM).addValue(teamName);
	}
	
	public boolean removeTeamName(String teamName)
	{
		return parameters.get(ParameterInfo.TEAM_NAMES_PARAM).getValues().remove(teamName);
	}

	public String getNumDrivers()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.NUM_DRIVERS_PARAM).getValues().get(0);
		}
		catch(Exception e)
		{}
		
		return retval;
	}
	
	public boolean setNumDrivers(int numDrivers)
	{
		parameters.get(ParameterInfo.NUM_DRIVERS_PARAM).clearValues();
		return parameters.get(ParameterInfo.NUM_DRIVERS_PARAM).addValue(numDrivers);
	}
	
	public String getSource()
	{
		String retval =null;
		try
		{
			retval = parameters.get(ParameterInfo.SOURCE_PARAM).getValues().get(0);
		}
		catch(Exception t)
		{}
		
		return retval;
	}
	
	public boolean setSource(String source)
	{
		parameters.get(ParameterInfo.SOURCE_PARAM).clearValues();
		return parameters.get(ParameterInfo.SOURCE_PARAM).addValue(source);
	}
	
    /**
     * Determine if this report is a time-centric (AGENT_TIME/TEAM_TIME) report.
     *  
     * @return      True if the report is a time-centric report. False otherwise.
     */
    public boolean isTimeReport()
    {
            String reportType = getReportType();
            
            return 
            (
            		reportType != null &&
                    (
                    				reportType.equals("" + ReportTypes.TIME_TREND_REPORT)
                    )
            );
    }
    
    public boolean isStackReport()
    {
    	String reportType = getReportType();
        
        return 
        (
        		reportType != null &&
        		(
        				reportType.equals("" + ReportTypes.STACK_REPORT) 
        		)
        );
    }
    
    public void setIsDriversReport(boolean isDriversReport)
    {
    	this.isDriversReport = isDriversReport;
    }
    
    public boolean isDriversReport()
    {
    	return isDriversReport;
    }
    
    public String toString()
    {
    	StringBuilder output = new StringBuilder();
    	
		for (String attrName : parameters.keySet()) 
		{
			output.append(attrName);
			output.append(" => ");
			
			output.append(Arrays.asList(parameters.get(attrName)));
			output.append("\n");		
		}
    	
    	return output.toString();
    }
}
