package helios.report.parameters.validation;

import java.util.LinkedHashMap;

import helios.report.Report;
import helios.report.parameters.ParameterInfo;

public class StackReportValidator extends ParameterGroupValidator 
{
	public StackReportValidator()
	{
		super();
	}
	
	
	public static LinkedHashMap<String,String> getParameterList()
	{
		LinkedHashMap<String,String> retval = new LinkedHashMap<String,String>();
		
		retval.put(ParameterInfo.START_DATE_PARAM, ParameterInfo.START_DATE_PARAM_DESC);
		retval.put(ParameterInfo.END_DATE_PARAM, ParameterInfo.END_DATE_PARAM_DESC);
		retval.put(ParameterInfo.USER_GRAIN_PARAM, ParameterInfo.USER_GRAIN_PARAM_DESC);
		retval.put(ParameterInfo.TEAM_NAMES_PARAM, ParameterInfo.TEAM_NAMES_PARAM_DESC);
		retval.put(ParameterInfo.AGENT_NAMES_PARAM, ParameterInfo.AGENT_NAMES_PARAM_DESC);
		
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
				if(report.getParameters().getParameter(ParameterInfo.USER_GRAIN_PARAM).validate())
				{
					//teams/agents --> needs at least one
					RosterValidator rosterValidator = new RosterValidator();
					if(rosterValidator.validate(report))
					{
						retval = true;
					}
					else
					{
						setErrorMessage(rosterValidator.getErrorMessage());
					}
				}
				else
				{
					setErrorMessage("Error validating user grain");
				}
			}
			else
			{
				setErrorMessage(timeIntervalValidator.getErrorMessage());
			}
		}
		catch (Exception e)
		{
			setErrorMessage("Exception validating stack parameters" + e.getMessage());
		}
			
		return retval;
	}
}
