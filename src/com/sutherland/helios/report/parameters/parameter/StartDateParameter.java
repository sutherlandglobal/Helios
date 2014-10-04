package com.sutherland.helios.report.parameters.parameter;

import java.util.GregorianCalendar;

import com.sutherland.helios.date.parsing.DateParser;
import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

public class StartDateParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.START_DATE_PARAM;
	public final static String PARAM_DESC = ParameterInfo.START_DATE_PARAM_DESC;
	
	public StartDateParameter() 
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
			String paramDate = StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN);
			
			clearValues();
			if(paramDate.equalsIgnoreCase(DateParser.NOW_DATE_KEYWORD))
			{
				GregorianCalendar now = new GregorianCalendar();
				addValue(DateParser.toSQLDateFormat(now));
			}
			else
			{
				addValue(DateParser.toSQLDateFormat(DateParser.convertSQLDateToGregorian(paramDate)));
			}
			
			retval = true;
		}
		catch (Exception e) 
		{
			setErrorMessage("Start Date validation failed.");
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
