package com.sutherland.helios.report.parameters.parameter;

import java.util.ArrayList;
import java.util.Arrays;

import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;


/**
 * A report parameter. Has a name, a list of string values, a way of validating those values
 * 
 * @author Jason Diamond
 *
 */
public abstract class Parameter
{
	public final static int MAX_NAME_LEN = 100;
	public final static int MAX_VAL_LEN = 100;
	
	protected ArrayList<String> values;
	
	protected boolean isRequiredParameter;
	protected String errorMessage;
	
	protected Parameter()
	{
		values = new ArrayList<String>();
		isRequiredParameter = false;
		errorMessage = "";
	}
	
	public boolean isRequired()
	{
		return isRequiredParameter;
	}
	
	public void setIsRequired(boolean val)
	{
		isRequiredParameter = val;
	}
	
	public boolean addValue(String value)
	{
		return values.add(StringSanitizer.sanitize(value, MAX_VAL_LEN));
	}
	
	public boolean addValue(int value)
	{
		return addValue("" + value);
	}
	
	public boolean addSingleValue(String value)
	{
		values.clear();
		return values.add(StringSanitizer.sanitize(value, MAX_VAL_LEN));
	}
	
	public void clearValues()
	{
		values.clear();
	}
	
	public ArrayList<String> getValues()
	{
		return values;
	}
	
	public String getErrorMessage()
	{
		return errorMessage ;
	}
	
	protected void setErrorMessage(String errorString)
	{
		errorMessage = StringSanitizer.sanitize(errorString, MAX_VAL_LEN);
	}
			
	public abstract boolean validate();

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + (isRequiredParameter ? 1231 : 1237);
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Parameter)) {
			return false;
		}
		Parameter other = (Parameter) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null) {
				return false;
			}
		} else if (!errorMessage.equals(other.errorMessage)) {
			return false;
		}
		if (isRequiredParameter != other.isRequiredParameter) {
			return false;
		}
		if (values == null) {
			if (other.values != null) {
				return false;
			}
		} else if (!values.equals(other.values)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		StringBuilder retval = new StringBuilder();
		
		retval.append("Required: " + isRequired() + "\n");
		retval.append(Arrays.asList(values));
		
	
		return retval.toString();
	}
}
