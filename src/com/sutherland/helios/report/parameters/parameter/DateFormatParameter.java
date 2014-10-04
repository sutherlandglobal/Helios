package com.sutherland.helios.report.parameters.parameter;

import com.sutherland.helios.date.formatting.DateFormatter;
import com.sutherland.helios.report.parameters.ParameterInfo;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

public class DateFormatParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.DATE_FORMAT_PARAM;
	public final static String PARAM_DESC = ParameterInfo.DATE_FORMAT_PARAM_DESC;
	
	public DateFormatParameter() 
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
			int format = Integer.parseInt(StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN));
			
			retval = 
			(
				format == DateFormatter.EXCEL_FORMAT || 
				format == DateFormatter.SQL_FORMAT
			);
		}
		catch(Exception e)
		{
			setErrorMessage("Date format validation failed");
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
