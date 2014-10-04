package com.sutherland.helios.report.parameters.validation;

import java.util.LinkedHashMap;

import com.sutherland.helios.report.Report;
import com.sutherland.helios.report.parameters.ParameterInfo;

public class TimeTrendReportValidator extends ParameterGroupValidator 
{
	public TimeTrendReportValidator()
	{
		super();
	}
	
	public static LinkedHashMap<String,String> getParameterList()
	{
		LinkedHashMap<String,String> retval = new LinkedHashMap<String,String>();
		
		retval.put(ParameterInfo.START_DATE_PARAM, ParameterInfo.START_DATE_PARAM_DESC);
		retval.put(ParameterInfo.END_DATE_PARAM, ParameterInfo.END_DATE_PARAM_DESC);
		retval.put(ParameterInfo.TIME_GRAIN_PARAM, ParameterInfo.TIME_GRAIN_PARAM_DESC);
		retval.put(ParameterInfo.TEAM_NAMES_PARAM, ParameterInfo.TEAM_NAMES_PARAM_DESC);
		retval.put(ParameterInfo.AGENT_NAMES_PARAM, ParameterInfo.AGENT_NAMES_PARAM_DESC);
		retval.put(ParameterInfo.NUM_DRIVERS_PARAM, ParameterInfo.NUM_DRIVERS_PARAM_DESC);
		retval.put(ParameterInfo.DATE_FORMAT_PARAM, ParameterInfo.DATE_FORMAT_PARAM_DESC);
		
		return retval;
	}
	
	public boolean validate(Report report)
	{
		boolean retval = false;
		
		try
		{
			TimeIntervalValidator timeIntervalValidator = new TimeIntervalValidator();
			if(timeIntervalValidator.validate(report))
			{
				//time grain
				if(report.getParameters().getParameter(ParameterInfo.TIME_GRAIN_PARAM).validate())
				{	
					//date format
					if(report.getParameters().getParameter(ParameterInfo.DATE_FORMAT_PARAM).validate())
					{	
						//teams/agents --> needs at least one
						RosterValidator rosterValidator = new RosterValidator();
						if(rosterValidator.validate(report))
						{
							if(report.isDriversReport())
							{
								//num drivers only if it's supplied. trust the report to define it
								if(report.getParameters().getParameter(ParameterInfo.NUM_DRIVERS_PARAM).validate())
								{
									retval = true;
								}
								else
								{
									setErrorMessage("Error validating numDrivers");
								}
							}
							else
							{
								retval = true;
							}
						}
						else
						{
							setErrorMessage(rosterValidator.getErrorMessage());
						}
					}
					else
					{
						setErrorMessage("Error validating date format");
					}
				}
				else
				{
					setErrorMessage("Error validating time grain");
				}
			}
			else
			{
				setErrorMessage(timeIntervalValidator.getErrorMessage());
			}
		}
		catch (Exception e)
		{
			setErrorMessage("Exception validating time trend parameters: " + e.getMessage());
		}
			
		return retval;
	}
}
