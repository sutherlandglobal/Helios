package helios.report.parameters.parameter;

import helios.report.parameters.ParameterInfo;
import helios.report.parameters.sanitize.StringSanitizer;

public class NumDriversParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.NUM_DRIVERS_PARAM;
	public final static String PARAM_DESC = ParameterInfo.NUM_DRIVERS_PARAM_DESC;
	
	public final static int MAX_DRIVERS = 50;
	
	public NumDriversParameter() 
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
			int numDrivers = Integer.parseInt(StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN));
			
			if(	numDrivers > 0)
			{
				if(numDrivers <= MAX_DRIVERS)
				{
					retval = true;
				}
				else
				{
					setErrorMessage("Number of drivers exceeds maximum");
				}
			}
			else
			{
				setErrorMessage("Negative number of drivers");
			}
		}
		catch (Exception e)
		{
			setErrorMessage("Invalid value for number of drivers");
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
