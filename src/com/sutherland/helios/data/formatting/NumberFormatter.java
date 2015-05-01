/**
 * 
 */
package com.sutherland.helios.data.formatting;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * A class to handle various number formatting conversions required by reports, especially those that use values generated from other reports.
 * 
 * @author Jason Diamond
 *
 */
public final class NumberFormatter 
{
	private NumberFormatter(){}
	
	/**
	 * Convert a double value to a currency looking value (numbers.nn). Rounding is 5 and up to the nearest cent.
	 * 
	 * @param value	Raw value to convert.
	 * 
	 * @return	The converted string.
	 */
	public static String convertToCurrency(double value)
	{
		return convertToPrecision(value, 2);
	}
	
	/**
	 * Convert a decimal value into a percentage-looking value. No % sign is asked.
	 * 
	 * @param value		The value to convert.
	 * @param places	The number of decimal places for rounding.
	 * 
	 * @return	The original value formatted as a percent.
	 */
	public static String convertToPercentage(double value, int places)
	{
		return convertToPrecision(100*value, places);
	}
	
	public static String convertToPercentage(double value)
	{
		return convertToPrecision(100*value, 2);
	}
	
	public static String convertToPrecision(double value, int decimalPlaces)
	{
		String formatDescriptor = "0.";

		for(int i = 0; i < decimalPlaces; i++)
		{
			formatDescriptor += "0";
		}
		
		DecimalFormat decimalFormat = new DecimalFormat(formatDescriptor);
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		
		//format and remove trailing decimal point
		return decimalFormat.format(value).replaceFirst("\\.$", "");
	}
}
