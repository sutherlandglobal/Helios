package helios.report.parameters.parameter;

import helios.data.granularity.time.TimeGrains;
import helios.report.parameters.ParameterInfo;
import helios.report.parameters.sanitize.StringSanitizer;

public class TimeGrainParameter extends Parameter 
{
	public final static String PARAM_NAME = ParameterInfo.TIME_GRAIN_PARAM;
	public final static String PARAM_DESC = ParameterInfo.TIME_GRAIN_PARAM_DESC;
	
	public TimeGrainParameter() 
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
			int timeGrain = Integer.parseInt(StringSanitizer.sanitize(values.get(0), MAX_VAL_LEN));
			
			retval = 
			(
				timeGrain == TimeGrains.FISCAL_YEARLY_GRANULARITY || 
				timeGrain == TimeGrains.YEARLY_GRANULARITY || 
				timeGrain == TimeGrains.QUARTERLY_GRANULARITY || 
				timeGrain == TimeGrains.FISCAL_QUARTERLY_GRANULARITY || 
				timeGrain == TimeGrains.MONTHLY_GRANULARITY || 
				timeGrain == TimeGrains.WEEKLY_GRANULARITY || 
				timeGrain == TimeGrains.DAILY_GRANULARITY || 
				timeGrain == TimeGrains.HOURLY_GRANULARITY
			);
		}
		catch(Exception e)
		{
			setErrorMessage("Time grain validation failed");
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
