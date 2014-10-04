package com.sutherland.helios.report.parameters.parameter;

import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

public class ReportClassNameParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.REPORT_CLASSNAME_PARAM;
	public final static String PARAM_DESC = ParameterInfo.REPORT_CLASSNAME_PARAM_DESC;
	public final static String NAME_REGEX = "[A-Za-z0-9\\.]+"; 
	
	public ReportClassNameParameter() 
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
			String className = StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN);
			
			if(className != null && !className.equals("") && className.matches(NAME_REGEX))
			{
				retval = true;
			}
		}
		catch(Exception e)
		{
			setErrorMessage("Source validation failed");
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
