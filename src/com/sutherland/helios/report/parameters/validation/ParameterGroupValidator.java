package com.sutherland.helios.report.parameters.validation;

import com.sutherland.helios.report.Report;
import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;

public abstract class ParameterGroupValidator 
{
	private String errorMessage;
	private final static int MAX_ERROR_MESSAGE_LEN = 200;
	
	protected ParameterGroupValidator()
	{
		errorMessage = "'";
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	protected void setErrorMessage(String message)
	{
		errorMessage = StringSanitizer.sanitize(message, MAX_ERROR_MESSAGE_LEN);
	}
	
	public abstract boolean validate(Report report); 
}
