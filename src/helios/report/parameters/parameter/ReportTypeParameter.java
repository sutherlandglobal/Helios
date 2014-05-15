package helios.report.parameters.parameter;

import helios.report.Report;
import helios.report.parameters.ParameterInfo;
import helios.report.parameters.sanitize.StringSanitizer;

public class ReportTypeParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.REPORT_TYPE_PARAM;
	public final static String PARAM_DESC = ParameterInfo.REPORT_TYPE_PARAM_DESC;
	
	public ReportTypeParameter() 
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
			int reportType = Integer.parseInt(StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN));
			
			retval = 
			(
					reportType == Report.STACK_REPORT || 
					reportType == Report.TIME_TREND_REPORT
			);
		}
		catch(Exception e)
		{
			setErrorMessage("Report type validation failed: " + e.getMessage());
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
