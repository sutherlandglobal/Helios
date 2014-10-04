package com.sutherland.helios.report.parameters.validation;

import com.sutherland.helios.date.parsing.DateParser;
import com.sutherland.helios.report.Report;
import com.sutherland.helios.report.parameters.ParameterInfo;

public class TimeIntervalValidator extends ParameterGroupValidator 
{
	public TimeIntervalValidator()
	{
		super();
	}
	
	public boolean validate(Report report)
	{
		boolean retval = false;
		
		try
		{
			 //startDate
			if(report.getParameters().getParameter(ParameterInfo.START_DATE_PARAM).validate())
			{
				//endDate
				if(report.getParameters().getParameter(ParameterInfo.END_DATE_PARAM).validate())
				{				
					//time interval
					//validate the time interval, or any other compound parameters, but only if the base parameter validations all succeeded
					String startDate = report.getParameters().getStartDate();
					String endDate = report.getParameters().getEndDate();
		
					//swap startdate and enddate if the interval is backwards
					if(DateParser.convertSQLDateToGregorian(startDate).after(DateParser.convertSQLDateToGregorian(endDate)))
					{
						report.getParameters().setStartDate(endDate);
						report.getParameters().setEndDate(startDate);
					}
					
					retval = true;
				}
				else
				{
					setErrorMessage("End Date failed validation");
				}
			}
			else
			{
				setErrorMessage("Start Date failed validation");
			}
		}
		catch (Exception e)
		{
			setErrorMessage("Exception validating time interval: " + e.getMessage());
		}
			
		return retval;
	}
}
