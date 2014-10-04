package com.sutherland.helios.report.parameters.parameter;

import com.sutherland.helios.data.granularity.user.UserGrains;
import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

public class UserGrainParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.USER_GRAIN_PARAM;
	public final static String PARAM_DESC = ParameterInfo.USER_GRAIN_PARAM_DESC;
	
	public UserGrainParameter() 
	{
		super();
	}
	
	public boolean addValue(String val)
	{
		return addSingleValue(val);
	}

	@Override
	public boolean validate() 
	{
		boolean retval = false;
		try
		{
			int userGrain = Integer.parseInt(StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN));
			
			retval = 
			(
					userGrain == UserGrains.AGENT_GRANULARITY || 
					userGrain == UserGrains.TEAM_GRANULARITY || 
					userGrain == UserGrains.PROGRAM_GRANULARITY || 
					userGrain == UserGrains.ORGUNIT_GRANULARITY
			);
		}
		catch(Exception e)
		{
			setErrorMessage("User grain validation failed");
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
