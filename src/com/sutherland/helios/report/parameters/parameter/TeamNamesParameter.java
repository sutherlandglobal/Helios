package com.sutherland.helios.report.parameters.parameter;

import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

public class TeamNamesParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.TEAM_NAMES_PARAM;
	public final static String PARAM_DESC = ParameterInfo.TEAM_NAMES_PARAM_DESC;
	public final static String NAME_REGEX = "[A-Za-z\\ \\-\\'\\,]+"; 
	
	public TeamNamesParameter() 
	{
		super();
	}

	@Override
	public boolean validate() 
	{
		boolean retval = true;
		try
		{
			for(String teamName : values)
			{
				teamName = StringSanitizer.sanitize(teamName, MAX_VAL_LEN);
				if(teamName == null || teamName.equals("") || !teamName.matches(NAME_REGEX))
				{
					retval = false;
					break;
				}
			}
		}
		catch(Exception e)
		{
			setErrorMessage("Team name validation failed");
			retval = false;
		}
		
		return retval;
	}
	
	@Override
	public String toString()
	{
		StringBuilder retval = new StringBuilder();
		
		retval.append("Param Name: " + PARAM_NAME);
		retval.append("\nParam Desc: " + PARAM_DESC);
		
		retval.append("\n" + super.toString());
		
		return retval.toString();
	}
}
