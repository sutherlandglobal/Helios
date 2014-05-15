package helios.report.parameters.parameter;

import helios.date.parsing.DateParser;
import helios.report.parameters.ParameterInfo;
import helios.report.parameters.sanitize.StringSanitizer;

import java.util.GregorianCalendar;

public class EndDateParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.END_DATE_PARAM;
	public final static String PARAM_DESC = ParameterInfo.END_DATE_PARAM_DESC;
	
	public EndDateParameter() 
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
			setErrorMessage("End Date validation failed.");
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
